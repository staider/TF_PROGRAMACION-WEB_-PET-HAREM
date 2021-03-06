package pe.edu.upc.spring.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Genero;
import pe.edu.upc.spring.repository.GeneroRepository;
import pe.edu.upc.spring.service.GeneroService;

@Service
public class GeneroServiceImpl implements GeneroService {

	@Autowired
	private GeneroRepository gDistrito;
	
	@Override
	@Transactional(readOnly = true)
	public List<Genero> listar() {
		return gDistrito.findAll();
	}

}
