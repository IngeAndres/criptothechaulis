package dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
    @NamedQuery(name = "Usuario.findByCodiUsua", query = "SELECT u FROM Usuario u WHERE u.codiUsua = :codiUsua"),
    @NamedQuery(name = "Usuario.findByLogiUsua", query = "SELECT u FROM Usuario u WHERE u.logiUsua = :logiUsua"),
    @NamedQuery(name = "Usuario.findByPassUsua", query = "SELECT u FROM Usuario u WHERE u.passUsua = :passUsua"),
    @NamedQuery(name = "Usuario.findByAuthUsua", query = "SELECT u FROM Usuario u WHERE u.authUsua = :authUsua"),
    @NamedQuery(name = "Usuario.validarUsua", query = "SELECT u FROM Usuario u WHERE u.logiUsua = :logiUsua AND u.passUsua = :passUsua")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "codiUsua")
    private String codiUsua;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "logiUsua")
    private String logiUsua;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "passUsua")
    private String passUsua;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "authUsua")
    private String authUsua;

    public Usuario() {
    }

    public Usuario(String codiUsua) {
        this.codiUsua = codiUsua;
    }

    public Usuario(String codiUsua, String logiUsua, String passUsua, String authUsua) {
        this.codiUsua = codiUsua;
        this.logiUsua = logiUsua;
        this.passUsua = passUsua;
        this.authUsua = authUsua;
    }

    public String getCodiUsua() {
        return codiUsua;
    }

    public void setCodiUsua(String codiUsua) {
        this.codiUsua = codiUsua;
    }

    public String getLogiUsua() {
        return logiUsua;
    }

    public void setLogiUsua(String logiUsua) {
        this.logiUsua = logiUsua;
    }

    public String getPassUsua() {
        return passUsua;
    }

    public void setPassUsua(String passUsua) {
        this.passUsua = passUsua;
    }

    public String getAuthUsua() {
        return authUsua;
    }

    public void setAuthUsua(String authUsua) {
        this.authUsua = authUsua;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiUsua != null ? codiUsua.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.codiUsua == null && other.codiUsua != null) || (this.codiUsua != null && !this.codiUsua.equals(other.codiUsua))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Usuario[ codiUsua=" + codiUsua + " ]";
    }

}
