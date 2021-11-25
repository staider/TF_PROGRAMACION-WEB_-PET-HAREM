package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Publicidad;
import pe.edu.upc.spring.repository.PublicidadRepository;
import pe.edu.upc.spring.service.PublicidadService;

@Service
public class PublicidadServiceImpl implements PublicidadService{
	@Autowired
	private PublicidadRepository dPub;
	
	@Override
	@Transactional
	public boolean registrar(Publicidad pub) {
		Publicidad objPub = dPub.save(pub);
		if (objPub == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idPub) {
		dPub.deleteById(idPub);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Publicidad> listarId(int idPub) {
		return dPub.findById(idPub);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Publicidad> buscarId(int idPub) {
		return dPub.findById(idPub);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Publicidad> listar() {
		return dPub.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Publicidad> buscarEmprendimiento(String NEmpre) {
		return dPub.buscarEmprendimiento(NEmpre);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Publicidad> buscarPlan(String NPlan) {
		return dPub.buscarPlan(NPlan);
	}
}
