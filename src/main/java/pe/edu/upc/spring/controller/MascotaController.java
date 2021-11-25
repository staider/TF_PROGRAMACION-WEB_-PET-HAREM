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

import pe.edu.upc.spring.model.Certificado;
import pe.edu.upc.spring.model.Duenio;
import pe.edu.upc.spring.model.Genero;
import pe.edu.upc.spring.model.Mascota;
import pe.edu.upc.spring.model.Raza;
import pe.edu.upc.spring.service.CertificadoService;
import pe.edu.upc.spring.service.DuenioService;
import pe.edu.upc.spring.service.GeneroService;
import pe.edu.upc.spring.service.MascotaService;
import pe.edu.upc.spring.service.RazaService;

@Controller
@RequestMapping("/mascota")
public class MascotaController {
	@Autowired
	private RazaService rService;
	
	@Autowired
	private DuenioService dService;
	
	@Autowired
	private MascotaService pService;	
	
	@Autowired
	private CertificadoService cService;
	
	@Autowired
	private GeneroService gService;
	
	@RequestMapping("/inicio")
	public String irPaginaBienvenida() {
		return "inicio";
	}
		
	@RequestMapping("/")
	public String irPaginaListadoMascotas(Map<String, Object> model) {
		model.put("listaMascotas", pService.listar());
		return "/Mascota/listMascota";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		
		model.addAttribute("raza", new Raza());
		model.addAttribute("duenio", new Duenio());
		model.addAttribute("mascota", new Mascota());
		model.addAttribute("genero", new Genero());
		model.addAttribute("certificado", new Certificado());
		model.addAttribute("certificado2", new Certificado());
		model.addAttribute("certificado3", new Certificado());
		
		model.addAttribute("listaRazas", rService.listar());
		model.addAttribute("listaDuenios", dService.listar());
		model.addAttribute("listaGeneros", gService.listar());
		model.addAttribute("listaCertificados", cService.listar());
		
		return "/Mascota/mascota";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@Valid Mascota objMas, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) 
			{
				model.addAttribute("listaRazas", rService.listar());
				model.addAttribute("listaDuenios", dService.listar());
				model.addAttribute("listaGeneros", gService.listar());
				model.addAttribute("listaCertificados", cService.listar());
				return "/Mascota/mascota";
			}
		else {
			boolean flag = pService.registrar(objMas);
			if (flag)
				return "redirect:/mascota/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/mascota/irRegistrar";
			}
		}
	}
	
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		Optional<Mascota> objMas = pService.buscarId(id);
		if (objMas == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/mascota/listar";
		}
		else {
			model.addAttribute("listaRazas", rService.listar());
			model.addAttribute("listaDuenios", dService.listar());
			model.addAttribute("listaGeneros", gService.listar());
			model.addAttribute("listaCertificados", cService.listar());
			
			if (objMas.isPresent())
				objMas.ifPresent(o -> model.addAttribute("mascota", o));
			
			return "/Mascota/mascota";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				pService.eliminar(id);
				model.put("listaMascotas", pService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaMascotas", pService.listar());
			
		}
		return "/Mascota/listMascota";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaMascotas", pService.listar());
		return "/Mascota/listMascota";
	}		
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Mascota mascota) 
	throws ParseException
	{
		pService.listarId(mascota.getIdMascota());
		return "/Mascota/listMascota";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) 
	{
		model.addAttribute("mascota", new Mascota());
		return "/Mascota/buscar";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Mascota mascota)
			throws ParseException
	{
		List<Mascota> listaMascotas;
		mascota.setNMascota(mascota.getNMascota());
		listaMascotas = pService.buscarNombre(mascota.getNMascota());
		if (listaMascotas.isEmpty()) {
			listaMascotas = pService.buscarDuenio(mascota.getNMascota());
		}
		if (listaMascotas.isEmpty()) {
			listaMascotas = pService.buscarRaza(mascota.getNMascota());
		}
		if (listaMascotas.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		
		model.put("listaMascotas", listaMascotas);		
		return "/Mascota/buscar";
	}
}
