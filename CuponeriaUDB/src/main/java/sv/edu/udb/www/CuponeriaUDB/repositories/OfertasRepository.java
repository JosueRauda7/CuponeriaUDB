package sv.edu.udb.www.CuponeriaUDB.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sv.edu.udb.www.CuponeriaUDB.entities.Administrador;
import sv.edu.udb.www.CuponeriaUDB.entities.Empresas;
import sv.edu.udb.www.CuponeriaUDB.entities.Ofertas;

@Repository("OfertasRepository")
public interface OfertasRepository extends JpaRepository<Ofertas, String>{
	@Query("SELECT o FROM Ofertas o WHERE o.empresas.usuarios.idUsuario =?1 and o.estadooferta.idEstadoOferta=?2 ")
	public abstract List<Ofertas> listaOfertas(int idestado,int estado);
}
