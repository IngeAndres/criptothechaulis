package dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ing. Andres Gomez
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuario.findByDenoUsuario", query = "SELECT u FROM Usuario u WHERE u.denoUsuario = :denoUsuario"),
    @NamedQuery(name = "Usuario.findByPassUsuario", query = "SELECT u FROM Usuario u WHERE u.passUsuario = :passUsuario"),
    @NamedQuery(name = "Usuario.findByAutenticacion", query = "SELECT u FROM Usuario u WHERE u.autenticacion = :autenticacion")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdUsuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DenoUsuario")
    private String denoUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "PassUsuario")
    private String passUsuario;
    @Size(max = 16)
    @Column(name = "Autenticacion")
    private String autenticacion;
    @JoinColumn(name = "IdTipoUsuario", referencedColumnName = "IdTipoUsuario")
    @ManyToOne(optional = false)
    private Tipousuario idTipoUsuario;
    @JoinColumn(name = "IdPersona", referencedColumnName = "IdPersona")
    @ManyToOne(optional = false)
    private Datospersonales idPersona;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(Integer idUsuario, String denoUsuario, String passUsuario) {
        this.idUsuario = idUsuario;
        this.denoUsuario = denoUsuario;
        this.passUsuario = passUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDenoUsuario() {
        return denoUsuario;
    }

    public void setDenoUsuario(String denoUsuario) {
        this.denoUsuario = denoUsuario;
    }

    public String getPassUsuario() {
        return passUsuario;
    }

    public void setPassUsuario(String passUsuario) {
        this.passUsuario = passUsuario;
    }

    public String getAutenticacion() {
        return autenticacion;
    }

    public void setAutenticacion(String autenticacion) {
        this.autenticacion = autenticacion;
    }

    public Tipousuario getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(Tipousuario idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public Datospersonales getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Datospersonales idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Usuario[ idUsuario=" + idUsuario + " ]";
    }

}
