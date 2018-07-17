/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.activofijo.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author JISTorres
 */
@Entity
@Table(name = "AF_SECCIONES_UNIDAD", catalog = "", schema = "ACTIVO_FIJO_V2")
@NamedQueries({
    @NamedQuery(name = "AfSeccionesUnidad.findAll", query = "SELECT a FROM AfSeccionesUnidad a"),
    @NamedQuery(name = "AfSeccionesUnidad.findByCodigoSeccion", query = "SELECT a FROM AfSeccionesUnidad a WHERE a.codigoSeccion = :codigoSeccion"),
    @NamedQuery(name = "AfSeccionesUnidad.findByNombreSeccion", query = "SELECT a FROM AfSeccionesUnidad a WHERE a.nombreSeccion = :nombreSeccion"),
    @NamedQuery(name = "AfSeccionesUnidad.findByTipoUnidad", query = "SELECT a FROM AfSeccionesUnidad a WHERE a.tipoUnidad = :tipoUnidad"),
    @NamedQuery(name = "AfSeccionesUnidad.findByIdSeccion", query = "SELECT a FROM AfSeccionesUnidad a WHERE a.idSeccion = :idSeccion")})
public class AfSeccionesUnidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "CODIGO_SECCION")
    private String codigoSeccion;
    @Column(name = "NOMBRE_SECCION")
    private String nombreSeccion;
    @Column(name = "TIPO_UNIDAD")
    private String tipoUnidad;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_SECCION")
    private Long idSeccion;
    @JoinColumns({
        @JoinColumn(name = "CODIGO_UNIDAD", referencedColumnName = "CODIGO_UNIDAD"),
        @JoinColumn(name = "UNIDAD_ACTIVO_FIJO", referencedColumnName = "UNIDAD_ACTIVO_FIJO")})
    @ManyToOne(optional = false)
    private AfUnidadesAdministrativas afUnidadesAdministrativas;

    public AfSeccionesUnidad() {
    }

    public AfSeccionesUnidad(Long idSeccion) {
        this.idSeccion = idSeccion;
    }

    public AfSeccionesUnidad(Long idSeccion, String codigoSeccion) {
        this.idSeccion = idSeccion;
        this.codigoSeccion = codigoSeccion;
    }

    public String getCodigoSeccion() {
        return codigoSeccion;
    }

    public void setCodigoSeccion(String codigoSeccion) {
        this.codigoSeccion = codigoSeccion;
    }

    public String getNombreSeccion() {
        return nombreSeccion;
    }

    public void setNombreSeccion(String nombreSeccion) {
        this.nombreSeccion = nombreSeccion;
    }

    public String getTipoUnidad() {
        return tipoUnidad;
    }

    public void setTipoUnidad(String tipoUnidad) {
        this.tipoUnidad = tipoUnidad;
    }

    public Long getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(Long idSeccion) {
        this.idSeccion = idSeccion;
    }

    public AfUnidadesAdministrativas getAfUnidadesAdministrativas() {
        return afUnidadesAdministrativas;
    }

    public void setAfUnidadesAdministrativas(AfUnidadesAdministrativas afUnidadesAdministrativas) {
        this.afUnidadesAdministrativas = afUnidadesAdministrativas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSeccion != null ? idSeccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfSeccionesUnidad)) {
            return false;
        }
        AfSeccionesUnidad other = (AfSeccionesUnidad) object;
        if ((this.idSeccion == null && other.idSeccion != null) || (this.idSeccion != null && !this.idSeccion.equals(other.idSeccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.app.activofijo.model.AfSeccionesUnidad[ idSeccion=" + idSeccion + " ]";
    }
    
}
