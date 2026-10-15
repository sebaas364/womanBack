package co.edu.unbosque.womenBack.repository;

import co.edu.unbosque.womenBack.model.NotaCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface NotaCreditoRepository extends JpaRepository<NotaCredito, Integer> {
	
	Optional<NotaCredito> findByCude(String cude);
}