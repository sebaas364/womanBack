package co.edu.unbosque.womenBack.repository;

import co.edu.unbosque.womenBack.model.Factura;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Integer> {
	
	Optional<Factura> findByNumero(String numero);
    boolean existsByNumero(String numero);
}