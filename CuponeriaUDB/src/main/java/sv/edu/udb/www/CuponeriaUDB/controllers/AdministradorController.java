package sv.edu.udb.www.CuponeriaUDB.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sv.edu.udb.www.CuponeriaUDB.entities.Empresas;
import sv.edu.udb.www.CuponeriaUDB.entities.Rubros;
import sv.edu.udb.www.CuponeriaUDB.entities.Usuarios;
import sv.edu.udb.www.CuponeriaUDB.repositories.AdministradorRepository;
import sv.edu.udb.www.CuponeriaUDB.repositories.EmpleadoRepository;
import sv.edu.udb.www.CuponeriaUDB.repositories.EmpresaRepository;
import sv.edu.udb.www.CuponeriaUDB.repositories.RubroRepository;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

	@Autowired
	@Qualifier("AdministradorRepository")
	AdministradorRepository administradorRepositiry;
	
	@Autowired
	@Qualifier("EmpresaRepository")
	EmpresaRepository empresaRepository;
	
	@Autowired
	@Qualifier("EmpleadoRepository")
	EmpleadoRepository empleadoRepository;
	
	@Autowired
	@Qualifier("RubroRepository")
	RubroRepository rubroRepository;
	
	
	@GetMapping("/index")
	public String listarOPC(Model model) {
		return "administrador/index";
	
	}
	
	@GetMapping("/clientes")
	public String listarClientes(Model model) {
		return "administrador/listaClientes";
	
	}
	
	@GetMapping("/cupones")
	public String listarCupones(Model model) {
		return "administrador/listaCupones";
	
	}
	
	@GetMapping("/ofertas")
	public String listarOfertas(Model model) {
		
		return "administrador/listaOfertas";
	
	}
	
	//Rubros
	@GetMapping("/rubros")
	public String listarRubros(Model model) {
		model.addAttribute("listaRubros", rubroRepository.findAll());
		return "administrador/listaRubros";
		
	}
	
	@DeleteMapping("/eliminar/{idrubro}")
	public String eliminarRubro(@PathVariable("idrubro") Integer idrubro, Model model, RedirectAttributes atributos) {
		
			Rubros rubro = new Rubros();
		try {
			rubro = rubroRepository.findByIdRubro(idrubro);
			System.out.println("hola "+rubro.getRubro());
			if(rubro != null) {
				rubroRepository.eliminarRubroPorId(rubro.getIdRubro());;
				atributos.addFlashAttribute("exito", "El rubro ha sido eliminado exitosamente.");
			}else {
				atributos.addFlashAttribute("fracaso", "El rubro no pudo ser eliminado.");
				
			}
		} catch (Exception e) {
			atributos.addFlashAttribute("fracaso", "El rubro no pudo ser eliminado.");
			return "/administrador/index";
		}
		
		return "redirect:/administrador/rubros";
		
	}
	
	
	//empresas
	@GetMapping("/empresas")
	public String listarEmpresas(Model model) {
		model.addAttribute("lista", empresaRepository.findAll());
		
		return "/administrador/listaEmpresa";
	}
	
	@GetMapping("/nuevaEmpresa")
	public String nuevaEmpresa(Model model) {
		model.addAttribute("empresa", new  Empresas());
		model.addAttribute("usuario", new  Usuarios());
		model.addAttribute("listaRubros", rubroRepository.findAll());
		return "/administrador/agregarEmpresa";
	}
	
	
	@PostMapping("/nuevaEmpresa")
	public String insertarEmpresa(@Valid @ModelAttribute("empresa") Empresas empresa,
			BindingResult result, Model model,
			RedirectAttributes atributos){
		
		if(result.hasErrors()) {
			model.addAttribute("empresa", empresa);
			return "/nuevaEmpresa";
		}
		else {
			if(empresaRepository.existsById(empresa.getCodigoEmpresa())) {
				return null;
			}
			else {
				empresaRepository.save(empresa);
				atributos.addFlashAttribute("exito", "Editorial guardada exitosamente");
				return "redirect:/editoriales/list";
			}
			
		}
	}
	
	@GetMapping("/modificarEmpresa/{codigo}")
	public String obtenerEmpresa(@PathVariable("codigo") String codigo, Model model, RedirectAttributes atributos) {
		model.addAttribute("empresa", empresaRepository.findByCodigoEmpresa(codigo));
		model.addAttribute("listaRubros", rubroRepository.findAll());
		return "/administrador/modificarEmpresas";
	}
	
	/*@PutMapping("/modificarEmpresa")
	public String modificarEmpresa(
			@Valid @ModelAttribute("empresa") Empresas empresa,
			BindingResult result, Model model,
			RedirectAttributes atributos) {
		
		if(result.hasErrors()) {
			model.addAttribute("empresa", empresa);
			return "modificar/";
		}
		else {
			if(editorialesRepositiry.existsById(editorial.getCodigoEditorial())) {
				return null;
			}
			else {
				editorialesRepositiry.save(editorial);
				atributos.addFlashAttribute("exito", "Editorial guardada exitosamente");
				return "redirect:/editoriales/list";
			}
			
		}*/
}
