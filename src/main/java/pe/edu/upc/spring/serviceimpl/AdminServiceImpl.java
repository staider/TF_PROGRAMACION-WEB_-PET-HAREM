package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Administrador;
import pe.edu.upc.spring.repository.AdminRepository;
import pe.edu.upc.spring.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository dAdmin;
	
	@Autowired
	private BCryptPasswordEncoder passwordEnconder;
	
	@Override
	@Transactional
	public boolean registrar(Administrador admin) {
		Administrador objAdmin = dAdmin.save(admin);
		if (objAdmin == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idAdmin) {
		dAdmin.deleteById(idAdmin);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Administrador> listarId(int idAdmin) {
		return dAdmin.findById(idAdmin);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Administrador> listar() {
		return dAdmin.findAll();
	}
	
	@Override
	@Transactional
	public Administrador registroSecurity(Administrador admin) {
		admin.setPassword(passwordEnconder.encode(admin.getPassword()));
		return dAdmin.save(admin);
	}
}
