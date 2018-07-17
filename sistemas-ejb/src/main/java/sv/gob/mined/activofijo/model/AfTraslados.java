/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.activofijo.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author JISTorres
 */
@Entity
@Table(name = "AF_TRASLADOS", catalog = "", schema = "ACTIVO_FIJO_V2")
@NamedQueries({
    @NamedQuery(name = "AfTraslados.findAll", query = "SELECT a FROM AfTraslados a"),
    @NamedQuery(name = "AfTraslados.findByIdTraslado", query = "SELECT a FROM AfTraslados a WHERE a.idTraslado = :idTraslado"),
    @NamedQuery(name = "AfTraslados.findByUnidadAfOrigen", query = "SELECT a FROM AfTraslados a WHERE a.unidadAfOrigen = :unidadAfOrigen"),
    @NamedQuery(name = "AfTraslados.findByCodigoUnidadOrigen", query = "SELECT a FROM AfTraslados a WHERE a.codigoUnidadOrigen = :codigoUnidadOrigen"),
    @NamedQuery(name = "AfTraslados.findByCodigoTraslado", query = "SELECT a FROM AfTraslados a WHERE a.codigoTraslado = :codigoTraslado"),
    @NamedQuery(name = "AfTraslados.findByUnidadAfDest", query = "SELECT a FROM AfTraslados a WHERE a.unidadAfDest = :unidadAfDest"),
    @NamedQuery(name = "AfTraslados.findByCodigoUnidadDest", query = "SELECT a FROM AfTraslados a WHERE a.codigoUnidadDest = :codigoUnidadDest"),
    @NamedQuery(name = "AfTraslados.findByFechaTraslado", query = "SELECT a FROM AfTraslados a WHERE a.fechaTraslado = :fechaTraslado"),
    @NamedQuery(name = "AfTraslados.findByIdTipoTraslado", query = "SELECT a FROM AfTraslados a WHERE a.idTipoTraslado = :idTipoTraslado"),
    @NamedQuery(name = "AfTraslados.findByNombreAutoriza", query = "SELECT a FROM AfTraslados a WHERE a.nombreAutoriza = :nombreAutoriza"),
    @NamedQuery(name = "AfTraslados.findByNombreRecibe", query = "SELECT a FROM AfTraslados a WHERE a.nombreRecibe = :nombreRecibe"),
    @NamedQuery(name = "AfTraslados.findByNombreReprAf", query = "SELECT a FROM AfTraslados a WHERE a.nombreReprAf = :nombreReprAf"),
    @NamedQuery(name = "AfTraslados.findByEstado", query = "SELECT a FROM AfTraslados a WHERE a.estado = :estado"),
    @NamedQuery(name = "AfTraslados.findByUsuarioCrea", query = "SELECT a FROM AfTraslados a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AfTraslados.findByFechaCreacion", query = "SELECT a FROM AfTraslados a WHERE a.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "AfTraslados.findByFechaSolicitud", query = "SELECT a FROM AfTraslados a WHERE a.fechaSolicitud = :fechaSolicitud"),
    @NamedQuery(name = "AfTraslados.findByObservacion", query = "SELECT a FROM AfTraslados a WHERE a.observacion = :observacion"),
    @NamedQuery(name = "AfTraslados.findByCargoRecibe", query = "SELECT a FROM AfTraslados a WHERE a.cargoRecibe = :cargoRecibe"),
    @NamedQuery(name = "AfTraslados.findByCargoAutoriza", query = "SELECT a FROM AfTraslados a WHERE a.cargoAutoriza = :cargoAutoriza"),
    @NamedQuery(name = "AfTraslados.findByUsuarioModifica", query = "SELECT a FROM AfTraslados a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AfTraslados.findByFechaAutoriza", query = "SELECT a FROM AfTraslados a WHERE a.fechaAutoriza = :fechaAutoriza"),
    @NamedQuery(name = "AfTraslados.findByFechaModifica", query = "SELECT a FROM AfTraslados a WHERE a.fechaModifica = :fechaModifica"),
   })
public class AfTraslados implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_TRASLADO")
     @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_TRASLADO")
    @SequenceGenerator(name="SEQ_TRASLADO", sequenceName="SEQ_TRASLADO",initialValue = 1,allocationSize = 1)
    private Long idTraslado;
    @Basic(optional = false)
    @Column(name = "UNIDAD_AF_ORIGEN")
    private String unidadAfOrigen;
    @Column(name = "CODIGO_UNIDAD_ORIGEN")
    private String codigoUnidadOrigen;
    @Column(name = "CODIGO_TRASLADO")
    private String codigoTraslado;
    @Column(name = "UNIDAD_AF_DEST")
    private String unidadAfDest;
    @Column(name = "CODIGO_UNIDAD_DEST")
    private String codigoUnidadDest;
    @Column(name = "FECHA_TRASLADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaTraslado;
    @Column(name = "ID_TIPO_TRASLADO")
    private Character idTipoTraslado;
    @Column(name = "NOMBRE_AUTORIZA")
    private String nombreAutoriza;
    @Column(name = "NOMBRE_RECIBE")
    private String nombreRecibe;
    @Column(name = "NOMBRE_REPR_AF")
    private String nombreReprAf;
    @Column(name = "ESTADO")
    private Character estado;
    @Column(name = "USUARIO_CREA")
    private String usuarioCrea;
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FECHA_SOLICITUD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolicitud;
    @Column(name = "OBSERVACION")
    private String observacion;
    @Column(name = "CARGO_RECIBE")
    private String cargoRecibe;
    @Column(name = "CARGO_AUTORIZA")
    private String cargoAutoriza;
    @Column(name = "USUARIO_MODIFICA")
    private String usuarioModifica;
    @Column(name = "FECHA_AUTORIZA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAutoriza;
    @Column(name = "FECHA_MODIFICA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModifica;
  
    public AfTraslados() {
    }

    public AfTraslados(Long idTraslado) {
        this.idTraslado = idTraslado;
    }

    public AfTraslados(Long idTraslado, String unidadAfOrigen) {
        this.idTraslado = idTraslado;
        this.unidadAfOrigen = unidadAfOrigen;
    }

    public Long getIdTraslado() {
        return idTraslado;
    }

    public void setIdTraslado(Long idTraslado) {
        this.idTraslado = idTraslado;
    }

    public String getUnidadAfOrigen() {
        return unidadAfOrigen;
    }

    public void setUnidadAfOrigen(String unidadAfOrigen) {
        this.unidadAfOrigen = unidadAfOrigen;
    }

    public String getCodigoUnidadOrigen() {
        return codigoUnidadOrigen;
    }

    public void setCodigoUnidadOrigen(String codigoUnidadOrigen) {
        this.codigoUnidadOrigen = codigoUnidadOrigen;
    }

    public String getCodigoTraslado() {
        return codigoTraslado;
    }

    public void setCodigoTraslado(String codigoTraslado) {
        this.codigoTraslado = codigoTraslado;
    }

    public String getUnidadAfDest() {
        return unidadAfDest;
    }

    public void setUnidadAfDest(String unidadAfDest) {
        this.unidadAfDest = unidadAfDest;
    }

    public String getCodigoUnidadDest() {
        return codigoUnidadDest;
    }

    public void setCodigoUnidadDest(String codigoUnidadDest) {
        this.codigoUnidadDest = codigoUnidadDest;
    }

    public Date getFechaTraslado() {
        return fechaTraslado;
    }

    public void setFechaTraslado(Date fechaTraslado) {
        this.fechaTraslado = fechaTraslado;
    }

    public Character getIdTipoTraslado() {
        return idTipoTraslado;
    }

    public void setIdTipoTraslado(Character idTipoTraslado) {
        this.idTipoTraslado = idTipoTraslado;
    }

    public String getNombreAutoriza() {
        return nombreAutoriza;
    }

    public void setNombreAutoriza(String nombreAutoriza) {
        this.nombreAutoriza = nombreAutoriza;
    }

    public String getNombreRecibe() {
        return nombreRecibe;
    }

    public void setNombreRecibe(String nombreRecibe) {
        this.nombreRecibe = nombreRecibe;
    }

    public String getNombreReprAf() {
        return nombreReprAf;
    }

    public void setNombreReprAf(String nombreReprAf) {
        this.nombreReprAf = nombreReprAf;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

   

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

   
    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getCargoRecibe() {
        return cargoRecibe;
    }

    public void setCargoRecibe(String cargoRecibe) {
        this.cargoRecibe = cargoRecibe;
    }

    public String getCargoAutoriza() {
        return cargoAutoriza;
    }

    public void setCargoAutoriza(String cargoAutoriza) {
        this.cargoAutoriza = cargoAutoriza;
    }

    public String getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(String usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    public Date getFechaAutoriza() {
        return fechaAutoriza;
    }

    public void setFechaAutoriza(Date fechaAutoriza) {
        this.fechaAutoriza = fechaAutoriza;
    }

 

    public Date getFechaModifica() {
        return fechaModifica;
    }

    public void setFechaModifica(Date fechaModifica) {
        this.fechaModifica = fechaModifica;
    }

      @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTraslado != null ? idTraslado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfTraslados)) {
            return false;
        }
        AfTraslados other = (AfTraslados) object;
        if ((this.idTraslado == null && other.idTraslado != null) || (this.idTraslado != null && !this.idTraslado.equals(other.idTraslado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.app.activofijo.model.AfTraslados[ idTraslado=" + idTraslado + " ]";
    }
    
}
