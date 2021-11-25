package pe.edu.upc.spring.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Distrito;
import pe.edu.upc.spring.repository.DistritoRepository;
import pe.edu.upc.spring.service.DistritoService;

@Service
public class DistritoServiceImpl implements DistritoService {

	@Autowired
	private DistritoRepository dDistrito;
	
	@Override
	@Transactional(readOnly = true)
	public List<Distrito> listar() {
		return dDistrito.findAll();
	}

}
