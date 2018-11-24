package sv.edu.udb.www.CuponeriaUDB.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sv.edu.udb.www.CuponeriaUDB.entities.Cupones;
import sv.edu.udb.www.CuponeriaUDB.entities.Estadocupon;
import sv.edu.udb.www.CuponeriaUDB.repositories.EmpleadoRepository;
import sv.edu.udb.www.CuponeriaUDB.repositories.CuponesRepository;


@RequestMapping("/empleado")
@Controller
public class EmpleadoController {

	@Autowired
	@Qualifier("EmpleadoRepository")	
	EmpleadoRepository empleadoRepository;
	
	@Autowired
	@Qualifier("CuponesRepository")	
	CuponesRepository cuponesRepository;

	private Cupones cupones;
	
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
	
	@PostMapping("/obtenerCupon")
	public String obtenerCupon(@Valid @ModelAttribute("cupon") Cupones cupones, BindingResult result, Model model)  {
		
		if(result.hasErrors()) {
			model.addAttribute("cupones", cupones);
			return "empleado/canjearCupon";
		}else {
			if(cuponesRepository.existsById(cupones.getCodigoCupo())) {
				
				model.addAttribute("cupones", cuponesRepository.findByCodigoCupo(cupones.getCodigoCupo()));
				model.addAttribute("cupone", cuponesRepository.findByCodigoCupo(cupones.getCodigoCupo()));
				
				cuponesRepository.canjearCupon(cupones.getCodigoCupo());
				
				return "empleado/canjearCupon";
			}else {
				return "redirect:empleado/canjearCupon";
			}
			
		}	
	
	}
	
	/*@PostMapping("/canjearCupon")
	public String canjearCupones(Model model) {
		
		if(cuponesRepository.canjearCupon() == 1) {
			return "empleado/home";
		}else {
			
		}
		
		
	}*/
	
}
