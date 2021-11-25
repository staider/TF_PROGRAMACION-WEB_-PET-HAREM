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

import pe.edu.upc.spring.model.Distrito;
import pe.edu.upc.spring.model.Duenio;
import pe.edu.upc.spring.service.DuenioService;
import pe.edu.upc.spring.service.DistritoService;

@Controller
@RequestMapping("/duenio")
public class DuenoController {
	@Autowired
	private DuenioService dService;
	
	@Autowired
	private DistritoService disService;
	
	@RequestMapping("/inicio")
	public String irPaginaBienvenida() {
		return "inicio";
	}
		
	@RequestMapping("/")
	public String irPaginaListadoRazas(Map<String, Object> model) {
		model.put("listaDuenios", dService.listar());
		return "/Duenio/listDuenio";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("duenio", new Duenio());
		model.addAttribute("distrito", new Distrito());
		
		model.addAttribute("listaDistritos", disService.listar());
		return "/Duenio/duenio";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@Valid Duenio objDuenio, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) {
			model.addAttribute("listaDistritos", disService.listar());
			return "/Duenio/duenio";
		}
		else {
			boolean flag = dService.registrar(objDuenio);
			if (flag)
				return "redirect:/duenio/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/duenio/irRegistrar";
			}
		}
	}
	
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		Optional<Duenio> objDuenio = dService.listarId(id);
		if (objDuenio == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/duenio/listar";
		}
		else {
			model.addAttribute("listaDistritos", disService.listar());
			if (objDuenio.isPresent())
				objDuenio.ifPresent(o -> model.addAttribute("duenio", o));
			
			return "/Duenio/duenio";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				dService.eliminar(id);
				model.put("listaDuenios", dService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un error");
			model.put("listaDuenios", dService.listar());
			
		}
		return "/Duenio/listDuenio";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaDuenios", dService.listar());
		return "/Duenio/listDuenio";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) 
	{
		model.addAttribute("duenio", new Duenio());
		return "/Duenio/buscarDuenio";
	}
	
	@RequestMapping("/buscarDuenio")
	public String buscar(Map<String, Object> model, @ModelAttribute Duenio duenio)
			throws ParseException
	{
		List<Duenio> listaDuenios;
		duenio.setNameDuenio(duenio.getNameDuenio());
		listaDuenios = dService.buscarNombre(duenio.getNameDuenio());
		if (listaDuenios.isEmpty()) {
			listaDuenios = dService.buscarDistrito(duenio.getNameDuenio());
		}
		if (listaDuenios.isEmpty()) {
			listaDuenios = dService.buscarCorreo(duenio.getNameDuenio());
		}
		if (listaDuenios.isEmpty()) {
			listaDuenios = dService.buscarDNI(duenio.getNameDuenio());
		}
		
		if (listaDuenios.isEmpty()) {
			listaDuenios = dService.buscarCelular(duenio.getNameDuenio());
		}
		if (listaDuenios.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		
		model.put("listaDuenios", listaDuenios);		
		return "/Duenio/buscarDuenio";
	}
}
