package sv.edu.udb.www.CuponeriaUDB.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sv.edu.udb.www.CuponeriaUDB.entities.Empleado;
import sv.edu.udb.www.CuponeriaUDB.entities.Empresas;
import sv.edu.udb.www.CuponeriaUDB.entities.Usuarios;
import sv.edu.udb.www.CuponeriaUDB.repositories.EmpleadoRepository;
import sv.edu.udb.www.CuponeriaUDB.repositories.EmpresaRepository;

@RequestMapping("/empresa")
@Controller
public class EmpresaController {



	@Autowired
	@Qualifier("EmpresaRepository")
	EmpresaRepository empresaRepository;
	
	@Autowired
	@Qualifier("EmpleadoRepository")
	EmpleadoRepository empleadoRepository;

	@GetMapping("/index")
	public String indexEmpresa(Model model) {
		Empresas  empresa;
		empresa = empresaRepository.encontrarPorUsuario(12);
		model.addAttribute("lista", empleadoRepository.encontrarPorEmpresa(empresa.getCodigoEmpresa()) );
		return "empresa/principalEmpresa";
	}

	@GetMapping("/nuevo")
	public String nuevoEmpleado(Model model) {
		model.addAttribute("empleado", new Empleado());
		return "empresa/nuevoEmpleado";
	}
}
