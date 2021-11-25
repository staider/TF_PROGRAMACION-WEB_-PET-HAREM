package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Mascota;

public interface MascotaService {
	public boolean registrar(Mascota mascota);
	public void eliminar(int idMascota);
	public Optional<Mascota> listarId(int idMascota);
	public Optional<Mascota> buscarId(int idMascota);
	List<Mascota> listar();
	List<Mascota> buscarNombre(String NMascota);
	List<Mascota> buscarRaza(String NRaza);
	List<Mascota> buscarDuenio(String nameDuenio);	
}
