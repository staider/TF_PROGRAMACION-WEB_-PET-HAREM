package pe.edu.upc.spring.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.spring.model.Duenio;

@Repository
public interface DuenioRepository extends JpaRepository<Duenio, Integer> {
	@Query("from Duenio d where d.nameDuenio like %:nameDuenio%")
	List<Duenio> buscarNombre(@Param("nameDuenio") String nameDuenio);
	
	@Query("from Duenio d where d.Distrito.nameDistrito like %:nameDistrito%")
	List<Duenio> buscarDistrito(@Param("nameDistrito") String nameDistrito);
	
	@Query("from Duenio d where d.numDNI like %:numDNI%")
	List<Duenio> buscarDNI(@Param("numDNI") String numDNI);
	
	@Query("from Duenio d where d.NCorreo like %:NCorreo%")
	List<Duenio> buscarCorreo(@Param("NCorreo") String NCorreo);
	
	@Query("from Duenio d where d.numCelular like %:numCelular%")
	List<Duenio> buscarCelular(@Param("numCelular") String numCelular);
	
	//borrar buscarNombre?
	public Duenio findByUsername(String username);
}
