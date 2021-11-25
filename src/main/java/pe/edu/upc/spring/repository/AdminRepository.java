package pe.edu.upc.spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.model.Administrador;

@Repository
public interface AdminRepository extends JpaRepository<Administrador, Integer> {

    public Administrador findByUsername(String username);
}