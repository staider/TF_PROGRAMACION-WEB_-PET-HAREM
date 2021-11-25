package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;
import pe.edu.upc.spring.model.Administrador;

public interface AdminService {
	public boolean registrar(Administrador admin);
	public void eliminar(int idAdmin);
	public Optional<Administrador> listarId(int idAdmin);
	List<Administrador> listar();
	//List<Administrador> buscarNombre(String nameAdmin);
	public Administrador registroSecurity(Administrador admin);
}
