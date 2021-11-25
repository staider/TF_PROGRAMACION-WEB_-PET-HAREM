package pe.edu.upc.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class InicioController {
	
	@RequestMapping("/inicio")
	public String irPaginaInicio() {
		return "inicio";
	}
	
	@RequestMapping("/nosotros")
	public String irPaginaNosotros() {
		return "nosotros";
	}
	
	@RequestMapping("/contactanos")
	public String irPaginaContactanos() {
		return "contactanos";
	}
	
	@RequestMapping("/login")
	public String irPaginaLogin() {
		return "login";
	}
}
