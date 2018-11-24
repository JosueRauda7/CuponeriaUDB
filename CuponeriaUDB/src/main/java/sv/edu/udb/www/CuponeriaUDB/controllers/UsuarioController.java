package sv.edu.udb.www.CuponeriaUDB.controllers;

import java.util.Collection;
import java.util.Iterator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import sv.edu.udb.www.CuponeriaUDB.entities.Clientes;
import sv.edu.udb.www.CuponeriaUDB.entities.Usuarios;
import sv.edu.udb.www.CuponeriaUDB.repositories.UsuarioRepository;

@Controller
public class UsuarioController {

	@Autowired
	@Qualifier("UsuarioRepository")
	UsuarioRepository usuarioRepository;
	
	@GetMapping("/login")
	public String login(Model model) {
		
		String tipoUsuario = "";
		
		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder
				.getContext().getAuthentication().getAuthorities();
		for (Iterator iterator = authorities.iterator(); iterator.hasNext();) {
			SimpleGrantedAuthority simpleGrantedAuthority = (SimpleGrantedAuthority) iterator.next();
			tipoUsuario = simpleGrantedAuthority.toString();
		}
				
		if(tipoUsuario.equals("Administrador")) {
			return "/administrador/index";
		}else if(tipoUsuario.equals("Empleado")) {
			return "/empleado/home";
		}else if(tipoUsuario.equals("Empresa")) {
			return "/empresa/principalEmpresa";
		}else if(tipoUsuario.equals("Cliente")) {
			return "";
		}
		
		model.addAttribute("cliente", new Clientes());
		model.addAttribute("usuarios", new Usuarios());
		
		return "/public/login";
	}
	
}
