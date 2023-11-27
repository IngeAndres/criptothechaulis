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
@Table(name = "tipousuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipousuario.findAll", query = "SELECT t FROM Tipousuario t"),
    @NamedQuery(name = "Tipousuario.findByIdTipoUsuario", query = "SELECT t FROM Tipousuario t WHERE t.idTipoUsuario = :idTipoUsuario"),
    @NamedQuery(name = "Tipousuario.findByDenoTipoUsuario", query = "SELECT t FROM Tipousuario t WHERE t.denoTipoUsuario = :denoTipoUsuario"),
    @NamedQuery(name = "Tipousuario.findByNivelTipoUsuario", query = "SELECT t FROM Tipousuario t WHERE t.nivelTipoUsuario = :nivelTipoUsuario")})
public class Tipousuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdTipoUsuario")
    private Integer idTipoUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DenoTipoUsuario")
    private String denoTipoUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NivelTipoUsuario")
    private String nivelTipoUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoUsuario")
    private List<Usuario> usuarioList;

    public Tipousuario() {
    }

    public Tipousuario(Integer idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public Tipousuario(Integer idTipoUsuario, String denoTipoUsuario, String nivelTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
        this.denoTipoUsuario = denoTipoUsuario;
        this.nivelTipoUsuario = nivelTipoUsuario;
    }

    public Integer getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(Integer idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getDenoTipoUsuario() {
        return denoTipoUsuario;
    }

    public void setDenoTipoUsuario(String denoTipoUsuario) {
        this.denoTipoUsuario = denoTipoUsuario;
    }

    public String getNivelTipoUsuario() {
        return nivelTipoUsuario;
    }

    public void setNivelTipoUsuario(String nivelTipoUsuario) {
        this.nivelTipoUsuario = nivelTipoUsuario;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoUsuario != null ? idTipoUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipousuario)) {
            return false;
        }
        Tipousuario other = (Tipousuario) object;
        if ((this.idTipoUsuario == null && other.idTipoUsuario != null) || (this.idTipoUsuario != null && !this.idTipoUsuario.equals(other.idTipoUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Tipousuario[ idTipoUsuario=" + idTipoUsuario + " ]";
    }

}
