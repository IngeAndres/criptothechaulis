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
@Table(name = "tipoinformacionbien")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipoinformacionbien.findAll", query = "SELECT t FROM Tipoinformacionbien t"),
    @NamedQuery(name = "Tipoinformacionbien.findByIdTipoInformacionBien", query = "SELECT t FROM Tipoinformacionbien t WHERE t.idTipoInformacionBien = :idTipoInformacionBien"),
    @NamedQuery(name = "Tipoinformacionbien.findByDenoTipoInformacionBien", query = "SELECT t FROM Tipoinformacionbien t WHERE t.denoTipoInformacionBien = :denoTipoInformacionBien")})
public class Tipoinformacionbien implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdTipoInformacionBien")
    private Integer idTipoInformacionBien;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "DenoTipoInformacionBien")
    private String denoTipoInformacionBien;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoInformacionBien")
    private List<Detalleprestamo> detalleprestamoList;

    public Tipoinformacionbien() {
    }

    public Tipoinformacionbien(Integer idTipoInformacionBien) {
        this.idTipoInformacionBien = idTipoInformacionBien;
    }

    public Tipoinformacionbien(Integer idTipoInformacionBien, String denoTipoInformacionBien) {
        this.idTipoInformacionBien = idTipoInformacionBien;
        this.denoTipoInformacionBien = denoTipoInformacionBien;
    }

    public Integer getIdTipoInformacionBien() {
        return idTipoInformacionBien;
    }

    public void setIdTipoInformacionBien(Integer idTipoInformacionBien) {
        this.idTipoInformacionBien = idTipoInformacionBien;
    }

    public String getDenoTipoInformacionBien() {
        return denoTipoInformacionBien;
    }

    public void setDenoTipoInformacionBien(String denoTipoInformacionBien) {
        this.denoTipoInformacionBien = denoTipoInformacionBien;
    }

    @XmlTransient
    public List<Detalleprestamo> getDetalleprestamoList() {
        return detalleprestamoList;
    }

    public void setDetalleprestamoList(List<Detalleprestamo> detalleprestamoList) {
        this.detalleprestamoList = detalleprestamoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoInformacionBien != null ? idTipoInformacionBien.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoinformacionbien)) {
            return false;
        }
        Tipoinformacionbien other = (Tipoinformacionbien) object;
        if ((this.idTipoInformacionBien == null && other.idTipoInformacionBien != null) || (this.idTipoInformacionBien != null && !this.idTipoInformacionBien.equals(other.idTipoInformacionBien))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Tipoinformacionbien[ idTipoInformacionBien=" + idTipoInformacionBien + " ]";
    }
    
}
