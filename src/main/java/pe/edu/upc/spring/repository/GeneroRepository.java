package pe.edu.upc.spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.spring.model.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Integer> {
}
