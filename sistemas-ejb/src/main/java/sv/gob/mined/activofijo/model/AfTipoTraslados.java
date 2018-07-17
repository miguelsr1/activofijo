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
@Table(name = "AF_TIPO_TRASLADOS", catalog = "", schema = "ACTIVO_FIJO_V2")
@NamedQueries({
    @NamedQuery(name = "AfTipoTraslados.findAll", query = "SELECT a FROM AfTipoTraslados a"),
    @NamedQuery(name = "AfTipoTraslados.findByIdTipoTraslado", query = "SELECT a FROM AfTipoTraslados a WHERE a.idTipoTraslado = :idTipoTraslado"),
    @NamedQuery(name = "AfTipoTraslados.findByNombreTipoTraslado", query = "SELECT a FROM AfTipoTraslados a WHERE a.nombreTipoTraslado = :nombreTipoTraslado"),
    @NamedQuery(name = "AfTipoTraslados.findByFechaAdicion", query = "SELECT a FROM AfTipoTraslados a WHERE a.fechaAdicion = :fechaAdicion"),
    @NamedQuery(name = "AfTipoTraslados.findByUsuarioAdicion", query = "SELECT a FROM AfTipoTraslados a WHERE a.usuarioAdicion = :usuarioAdicion"),
    @NamedQuery(name = "AfTipoTraslados.findByFechaModifica", query = "SELECT a FROM AfTipoTraslados a WHERE a.fechaModifica = :fechaModifica"),
    @NamedQuery(name = "AfTipoTraslados.findByUsuarioModifica", query = "SELECT a FROM AfTipoTraslados a WHERE a.usuarioModifica = :usuarioModifica")})
public class AfTipoTraslados implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_TIPO_TRASLADO")
    private Character idTipoTraslado;
    @Column(name = "NOMBRE_TIPO_TRASLADO")
    private String nombreTipoTraslado;
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

    public AfTipoTraslados() {
    }

    public AfTipoTraslados(Character idTipoTraslado) {
        this.idTipoTraslado = idTipoTraslado;
    }

    public Character getIdTipoTraslado() {
        return idTipoTraslado;
    }

    public void setIdTipoTraslado(Character idTipoTraslado) {
        this.idTipoTraslado = idTipoTraslado;
    }

    public String getNombreTipoTraslado() {
        return nombreTipoTraslado;
    }

    public void setNombreTipoTraslado(String nombreTipoTraslado) {
        this.nombreTipoTraslado = nombreTipoTraslado;
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
        hash += (idTipoTraslado != null ? idTipoTraslado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfTipoTraslados)) {
            return false;
        }
        AfTipoTraslados other = (AfTipoTraslados) object;
        if ((this.idTipoTraslado == null && other.idTipoTraslado != null) || (this.idTipoTraslado != null && !this.idTipoTraslado.equals(other.idTipoTraslado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.app.activofijo.model.AfTipoTraslados[ idTipoTraslado=" + idTipoTraslado + " ]";
    }
    
}
