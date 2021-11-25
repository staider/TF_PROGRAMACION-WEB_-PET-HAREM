package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Publicidad;

@Repository
public interface PublicidadRepository extends JpaRepository<Publicidad, Integer>{
	@Query("from Publicidad p where p.Empre.NEmpre like %:NEmpre%")
	List<Publicidad> buscarEmprendimiento(@Param("NEmpre") String NEmpre);
	
	@Query("from Publicidad p where p.Plan.NPlan like %:NPlan%")
	List<Publicidad> buscarPlan(@Param("NPlan") String NPlan);
}
