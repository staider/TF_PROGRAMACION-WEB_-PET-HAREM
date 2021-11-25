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

import pe.edu.upc.spring.model.Plan;
import pe.edu.upc.spring.service.PlanService;

@Controller
@RequestMapping("/plan")
public class PlanController {
	@Autowired
	private PlanService pService;
	
	@RequestMapping("/inicio")
	public String irPaginaBienvenida() {
		return "inicio";
	}
		
	@RequestMapping("/")
	public String irPaginaListadoRazas(Map<String, Object> model) {
		model.put("listaPlanes", pService.listar());
		return "/Plan/listPlan";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("plan", new Plan());
		
		model.addAttribute("listaPlanes", pService.listar());
		return "/Plan/plan";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@Valid Plan objPlan, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) {
			return "/Plan/plan";
		}
		else {
			boolean flag = pService.registrar(objPlan);
			if (flag)
				return "redirect:/plan/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/plan/irRegistrar";
			}
		}
	}
	
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		Optional<Plan> objPlan = pService.listarId(id);
		if (objPlan == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/plan/listar";
		}
		else {
			model.addAttribute("plan", objPlan);
			return "/Plan/plan";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				pService.eliminar(id);
				model.put("listaPlanes", pService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error, este plan es usado en una publicidad");
			model.put("listaPlanes", pService.listar());
			
		}
		return "/Plan/listPlan";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaPlanes", pService.listar());
		return "/Plan/listPlan";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) 
	{
		model.addAttribute("plan", new Plan());
		return "/Plan/buscarPlan";
	}
	
	@RequestMapping("/buscarPlan")
	public String buscar(Map<String, Object> model, @ModelAttribute Plan plan)
			throws ParseException
	{
		List<Plan> listaPlanes;
		plan.setNPlan(plan.getNPlan());
		listaPlanes = pService.buscarNombre(plan.getNPlan());
		if (listaPlanes.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		
		model.put("listaPlanes", listaPlanes);		
		return "/Plan/buscarPlan";
	}
}
