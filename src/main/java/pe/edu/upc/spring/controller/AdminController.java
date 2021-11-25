package pe.edu.upc.spring.controller;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Distrito;
import pe.edu.upc.spring.model.Administrador;
import pe.edu.upc.spring.service.AdminService;
import pe.edu.upc.spring.service.DistritoService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService dService;
	
	@Autowired
	private DistritoService disService;
	
	@RequestMapping("/inicio")
	public String irPaginaBienvenida() {
		return "inicio";
	}
		
	@RequestMapping("/")
	public String irPaginaListadoRazas(Map<String, Object> model) {
		model.put("listaAdmins", dService.listar());
		return "/Admin/listAdmin";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("admin", new Administrador());
		model.addAttribute("distrito", new Distrito());
		
		model.addAttribute("listaDistritos", disService.listar());
		return "/Admin/admin";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@Valid Administrador objAdmin, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaDistritos", disService.listar());
			return "/Admin/admin";
		}
		else {
			boolean flag = dService.registrar(objAdmin);
			if (flag)
				return "redirect:/admin/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/admin/irRegistrar";
			}
		}
	}
	
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		Optional<Administrador> objAdmin = dService.listarId(id);
		if (objAdmin == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/admin/listar";
		}
		else {
			model.addAttribute("listaAdmins", disService.listar());
			if (objAdmin.isPresent())
				objAdmin.ifPresent(o -> model.addAttribute("admin", o));
			
			return "/Admin/admin";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				dService.eliminar(id);
				model.put("listaAdmins", dService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaAdmins", dService.listar());
			
		}
		return "/Admin/listAdmin";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaAdmins", dService.listar());
		return "/Admin/listAdmin";
	}
	
}
