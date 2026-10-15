package co.edu.unbosque.womenBack.repository;

import co.edu.unbosque.womenBack.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
	
}