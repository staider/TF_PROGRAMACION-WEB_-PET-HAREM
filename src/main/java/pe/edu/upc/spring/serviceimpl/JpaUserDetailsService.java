package pe.edu.upc.spring.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.model.Usuarios;
import pe.edu.upc.spring.model.Rol;
import pe.edu.upc.spring.repository.IUsuarioRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService{
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Override
	@Transactional(readOnly = true)	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		
		Usuarios usuario = usuarioRepository.findByUsername(username);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(Rol rol : usuario.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(rol.getAuthority()));
		}
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}
	
}
