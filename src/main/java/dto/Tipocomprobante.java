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
@Table(name = "tipocomprobante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipocomprobante.findAll", query = "SELECT t FROM Tipocomprobante t"),
    @NamedQuery(name = "Tipocomprobante.findByIdTipoComprobante", query = "SELECT t FROM Tipocomprobante t WHERE t.idTipoComprobante = :idTipoComprobante"),
    @NamedQuery(name = "Tipocomprobante.findByDenoTipoComprobante", query = "SELECT t FROM Tipocomprobante t WHERE t.denoTipoComprobante = :denoTipoComprobante")})
public class Tipocomprobante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdTipoComprobante")
    private Integer idTipoComprobante;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "DenoTipoComprobante")
    private String denoTipoComprobante;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoComprobante")
    private List<Prestamo> prestamoList;

    public Tipocomprobante() {
    }

    public Tipocomprobante(Integer idTipoComprobante) {
        this.idTipoComprobante = idTipoComprobante;
    }

    public Tipocomprobante(Integer idTipoComprobante, String denoTipoComprobante) {
        this.idTipoComprobante = idTipoComprobante;
        this.denoTipoComprobante = denoTipoComprobante;
    }

    public Integer getIdTipoComprobante() {
        return idTipoComprobante;
    }

    public void setIdTipoComprobante(Integer idTipoComprobante) {
        this.idTipoComprobante = idTipoComprobante;
    }

    public String getDenoTipoComprobante() {
        return denoTipoComprobante;
    }

    public void setDenoTipoComprobante(String denoTipoComprobante) {
        this.denoTipoComprobante = denoTipoComprobante;
    }

    @XmlTransient
    public List<Prestamo> getPrestamoList() {
        return prestamoList;
    }

    public void setPrestamoList(List<Prestamo> prestamoList) {
        this.prestamoList = prestamoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoComprobante != null ? idTipoComprobante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipocomprobante)) {
            return false;
        }
        Tipocomprobante other = (Tipocomprobante) object;
        if ((this.idTipoComprobante == null && other.idTipoComprobante != null) || (this.idTipoComprobante != null && !this.idTipoComprobante.equals(other.idTipoComprobante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Tipocomprobante[ idTipoComprobante=" + idTipoComprobante + " ]";
    }
    
}
