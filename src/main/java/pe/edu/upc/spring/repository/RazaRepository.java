package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Raza;

@Repository
public interface RazaRepository extends JpaRepository<Raza, Integer>{
	@Query("select count(r.NRaza) from Raza r where r.NRaza =:NRaza")
	public int buscarNombre(@Param("NRaza") String NRaza);
	
	@Query("from Raza r where r.NRaza like %:NRaza%")
	List<Raza> findByName(@Param("NRaza") String NRaza);
}
