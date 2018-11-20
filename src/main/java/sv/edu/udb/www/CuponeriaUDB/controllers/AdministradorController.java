package sv.edu.udb.www.CuponeriaUDB.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sv.edu.udb.www.CuponeriaUDB.repositories.AdministradorRepository;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

	@Autowired
	@Qualifier("AdministradorRepository")
	AdministradorRepository administradorRepositiry;
	
	
	@GetMapping("/list")
	public String listarEditoriales(Model model) {
		return "administrador/index";
	
	}
}
