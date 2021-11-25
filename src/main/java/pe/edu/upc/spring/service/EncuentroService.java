package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Encuentro;

public interface EncuentroService {
	public boolean registrar(Encuentro mascota);
	public void eliminar(int idEncuentro);
	public Optional<Encuentro> listarId(int idEncuentro);
	public Optional<Encuentro> buscarId(int idEncuentro);
	List<Encuentro> listar();
	List<Encuentro> buscarMascota(String NMascota);
	List<Encuentro> buscarDuenio(String nameDuenio);
}
