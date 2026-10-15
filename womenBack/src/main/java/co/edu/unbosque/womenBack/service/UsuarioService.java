package co.edu.unbosque.womenBack.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.unbosque.womenBack.dto.UsuarioDTO;
import co.edu.unbosque.womenBack.model.Rol;
import co.edu.unbosque.womenBack.model.Usuario;
import co.edu.unbosque.womenBack.repository.RolRepository;
import co.edu.unbosque.womenBack.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepo;

	@Autowired
	private RolRepository rolRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public int create(UsuarioDTO dto) {
		if (dto == null || dto.getDocumento() == null || dto.getDocumento().isBlank() || dto.getNombres() == null
				|| dto.getNombres().isBlank() || dto.getApellidos() == null || dto.getApellidos().isBlank()
				|| dto.getCorreo() == null || dto.getCorreo().isBlank() || dto.getIdRol() == null
				|| dto.getContrasenia() == null || dto.getContrasenia().isBlank()) {
			return 5;
		}
		if (usuarioRepo.existsByDocumento(dto.getDocumento())) {
			return 2;
		}
		if (usuarioRepo.existsByCorreo(dto.getCorreo())) {
			return 3;
		}
		Optional<Rol> rol = rolRepo.findById(dto.getIdRol());
		if (rol.isEmpty()) {
			return 4;
		}

		Usuario entity = modelMapper.map(dto, Usuario.class);
		entity.setIdUsuario(null);
		entity.setRol(rol.get());
		entity.setContrasenaHash(passwordEncoder.encode(dto.getContrasenia()));
		entity.setIntentosFallidos(0);
		
		if (entity.getEstado() == null || entity.getEstado().isBlank()) {
			entity.setEstado("ACTIVO");
		}
		usuarioRepo.save(entity);
		return 0;
	}

	public List<UsuarioDTO> getAll() {
		List<UsuarioDTO> dtoList = new ArrayList<>();
		for (Usuario u : usuarioRepo.findAll()) {
			dtoList.add(modelMapper.map(u, UsuarioDTO.class));
		}
		return dtoList;
	}

	public Optional<UsuarioDTO> getById(int idUsuario) {
		Optional<Usuario> found = usuarioRepo.findById(idUsuario);
		return found.map(u -> modelMapper.map(u, UsuarioDTO.class));
	}

	public int update(int idUsuario, UsuarioDTO dto) {
		Optional<Usuario> found = usuarioRepo.findById(idUsuario);
		if (found.isEmpty()) {
			return 1;
		}

		if (dto == null || dto.getDocumento() == null || dto.getDocumento().isBlank() || dto.getNombres() == null || dto.getNombres().isBlank()
				|| dto.getApellidos() == null || dto.getApellidos().isBlank() || dto.getCorreo() == null || dto.getCorreo().isBlank() || dto.getIdRol() == null) {
			return 5;
		}
		if (usuarioRepo.existsByDocumentoAndIdUsuarioNot(dto.getDocumento(), idUsuario)) {
			return 2;
		}
		if (usuarioRepo.existsByCorreoAndIdUsuarioNot(dto.getCorreo(), idUsuario)) {
			return 3;
		}
		Optional<Rol> rol = rolRepo.findById(dto.getIdRol());
		if (rol.isEmpty()) {
			return 4;
		}

		Usuario usuario = found.get();
		usuario.setDocumento(dto.getDocumento());
		usuario.setNombres(dto.getNombres());
		usuario.setApellidos(dto.getApellidos());
		usuario.setCorreo(dto.getCorreo());
		usuario.setEstado(dto.getEstado());
		usuario.setRol(rol.get());

		if (dto.getContrasenia() != null && !dto.getContrasenia().isBlank()) {
			usuario.setContrasenaHash(passwordEncoder.encode(dto.getContrasenia()));
		}
		if (dto.getEstado() != null && !dto.getEstado().isBlank()) {
			usuario.setEstado(dto.getEstado());
		}

		usuarioRepo.save(usuario);
		return 0;
	}

	public int delete(int idUsuario) {
		Optional<Usuario> found = usuarioRepo.findById(idUsuario);
		if (found.isPresent()) {
			Usuario usuario = found.get();
			usuario.setEstado("INACTIVO");
			usuarioRepo.save(usuario);
			return 0;
		}
		return 1;
	}

}