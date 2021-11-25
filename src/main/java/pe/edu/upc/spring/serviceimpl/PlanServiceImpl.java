package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Plan;
import pe.edu.upc.spring.repository.PlanRepository;
import pe.edu.upc.spring.service.PlanService;

@Service
public class PlanServiceImpl implements PlanService{
	@Autowired
	private PlanRepository dPlan;
	
	@Override
	@Transactional
	public boolean registrar(Plan plan) {
		Plan objPlan = dPlan.save(plan);
		if (objPlan == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idPlan) {
		dPlan.deleteById(idPlan);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Plan> listarId(int idPlan) {
		return dPlan.findById(idPlan);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Plan> buscarId(int idMas) {
		return dPlan.findById(idMas);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Plan> listar() {
		return dPlan.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Plan> buscarNombre(String NPlan) {
		return dPlan.buscarNombre(NPlan);
	}
	
}
