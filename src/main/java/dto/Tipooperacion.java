package dto;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ing. Andres Gomez
 */
@Entity
@Table(name = "tipooperacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipooperacion.findAll", query = "SELECT t FROM Tipooperacion t"),
    @NamedQuery(name = "Tipooperacion.findByIdTipoOperacion", query = "SELECT t FROM Tipooperacion t WHERE t.idTipoOperacion = :idTipoOperacion"),
    @NamedQuery(name = "Tipooperacion.findByDenoTipoOperacion", query = "SELECT t FROM Tipooperacion t WHERE t.denoTipoOperacion = :denoTipoOperacion"),
    @NamedQuery(name = "Tipooperacion.findByCategoriaTipoOperacion", query = "SELECT t FROM Tipooperacion t WHERE t.categoriaTipoOperacion = :categoriaTipoOperacion")})
public class Tipooperacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdTipoOperacion")
    private Integer idTipoOperacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "DenoTipoOperacion")
    private String denoTipoOperacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "CategoriaTipoOperacion")
    private String categoriaTipoOperacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoOperacion")
    private List<Operacionesotrascuentas> operacionesotrascuentasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoOperacion")
    private List<Operacionescuentaspropias> operacionescuentaspropiasList;

    public Tipooperacion() {
    }

    public Tipooperacion(Integer idTipoOperacion) {
        this.idTipoOperacion = idTipoOperacion;
    }

    public Tipooperacion(Integer idTipoOperacion, String denoTipoOperacion, String categoriaTipoOperacion) {
        this.idTipoOperacion = idTipoOperacion;
        this.denoTipoOperacion = denoTipoOperacion;
        this.categoriaTipoOperacion = categoriaTipoOperacion;
    }

    public Integer getIdTipoOperacion() {
        return idTipoOperacion;
    }

    public void setIdTipoOperacion(Integer idTipoOperacion) {
        this.idTipoOperacion = idTipoOperacion;
    }

    public String getDenoTipoOperacion() {
        return denoTipoOperacion;
    }

    public void setDenoTipoOperacion(String denoTipoOperacion) {
        this.denoTipoOperacion = denoTipoOperacion;
    }

    public String getCategoriaTipoOperacion() {
        return categoriaTipoOperacion;
    }

    public void setCategoriaTipoOperacion(String categoriaTipoOperacion) {
        this.categoriaTipoOperacion = categoriaTipoOperacion;
    }

    @XmlTransient
    public List<Operacionesotrascuentas> getOperacionesotrascuentasList() {
        return operacionesotrascuentasList;
    }

    public void setOperacionesotrascuentasList(List<Operacionesotrascuentas> operacionesotrascuentasList) {
        this.operacionesotrascuentasList = operacionesotrascuentasList;
    }

    @XmlTransient
    public List<Operacionescuentaspropias> getOperacionescuentaspropiasList() {
        return operacionescuentaspropiasList;
    }

    public void setOperacionescuentaspropiasList(List<Operacionescuentaspropias> operacionescuentaspropiasList) {
        this.operacionescuentaspropiasList = operacionescuentaspropiasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoOperacion != null ? idTipoOperacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipooperacion)) {
            return false;
        }
        Tipooperacion other = (Tipooperacion) object;
        if ((this.idTipoOperacion == null && other.idTipoOperacion != null) || (this.idTipoOperacion != null && !this.idTipoOperacion.equals(other.idTipoOperacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Tipooperacion[ idTipoOperacion=" + idTipoOperacion + " ]";
    }
    
}
