package sv.edu.udb.www.CuponeriaUDB.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sv.edu.udb.www.CuponeriaUDB.entities.Empleado;

@Repository("EmpleadoRepository")
public interface EmpleadoRepository extends JpaRepository<Empleado, String> {

}
