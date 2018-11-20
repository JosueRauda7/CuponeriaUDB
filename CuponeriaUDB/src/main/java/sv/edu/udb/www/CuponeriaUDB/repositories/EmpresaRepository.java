package sv.edu.udb.www.CuponeriaUDB.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sv.edu.udb.www.CuponeriaUDB.entities.Empleado;
import sv.edu.udb.www.CuponeriaUDB.entities.Empresas;


@Repository("EmpresaRepository")
public interface EmpresaRepository extends JpaRepository<Empresas, String>{
	
	@Query("SELECT e FROM Empresas e WHERE e.usuarios.idUsuario=?1")
	public abstract Empresas encontrarPorUsuario(int id);
	
	public abstract List<Empresas> findAllByOrderByNombreEmpresa();



}
