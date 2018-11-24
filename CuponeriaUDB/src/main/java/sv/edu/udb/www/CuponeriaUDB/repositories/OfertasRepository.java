package sv.edu.udb.www.CuponeriaUDB.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sv.edu.udb.www.CuponeriaUDB.entities.Ofertas;

@Repository("OfertasRepository")
public interface OfertasRepository extends JpaRepository<Ofertas, Integer> {
	
	public abstract Ofertas findByIdOferta(Integer id);
}
