package co.edu.unbosque.womenBack.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unbosque.womenBack.dto.DetalleFacturaDTO;
import co.edu.unbosque.womenBack.dto.FacturaDTO;
import co.edu.unbosque.womenBack.model.Cliente;
import co.edu.unbosque.womenBack.model.DetalleFactura;
import co.edu.unbosque.womenBack.model.Factura;
import co.edu.unbosque.womenBack.model.Producto;
import co.edu.unbosque.womenBack.model.Usuario;
import co.edu.unbosque.womenBack.repository.ClienteRepository;
import co.edu.unbosque.womenBack.repository.DetalleFacturaRepository;
import co.edu.unbosque.womenBack.repository.FacturaRepository;
import co.edu.unbosque.womenBack.repository.ProductoRepository;
import co.edu.unbosque.womenBack.repository.UsuarioRepository;

@Service
public class FacturaService {

	@Autowired
	private FacturaRepository facturaRepo;

	@Autowired
	private DetalleFacturaRepository detalleRepo;

	@Autowired
	private ClienteRepository clienteRepo;

	@Autowired
	private UsuarioRepository usuarioRepo;

	@Autowired
	private ProductoRepository productoRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Transactional
	public int create(FacturaDTO dto) {
		if (dto == null || dto.getIdCliente() == null || dto.getIdUsuario() == null || dto.getDetalles() == null
				|| dto.getDetalles().isEmpty()) {
			return 5;
		}
		Optional<Cliente> cliente = clienteRepo.findById(dto.getIdCliente());
		if (cliente.isEmpty()) {
			return 2;
		}
		Optional<Usuario> usuario = usuarioRepo.findById(dto.getIdUsuario());
		if (usuario.isEmpty()) {
			return 3;
		}

		List<DetalleFactura> detalles = new ArrayList<>();
		int estado = createDetalle(dto.getDetalles(), new ArrayList<>(), detalles);
		if (estado != 0) {
			return estado;
		}
		
		BigDecimal subtotal = BigDecimal.ZERO;
		BigDecimal impuestos = BigDecimal.ZERO ;
		for (DetalleFactura d : detalles) {
			subtotal = subtotal.add(d.getSubtotal());
			impuestos = impuestos.add(d.getValorImpuesto());
		}

		Factura factura = new Factura();
		factura.setNumero("FAC-" + String.format("%06d", facturaRepo.count() + 1));
		factura.setCufe(dto.getCufe());
		factura.setFecha(LocalDateTime.now());
		factura.setSubtotal(subtotal);
		factura.setTotalImpuestos(impuestos);
		factura.setTotal(subtotal.add(impuestos));
		factura.setEstado("EMITIDA");
		factura.setCliente(cliente.get());
		factura.setUsuario(usuario.get());
		factura = facturaRepo.save(factura);

		for (DetalleFactura detalle : detalles) {
			detalle.setFactura(factura);
			detalleRepo.save(detalle);

			Producto p = detalle.getProducto();
			p.setStock(p.getStock() - detalle.getCantidad());
			productoRepo.save(p);
		}
		return 0;
	}

	@Transactional()
	public List<FacturaDTO> getAll() {
		List<FacturaDTO> dtoList = new ArrayList<>();
		for (Factura f : facturaRepo.findAll()) {
			dtoList.add(modelMapper.map(f, FacturaDTO.class));
		}
		return dtoList;
	}

	@Transactional()
	public Optional<FacturaDTO> getById(int idFactura) {
		Optional<Factura> found = facturaRepo.findById(idFactura);
		return found.map(f -> modelMapper.map(f, FacturaDTO.class));
	}

