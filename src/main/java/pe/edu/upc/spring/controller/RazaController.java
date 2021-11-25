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

import pe.edu.upc.spring.model.Raza;
import pe.edu.upc.spring.service.RazaService;

@Controller
@RequestMapping("/raza")
public class RazaController {
	@Autowired
	private RazaService rService;
	
	@RequestMapping("/inicio")
	public String irPaginaBienvenida() {
		return "inicio";
	}
		
	@RequestMapping("/")
	public String irPaginaListadoRazas(Map<String, Object> model) {
		model.put("listaRazas", rService.listar());
		return "/Raza/listRaza";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("raza", new Raza());
		return "/Raza/raza";
	}
	
	@RequestMapping("/registrar")//No permite modificar
	public String registrar(@Valid Raza objRaza, BindingResult binRes, Model model)
			throws ParseException {
		if (binRes.hasErrors())
			return "/Raza/raza";
		else {
			int count = rService.registrar(objRaza);
			if (count > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "redirect:/raza/irRegistrar";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				
			}
		}
		return "redirect:/raza/listar";
	}
	
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		Optional<Raza> objRaza = rService.listarId(id);
		if (objRaza == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/raza/listar";
		}
		else {
			model.addAttribute("raza", objRaza);
			return "/Raza/raza";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				rService.eliminar(id);
				model.put("listaRazas", rService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un problema");
			model.put("listaRazas", rService.listar());
			
		}
		return "/Raza/listRaza";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaRazas", rService.listar());
		return "/Raza/listRaza";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) 
	{
		model.addAttribute("raza", new Raza());
		return "/Raza/buscarRaza";
	}
	
	@RequestMapping("/buscarRaza")
	public String buscar(Map<String, Object> model, @ModelAttribute Raza raza)
			throws ParseException
	{
		List<Raza> listaRazas;
		raza.setNRaza(raza.getNRaza());
		listaRazas = rService.buscarNombre(raza.getNRaza());
		if (listaRazas.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		
		model.put("listaRazas", listaRazas);		
		return "/Raza/buscarRaza";
	}
}
