package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Plan;

public interface PlanService {
	public boolean registrar(Plan plan);
	public void eliminar(int idPlan);
	public Optional<Plan> listarId(int idPlan);
	public Optional<Plan> buscarId(int idPlan);
	List<Plan> listar();
	List<Plan> buscarNombre(String NPlan);
}
