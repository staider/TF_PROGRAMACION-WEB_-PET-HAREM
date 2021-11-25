package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;
import pe.edu.upc.spring.model.Emprendimiento;

public interface EmprendimientoService {
	public boolean registrar(Emprendimiento empre);
	public void eliminar(int idEmpre);
	public Optional<Emprendimiento> listarId(int idEmpre);
	List<Emprendimiento> listar();
	List<Emprendimiento> buscarNombre(String NEmpre);
	List<Emprendimiento> buscarDistrito(String nameDistrito);
	List<Emprendimiento> buscarRUC(String numRUC);
}
