package sv.edu.udb.www.CuponeriaUDB.repositories;

<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
=======
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

>>>>>>> 81e0b305640e3391be9eb9c5adf09117b54024f2
import sv.edu.udb.www.CuponeriaUDB.entities.Empleado;

@Repository("EmpleadoRepository")
public interface EmpleadoRepository extends JpaRepository<Empleado, String> {
<<<<<<< HEAD

=======
	
	@Query("SELECT e FROM Empleado e WHERE e.empresas.codigoEmpresa=?1")
	public abstract List<Empleado> encontrarPorEmpresa(String codigo);
>>>>>>> 81e0b305640e3391be9eb9c5adf09117b54024f2
}
