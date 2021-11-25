package pe.edu.upc.spring.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Emprendimiento;
import pe.edu.upc.spring.model.Distrito;
import pe.edu.upc.spring.service.DistritoService;
import pe.edu.upc.spring.service.EmprendimientoService;

@Controller
@RequestMapping("/emprendimiento")
public class EmprendimientoController {
	@Autowired
	private EmprendimientoService eService;
	
	@Autowired
	private DistritoService disService;
	
	@RequestMapping("/inicio")
	public String irPaginaBienvenida() {
		return "inicio";
	}
		
	@RequestMapping("/")
	public String irPaginaListadoRazas(Map<String, Object> model) {
		model.put("listaEmpres", eService.listar());
		return "/Emprendimiento/listEmpre";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("emprendimiento", new Emprendimiento());
		model.addAttribute("distrito", new Distrito());
		
		model.addAttribute("listaDistritos", disService.listar());
		return "/Emprendimiento/emprendimiento";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@Valid Emprendimiento objEmpre, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaDistritos", disService.listar());
			return "/Emprendimiento/emprendimiento";
		}
		else {
			boolean flag = eService.registrar(objEmpre);
			if (flag)
				return "redirect:/emprendimiento/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/emprendimiento/irRegistrar";
			}
		}
	}
	
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		Optional<Emprendimiento> objEmpre = eService.listarId(id);
		if (objEmpre == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/emprendimiento/listar";
		}
		else {
			model.addAttribute("listaDistritos", disService.listar());
			
			if (objEmpre.isPresent())
				objEmpre.ifPresent(o -> model.addAttribute("emprendimiento", o));
			
			return "/Emprendimiento/emprendimiento";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				eService.eliminar(id);
				model.put("listaEmpres", eService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaEmpres", eService.listar());
			
		}
		return "/Emprendimiento/listEmpre";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaEmpres", eService.listar());
		return "/Emprendimiento/listEmpre";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) 
	{
		model.addAttribute("emprendimiento", new Emprendimiento());
		return "/Emprendimiento/buscarEmpre";
	}
	
	@RequestMapping("/buscarEmpre")
	public String buscar(Map<String, Object> model, @ModelAttribute Emprendimiento empre)
			throws ParseException
	{
		List<Emprendimiento> listaEmpres;
		empre.setNEmpre(empre.getNEmpre());
		listaEmpres = eService.buscarNombre(empre.getNEmpre());
		if (listaEmpres.isEmpty()) {
			listaEmpres = eService.buscarDistrito(empre.getNEmpre());
		}
		if (listaEmpres.isEmpty()) {
			listaEmpres = eService.buscarRUC(empre.getNEmpre());
		}
		if (listaEmpres.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		
		model.put("listaEmpres", listaEmpres);		
		return "/Emprendimiento/buscarEmpre";
	}
}
