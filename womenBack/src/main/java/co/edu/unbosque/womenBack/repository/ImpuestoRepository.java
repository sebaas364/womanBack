package co.edu.unbosque.womenBack.repository;

import co.edu.unbosque.womenBack.model.Impuesto;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ImpuestoRepository extends JpaRepository<Impuesto, Integer> {
	Optional<Impuesto> findByIdImpuesto(Integer idImpuesto);
}