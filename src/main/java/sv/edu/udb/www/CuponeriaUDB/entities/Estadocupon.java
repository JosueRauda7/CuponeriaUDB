package sv.edu.udb.www.CuponeriaUDB.entities;
// Generated 11-17-2018 11:30:29 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Estadocupon generated by hbm2java
 */
@Entity
@Table(name="estadocupon"
    ,catalog="bddpoo"
)
public class Estadocupon  implements java.io.Serializable {


     private Integer idEstadoCupon;
     private String estado;
     private Set<Cupones> cuponeses = new HashSet<Cupones>(0);

    public Estadocupon() {
    }

	
    public Estadocupon(String estado) {
        this.estado = estado;
    }
    public Estadocupon(String estado, Set<Cupones> cuponeses) {
       this.estado = estado;
       this.cuponeses = cuponeses;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="IdEstadoCupon", unique=true, nullable=false)
    public Integer getIdEstadoCupon() {
        return this.idEstadoCupon;
    }
    
    public void setIdEstadoCupon(Integer idEstadoCupon) {
        this.idEstadoCupon = idEstadoCupon;
    }

    
    @Column(name="Estado", nullable=false, length=10)
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="estadocupon")
    public Set<Cupones> getCuponeses() {
        return this.cuponeses;
    }
    
    public void setCuponeses(Set<Cupones> cuponeses) {
        this.cuponeses = cuponeses;
    }




}


