/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.activofijo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JISTorres
 */
@Entity
@Table(name = "AF_UNIDADES_ADMINISTRATIVAS", catalog = "", schema = "ACTIVO_FIJO_V2")
@NamedQueries({
    @NamedQuery(name = "AfUnidadesAdministrativas.findAll", query = "SELECT a FROM AfUnidadesAdministrativas a"),
    @NamedQuery(name = "AfUnidadesAdministrativas.findByUnidadActivoFijo", query = "SELECT a FROM AfUnidadesAdministrativas a WHERE a.afUnidadesAdministrativasPK.unidadActivoFijo = :unidadActivoFijo"),
    @NamedQuery(name = "AfUnidadesAdministrativas.findByCodigoUnidad", query = "SELECT a FROM AfUnidadesAdministrativas a WHERE a.afUnidadesAdministrativasPK.codigoUnidad = :codigoUnidad"),
    @NamedQuery(name = "AfUnidadesAdministrativas.findByNombreUnidad", query = "SELECT a FROM AfUnidadesAdministrativas a WHERE a.nombreUnidad = :nombreUnidad"),
    @NamedQuery(name = "AfUnidadesAdministrativas.findByDireccion", query = "SELECT a FROM AfUnidadesAdministrativas a WHERE a.direccion = :direccion"),
    @NamedQuery(name = "AfUnidadesAdministrativas.findByNombreDirector", query = "SELECT a FROM AfUnidadesAdministrativas a WHERE a.nombreDirector = :nombreDirector"),
    @NamedQuery(name = "AfUnidadesAdministrativas.findByCargoDirector", query = "SELECT a FROM AfUnidadesAdministrativas a WHERE a.cargoDirector = :cargoDirector"),
    @NamedQuery(name = "AfUnidadesAdministrativas.findByTelefono", query = "SELECT a FROM AfUnidadesAdministrativas a WHERE a.telefono = :telefono"),
    @NamedQuery(name = "AfUnidadesAdministrativas.findByNombreResponsable", query = "SELECT a FROM AfUnidadesAdministrativas a WHERE a.nombreResponsable = :nombreResponsable"),
    @NamedQuery(name = "AfUnidadesAdministrativas.findByFechaInventario", query = "SELECT a FROM AfUnidadesAdministrativas a WHERE a.fechaInventario = :fechaInventario"),
    @NamedQuery(name = "AfUnidadesAdministrativas.findByTipoUnidad", query = "SELECT a FROM AfUnidadesAdministrativas a WHERE a.tipoUnidad = :tipoUnidad"),
    @NamedQuery(name = "AfUnidadesAdministrativas.findByFechaAdicion", query = "SELECT a FROM AfUnidadesAdministrativas a WHERE a.fechaAdicion = :fechaAdicion"),
    @NamedQuery(name = "AfUnidadesAdministrativas.findByUsuarioAdicion", query = "SELECT a FROM AfUnidadesAdministrativas a WHERE a.usuarioAdicion = :usuarioAdicion"),
    @NamedQuery(name = "AfUnidadesAdministrativas.findByFechaModifica", query = "SELECT a FROM AfUnidadesAdministrativas a WHERE a.fechaModifica = :fechaModifica"),
    @NamedQuery(name = "AfUnidadesAdministrativas.findByUsuarioModifica", query = "SELECT a FROM AfUnidadesAdministrativas a WHERE a.usuarioModifica = :usuarioModifica")})
public class AfUnidadesAdministrativas implements Serializable {

    @EmbeddedId
    protected AfUnidadesAdministrativasPK afUnidadesAdministrativasPK;
    @Column(name = "NOMBRE_UNIDAD")
    private String nombreUnidad;
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "NOMBRE_DIRECTOR")
    private String nombreDirector;
    @Column(name = "CARGO_DIRECTOR")
    private String cargoDirector;
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "NOMBRE_RESPONSABLE")
    private String nombreResponsable;
    @Column(name = "FECHA_INVENTARIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInventario;
    @Basic(optional = false)
    @Column(name = "TIPO_UNIDAD")
    private String tipoUnidad;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "afUnidadesAdministrativas")
    private List<AfSeccionesUnidad> afSeccionesUnidadList;
    
    public AfUnidadesAdministrativas() {
    }

    public AfUnidadesAdministrativas(AfUnidadesAdministrativasPK afUnidadesAdministrativasPK) {
        this.afUnidadesAdministrativasPK = afUnidadesAdministrativasPK;
    }

    public AfUnidadesAdministrativas(AfUnidadesAdministrativasPK afUnidadesAdministrativasPK, String tipoUnidad) {
        this.afUnidadesAdministrativasPK = afUnidadesAdministrativasPK;
        this.tipoUnidad = tipoUnidad;
    }

    public AfUnidadesAdministrativas(String unidadActivoFijo, String codigoUnidad) {
        this.afUnidadesAdministrativasPK = new AfUnidadesAdministrativasPK(unidadActivoFijo, codigoUnidad);
    }

    public AfUnidadesAdministrativasPK getAfUnidadesAdministrativasPK() {
        return afUnidadesAdministrativasPK;
    }

    public void setAfUnidadesAdministrativasPK(AfUnidadesAdministrativasPK afUnidadesAdministrativasPK) {
        this.afUnidadesAdministrativasPK = afUnidadesAdministrativasPK;
    }

    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreDirector() {
        return nombreDirector;
    }

    public void setNombreDirector(String nombreDirector) {
        this.nombreDirector = nombreDirector;
    }

    public String getCargoDirector() {
        return cargoDirector;
    }

    public void setCargoDirector(String cargoDirector) {
        this.cargoDirector = cargoDirector;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    public Date getFechaInventario() {
        return fechaInventario;
    }

    public void setFechaInventario(Date fechaInventario) {
        this.fechaInventario = fechaInventario;
    }

    public String getTipoUnidad() {
        return tipoUnidad;
    }

    public void setTipoUnidad(String tipoUnidad) {
        this.tipoUnidad = tipoUnidad;
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

    public List<AfSeccionesUnidad> getAfSeccionesUnidadList() {
        return afSeccionesUnidadList;
    }

    public void setAfSeccionesUnidadList(List<AfSeccionesUnidad> afSeccionesUnidadList) {
        this.afSeccionesUnidadList = afSeccionesUnidadList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (afUnidadesAdministrativasPK != null ? afUnidadesAdministrativasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfUnidadesAdministrativas)) {
            return false;
        }
        AfUnidadesAdministrativas other = (AfUnidadesAdministrativas) object;
        if ((this.afUnidadesAdministrativasPK == null && other.afUnidadesAdministrativasPK != null) || (this.afUnidadesAdministrativasPK != null && !this.afUnidadesAdministrativasPK.equals(other.afUnidadesAdministrativasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreUnidad ;
    }
  
}
