package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Encuentro;
import pe.edu.upc.spring.repository.EncuentroRepository;
import pe.edu.upc.spring.service.EncuentroService;

@Service
public class EncuentroServiceImpl implements EncuentroService{
	@Autowired
	private EncuentroRepository dEnc;
	
	@Override
	@Transactional
	public boolean registrar(Encuentro enc) {
		Encuentro objEnc = dEnc.save(enc);
		if (objEnc == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idEnc) {
		dEnc.deleteById(idEnc);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Encuentro> listarId(int idEnc) {
		return dEnc.findById(idEnc);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Encuentro> buscarId(int idEnc) {
		return dEnc.findById(idEnc);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Encuentro> listar() {
		return dEnc.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Encuentro> buscarMascota(String NMascota) {
		return dEnc.buscarMascota(NMascota);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Encuentro> buscarDuenio(String nameDuenio) {
		return dEnc.buscarDuenio(nameDuenio);
	}		
}
