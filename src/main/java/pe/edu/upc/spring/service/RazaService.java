package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Raza;

public interface RazaService {
	public Integer registrar(Raza raza);
	public void eliminar(int idRace);
	public Optional<Raza> listarId(int idRaza);
	List<Raza> listar();
	List<Raza> buscarNombre(String NRaza);
}
