package sv.edu.udb.www.CuponeriaUDB.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sv.edu.udb.www.CuponeriaUDB.repositories.EmpresaRepository;

@RequestMapping("/empresa")
@Controller
public class EmpresaController {



	@Autowired
	@Qualifier("EmpresaRepository")
	EmpresaRepository empresaRepository;
	
	@GetMapping("/index")
	public String indexEmpresa(Model model) {
		model.addAttribute("lista", empresaRepository.findAllByOrderByNombreEmpleado());
		return "empresa/principalEmpresa";
	}
}
