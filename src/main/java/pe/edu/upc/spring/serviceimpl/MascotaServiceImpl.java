package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Mascota;
import pe.edu.upc.spring.repository.MascotaRepository;
import pe.edu.upc.spring.service.MascotaService;

@Service
public class MascotaServiceImpl implements MascotaService{
	@Autowired
	private MascotaRepository dMas;
	
	@Override
	@Transactional
	public boolean registrar(Mascota mas) {
		Mascota objMas = dMas.save(mas);
		if (objMas == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idMas) {
		dMas.deleteById(idMas);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Mascota> listarId(int idMas) {
		return dMas.findById(idMas);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Mascota> buscarId(int idMas) {
		return dMas.findById(idMas);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Mascota> listar() {
		return dMas.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Mascota> buscarNombre(String NMascota) {
		return dMas.buscarNombre(NMascota);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Mascota> buscarRaza(String NRaza) {
		return dMas.buscarRaza(NRaza);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Mascota> buscarDuenio(String nameDuenio) {
		return dMas.buscarDuenio(nameDuenio);
	}		
}
