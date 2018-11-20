package sv.edu.udb.www.CuponeriaUDB.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sv.edu.udb.www.CuponeriaUDB.entities.Cupones;
import sv.edu.udb.www.CuponeriaUDB.repositories.EmpleadoRepository;

@RequestMapping("/empleado")
@Controller
public class EmpleadoController {

	@Autowired
	@Qualifier("EmpleadoRepository")	
	EmpleadoRepository empleadoRepository;
	
	@GetMapping("/index")
	public String homeEmpleado(Model model) {
		model.addAttribute("lista", "Prueba");
		return "empleado/home";
	}
	
	@GetMapping("/canjear")
	public String canjearCupon(Model model) {
		model.addAttribute("cupon", new Cupones());
		
		return "empleado/canjearCupon";
	}
	
	/*public String obtenerCupon(Model model) {
		
		model.addAttribute(, empleadoRepository.obtenerCupon("OMG1230029312"));
		
		return "/canjearCupon";
	}*/
	
}
