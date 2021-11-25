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

import pe.edu.upc.spring.model.Duenio;
import pe.edu.upc.spring.model.Mascota;
import pe.edu.upc.spring.model.Encuentro;
import pe.edu.upc.spring.service.DuenioService;
import pe.edu.upc.spring.service.MascotaService;
import pe.edu.upc.spring.service.EncuentroService;

@Controller
@RequestMapping("/encuentro")
public class EncuentroController {
	@Autowired
	private EncuentroService eService;
	
	@Autowired
	private DuenioService dService;
	
	@Autowired
	private MascotaService mService;	
	
	@RequestMapping("/inicio")
	public String irPaginaBienvenida() {
		return "inicio";
	}
	
	@RequestMapping("/")
	public String irPaginaListadoMascotas(Map<String, Object> model) {
		model.put("listaEncuentros", eService.listar());
		return "/Encuentro/listEncuentro";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		
		model.addAttribute("encuentro", new Encuentro());
		model.addAttribute("duenio", new Duenio());
		model.addAttribute("mascota", new Mascota());
		
		model.addAttribute("listaMascotas", mService.listar());
		model.addAttribute("listaDuenios", dService.listar());
		
		return "/Encuentro/encuentro";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@Valid Encuentro objEnc, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) 
			{
				model.addAttribute("listaMascotas", mService.listar());
				model.addAttribute("listaDuenios", dService.listar());
				return "/Encuentro/encuentro";
			}
		else {
			boolean flag = eService.registrar(objEnc);
			if (flag)
				return "redirect:/encuentro/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/encuentro/irRegistrar";
			}
		}
	}
	
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		Optional<Encuentro> objEnc = eService.buscarId(id);
		if (objEnc == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/encuentro/listar";
		}
		else {
			model.addAttribute("listaMascotas", mService.listar());
			model.addAttribute("listaDuenios", dService.listar());
			
			if (objEnc.isPresent())
				objEnc.ifPresent(o -> model.addAttribute("encuentro", o));
			
			return "/Encuentro/encuentro";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				eService.eliminar(id);
				model.put("listaEncuentros", eService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaEncuentros", eService.listar());
			
		}
		return "/Encuentro/listEncuentro";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaEncuentros", eService.listar());
		return "/Encuentro/listEncuentro";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) 
	{
		model.addAttribute("encuentro", new Encuentro());
		return "/Encuentro/buscarEncuentro";
	}
	
	@RequestMapping("/buscarEncuentro")
	public String buscar(Map<String, Object> model, @ModelAttribute Encuentro encuentro)
			throws ParseException
	{
		List<Encuentro> listaEncuentros;
		encuentro.setMascota(encuentro.getMascota());
		listaEncuentros = eService.buscarMascota(encuentro.getMascota().getNMascota());
		if (listaEncuentros.isEmpty()) {
			listaEncuentros = eService.buscarDuenio(encuentro.getMascota().getNMascota());
		}
		if (listaEncuentros.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		
		model.put("listaEncuentros", listaEncuentros);		
		return "/Encuentro/buscarEncuentro";
	}
}
