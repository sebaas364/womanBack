package co.edu.unbosque.womenBack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.womenBack.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,  Integer> {

}
