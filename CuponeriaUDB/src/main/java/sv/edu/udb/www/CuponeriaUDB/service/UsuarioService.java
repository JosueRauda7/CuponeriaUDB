package sv.edu.udb.www.CuponeriaUDB.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sv.edu.udb.www.CuponeriaUDB.repositories.UsuarioRepository;

import sv.edu.udb.www.CuponeriaUDB.entities.Usuarios;


@Service("usuarioService")
public class UsuarioService implements UserDetailsService {
	
	@Autowired
	@Qualifier("UsuarioRepository")
	UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuarios usuario = usuarioRepository.findByCorreoAndConfirmado(username);
		List<GrantedAuthority> lista = new ArrayList<>();
		lista.add(new SimpleGrantedAuthority(usuario.getTipousuario().getTipoUsuario()));
		return new User(username, usuario.getContrasena(), lista);
	}
}
