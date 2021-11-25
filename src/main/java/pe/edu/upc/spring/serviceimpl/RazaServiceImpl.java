package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Raza;
import pe.edu.upc.spring.repository.RazaRepository;
import pe.edu.upc.spring.service.RazaService;

@Service
public class RazaServiceImpl implements RazaService {
	
	@Autowired
	private RazaRepository dRaza;
	
	@Override
	@Transactional
	public Integer registrar(Raza raza) {
		/*Raza objRaza = dRaza.save(raza);
		if (objRaza == null)
			return false;
		else
			return true;*/
		int count = dRaza.buscarNombre(raza.getNRaza());
		if (count == 0) {
			dRaza.save(raza);
		}
		return count;
	}

	@Override
	@Transactional
	public void eliminar(int idRaza) {
		dRaza.deleteById(idRaza);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Raza> listarId(int idRaza) {
		return dRaza.findById(idRaza);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Raza> listar() {
		return dRaza.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Raza> buscarNombre(String nameRaza) {
		return dRaza.findByName(nameRaza);
	}
}
