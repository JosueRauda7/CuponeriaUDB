package sv.edu.udb.www.CuponeriaUDB.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sv.edu.udb.www.CuponeriaUDB.entities.Usuarios;


@Repository("UsuarioRepository")
public interface UsuarioRepository extends JpaRepository<Usuarios, Integer>{
	
	public abstract boolean existsByIdUsuario(int iduser);
	
	public abstract Usuarios findTopByConfirmadoOrderByIdUsuarioDesc(boolean confirmado);

	public abstract Usuarios findByIdUsuario(int iduser);
	
<<<<<<< HEAD
	@Query("SELECT u FROM Usuarios u WHERE u.correo=?1 AND u.confirmado=1")
	public abstract Usuarios findByCorreoAndConfirmado(String correo);
=======
	public abstract Usuarios findByCorreo(String correo);
>>>>>>> 295df5adabd9b9f983723d28840c53ce8f2d9a21
}
