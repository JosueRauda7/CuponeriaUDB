package sv.edu.udb.www.CuponeriaUDB.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import sv.edu.udb.www.CuponeriaUDB.entities.Cupones;
import sv.edu.udb.www.CuponeriaUDB.entities.Empleado;

@Repository("EmpleadoRepository")
public interface EmpleadoRepository extends JpaRepository<Empleado, String> {

	@Query("SELECT e FROM Empleado e WHERE e.empresas.codigoEmpresa=?1")
	public abstract List<Empleado> encontrarPorEmpresa(String codigo);
	
	public abstract boolean existsByIdEmpleado(int idEmpleado);
	
<<<<<<< HEAD
	@Query("SELECT e FROM Cupones e JOIN e.clientes cli JOIN e.ofertas ofer JOIN e.estadocupon esta WHERE e.codigoCupo = (?1)")
	public abstract List<Cupones> obtenerCupon(String codigo);
=======
	public abstract Empleado findByIdEmpleado(int idEmpleado);
>>>>>>> 4d93654979770ef9f8c090d81eef1833ec46fce2

}
