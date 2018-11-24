package sv.edu.udb.www.CuponeriaUDB.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sv.edu.udb.www.CuponeriaUDB.entities.Clientes;
import sv.edu.udb.www.CuponeriaUDB.entities.Usuarios;

@Repository("ClienteRepository")
public interface ClienteRepository extends JpaRepository<Clientes, String>{

}
