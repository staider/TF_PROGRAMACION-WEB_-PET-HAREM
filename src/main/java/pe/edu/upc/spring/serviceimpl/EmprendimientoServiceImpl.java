package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Emprendimiento;
import pe.edu.upc.spring.repository.EmprendimientoRepository;
import pe.edu.upc.spring.service.EmprendimientoService;

@Service
public class EmprendimientoServiceImpl implements EmprendimientoService {

	@Autowired
	private EmprendimientoRepository dEmpre;
	
	@Override
	@Transactional
	public boolean registrar(Emprendimiento empre) {
		Emprendimiento objEmpre = dEmpre.save(empre);
		if (objEmpre == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idEmpre) {
		dEmpre.deleteById(idEmpre);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Emprendimiento> listarId(int idEmpre) {
		return dEmpre.findById(idEmpre);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Emprendimiento> listar() {
		return dEmpre.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Emprendimiento> buscarNombre(String nameEmpre) {
		return dEmpre.buscarNombre(nameEmpre);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Emprendimiento> buscarDistrito(String nameDistrito) {
		return dEmpre.buscarDistrito(nameDistrito);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Emprendimiento> buscarRUC(String numRUC) {
		return dEmpre.buscarRUC(numRUC);
	}

}
