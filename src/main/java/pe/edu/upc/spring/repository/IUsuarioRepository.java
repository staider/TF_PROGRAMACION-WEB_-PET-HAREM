package pe.edu.upc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Usuarios;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuarios, Long>{
	public Usuarios findByUsername(String username);
}
