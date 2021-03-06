package sv.edu.udb.www.CuponeriaUDB.controllers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sv.edu.udb.www.CuponeriaUDB.configuration.Correo;
import sv.edu.udb.www.CuponeriaUDB.entities.Empleado;
import sv.edu.udb.www.CuponeriaUDB.entities.Empresas;
import sv.edu.udb.www.CuponeriaUDB.entities.Tipousuario;
import sv.edu.udb.www.CuponeriaUDB.entities.Usuarios;
import sv.edu.udb.www.CuponeriaUDB.repositories.EmpleadoRepository;
import sv.edu.udb.www.CuponeriaUDB.repositories.EmpresaRepository;
import sv.edu.udb.www.CuponeriaUDB.repositories.OfertasRepository;
import sv.edu.udb.www.CuponeriaUDB.repositories.UsuarioRepository;

@RequestMapping("/empresa")
@Controller
public class EmpresaController {

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
	@Qualifier("OfertasRepository")
	OfertasRepository ofertasRepository;


	@GetMapping("/index")
	public String indexEmpresa(Model model) {
		Empresas empresa;
		empresa = empresaRepository.encontrarPorUsuario(12);
		model.addAttribute("lista", empleadoRepository.encontrarPorEmpresa(empresa.getCodigoEmpresa()));
		return "empresa/principalEmpresa";
	}

	@GetMapping("/nuevo")
	public String nuevoEmpleado(Model model) {
		model.addAttribute("empleado", new Empleado());
		model.addAttribute("empresas", empresaRepository.findAllByOrderByNombreEmpresa());
		return "empresa/nuevoEmpleado";
	}

	@GetMapping("/modificar/{idempleado}")
	public String modificarEmpleado(Model model, @PathVariable("idempleado") int idempleado) {
		Empleado empleado = new Empleado();

		empleado = empleadoRepository.findByIdEmpleado(idempleado);
		Usuarios usuario = new Usuarios();
		usuario = empleado.getUsuarios();
		model.addAttribute("empleado", empleado);
		model.addAttribute("usuarios", usuario);
		return "empresa/modificarEmpleado";
	}

	@GetMapping("/eliminar/{idempleado}")
	public String eliminarEmpleado(@PathVariable("idempleado") int idempleado, Model model,
			RedirectAttributes atributos) {

		Usuarios usuario = new Usuarios();
		Empleado empleado = new Empleado();

		empleado = empleadoRepository.findByIdEmpleado(idempleado);
		usuario = usuarioRepository.findByIdUsuario(empleado.getUsuarios().getIdUsuario());

		empleadoRepository.delete(empleado);
		usuarioRepository.delete(usuario);

		atributos.addFlashAttribute("exito", "Empleado eliminado exitosamente");
		return "redirect:/empresa/index";
	}

	@PostMapping("/agregar")
	public String agregarEmpleado(@Valid @ModelAttribute("editorial") Empleado empleado, BindingResult result,
			Model model, RedirectAttributes atributos) throws NoSuchAlgorithmException {

		String cadenaAleatoria = UUID.randomUUID().toString();
		char[] caracteres;
		caracteres = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
				'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b',
				'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
				'x', 'y', 'z' };
		String pass = "";
		for (int i = 0; i < 8; i++) {
			pass += caracteres[new Random().nextInt(62)];
		}

		MessageDigest SHA256 = MessageDigest.getInstance("SHA-256");
		byte[] valores = SHA256.digest(pass.getBytes());

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < valores.length; i++) {
			sb.append(Integer.toString((valores[i] & 0xff) + 0x100, 16).substring(1));
		}

		if (result.hasErrors()) {
			model.addAttribute("empleado", empleado);
			return "empresa/nuevoEmpleado";
		} else {

			Tipousuario tipousuario = new Tipousuario();
			tipousuario.setIdTipoUsuario(3);
			empleado.getUsuarios().setContrasena(sb.toString());
			empleado.getUsuarios().setIdConfirmacion(cadenaAleatoria);
			empleado.getUsuarios().setConfirmado(false);
			empleado.getUsuarios().setTipousuario(tipousuario);
			empleado.setEmpresas(empresaRepository.encontrarPorUsuario(12));
			usuarioRepository.save(empleado.getUsuarios());
			   String texto = "<h1>Bienvendo a La cuponera</h1><p>Tu usuario es: "+empleado.getUsuarios().getCorreo()+"</p><p>Tu contaseña es:"+pass+"</p>";
               Correo correo = new Correo();
               correo.setAsunto("Credenciales");
               correo.setMensaje(texto);
               correo.setDestinatario(empleado.getUsuarios().getCorreo());
               correo.enviarCorreo();
			Usuarios usuariocon = usuarioRepository.findTopByConfirmadoOrderByIdUsuarioDesc(false);
			empleado.setUsuarios(usuariocon);
			empleadoRepository.save(empleado);
			atributos.addFlashAttribute("exito", "Empleado ingresado exitosamente");
			return "redirect:/empresa/index";
		}

	}

	@PostMapping("/update")
	public String updateEmpleado(@Valid @ModelAttribute("editorial") Empleado empleado,
		 BindingResult result, Model model,
			RedirectAttributes atributos) throws NoSuchAlgorithmException {

		if (result.hasErrors()) {
			model.addAttribute("empleado", empleado);
			return "empresa/modificarEmpleado";
		} else {

			
			empleadoRepository.save(empleado);
			usuarioRepository.save(empleado.getUsuarios());
			atributos.addFlashAttribute("exito", "Empleado modificado exitosamente");
			return "redirect:/empresa/index";
		}

	}
	
	@GetMapping("/verofertas")
	public String verOfertas(Model model) {
		
		model.addAttribute("lista", ofertasRepository.listaOfertas(12, 1));
		return "empresa/listaOfertas";
	}
	
	

}
