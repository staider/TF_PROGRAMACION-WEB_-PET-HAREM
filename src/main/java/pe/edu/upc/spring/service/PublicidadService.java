package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Publicidad;

public interface PublicidadService {
	public boolean registrar(Publicidad publicidad);
	public void eliminar(int idPublicidad);
	public Optional<Publicidad> listarId(int idPublicidad);
	public Optional<Publicidad> buscarId(int idPublicidad);
	List<Publicidad> listar();
	List<Publicidad> buscarEmprendimiento(String NEmpre);
	List<Publicidad> buscarPlan(String NPlan);	
}
