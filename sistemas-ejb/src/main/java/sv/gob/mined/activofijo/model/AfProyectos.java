/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.activofijo.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author JISTorres
 */
@Entity
@Table(name = "AF_PROYECTOS", catalog = "", schema = "ACTIVO_FIJO_V2")
@NamedQueries({
    @NamedQuery(name = "AfProyectos.findAll", query = "SELECT a FROM AfProyectos a"),
    @NamedQuery(name = "AfProyectos.findByCodigoProyecto", query = "SELECT a FROM AfProyectos a WHERE a.codigoProyecto = :codigoProyecto"),
    @NamedQuery(name = "AfProyectos.findByNombreProyecto", query = "SELECT a FROM AfProyectos a WHERE a.nombreProyecto = :nombreProyecto"),
    @NamedQuery(name = "AfProyectos.findByFechaAdicion", query = "SELECT a FROM AfProyectos a WHERE a.fechaAdicion = :fechaAdicion"),
    @NamedQuery(name = "AfProyectos.findByUsuarioAdicion", query = "SELECT a FROM AfProyectos a WHERE a.usuarioAdicion = :usuarioAdicion"),
    @NamedQuery(name = "AfProyectos.findByFechaModifica", query = "SELECT a FROM AfProyectos a WHERE a.fechaModifica = :fechaModifica"),
    @NamedQuery(name = "AfProyectos.findByUsuarioModifica", query = "SELECT a FROM AfProyectos a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AfProyectos.findByIdProyecto", query = "SELECT a FROM AfProyectos a WHERE a.idProyecto = :idProyecto")})
public class AfProyectos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "CODIGO_PROYECTO")
    private String codigoProyecto;
    @Column(name = "NOMBRE_PROYECTO")
    private String nombreProyecto;
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
    @Id
    @Basic(optional = false)
    @Column(name = "ID_PROYECTO")
    private Long idProyecto;

    public AfProyectos() {
    }

    public AfProyectos(Long idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getCodigoProyecto() {
        return codigoProyecto;
    }

    public void setCodigoProyecto(String codigoProyecto) {
        this.codigoProyecto = codigoProyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
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

    public Long getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Long idProyecto) {
        this.idProyecto = idProyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProyecto != null ? idProyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfProyectos)) {
            return false;
        }
        AfProyectos other = (AfProyectos) object;
        if ((this.idProyecto == null && other.idProyecto != null) || (this.idProyecto != null && !this.idProyecto.equals(other.idProyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.app.activofijo.model.AfProyectos[ idProyecto=" + idProyecto + " ]";
    }
    
}
