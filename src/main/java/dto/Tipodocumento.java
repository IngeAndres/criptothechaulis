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
@Table(name = "tipodocumento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipodocumento.findAll", query = "SELECT t FROM Tipodocumento t"),
    @NamedQuery(name = "Tipodocumento.findByIdTipoDocumento", query = "SELECT t FROM Tipodocumento t WHERE t.idTipoDocumento = :idTipoDocumento"),
    @NamedQuery(name = "Tipodocumento.findByDenoTipoDocumento", query = "SELECT t FROM Tipodocumento t WHERE t.denoTipoDocumento = :denoTipoDocumento")})
public class Tipodocumento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdTipoDocumento")
    private Integer idTipoDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DenoTipoDocumento")
    private String denoTipoDocumento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoDocumento")
    private List<Datospersonales> datospersonalesList;

    public Tipodocumento() {
    }

    public Tipodocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public Tipodocumento(Integer idTipoDocumento, String denoTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
        this.denoTipoDocumento = denoTipoDocumento;
    }

    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getDenoTipoDocumento() {
        return denoTipoDocumento;
    }

    public void setDenoTipoDocumento(String denoTipoDocumento) {
        this.denoTipoDocumento = denoTipoDocumento;
    }

    @XmlTransient
    public List<Datospersonales> getDatospersonalesList() {
        return datospersonalesList;
    }

    public void setDatospersonalesList(List<Datospersonales> datospersonalesList) {
        this.datospersonalesList = datospersonalesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoDocumento != null ? idTipoDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipodocumento)) {
            return false;
        }
        Tipodocumento other = (Tipodocumento) object;
        if ((this.idTipoDocumento == null && other.idTipoDocumento != null) || (this.idTipoDocumento != null && !this.idTipoDocumento.equals(other.idTipoDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Tipodocumento[ idTipoDocumento=" + idTipoDocumento + " ]";
    }
    
}
