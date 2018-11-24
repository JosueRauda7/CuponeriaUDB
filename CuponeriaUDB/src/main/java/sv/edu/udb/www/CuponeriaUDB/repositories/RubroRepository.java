package sv.edu.udb.www.CuponeriaUDB.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sv.edu.udb.www.CuponeriaUDB.entities.Rubros;

@Repository("RubroRepository")
public interface RubroRepository extends JpaRepository<Rubros, String>{
	
	public abstract Rubros findByIdRubro(Integer id);
	
	@Transactional
	@Modifying
	@Query("Delete From Rubros r where r.rubro=?1")
	public abstract void eliminarRubroPorId(Integer id);
}
