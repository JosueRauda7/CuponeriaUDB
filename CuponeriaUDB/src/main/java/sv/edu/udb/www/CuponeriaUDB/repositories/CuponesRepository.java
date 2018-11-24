package sv.edu.udb.www.CuponeriaUDB.repositories;	

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

import javax.transaction.Transactional;

import sv.edu.udb.www.CuponeriaUDB.entities.Cupones;

@Repository("CuponesRepository")
public interface CuponesRepository extends JpaRepository<Cupones, String> {

	//@Query("SELECT e FROM Cupones e INNER JOIN e.estadocupon.idEstadoCupon WHERE e.codigoCupo=?1")
	//public abstract Cupones obtenerCupon(String codigo);
	
	public abstract Cupones findAllByCodigoCupo(String codigo);	
	
	//public abstract List<Cupones> obtenerCupon(String codigo);
	
	@Transactional
	@Query("SELECT c FROM Cupones c INNER JOIN c.clientes cl  WHERE cl.idCliente=?1 AND c.estadocupon.idEstadoCupon=?2")
	public abstract List<Cupones> listarCuponesPorCliente(Integer idCliente,Integer idEstadoCupon);
	
	public abstract Cupones findByCodigoCupo(String codigo);
	
	@Transactional
	@Modifying
	@Query("UPDATE Cupones c SET c.estadocupon.idEstadoCupon=2 WHERE c.codigoCupo=?1")
	public abstract Integer canjearCupon(String codigo);

}
