package pe.edu.upc.spring.serviceimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Administrador;
import pe.edu.upc.spring.repository.AdminRepository;

@Service
public class AdminDetailsService implements UserDetailsService{
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	@Transactional(readOnly = true)	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		
		Administrador admin = adminRepository.findByUsername(username);
		UserBuilder builder = null;
		
		if (admin != null) {
			builder = User.withUsername(username);
			builder.disabled(false);
			builder.password(admin.getPassword());
			builder.authorities(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		else {
			throw new  UsernameNotFoundException("El usuario no existe");
		}
		
		return builder.build();
		
	}
	
}
