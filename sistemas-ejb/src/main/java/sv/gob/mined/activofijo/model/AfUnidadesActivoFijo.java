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
@Table(name = "AF_UNIDADES_ACTIVO_FIJO", catalog = "", schema = "ACTIVO_FIJO_V2")
@NamedQueries({
    @NamedQuery(name = "AfUnidadesActivoFijo.findAll", query = "SELECT a FROM AfUnidadesActivoFijo a"),
    @NamedQuery(name = "AfUnidadesActivoFijo.findByUnidadActivoFijo", query = "SELECT a FROM AfUnidadesActivoFijo a WHERE a.unidadActivoFijo = :unidadActivoFijo"),
    @NamedQuery(name = "AfUnidadesActivoFijo.findByNombreUnidadAf", query = "SELECT a FROM AfUnidadesActivoFijo a WHERE a.nombreUnidadAf = :nombreUnidadAf"),
    @NamedQuery(name = "AfUnidadesActivoFijo.findByCodigoDepartamento", query = "SELECT a FROM AfUnidadesActivoFijo a WHERE a.codigoDepartamento = :codigoDepartamento"),
    @NamedQuery(name = "AfUnidadesActivoFijo.findByEsDepartamental", query = "SELECT a FROM AfUnidadesActivoFijo a WHERE a.esDepartamental = :esDepartamental"),
    @NamedQuery(name = "AfUnidadesActivoFijo.findByFechaAdicion", query = "SELECT a FROM AfUnidadesActivoFijo a WHERE a.fechaAdicion = :fechaAdicion"),
    @NamedQuery(name = "AfUnidadesActivoFijo.findByUsuarioAdicion", query = "SELECT a FROM AfUnidadesActivoFijo a WHERE a.usuarioAdicion = :usuarioAdicion"),
    @NamedQuery(name = "AfUnidadesActivoFijo.findByFechaModifica", query = "SELECT a FROM AfUnidadesActivoFijo a WHERE a.fechaModifica = :fechaModifica"),
    @NamedQuery(name = "AfUnidadesActivoFijo.findByUsuarioModifica", query = "SELECT a FROM AfUnidadesActivoFijo a WHERE a.usuarioModifica = :usuarioModifica")})
public class AfUnidadesActivoFijo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "UNIDAD_ACTIVO_FIJO")
    private String unidadActivoFijo;
    @Column(name = "NOMBRE_UNIDAD_AF")
    private String nombreUnidadAf;
    @Column(name = "CODIGO_DEPARTAMENTO")
    private String codigoDepartamento;
    @Column(name = "ES_DEPARTAMENTAL")
    private Character esDepartamental;
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

    public AfUnidadesActivoFijo() {
    }

    public AfUnidadesActivoFijo(String unidadActivoFijo) {
        this.unidadActivoFijo = unidadActivoFijo;
    }

    public String getUnidadActivoFijo() {
        return unidadActivoFijo;
    }

    public void setUnidadActivoFijo(String unidadActivoFijo) {
        this.unidadActivoFijo = unidadActivoFijo;
    }

    public String getNombreUnidadAf() {
        return nombreUnidadAf;
    }

    public void setNombreUnidadAf(String nombreUnidadAf) {
        this.nombreUnidadAf = nombreUnidadAf;
    }

    public String getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public Character getEsDepartamental() {
        return esDepartamental;
    }

    public void setEsDepartamental(Character esDepartamental) {
        this.esDepartamental = esDepartamental;
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
        hash += (unidadActivoFijo != null ? unidadActivoFijo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfUnidadesActivoFijo)) {
            return false;
        }
        AfUnidadesActivoFijo other = (AfUnidadesActivoFijo) object;
        if ((this.unidadActivoFijo == null && other.unidadActivoFijo != null) || (this.unidadActivoFijo != null && !this.unidadActivoFijo.equals(other.unidadActivoFijo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return unidadActivoFijo + " "+ nombreUnidadAf;
    }
    
}
