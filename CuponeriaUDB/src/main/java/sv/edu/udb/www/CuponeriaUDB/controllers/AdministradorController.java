package sv.edu.udb.www.CuponeriaUDB.controllers;

import java.util.Random;
import java.util.UUID;

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

import sv.edu.udb.www.CuponeriaUDB.configuration.Correo;
import sv.edu.udb.www.CuponeriaUDB.entities.Empresas;
import sv.edu.udb.www.CuponeriaUDB.entities.Rubros;
import sv.edu.udb.www.CuponeriaUDB.entities.Tipousuario;
import sv.edu.udb.www.CuponeriaUDB.entities.Usuarios;
import sv.edu.udb.www.CuponeriaUDB.repositories.AdministradorRepository;
import sv.edu.udb.www.CuponeriaUDB.repositories.ClienteRepository;
import sv.edu.udb.www.CuponeriaUDB.repositories.EmpleadoRepository;
import sv.edu.udb.www.CuponeriaUDB.repositories.EmpresaRepository;
import sv.edu.udb.www.CuponeriaUDB.repositories.OfertasRepository;
import sv.edu.udb.www.CuponeriaUDB.repositories.RubroRepository;
import sv.edu.udb.www.CuponeriaUDB.repositories.UsuarioRepository;
import sv.edu.udb.www.CuponeriaUDB.utils.SecurityUtils;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

	@Autowired
	@Qualifier("AdministradorRepository")
	AdministradorRepository administradorRepositiry;
	
	
	@Autowired
	@Qualifier("ClienteRepository")
	ClienteRepository clienteRepository;
	
	@Autowired
	@Qualifier("OfertasRepository")
	OfertasRepository ofertaRepository;
	
	@Autowired
	@Qualifier("EmpresaRepository")
	EmpresaRepository empresaRepository;
	
	@Autowired
	@Qualifier("EmpleadoRepository")
	EmpleadoRepository empleadoRepository;
	
	@Autowired
	@Qualifier("UsuarioRepository")
	UsuarioRepository usuarioRepository;
	
	@Autowired
	@Qualifier("RubroRepository")
	RubroRepository rubroRepository;
	
	
	@GetMapping("/index")
	public String listarOPC(Model model) {
		return "administrador/index";
	
	}
	
	@GetMapping("/clientes")
	public String listarClientes(Model model) {
		model.addAttribute("lista",clienteRepository.findAll());
		return "/administrador/listaClientes";
	
	}
	
	@GetMapping("/cupones")
	public String listarCupones(Model model) {
		return "administrador/listaCupones";
	
	}
	
	@GetMapping("/ofertas")
	public String listarOfertas(Model model) {
		model.addAttribute("lista",ofertaRepository.findAll());
		return "/administrador/listaOfertas";
	
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
			RedirectAttributes atributos,
			@Valid @ModelAttribute("usuarios") Usuarios usuarios) throws Exception{
		
		if(result.hasErrors()) {
			model.addAttribute("empresa", empresa);
			model.addAttribute("usuario", usuarios);
			model.addAttribute("listaRubros", rubroRepository.findAll());
			return "/administrador/nuevaEmpresa";
		}
		else {
			empresa.setCodigoEmpresa("EMP" + (empresaRepository.generarCodigoEmpresa() + 1));
			System.out.println(empresa.getCodigoEmpresa());

			String cadenaAleatoria = UUID.randomUUID().toString();
			// Creacion password

			char[] caracteres;
			caracteres = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
					'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
					'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
					'u', 'v', 'w', 'x', 'y', 'z' };
			String pass = "";
			for (int i = 0; i < 8; i++) {
				pass += caracteres[new Random().nextInt(62)];
			}

			usuarios.setContrasena(SecurityUtils.encriptarSHA(pass));
			usuarios.setConfirmado(true);
			usuarios.setIdConfirmacion(cadenaAleatoria);
			usuarios.setTipousuario(new Tipousuario(2));
			if((usuarios.getCorreo()) == null) {
				return null;
			}
			if (usuarioRepository.findByCorreo(usuarios.getCorreo()) != null) {
				atributos.addFlashAttribute("fracaso", "El correo ya se encuentra registrado");
				return "redirect:/administrador/nuevaEmpresa";
			}
			usuarioRepository.save(usuarios); 

			usuarios = usuarioRepository.findByCorreo(usuarios.getCorreo());

			String texto = "";
			texto += "Su cuenta ha sido creada, para ingresar al sistema como Empresa debe utilizar <br/>" + "Correo: "
					+ usuarios.getCorreo() + "<br/>" + "Contraseña: " + usuarios.getContrasena();

			Correo correo = new Correo();
			correo.setAsunto("Confirmacion de registro");
			correo.setMensaje(texto);
			correo.setDestinatario(usuarios.getCorreo());
			correo.enviarCorreo();

			empresa.setUsuarios(new Usuarios(usuarios.getIdUsuario()));

			if (empresaRepository.existsById(empresa.getCodigoEmpresa())) {
				return null;
			} else {

				empresaRepository.save(empresa);
				atributos.addFlashAttribute("exito", "Empresa ingresada exitosamente!");
				return "redirect:/administrador/empresas";
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
