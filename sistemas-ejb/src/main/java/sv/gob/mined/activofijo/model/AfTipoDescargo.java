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
@Table(name = "AF_TIPO_DESCARGO", catalog = "", schema = "ACTIVO_FIJO_V2")
@NamedQueries({
    @NamedQuery(name = "AfTipoDescargo.findAll", query = "SELECT a FROM AfTipoDescargo a"),
    @NamedQuery(name = "AfTipoDescargo.findByIdTipoDescargo", query = "SELECT a FROM AfTipoDescargo a WHERE a.idTipoDescargo = :idTipoDescargo"),
    @NamedQuery(name = "AfTipoDescargo.findByNombreTipoDescargo", query = "SELECT a FROM AfTipoDescargo a WHERE a.nombreTipoDescargo = :nombreTipoDescargo"),
    @NamedQuery(name = "AfTipoDescargo.findByFechaAdicion", query = "SELECT a FROM AfTipoDescargo a WHERE a.fechaAdicion = :fechaAdicion"),
    @NamedQuery(name = "AfTipoDescargo.findByUsuarioAdicion", query = "SELECT a FROM AfTipoDescargo a WHERE a.usuarioAdicion = :usuarioAdicion"),
    @NamedQuery(name = "AfTipoDescargo.findByFechaModifica", query = "SELECT a FROM AfTipoDescargo a WHERE a.fechaModifica = :fechaModifica"),
    @NamedQuery(name = "AfTipoDescargo.findByUsuarioModifica", query = "SELECT a FROM AfTipoDescargo a WHERE a.usuarioModifica = :usuarioModifica")})
public class AfTipoDescargo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_TIPO_DESCARGO")
    private String idTipoDescargo;
    @Column(name = "NOMBRE_TIPO_DESCARGO")
    private String nombreTipoDescargo;
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

    public AfTipoDescargo() {
    }

    public AfTipoDescargo(String idTipoDescargo) {
        this.idTipoDescargo = idTipoDescargo;
    }

    public String getIdTipoDescargo() {
        return idTipoDescargo;
    }

    public void setIdTipoDescargo(String idTipoDescargo) {
        this.idTipoDescargo = idTipoDescargo;
    }

    public String getNombreTipoDescargo() {
        return nombreTipoDescargo;
    }

    public void setNombreTipoDescargo(String nombreTipoDescargo) {
        this.nombreTipoDescargo = nombreTipoDescargo;
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
        hash += (idTipoDescargo != null ? idTipoDescargo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfTipoDescargo)) {
            return false;
        }
        AfTipoDescargo other = (AfTipoDescargo) object;
        if ((this.idTipoDescargo == null && other.idTipoDescargo != null) || (this.idTipoDescargo != null && !this.idTipoDescargo.equals(other.idTipoDescargo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.app.activofijo.model.AfTipoDescargo[ idTipoDescargo=" + idTipoDescargo + " ]";
    }
    
}
