package sv.edu.udb.www.CuponeriaUDB.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sv.edu.udb.www.CuponeriaUDB.entities.Cupones;

@Repository("CuponesRepository")
public interface CuponesRepository extends JpaRepository<Cupones, String> {

	public abstract Cupones findAllByCodigoCupo(String codigo);

}
