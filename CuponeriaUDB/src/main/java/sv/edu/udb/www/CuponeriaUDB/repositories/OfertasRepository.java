package sv.edu.udb.www.CuponeriaUDB.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sv.edu.udb.www.CuponeriaUDB.entities.Ofertas;


@Repository("OfertasRepository")
public interface OfertasRepository extends JpaRepository<Ofertas, String>{
	
}
