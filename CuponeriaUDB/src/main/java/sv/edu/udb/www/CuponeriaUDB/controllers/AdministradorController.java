package sv.edu.udb.www.CuponeriaUDB.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@GetMapping("/rubros")
	public String listarRubros(Model model) {
		model.addAttribute("listaRubros", rubroRepository.findAll());
		return "administrador/listaRubros";
		
	}
	
	@GetMapping("/empresas")
	public String listarEmpresas(Model model) {
		return "administrador/listaEmpresa";
	
	}
}
