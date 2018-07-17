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
@Table(name = "AF_FORMA_ADQUISICION", catalog = "", schema = "ACTIVO_FIJO_V2")
@NamedQueries({
    @NamedQuery(name = "AfFormaAdquisicion.findAll", query = "SELECT a FROM AfFormaAdquisicion a"),
    @NamedQuery(name = "AfFormaAdquisicion.findByNombreFormaAdquisicion", query = "SELECT a FROM AfFormaAdquisicion a WHERE a.nombreFormaAdquisicion = :nombreFormaAdquisicion"),
    @NamedQuery(name = "AfFormaAdquisicion.findByFechaAdicion", query = "SELECT a FROM AfFormaAdquisicion a WHERE a.fechaAdicion = :fechaAdicion"),
    @NamedQuery(name = "AfFormaAdquisicion.findByUsuarioAdicion", query = "SELECT a FROM AfFormaAdquisicion a WHERE a.usuarioAdicion = :usuarioAdicion"),
    @NamedQuery(name = "AfFormaAdquisicion.findByFechaModifica", query = "SELECT a FROM AfFormaAdquisicion a WHERE a.fechaModifica = :fechaModifica"),
    @NamedQuery(name = "AfFormaAdquisicion.findByUsuarioModifica", query = "SELECT a FROM AfFormaAdquisicion a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AfFormaAdquisicion.findByIdFormaAdquisicion", query = "SELECT a FROM AfFormaAdquisicion a WHERE a.idFormaAdquisicion = :idFormaAdquisicion")})
public class AfFormaAdquisicion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "NOMBRE_FORMA_ADQUISICION")
    private String nombreFormaAdquisicion;
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
    @Column(name = "ID_FORMA_ADQUISICION")
    private Long idFormaAdquisicion;
    @OneToMany(mappedBy = "idFormaAdquisicion")
    private List<AfBienesDepreciables> afBienesDepreciablesList;

    public AfFormaAdquisicion() {
    }

    public AfFormaAdquisicion(Long idFormaAdquisicion) {
        this.idFormaAdquisicion = idFormaAdquisicion;
    }

    public String getNombreFormaAdquisicion() {
        return nombreFormaAdquisicion;
    }

    public void setNombreFormaAdquisicion(String nombreFormaAdquisicion) {
        this.nombreFormaAdquisicion = nombreFormaAdquisicion;
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

    public Long getIdFormaAdquisicion() {
        return idFormaAdquisicion;
    }

    public void setIdFormaAdquisicion(Long idFormaAdquisicion) {
        this.idFormaAdquisicion = idFormaAdquisicion;
    }

    public List<AfBienesDepreciables> getAfBienesDepreciablesList() {
        return afBienesDepreciablesList;
    }

    public void setAfBienesDepreciablesList(List<AfBienesDepreciables> afBienesDepreciablesList) {
        this.afBienesDepreciablesList = afBienesDepreciablesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFormaAdquisicion != null ? idFormaAdquisicion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfFormaAdquisicion)) {
            return false;
        }
        AfFormaAdquisicion other = (AfFormaAdquisicion) object;
        if ((this.idFormaAdquisicion == null && other.idFormaAdquisicion != null) || (this.idFormaAdquisicion != null && !this.idFormaAdquisicion.equals(other.idFormaAdquisicion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.app.activofijo.model.AfFormaAdquisicion[ idFormaAdquisicion=" + idFormaAdquisicion + " ]";
    }
    
}
