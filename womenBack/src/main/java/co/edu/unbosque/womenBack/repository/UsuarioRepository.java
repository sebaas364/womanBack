package co.edu.unbosque.womenBack.repository;

import co.edu.unbosque.womenBack.model.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByDocumentp(String documento);
	Optional<Usuario> findByCorreo(String correo);
	
    boolean existsByDocumento(String documento);

    boolean existsByCorreo(String correo);

    boolean existsByDocumentoAndIdUsuario(String documento, Integer idUsuario);

    boolean existsByCorreoAndIdUsuario(String correo, Integer idUsuario);
}