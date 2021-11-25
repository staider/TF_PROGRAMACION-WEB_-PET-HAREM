package pe.edu.upc.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Mascota;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Integer>{
	@Query("from Mascota p where p.NMascota like %:NMascota%")
	List<Mascota> buscarNombre(@Param("NMascota") String NMascota);
	
	@Query("from Mascota p where p.raza.NRaza like %:NRaza%")
	List<Mascota> buscarRaza(@Param("NRaza") String NRaza);	
	
	@Query("from Mascota p where p.duenio.nameDuenio like %:nameDuenio%")
	List<Mascota> buscarDuenio(@Param("nameDuenio") String nameDuenio);
	
	List<Mascota> findByBirthDatePet(Date birthDatePet);
}
