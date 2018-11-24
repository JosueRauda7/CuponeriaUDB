package sv.edu.udb.www.CuponeriaUDB.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import antlr.collections.List;
import sv.edu.udb.www.CuponeriaUDB.entities.Clientes;
import sv.edu.udb.www.CuponeriaUDB.entities.Cupones;
import sv.edu.udb.www.CuponeriaUDB.entities.Estadocupon;
import sv.edu.udb.www.CuponeriaUDB.entities.Ofertas;
import sv.edu.udb.www.CuponeriaUDB.entities.Rubros;
import sv.edu.udb.www.CuponeriaUDB.repositories.ClienteRepository;
import sv.edu.udb.www.CuponeriaUDB.repositories.CuponesRepository;
import sv.edu.udb.www.CuponeriaUDB.repositories.OfertasRepository;
import sv.edu.udb.www.CuponeriaUDB.repositories.RubroRepository;
import sv.edu.udb.www.CuponeriaUDB.repositories.UsuarioRepository;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	@Autowired
	@Qualifier("ClienteRepository")
	ClienteRepository clienteRepositiry;
	
	@Autowired
	@Qualifier("UsuarioRepository")
	UsuarioRepository usuarioRepositiry;
	
	@Autowired
	@Qualifier("CuponesRepository")
	CuponesRepository cuponesRepository;
	
	@Autowired
	@Qualifier("RubroRepository")
	RubroRepository rubroRepository;
	
	@Autowired
	@Qualifier("OfertasRepository")
	OfertasRepository ofertaRepository;
	
	@GetMapping("/inicio")
	public String inicioCliente(Model model) {
		return "clientes/inicio";
	
	}
	
	@GetMapping("/tiendaCupones")
	public String tiendaCupones(Model model) {
		model.addAttribute("listaRubros",  rubroRepository.findAll());
		model.addAttribute("listaOfertas",  ofertaRepository.findAll());
		return "clientes/comprarCupones";
	
	}
	@GetMapping("/filtrar")
	public String tiendaCuponesDos(@Valid @ModelAttribute("rubro") Rubros rubro, Model model) {
		model.addAttribute("listaRubros",  rubroRepository.findAll());
		//model.addAttribute("listaOfertas",  ofertaRepository.obtenerOfertasPorRubro(rubro.getIdRubro()));
		return "clientes/comprarCupones";
	
	}
	@GetMapping("/agregar/{id}")
	public String agregar(@PathVariable(name="id") String id, Model model) {
		model.addAttribute("oferta",  ofertaRepository.findById(Integer.parseInt(id)));
		return "clientes/tarjetaCompra";
	
	}
	@RequestMapping(value="/comprar", method=RequestMethod.POST)
	public String comprar(@RequestParam(name="id") String id, Model model) {
		Ofertas oferta = new Ofertas();
		oferta=ofertaRepository.findByIdOferta(Integer.parseInt(id));
		Cupones cupon = new Cupones();
		cupon.setCodigoCupo("OMG"+(int)(Math.random() * 8999999 + 1000000));
		
		Clientes cliente = new Clientes();
		cliente.setIdCliente(4);
		cupon.setClientes(cliente);
		cupon.setOfertas(oferta);
		Estadocupon estadoCupon = new Estadocupon();
		estadoCupon.setIdEstadoCupon(1);
		cupon.setEstadocupon(estadoCupon);
		
		cupon.setFechaCanje(null);
		cupon.setFechaCompra(new Date());
		
		cuponesRepository.save(cupon);
		model.addAttribute("exito",  "Cupón comprado exitosamente.");
		return "clientes/comprarCupones";
	
	}
}