	//Unicamente se puede actualizar si la factura esta con estado "EMITIDA"
	@Transactional
	public int update(int idFactura, FacturaDTO dto) {
		Optional<Factura> found = facturaRepo.findById(idFactura);
		if (found.isEmpty()) {
			return 1;
		}
		Factura factura = found.get();
		if (!"EMITIDA".equals(factura.getEstado())) {
			return 7;
		}
		if (dto == null || dto.getIdCliente() == null || dto.getDetalles() == null || dto.getDetalles().isEmpty()) {
			return 5;
		}
		Optional<Cliente> cliente = clienteRepo.findById(dto.getIdCliente());
		if (cliente.isEmpty()) {
			return 2;
		}


		List<DetalleFactura> anteriores = detalleRepo.findByFacturaIdFactura(idFactura);
		List<DetalleFactura> nuevos = new ArrayList<>();
		int estado = createDetalle(dto.getDetalles(), anteriores, nuevos);
		
		if (estado != 0) {
			return estado;
		}

		for (DetalleFactura a : anteriores) {
			Producto p = a.getProducto();
			p.setStock(p.getStock() + a.getCantidad());
			productoRepo.save(p);
		}
		detalleRepo.deleteAll(anteriores);

		BigDecimal subtotal = BigDecimal.ZERO;
		BigDecimal impuestos = BigDecimal.ZERO;
		for (DetalleFactura d : nuevos) {
			subtotal = subtotal.add(d.getSubtotal());
			impuestos = impuestos.add(d.getValorImpuesto());
		}
		
		factura.setCliente(cliente.get());
		factura.setCufe(dto.getCufe());
		factura.setSubtotal(subtotal);
		factura.setTotalImpuestos(impuestos);
		factura.setTotal(subtotal.add(impuestos));
		facturaRepo.save(factura);

		for (DetalleFactura detalle : nuevos) {
			detalle.setFactura(factura);
			detalleRepo.save(detalle);

			Producto p = detalle.getProducto();
			p.setStock(p.getStock() - detalle.getCantidad());
			productoRepo.save(p);
		}
		return 0;
	}

	
	// No se elimina la factura de la base de datos, se cambia el estado a "ANULADA"
	@Transactional
	public int delete(int idFactura) {
		Optional<Factura> found = facturaRepo.findById(idFactura);
		if (found.isEmpty()) {
			return 1;
		}
		Factura factura = found.get();
		if ("ANULADA".equals(factura.getEstado())) {
			return 7;
		}

		factura.setEstado("ANULADA");
		facturaRepo.save(factura);
		return 0;
	}

	
	private int createDetalle(List<DetalleFacturaDTO> dtos, List<DetalleFactura> anteriores, List<DetalleFactura> detalles) {
		for (DetalleFacturaDTO d : dtos) {
			if (d == null || d.getIdProducto() == null || d.getCantidad() == null || d.getCantidad() <= 0) {
				return 5;
			}

			// verificar que un producto no se repita
			for (DetalleFactura yaArmado : detalles) {
				if (yaArmado.getProducto().getIdProducto().equals(d.getIdProducto())) {
					return 5;
				}
			}

			Optional<Producto> producto = productoRepo.findById(d.getIdProducto());
			if (producto.isEmpty()) {
				return 4;
			}
			Producto p = producto.get();

			int cantidadPrevia = 0;
			for (DetalleFactura anterior : anteriores) {
				if (anterior.getProducto().getIdProducto().equals(p.getIdProducto())) {
					cantidadPrevia = anterior.getCantidad();
					break;
				}
			}
			if (p.getStock() + cantidadPrevia < d.getCantidad()) {
				return 6;
			}

			BigDecimal subtotal = p.getPrecio().multiply(BigDecimal.valueOf(d.getCantidad())).setScale(2, RoundingMode.HALF_UP);
			BigDecimal impuesto = subtotal.multiply(p.getImpuesto().getTarifa()).divide(BigDecimal.valueOf(100), 2,
					RoundingMode.HALF_UP);
			

			DetalleFactura detalle = new DetalleFactura();
			detalle.setProducto(p);
			detalle.setCantidad(d.getCantidad());
			detalle.setPrecioUnitario(p.getPrecio());
			detalle.setValorImpuesto(impuesto);
			detalle.setSubtotal(subtotal);
			detalles.add(detalle);
		}
		return 0;
	}

}