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

import co.edu.unbosque.womenBack.dto.UsuarioDTO;
import co.edu.unbosque.womenBack.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioServ;

	@GetMapping("/getAll")
	public ResponseEntity<List<UsuarioDTO>> getAll() {
		return ResponseEntity.ok(usuarioServ.getAll());
	}

	@GetMapping("/getbyid/{idUsuario}")
	public ResponseEntity<UsuarioDTO> getById(@PathVariable int idUsuario) {
		Optional<UsuarioDTO> found = usuarioServ.getById(idUsuario);
		
		return found.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping("/create")
	public ResponseEntity<String> create(@RequestBody UsuarioDTO dto) {
		
		ResponseEntity<String> response;
		switch (usuarioServ.create(dto)) {		
		case 0:
			response =ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado correctamente");
			break;
		case 2: 
			response = ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un usuario con ese documento");
			break;
		case 3:
			response = ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un usuario con ese correo");
			break;
		case 4:
			response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El rol indicado no existe");
			break;
		default: 
			response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body("Datos inválidos: revise los campos obligatorios, el formato del correo y las longitudes");
			break;
		}
		return response;
	}

	@PutMapping("/update/{idUsuario}")
	public ResponseEntity<String> update( @RequestBody UsuarioDTO dto) {
		
		ResponseEntity<String> response;
		switch (usuarioServ.update(dto.getIdUsuario(), dto)) {
		case 0:
			response = ResponseEntity.ok("Usuario actualizado correctamente");
			break;
		case 1:
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
			break;
		case 2: 
			response = ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un usuario con ese documento");
			break;
		case 3:
			response = ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe un usuario con ese correo");
			break;
		case 4:
			response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El rol indicado no existe");
			break;
		default : 
			response = ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body("Datos inválidos: revise los campos obligatorios, el formato del correo y las longitudes");
			break;
		}
		return response;
	}

	@DeleteMapping("/delete/{idUsuario}")
	public ResponseEntity<String> delete(@PathVariable int id) {
		ResponseEntity<String> response;
		switch (usuarioServ.delete(id)) {
		case 0: 
			response = ResponseEntity.ok("Usuario eliminado correctamente");
			break;
		default:
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
			break;
		}
		return response;
	}

}	