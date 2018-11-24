package sv.edu.udb.www.CuponeriaUDB.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sv.edu.udb.www.CuponeriaUDB.repositories.UsuarioRepository;

@Controller
@RequestMapping("/clientes")
public class PublicController {
	@Autowired
	@Qualifier("UsuarioRepository")
	UsuarioRepository usuarioRepositiry;
	
	@GetMapping("/login")
	public String showFormLogin() {
		return "/login";
	}
}
