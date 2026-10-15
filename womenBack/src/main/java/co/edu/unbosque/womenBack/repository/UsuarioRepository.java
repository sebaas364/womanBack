package co.edu.unbosque.womenBack.repository;

import co.edu.unbosque.womenBack.model.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByDocumento(String documento);
	Optional<Usuario> findByCorreo(String correo);
	
    boolean existsByDocumento(String documento);

    boolean existsByCorreo(String correo);

    boolean existsByDocumentoAndIdUsuarioNot(String documento, Integer idUsuario);

    boolean existsByCorreoAndIdUsuarioNot(String correo, Integer idUsuario);
}