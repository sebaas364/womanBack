package co.edu.unbosque.womenBack.repository;

import co.edu.unbosque.womenBack.model.DetalleFactura;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Integer> {
	List<DetalleFactura> findByFacturaIdFactura(Integer idFactura);
}