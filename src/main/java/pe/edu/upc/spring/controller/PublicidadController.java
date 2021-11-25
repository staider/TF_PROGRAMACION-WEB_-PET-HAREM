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
import pe.edu.upc.spring.model.Publicidad;
import pe.edu.upc.spring.model.Plan;
import pe.edu.upc.spring.service.EmprendimientoService;
import pe.edu.upc.spring.service.PublicidadService;
import pe.edu.upc.spring.service.PlanService;

@Controller
@RequestMapping("/publicidad")
public class PublicidadController {
	@Autowired
	private EmprendimientoService eService;
	
	@Autowired
	private PlanService plService;
	
	@Autowired
	private PublicidadService pService;	
	
	@RequestMapping("/inicio")
	public String irPaginaBienvenida() {
		return "inicio";
	}
		
	@RequestMapping("/")
	public String irPaginaListadoMascotas(Map<String, Object> model) {
		model.put("listaPublis", pService.listar());
		return "/Publicidad/listPublicidad";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		
		model.addAttribute("publicidad", new Publicidad());
		model.addAttribute("empre", new Emprendimiento());
		model.addAttribute("plan", new Plan());
		
		model.addAttribute("listaEmpres", eService.listar());
		model.addAttribute("listaPlanes", plService.listar());
		
		return "/Publicidad/publicidad";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@Valid Publicidad objPub, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) 
			{
				model.addAttribute("listaEmpres", eService.listar());
				model.addAttribute("listaPlanes", plService.listar());
				return "/Publicidad/publicidad";
			}
		else {
			boolean flag = pService.registrar(objPub);
			if (flag)
				return "redirect:/publicidad/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/publicidad/irRegistrar";
			}
		}
	}
	
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		Optional<Publicidad> objPub = pService.buscarId(id);
		if (objPub == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/publicidad/listar";
		}
		else {
			model.addAttribute("listaEmpres", eService.listar());
			model.addAttribute("listaPlanes", plService.listar());
			
			if (objPub.isPresent())
				objPub.ifPresent(o -> model.addAttribute("publicidad", o));
			
			return "/Publicidad/publicidad";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				pService.eliminar(id);
				model.put("listaPublis", pService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un roche");
			model.put("listaPublis", pService.listar());
			
		}
		return "/Publicidad/listPublicidad";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaPublis", pService.listar());
		return "/Publicidad/listPublicidad";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) 
	{
		model.addAttribute("publicidad", new Publicidad());
		return "/Publicidad/buscarPublicidad";
	}
	
	@RequestMapping("/buscarPublicidad")
	public String buscar(Map<String, Object> model, @ModelAttribute Publicidad pub)
			throws ParseException
	{
		List<Publicidad> listaPubs;
		pub.setEmpre(pub.getEmpre());
		listaPubs = pService.buscarEmprendimiento(pub.getEmpre().getNEmpre());
		
		if (listaPubs.isEmpty()) {
			listaPubs = pService.buscarPlan(pub.getEmpre().getNEmpre());
		}
		if (listaPubs.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		
		model.put("listaPublis", listaPubs);		
		return "/Publicidad/buscarPublicidad";
	}
}
