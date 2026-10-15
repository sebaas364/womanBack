package co.edu.unbosque.womenBack.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FacturaDTO {

	private Integer idFactura;
	private String numero;
	private String cufe;
	private LocalDateTime fecha;
	private BigDecimal subtotal;
	private BigDecimal totalImpuestos;
	private BigDecimal total;
	private String estado;
	private Integer idCliente;
	private String nombreCliente;
	private Integer idUsuario;
	private List<DetalleFacturaDTO> detalles = new ArrayList<>();

	public FacturaDTO() {
	}

	public FacturaDTO(Integer idFactura, String numero, String cufe, LocalDateTime fecha, BigDecimal subtotal,
			BigDecimal totalImpuestos, BigDecimal total, String estado, Integer idCliente, String nombreCliente,
			Integer idUsuario, List<DetalleFacturaDTO> detalles) {
		super();
		this.idFactura = idFactura;
		this.numero = numero;
		this.cufe = cufe;
		this.fecha = fecha;
		this.subtotal = subtotal;
		this.totalImpuestos = totalImpuestos;
		this.total = total;
		this.estado = estado;
		this.idCliente = idCliente;
		this.nombreCliente = nombreCliente;
		this.idUsuario = idUsuario;
		this.detalles = detalles;
	}


	public Integer getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(Integer idFactura) {
		this.idFactura = idFactura;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCufe() {
		return cufe;
	}

	public void setCufe(String cufe) {
		this.cufe = cufe;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getTotalImpuestos() {
		return totalImpuestos;
	}

	public void setTotalImpuestos(BigDecimal totalImpuestos) {
		this.totalImpuestos = totalImpuestos;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public List<DetalleFacturaDTO> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleFacturaDTO> detalles) {
		this.detalles = detalles;
	}
}