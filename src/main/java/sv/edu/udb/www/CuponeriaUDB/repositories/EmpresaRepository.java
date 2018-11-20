package sv.edu.udb.www.CuponeriaUDB.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sv.edu.udb.www.CuponeriaUDB.entities.Empleado;
import sv.edu.udb.www.CuponeriaUDB.entities.Empresas;


@Repository("EmpresaRepository")
public interface EmpresaRepository extends JpaRepository<Empleado, String>{
	
	public abstract List<Empleado> findAllByOrderByNombreEmpleado();

}
