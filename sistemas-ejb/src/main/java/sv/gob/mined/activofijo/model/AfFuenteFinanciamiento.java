/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.activofijo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author JISTorres
 */
@Entity
@Table(name = "AF_FUENTE_FINANCIAMIENTO", catalog = "", schema = "ACTIVO_FIJO_V2")
@NamedQueries({
    @NamedQuery(name = "AfFuenteFinanciamiento.findAll", query = "SELECT a FROM AfFuenteFinanciamiento a"),
    @NamedQuery(name = "AfFuenteFinanciamiento.findByIdFuente", query = "SELECT a FROM AfFuenteFinanciamiento a WHERE a.idFuente = :idFuente"),
    @NamedQuery(name = "AfFuenteFinanciamiento.findByNombre", query = "SELECT a FROM AfFuenteFinanciamiento a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "AfFuenteFinanciamiento.findByAbreviatura", query = "SELECT a FROM AfFuenteFinanciamiento a WHERE a.abreviatura = :abreviatura"),
    @NamedQuery(name = "AfFuenteFinanciamiento.findByFechaAdicion", query = "SELECT a FROM AfFuenteFinanciamiento a WHERE a.fechaAdicion = :fechaAdicion"),
    @NamedQuery(name = "AfFuenteFinanciamiento.findByUsuarioAdicion", query = "SELECT a FROM AfFuenteFinanciamiento a WHERE a.usuarioAdicion = :usuarioAdicion"),
    @NamedQuery(name = "AfFuenteFinanciamiento.findByFechaModifica", query = "SELECT a FROM AfFuenteFinanciamiento a WHERE a.fechaModifica = :fechaModifica"),
    @NamedQuery(name = "AfFuenteFinanciamiento.findByUsuarioModifica", query = "SELECT a FROM AfFuenteFinanciamiento a WHERE a.usuarioModifica = :usuarioModifica")})
public class AfFuenteFinanciamiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_FUENTE")
    private Long idFuente;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "ABREVIATURA")
    private String abreviatura;
    @Column(name = "FECHA_ADICION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAdicion;
    @Column(name = "USUARIO_ADICION")
    private String usuarioAdicion;
    @Column(name = "FECHA_MODIFICA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModifica;
    @Column(name = "USUARIO_MODIFICA")
    private String usuarioModifica;

    public AfFuenteFinanciamiento() {
    }

    public AfFuenteFinanciamiento(Long idFuente) {
        this.idFuente = idFuente;
    }

    public Long getIdFuente() {
        return idFuente;
    }

    public void setIdFuente(Long idFuente) {
        this.idFuente = idFuente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public Date getFechaAdicion() {
        return fechaAdicion;
    }

    public void setFechaAdicion(Date fechaAdicion) {
        this.fechaAdicion = fechaAdicion;
    }

    public String getUsuarioAdicion() {
        return usuarioAdicion;
    }

    public void setUsuarioAdicion(String usuarioAdicion) {
        this.usuarioAdicion = usuarioAdicion;
    }

    public Date getFechaModifica() {
        return fechaModifica;
    }

    public void setFechaModifica(Date fechaModifica) {
        this.fechaModifica = fechaModifica;
    }

    public String getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(String usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFuente != null ? idFuente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfFuenteFinanciamiento)) {
            return false;
        }
        AfFuenteFinanciamiento other = (AfFuenteFinanciamiento) object;
        if ((this.idFuente == null && other.idFuente != null) || (this.idFuente != null && !this.idFuente.equals(other.idFuente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.app.activofijo.model.AfFuenteFinanciamiento[ idFuente=" + idFuente + " ]";
    }
    
}
