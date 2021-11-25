package pe.edu.upc.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pe.edu.upc.spring.auth.handler.LoginSuccessHandler;
import pe.edu.upc.spring.serviceimpl.AdminDetailsService;
import pe.edu.upc.spring.serviceimpl.DuenioDetailsService;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DuenioDetailsService dDetailService;
	
	@Autowired
	private AdminDetailsService aDetailsService;
	
	@Autowired
	private LoginSuccessHandler successHandler;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(dDetailService).passwordEncoder(passwordEncoder());
		auth.userDetailsService(aDetailsService).passwordEncoder(passwordEncoder());
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		try {
			http.authorizeRequests()
				.antMatchers("/emprendimiento/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/publicidad/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/plan/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/raza/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/duenio/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/mascota/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
				.antMatchers("/encuentro/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
				.antMatchers("/welcome/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')").and()
				.formLogin().successHandler(successHandler).loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/raza/listar")
				.permitAll().and().logout().logoutSuccessUrl("/login").permitAll().and().exceptionHandling().accessDeniedPage("/error_403");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}

