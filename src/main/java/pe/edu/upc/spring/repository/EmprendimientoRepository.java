package pe.edu.upc.spring.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Emprendimiento;

@Repository
public interface EmprendimientoRepository extends JpaRepository<Emprendimiento, Integer> {
	@Query("from Emprendimiento e where e.NEmpre like %:NEmpre%")
	List<Emprendimiento> buscarNombre(@Param("NEmpre") String NEmpre);
	
	@Query("from Emprendimiento e where e.Distrito.nameDistrito like %:nameDistrito%")
	List<Emprendimiento> buscarDistrito(@Param("nameDistrito") String nameDistrito);
	
	@Query("from Emprendimiento e where e.numRUC like %:numRUC%")
	List<Emprendimiento> buscarRUC(@Param("numRUC") String numRUC);
}
