package sv.edu.udb.www.CuponeriaUDB.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sv.edu.udb.www.CuponeriaUDB.entities.Usuarios;


@Repository("UsuarioRepository")
public interface UsuarioRepository extends JpaRepository<Usuarios, Integer>{
	
	public abstract boolean existsByIdUsuario(int iduser);
	
	public abstract Usuarios findTopByConfirmadoOrderByIdUsuarioDesc(boolean confirmado);

	public abstract Usuarios findByIdUsuario(int iduser);
}
