package dto;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuario.findByPassUsuario", query = "SELECT u FROM Usuario u WHERE u.passUsuario = :passUsuario"),
    @NamedQuery(name = "Usuario.findByAutenticacion", query = "SELECT u FROM Usuario u WHERE u.autenticacion = :autenticacion"),
    @NamedQuery(
            name = "Usuario.listar",
            query = "SELECT u.idUsuario, t.denoTipoDocumento, d.docuPersona, d.apPaPersona, d.apMaPersona, d.nombPersona "
            + "FROM Usuario u "
            + "JOIN u.idPersona d "
            + "JOIN d.idTipoDocumento t "
            + "WHERE u.idTipoUsuario = :idTipoUsuario"),
    @NamedQuery(
            name = "Usuario.obtenerUsuarioPorIdPersona",
            query = "SELECT d.idPersona, t.denoTipoDocumento, d.docuPersona, d.apPaPersona, d.apMaPersona, d.nombPersona, d.celuPersona, d.emailPersona "
            + "FROM Usuario u "
            + "JOIN u.idPersona d "
            + "JOIN d.idTipoDocumento t "
            + "WHERE u.idTipoUsuario = :idTipoUsuario AND d.idPersona = :idPersona"),
    @NamedQuery(
            name = "Usuario.obtenerNombresUsuario",
            query = "SELECT d.nombPersona, d.apPaPersona "
            + "FROM Usuario u "
            + "JOIN u.idPersona d "
            + "WHERE u.idUsuario = :idUsuario"
    )
})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "IdUsuario")
    private String idUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "PassUsuario")
    private String passUsuario;
    @Size(max = 16)
    @Column(name = "Autenticacion")
    private String autenticacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<Cuenta> cuentaList;
    @JoinColumn(name = "IdTipoUsuario", referencedColumnName = "IdTipoUsuario")
    @ManyToOne(optional = false)
    private Tipousuario idTipoUsuario;
    @JoinColumn(name = "IdPersona", referencedColumnName = "IdPersona")
    @ManyToOne(optional = false)
    private Datospersonales idPersona;

    public Usuario() {
    }

    public Usuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(String idUsuario, String passUsuario) {
        this.idUsuario = idUsuario;
        this.passUsuario = passUsuario;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
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

    @XmlTransient
    public List<Cuenta> getCuentaList() {
        return cuentaList;
    }

    public void setCuentaList(List<Cuenta> cuentaList) {
        this.cuentaList = cuentaList;
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
