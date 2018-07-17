/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.activofijo.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author JISTorres
 */
@Entity
@Table(name = "AF_DESCARGOS", catalog = "", schema = "ACTIVO_FIJO_V2")
@NamedQueries({
    @NamedQuery(name = "AfDescargos.findAll", query = "SELECT a FROM AfDescargos a"),
    @NamedQuery(name = "AfDescargos.findByUnidadActivoFijo", query = "SELECT a FROM AfDescargos a WHERE a.unidadActivoFijo = :unidadActivoFijo"),
    @NamedQuery(name = "AfDescargos.findByCodigoDescargo", query = "SELECT a FROM AfDescargos a WHERE a.codigoDescargo = :codigoDescargo"),
    @NamedQuery(name = "AfDescargos.findByFechaDescargo", query = "SELECT a FROM AfDescargos a WHERE a.fechaDescargo = :fechaDescargo"),
    @NamedQuery(name = "AfDescargos.findByNombreCoordAdm", query = "SELECT a FROM AfDescargos a WHERE a.nombreCoordAdm = :nombreCoordAdm"),
    @NamedQuery(name = "AfDescargos.findByNombreEncargadoAf", query = "SELECT a FROM AfDescargos a WHERE a.nombreEncargadoAf = :nombreEncargadoAf"),
    @NamedQuery(name = "AfDescargos.findByNombreDirector", query = "SELECT a FROM AfDescargos a WHERE a.nombreDirector = :nombreDirector"),
    @NamedQuery(name = "AfDescargos.findByEstado", query = "SELECT a FROM AfDescargos a WHERE a.estado = :estado"),
    @NamedQuery(name = "AfDescargos.findByUsuarioCrea", query = "SELECT a FROM AfDescargos a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AfDescargos.findByFechaCreacion", query = "SELECT a FROM AfDescargos a WHERE a.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "AfDescargos.findByFechaFallo", query = "SELECT a FROM AfDescargos a WHERE a.fechaFallo = :fechaFallo"),
    @NamedQuery(name = "AfDescargos.findByIdDescargoCe", query = "SELECT a FROM AfDescargos a WHERE a.idDescargoCe = :idDescargoCe"),
    @NamedQuery(name = "AfDescargos.findByNombreAutoriza", query = "SELECT a FROM AfDescargos a WHERE a.nombreAutoriza = :nombreAutoriza"),
    @NamedQuery(name = "AfDescargos.findByCargoAutoriza", query = "SELECT a FROM AfDescargos a WHERE a.cargoAutoriza = :cargoAutoriza"),
    @NamedQuery(name = "AfDescargos.findByObservacionFallo", query = "SELECT a FROM AfDescargos a WHERE a.observacionFallo = :observacionFallo"),
    @NamedQuery(name = "AfDescargos.findByFechaSolicitud", query = "SELECT a FROM AfDescargos a WHERE a.fechaSolicitud = :fechaSolicitud"),
    @NamedQuery(name = "AfDescargos.findByUsuarioModifica", query = "SELECT a FROM AfDescargos a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AfDescargos.findByFechaModifica", query = "SELECT a FROM AfDescargos a WHERE a.fechaModifica = :fechaModifica"),
    @NamedQuery(name = "AfDescargos.findByEnvioCe", query = "SELECT a FROM AfDescargos a WHERE a.envioCe = :envioCe"),
    @NamedQuery(name = "AfDescargos.findByRecepcionCe", query = "SELECT a FROM AfDescargos a WHERE a.recepcionCe = :recepcionCe"),
    @NamedQuery(name = "AfDescargos.findByIdTipoDescargo", query = "SELECT a FROM AfDescargos a WHERE a.idTipoDescargo = :idTipoDescargo"),
    @NamedQuery(name = "AfDescargos.findByDescargoId", query = "SELECT a FROM AfDescargos a WHERE a.descargoId = :descargoId")})
public class AfDescargos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "DESCARGO_ANT")
    private long DescargoAnt;
    @Column(name = "UNIDAD_ACTIVO_FIJO")
    private String unidadActivoFijo;
    @Basic(optional = false)
    @Column(name = "CODIGO_DESCARGO")
    private String codigoDescargo;
    @Column(name = "FECHA_DESCARGO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDescargo;
    @Column(name = "NOMBRE_COORD_ADM")
    private String nombreCoordAdm;
    @Column(name = "NOMBRE_ENCARGADO_AF")
    private String nombreEncargadoAf;
    @Column(name = "NOMBRE_DIRECTOR")
    private String nombreDirector;
    @Column(name = "ESTADO")
    private Character estado;
    @Column(name = "USUARIO_CREA")
    private String usuarioCrea;
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FECHA_FALLO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFallo;
    @Column(name = "ID_DESCARGO_CE")
    private BigInteger idDescargoCe;
    @Column(name = "NOMBRE_AUTORIZA")
    private String nombreAutoriza;
    @Column(name = "CARGO_AUTORIZA")
    private String cargoAutoriza;
    @Column(name = "OBSERVACION_FALLO")
    private String observacionFallo;
    @Column(name = "FECHA_SOLICITUD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolicitud;
    @Column(name = "USUARIO_MODIFICA")
    private String usuarioModifica;
    @Column(name = "FECHA_MODIFICA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModifica;
    @Column(name = "ENVIO_CE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date envioCe;
    @Column(name = "RECEPCION_CE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date recepcionCe;
    @Column(name = "ID_TIPO_DESCARGO")
    private String idTipoDescargo;
    @Id
    @Basic(optional = false)
    @Column(name = "DESCARGO_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_DESCARGO")
    @SequenceGenerator(name="SEQ_DESCARGO", sequenceName="SEQ_DESCARGO",initialValue = 1,allocationSize = 1)
    private Long descargoId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "descargoId")
    private List<AfDescargosDetalle> afDescargosDetalleList;
    @Column(name = "TIPO_DESCARGO")
    private Character tipoDescargo;

    public AfDescargos() {
    }

    public AfDescargos(Long descargoId) {
        this.descargoId = descargoId;
    }

    public AfDescargos(Long descargoId, long descargoAnt, String codigoDescargo, String grupoBien) {
        this.descargoId = descargoId;
        this.DescargoAnt = descargoAnt;
        this.codigoDescargo = codigoDescargo;
    }

    public long getDescargoAnt() {
        return DescargoAnt;
    }

    public void setDescargoAnt(long DescargoAnt) {
        this.DescargoAnt = DescargoAnt;
    }

   
    public String getUnidadActivoFijo() {
        return unidadActivoFijo;
    }

    public void setUnidadActivoFijo(String unidadActivoFijo) {
        this.unidadActivoFijo = unidadActivoFijo;
    }

    public String getCodigoDescargo() {
        return codigoDescargo;
    }

    public void setCodigoDescargo(String codigoDescargo) {
        this.codigoDescargo = codigoDescargo;
    }

    public Date getFechaDescargo() {
        return fechaDescargo;
    }

    public void setFechaDescargo(Date fechaDescargo) {
        this.fechaDescargo = fechaDescargo;
    }

    public String getNombreCoordAdm() {
        return nombreCoordAdm;
    }

    public void setNombreCoordAdm(String nombreCoordAdm) {
        this.nombreCoordAdm = nombreCoordAdm;
    }

    public String getNombreEncargadoAf() {
        return nombreEncargadoAf;
    }

    public void setNombreEncargadoAf(String nombreEncargadoAf) {
        this.nombreEncargadoAf = nombreEncargadoAf;
    }

    public String getNombreDirector() {
        return nombreDirector;
    }

    public void setNombreDirector(String nombreDirector) {
        this.nombreDirector = nombreDirector;
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

    public Character getTipoDescargo() {
        return tipoDescargo;
    }

    public void setTipoDescargo(Character tipoDescargo) {
        this.tipoDescargo = tipoDescargo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaFallo() {
        return fechaFallo;
    }

    public void setFechaFallo(Date fechaFallo) {
        this.fechaFallo = fechaFallo;
    }

    public BigInteger getIdDescargoCe() {
        return idDescargoCe;
    }

    public void setIdDescargoCe(BigInteger idDescargoCe) {
        this.idDescargoCe = idDescargoCe;
    }

    public String getNombreAutoriza() {
        return nombreAutoriza;
    }

    public void setNombreAutoriza(String nombreAutoriza) {
        this.nombreAutoriza = nombreAutoriza;
    }

    public String getCargoAutoriza() {
        return cargoAutoriza;
    }

    public void setCargoAutoriza(String cargoAutoriza) {
        this.cargoAutoriza = cargoAutoriza;
    }

    public String getObservacionFallo() {
        return observacionFallo;
    }

    public void setObservacionFallo(String observacionFallo) {
        this.observacionFallo = observacionFallo;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(String usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    public Date getFechaModifica() {
        return fechaModifica;
    }

    public void setFechaModifica(Date fechaModifica) {
        this.fechaModifica = fechaModifica;
    }

    public Date getEnvioCe() {
        return envioCe;
    }

    public void setEnvioCe(Date envioCe) {
        this.envioCe = envioCe;
    }

    public Date getRecepcionCe() {
        return recepcionCe;
    }

    public void setRecepcionCe(Date recepcionCe) {
        this.recepcionCe = recepcionCe;
    }

    public String getIdTipoDescargo() {
        return idTipoDescargo;
    }

    public void setIdTipoDescargo(String idTipoDescargo) {
        this.idTipoDescargo = idTipoDescargo;
    }

     public Long getDescargoId() {
        return descargoId;
    }

    public void setDescargoId(Long descargoId) {
        this.descargoId = descargoId;
    }

    public List<AfDescargosDetalle> getAfDescargosDetalleList() {
        return afDescargosDetalleList;
    }

    public void setAfDescargosDetalleList(List<AfDescargosDetalle> afDescargosDetalleList) {
        this.afDescargosDetalleList = afDescargosDetalleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (descargoId != null ? descargoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfDescargos)) {
            return false;
        }
        AfDescargos other = (AfDescargos) object;
        if ((this.descargoId == null && other.descargoId != null) || (this.descargoId != null && !this.descargoId.equals(other.descargoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.app.activofijo.model.AfDescargos[ descargoId=" + descargoId + " ]";
    }
    
}
