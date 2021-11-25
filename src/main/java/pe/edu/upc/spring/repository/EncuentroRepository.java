package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Encuentro;

@Repository
public interface EncuentroRepository extends JpaRepository<Encuentro, Integer>{
	@Query("from Encuentro e where e.mascota.NMascota like %:NMascota%")
	List<Encuentro> buscarMascota(@Param("NMascota") String NMascota);	
	
	@Query("from Encuentro e where e.duenio.nameDuenio like %:nameDuenio%")
	List<Encuentro> buscarDuenio(@Param("nameDuenio") String nameDuenio);
}
