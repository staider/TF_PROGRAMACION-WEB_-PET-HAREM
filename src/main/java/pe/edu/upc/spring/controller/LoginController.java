package pe.edu.upc.spring.controller;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.spring.model.Administrador;
import pe.edu.upc.spring.model.Distrito;
import pe.edu.upc.spring.model.Duenio;
import pe.edu.upc.spring.service.AdminService;
import pe.edu.upc.spring.service.DistritoService;
import pe.edu.upc.spring.service.DuenioService;

@Controller
@RequestMapping()
public class LoginController {
	
	@Autowired
	private DuenioService dService;
	
	@Autowired
	private AdminService aService;
	
	@Autowired
	private DistritoService disService;
	
	@GetMapping("/login")
	public String loginA(Model model) {
		model.addAttribute("duenio", new Duenio());
		return "login";
	}
	
	@GetMapping("/registro")
	public String registroDuenio(Model model) {
		model.addAttribute("duenio", new Duenio());
		model.addAttribute("distrito", new Distrito());
		
		model.addAttribute("listaDistritos", disService.listar());
		
		return "registro";
	}
	
	@PostMapping("/registro")
	public String registro(@Valid @ModelAttribute Duenio duenio, BindingResult result, Model model) throws ParseException {
		if(result.hasErrors()) {
			return "redirect:/registro";
		}
		else {
			model.addAttribute("duenio", dService.registroSecurity(duenio));
		}
		return "redirect:/login";
	}
	
	@GetMapping("/registroAdmin")
	public String registroAdmin(Model model) {
		model.addAttribute("admin", new Administrador());
		model.addAttribute("distrito", new Distrito());
		
		model.addAttribute("listaDistritos", disService.listar());
		
		return "registroAdmin";
	}
	
	@PostMapping("/registroAdmin")
	public String registro(@Valid @ModelAttribute Administrador admin, BindingResult result, Model model) throws ParseException {
		if(result.hasErrors()) {
			return "redirect:/registro";
		}
		else {
			model.addAttribute("admin", aService.registroSecurity(admin));
		}
		return "redirect:/login";
	}
}
