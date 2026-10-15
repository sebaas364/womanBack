package co.edu.unbosque.womenBack.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.womenBack.dto.FacturaDTO;
import co.edu.unbosque.womenBack.service.FacturaService;

@RestController
@RequestMapping("/factura")
public class FacturaController {

	@Autowired
	private FacturaService facturaServ;

	@GetMapping("/getAll")
	public ResponseEntity<List<FacturaDTO>> getAll() {
		return ResponseEntity.ok(facturaServ.getAll());
	}

	@GetMapping("/getbyid/{idFactura}")
	public ResponseEntity<FacturaDTO> getById(@PathVariable int idFactura) {
		Optional<FacturaDTO> found = facturaServ.getById(idFactura);

		return found.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping("/create")
	public ResponseEntity<String> create(@RequestBody FacturaDTO dto) {

		ResponseEntity<String> response;
		switch (facturaServ.create(dto)) {
		case 0:
			response = ResponseEntity.status(HttpStatus.CREATED).body("Factura creada correctamente");
			break;
		case 2:
			response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El cliente indicado no existe");
			break;
		case 3:
			response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario indicado no existe");
			break;
		case 4:
			response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Uno de los productos indicados no existe");
			break;
		case 6:
			response = ResponseEntity.status(HttpStatus.CONFLICT).body("Stock insuficiente para uno de los productos");
			break;
		default:
			response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					"Datos inválidos: indique cliente, usuario y al menos un detalle con producto y cantidad mayor a 0, sin productos repetidos");
			break;
		}
		return response;
	}

	@PutMapping("/update/{idFactura}")
	public ResponseEntity<String> update(@PathVariable int idFactura, @RequestBody FacturaDTO dto) {

		ResponseEntity<String> response;
		switch (facturaServ.update(idFactura, dto)) {
		case 0:
			response = ResponseEntity.ok("Factura actualizada correctamente");
			break;
		case 1:
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Factura no encontrada");
			break;
		case 2:
			response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El cliente indicado no existe");
			break;
		case 4:
			response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Uno de los productos indicados no existe");
			break;
		case 6:
			response = ResponseEntity.status(HttpStatus.CONFLICT).body("Stock insuficiente para uno de los productos");
			break;
		case 7:
			response = ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Solo se pueden actualizar facturas en estado EMITIDA");
			break;
		default:
			response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					"Datos inválidos: indique cliente y al menos un detalle con producto y cantidad mayor a 0, sin productos repetidos");
			break;
		}
		return response;
	}

	
	@DeleteMapping("/delete/{idFactura}")
	public ResponseEntity<String> delete(@PathVariable int idFactura) {

		ResponseEntity<String> response;
		switch (facturaServ.delete(idFactura)) {
		case 0:
			response = ResponseEntity.ok("Factura anulada correctamente");
			break;
		case 7:
			response = ResponseEntity.status(HttpStatus.CONFLICT).body("La factura ya está anulada");
			break;
		default:
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Factura no encontrada");
			break;
		}
		return response;
	}

}