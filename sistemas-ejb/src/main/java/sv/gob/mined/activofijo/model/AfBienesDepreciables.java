/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.activofijo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.eclipse.persistence.annotations.Direction;
import org.eclipse.persistence.annotations.NamedStoredProcedureQuery;
import org.eclipse.persistence.annotations.StoredProcedureParameter;

/**
 *
 * @author JISTorres
 */
@Entity
@Table(name = "AF_BIENES_DEPRECIABLES", catalog = "", schema = "ACTIVO_FIJO_V2")
@NamedQueries({
    @NamedQuery(name = "AfBienesDepreciables.findAll", query = "SELECT a FROM AfBienesDepreciables a"),
    @NamedQuery(name = "AfBienesDepreciables.findByUnidadActivoFijo", query = "SELECT a FROM AfBienesDepreciables a WHERE a.unidadActivoFijo = :unidadActivoFijo"),
    @NamedQuery(name = "AfBienesDepreciables.findByIdBien", query = "SELECT a FROM AfBienesDepreciables a WHERE a.idBien = :idBien"),
    @NamedQuery(name = "AfBienesDepreciables.findByCodigoInventario", query = "SELECT a FROM AfBienesDepreciables a WHERE a.codigoInventario = :codigoInventario"),
    @NamedQuery(name = "AfBienesDepreciables.findByCodigoUnidad", query = "SELECT a FROM AfBienesDepreciables a WHERE a.codigoUnidad = :codigoUnidad"),
    @NamedQuery(name = "AfBienesDepreciables.findByCorrelativo", query = "SELECT a FROM AfBienesDepreciables a WHERE a.correlativo = :correlativo"),
    @NamedQuery(name = "AfBienesDepreciables.findByCodigoSeccion", query = "SELECT a FROM AfBienesDepreciables a WHERE a.codigoSeccion = :codigoSeccion"),
    @NamedQuery(name = "AfBienesDepreciables.findByAsignadoA", query = "SELECT a FROM AfBienesDepreciables a WHERE a.asignadoA = :asignadoA"),
    @NamedQuery(name = "AfBienesDepreciables.findByDescripcionBien", query = "SELECT a FROM AfBienesDepreciables a WHERE a.descripcionBien = :descripcionBien"),
    @NamedQuery(name = "AfBienesDepreciables.findByFechaAdquisicion", query = "SELECT a FROM AfBienesDepreciables a WHERE a.fechaAdquisicion = :fechaAdquisicion"),
    @NamedQuery(name = "AfBienesDepreciables.findByProveedor", query = "SELECT a FROM AfBienesDepreciables a WHERE a.proveedor = :proveedor"),
    @NamedQuery(name = "AfBienesDepreciables.findByDocumentoAdquisicion", query = "SELECT a FROM AfBienesDepreciables a WHERE a.documentoAdquisicion = :documentoAdquisicion"),
    @NamedQuery(name = "AfBienesDepreciables.findByIdProyecto", query = "SELECT a FROM AfBienesDepreciables a WHERE a.idProyecto = :idProyecto"),
    @NamedQuery(name = "AfBienesDepreciables.findByObservaciones", query = "SELECT a FROM AfBienesDepreciables a WHERE a.observaciones = :observaciones"),
    @NamedQuery(name = "AfBienesDepreciables.findByIdTipoDescargo", query = "SELECT a FROM AfBienesDepreciables a WHERE a.idTipoDescargo = :idTipoDescargo"),
    @NamedQuery(name = "AfBienesDepreciables.findByFechaDescargo", query = "SELECT a FROM AfBienesDepreciables a WHERE a.fechaDescargo = :fechaDescargo"),
    @NamedQuery(name = "AfBienesDepreciables.findByValorAdquisicion", query = "SELECT a FROM AfBienesDepreciables a WHERE a.valorAdquisicion = :valorAdquisicion"),
    @NamedQuery(name = "AfBienesDepreciables.findByEsValorEstimado", query = "SELECT a FROM AfBienesDepreciables a WHERE a.esValorEstimado = :esValorEstimado"),
    @NamedQuery(name = "AfBienesDepreciables.findByValorResidual", query = "SELECT a FROM AfBienesDepreciables a WHERE a.valorResidual = :valorResidual"),
    @NamedQuery(name = "AfBienesDepreciables.findByModelo", query = "SELECT a FROM AfBienesDepreciables a WHERE a.modelo = :modelo"),
    @NamedQuery(name = "AfBienesDepreciables.findByNumeroSerie", query = "SELECT a FROM AfBienesDepreciables a WHERE a.numeroSerie = :numeroSerie"),
    @NamedQuery(name = "AfBienesDepreciables.findByNumeroPlaca", query = "SELECT a FROM AfBienesDepreciables a WHERE a.numeroPlaca = :numeroPlaca"),
    @NamedQuery(name = "AfBienesDepreciables.findByNumeroMotor", query = "SELECT a FROM AfBienesDepreciables a WHERE a.numeroMotor = :numeroMotor"),
    @NamedQuery(name = "AfBienesDepreciables.findByNumeroChasis", query = "SELECT a FROM AfBienesDepreciables a WHERE a.numeroChasis = :numeroChasis"),
    @NamedQuery(name = "AfBienesDepreciables.findByNumeroVin", query = "SELECT a FROM AfBienesDepreciables a WHERE a.numeroVin = :numeroVin"),
    @NamedQuery(name = "AfBienesDepreciables.findByTipoCombustible", query = "SELECT a FROM AfBienesDepreciables a WHERE a.tipoCombustible = :tipoCombustible"),
    @NamedQuery(name = "AfBienesDepreciables.findByNumeroAsientos", query = "SELECT a FROM AfBienesDepreciables a WHERE a.numeroAsientos = :numeroAsientos"),
    @NamedQuery(name = "AfBienesDepreciables.findByColorCarroceria", query = "SELECT a FROM AfBienesDepreciables a WHERE a.colorCarroceria = :colorCarroceria"),
    @NamedQuery(name = "AfBienesDepreciables.findByCapacidadToneladas", query = "SELECT a FROM AfBienesDepreciables a WHERE a.capacidadToneladas = :capacidadToneladas"),
    @NamedQuery(name = "AfBienesDepreciables.findByActivoFijo", query = "SELECT a FROM AfBienesDepreciables a WHERE a.activoFijo = :activoFijo"),
    @NamedQuery(name = "AfBienesDepreciables.findByAnioVehiculo", query = "SELECT a FROM AfBienesDepreciables a WHERE a.anioVehiculo = :anioVehiculo"),
    @NamedQuery(name = "AfBienesDepreciables.findByUsuarioCrea", query = "SELECT a FROM AfBienesDepreciables a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AfBienesDepreciables.findByFechaCreacion", query = "SELECT a FROM AfBienesDepreciables a WHERE a.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "AfBienesDepreciables.findByIdClasifBien", query = "SELECT a FROM AfBienesDepreciables a WHERE a.idClasifBien = :idClasifBien"),
    @NamedQuery(name = "AfBienesDepreciables.findByVidaUtil", query = "SELECT a FROM AfBienesDepreciables a WHERE a.vidaUtil = :vidaUtil"),
    @NamedQuery(name = "AfBienesDepreciables.findByUsuarioModif", query = "SELECT a FROM AfBienesDepreciables a WHERE a.usuarioModif = :usuarioModif"),
    @NamedQuery(name = "AfBienesDepreciables.findByFechaModif", query = "SELECT a FROM AfBienesDepreciables a WHERE a.fechaModif = :fechaModif"),
    @NamedQuery(name = "AfBienesDepreciables.findByDescargo", query = "SELECT a FROM AfBienesDepreciables a WHERE a.descargo = :descargo"),
    @NamedQuery(name = "AfBienesDepreciables.findByFechaRegContable", query = "SELECT a FROM AfBienesDepreciables a WHERE a.fechaRegContable = :fechaRegContable"),
    @NamedQuery(name = "AfBienesDepreciables.findByIdBienCe", query = "SELECT a FROM AfBienesDepreciables a WHERE a.idBienCe = :idBienCe"),
    @NamedQuery(name = "AfBienesDepreciables.findByEnvioCe", query = "SELECT a FROM AfBienesDepreciables a WHERE a.envioCe = :envioCe"),
    @NamedQuery(name = "AfBienesDepreciables.findByFechaRecepcion", query = "SELECT a FROM AfBienesDepreciables a WHERE a.fechaRecepcion = :fechaRecepcion"),
    @NamedQuery(name = "AfBienesDepreciables.findByEstadoRegistro", query = "SELECT a FROM AfBienesDepreciables a WHERE a.estadoRegistro = :estadoRegistro"),
    @NamedQuery(name = "AfBienesDepreciables.findByObservacionDde", query = "SELECT a FROM AfBienesDepreciables a WHERE a.observacionDde = :observacionDde")})
    @NamedStoredProcedureQuery( name = "PRD_DEPRECIACION",
        procedureName = "PRD_DEPRECIACION",
        returnsResultSet = false,
        parameters = {
            @StoredProcedureParameter(queryParameter = "param1", name = "pBien", direction = Direction.IN, type = String.class),//idbien
            @StoredProcedureParameter(queryParameter = "param2", name = "pAnio", direction = Direction.IN, type = String.class),//anio
            @StoredProcedureParameter(queryParameter = "param3", name = "pMes", direction = Direction.IN, type = String.class),//mes
            @StoredProcedureParameter(queryParameter = "param4", name = "pFuente", direction = Direction.IN, type = String.class),//fuente 
            @StoredProcedureParameter(queryParameter = "param5", name = "pProyecto", direction = Direction.IN, type = String.class),//proyecto
            @StoredProcedureParameter(queryParameter = "param6", name = "pBienes", direction = Direction.OUT, type = Integer.class)//numeroBienes    
        }
)

public class AfBienesDepreciables implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "UNIDAD_ACTIVO_FIJO")
    private String unidadActivoFijo;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_BIEN")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_BIEN")
    @SequenceGenerator(name="SEQ_BIEN", sequenceName="SEQ_BIEN",initialValue = 1,allocationSize = 1)
    private Long idBien;
    @Column(name = "CODIGO_INVENTARIO")
    private String codigoInventario;
    @Column(name = "CODIGO_UNIDAD")
    private String codigoUnidad;
    @Column(name = "CORRELATIVO")
    private String correlativo;
    @Column(name = "CODIGO_SECCION")
    private String codigoSeccion;
    @Column(name = "ASIGNADO_A")
    private String asignadoA;
    @Column(name = "DESCRIPCION_BIEN")
    private String descripcionBien;
    @Column(name = "FECHA_ADQUISICION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAdquisicion;
    @Column(name = "PROVEEDOR")
    private String proveedor;
    @Column(name = "DOCUMENTO_ADQUISICION")
    private String documentoAdquisicion;
    @Column(name = "ID_PROYECTO")
    private Long idProyecto;
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Column(name = "ID_TIPO_DESCARGO")
    private Long idTipoDescargo;
    @Column(name = "FECHA_DESCARGO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDescargo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR_ADQUISICION")
    private BigDecimal valorAdquisicion;
    @Basic(optional = false)
    @Column(name = "ES_VALOR_ESTIMADO")
    private char esValorEstimado;
    @Column(name = "VALOR_RESIDUAL")
    private BigDecimal valorResidual;
    @Column(name = "MODELO")
    private String modelo;
    @Column(name = "NUMERO_SERIE")
    private String numeroSerie;
    @Column(name = "NUMERO_PLACA")
    private String numeroPlaca;
    @Column(name = "NUMERO_MOTOR")
    private String numeroMotor;
    @Column(name = "NUMERO_CHASIS")
    private String numeroChasis;
    @Column(name = "NUMERO_VIN")
    private String numeroVin;
    @Column(name = "TIPO_COMBUSTIBLE")
    private Character tipoCombustible;
    @Column(name = "NUMERO_ASIENTOS")
    private Long numeroAsientos;
    @Column(name = "COLOR_CARROCERIA")
    private String colorCarroceria;
    @Column(name = "CAPACIDAD_TONELADAS")
    private BigDecimal capacidadToneladas;
    @Column(name = "ACTIVO_FIJO")
    private Character activoFijo;
    @Column(name = "ANIO_VEHICULO")
    private String anioVehiculo;
    @Column(name = "USUARIO_CREA")
    private String usuarioCrea;
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "ID_CLASIF_BIEN")
    private Long idClasifBien;
    @Column(name = "VIDA_UTIL")
    private BigInteger vidaUtil;
    @Column(name = "USUARIO_MODIF")
    private String usuarioModif;
    @Column(name = "FECHA_MODIF")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModif;
    @Column(name = "DESCARGO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date descargo;
    @Column(name = "FECHA_REG_CONTABLE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegContable;
    @Column(name = "ID_BIEN_CE")
    private Long idBienCe;
    @Column(name = "ENVIO_CE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date envioCe;
    @Column(name = "FECHA_RECEPCION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRecepcion;
    @Column(name = "ESTADO_REGISTRO")
    private Character estadoRegistro;
    @Column(name = "OBSERVACION_DDE")
    private String observacionDde;
    @Column(name = "ID_TIPO_BIEN")
  //  @JoinColumn(name = "ID_TIPO_BIEN", referencedColumnName = "ID_TIPO_BIEN")
  //  @ManyToOne(optional = false)
    private Long idTipoBien;
    @Column(name = "ID_FUENTE")
    private Long idFuente;
    @JoinColumn(name = "ID_FORMA_ADQUISICION", referencedColumnName = "ID_FORMA_ADQUISICION")
    @ManyToOne
    private AfFormaAdquisicion idFormaAdquisicion;
    @JoinColumn(name = "ID_ESTATUS_BIEN", referencedColumnName = "ID_ESTATUS_BIEN")
    @ManyToOne
    private AfEstatusBien idEstatusBien;
    @JoinColumn(name = "ID_CAT_BIEN", referencedColumnName = "ID_CAT_BIEN")
    @ManyToOne(optional = false)
    private AfCategoriasBien idCatBien;
    @JoinColumn(name = "CODIGO_CALIDAD_BIEN", referencedColumnName = "CODIGO_CALIDAD_BIEN")
    @ManyToOne
    private AfCalidadBien codigoCalidadBien;
    @Column(name = "MARCA")
    private String marca;
 
  

    public AfBienesDepreciables() {
    }

    public AfBienesDepreciables(Long idBien) {
        this.idBien = idBien;
    }

    public AfBienesDepreciables(Long idBien, String unidadActivoFijo, char esValorEstimado) {
        this.idBien = idBien;
        this.unidadActivoFijo = unidadActivoFijo;
        this.esValorEstimado = esValorEstimado;
    }

    public String getUnidadActivoFijo() {
        return unidadActivoFijo;
    }

    public void setUnidadActivoFijo(String unidadActivoFijo) {
        this.unidadActivoFijo = unidadActivoFijo;
    }

   

    public Long getIdBien() {
        return idBien;
    }

    public void setIdBien(Long idBien) {
        this.idBien = idBien;
    }

    public String getCodigoInventario() {
        return codigoInventario;
    }

    public void setCodigoInventario(String codigoInventario) {
        this.codigoInventario = codigoInventario;
    }

    public String getCodigoUnidad() {
        return codigoUnidad;
    }

   

    public void setCodigoUnidad(String codigoUnidad) {
        this.codigoUnidad = codigoUnidad;
    }

    public String getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }

    public String getCodigoSeccion() {
        return codigoSeccion;
    }

    public void setCodigoSeccion(String codigoSeccion) {
        this.codigoSeccion = codigoSeccion;
    }

    public String getAsignadoA() {
        return asignadoA;
    }

    public void setAsignadoA(String asignadoA) {
        this.asignadoA = asignadoA;
    }

    public String getDescripcionBien() {
        return descripcionBien;
    }

    public void setDescripcionBien(String descripcionBien) {
        this.descripcionBien = descripcionBien;
    }

    public Date getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getDocumentoAdquisicion() {
        return documentoAdquisicion;
    }

    public void setDocumentoAdquisicion(String documentoAdquisicion) {
        this.documentoAdquisicion = documentoAdquisicion;
    }

    public Long getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Long idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Long getIdTipoDescargo() {
        return idTipoDescargo;
    }

    public void setIdTipoDescargo(Long idTipoDescargo) {
        this.idTipoDescargo = idTipoDescargo;
    }

    public Date getFechaDescargo() {
        return fechaDescargo;
    }

    public void setFechaDescargo(Date fechaDescargo) {
        this.fechaDescargo = fechaDescargo;
    }

    public BigDecimal getValorAdquisicion() {
        return valorAdquisicion;
    }

    public void setValorAdquisicion(BigDecimal valorAdquisicion) {
        this.valorAdquisicion = valorAdquisicion;
    }

    public char getEsValorEstimado() {
        return esValorEstimado;
    }

    public void setEsValorEstimado(char esValorEstimado) {
        this.esValorEstimado = esValorEstimado;
    }

    public BigDecimal getValorResidual() {
        return valorResidual;
    }

    public void setValorResidual(BigDecimal valorResidual) {
        this.valorResidual = valorResidual;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getNumeroPlaca() {
        return numeroPlaca;
    }

    public void setNumeroPlaca(String numeroPlaca) {
        this.numeroPlaca = numeroPlaca;
    }

    public String getNumeroMotor() {
        return numeroMotor;
    }

    public void setNumeroMotor(String numeroMotor) {
        this.numeroMotor = numeroMotor;
    }

    public String getNumeroChasis() {
        return numeroChasis;
    }

    public void setNumeroChasis(String numeroChasis) {
        this.numeroChasis = numeroChasis;
    }

    public String getNumeroVin() {
        return numeroVin;
    }

    public void setNumeroVin(String numeroVin) {
        this.numeroVin = numeroVin;
    }

    public Character getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(Character tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public Long getNumeroAsientos() {
        return numeroAsientos;
    }

    public void setNumeroAsientos(Long numeroAsientos) {
        this.numeroAsientos = numeroAsientos;
    }

    public String getColorCarroceria() {
        return colorCarroceria;
    }

    public void setColorCarroceria(String colorCarroceria) {
        this.colorCarroceria = colorCarroceria;
    }

    public BigDecimal getCapacidadToneladas() {
        return capacidadToneladas;
    }

    public void setCapacidadToneladas(BigDecimal capacidadToneladas) {
        this.capacidadToneladas = capacidadToneladas;
    }

    public Character getActivoFijo() {
        return activoFijo;
    }

    public void setActivoFijo(Character activoFijo) {
        this.activoFijo = activoFijo;
    }

    public String getAnioVehiculo() {
        return anioVehiculo;
    }

    public void setAnioVehiculo(String anioVehiculo) {
        this.anioVehiculo = anioVehiculo;
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

    public Long getIdClasifBien() {
        return idClasifBien;
    }

    public void setIdClasifBien(Long idClasifBien) {
        this.idClasifBien = idClasifBien;
    }

    public BigInteger getVidaUtil() {
        return vidaUtil;
    }

    public void setVidaUtil(BigInteger vidaUtil) {
        this.vidaUtil = vidaUtil;
    }

    public String getUsuarioModif() {
        return usuarioModif;
    }

    public void setUsuarioModif(String usuarioModif) {
        this.usuarioModif = usuarioModif;
    }

    public Date getFechaModif() {
        return fechaModif;
    }

    public void setFechaModif(Date fechaModif) {
        this.fechaModif = fechaModif;
    }

    public Date getDescargo() {
        return descargo;
    }

    public void setDescargo(Date descargo) {
        this.descargo = descargo;
    }

    public Date getFechaRegContable() {
        return fechaRegContable;
    }

    public void setFechaRegContable(Date fechaRegContable) {
        this.fechaRegContable = fechaRegContable;
    }

    public Long getIdBienCe() {
        return idBienCe;
    }

    public void setIdBienCe(Long idBienCe) {
        this.idBienCe = idBienCe;
    }

    public Date getEnvioCe() {
        return envioCe;
    }

    public void setEnvioCe(Date envioCe) {
        this.envioCe = envioCe;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public Character getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(Character estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public String getObservacionDde() {
        return observacionDde;
    }

    public void setObservacionDde(String observacionDde) {
        this.observacionDde = observacionDde;
    }

    public Long getIdTipoBien() {
        return idTipoBien;
    }

    public void setIdTipoBien(Long idTipoBien) {
        this.idTipoBien = idTipoBien;
    }

    
  
   

    public Long getIdFuente() {
        return idFuente;
    }

    public void setIdFuente(Long idFuente) {
        this.idFuente = idFuente;
    }

    public AfFormaAdquisicion getIdFormaAdquisicion() {
        return idFormaAdquisicion;
    }

    public void setIdFormaAdquisicion(AfFormaAdquisicion idFormaAdquisicion) {
        this.idFormaAdquisicion = idFormaAdquisicion;
    }

    public AfEstatusBien getIdEstatusBien() {
        return idEstatusBien;
    }

    public void setIdEstatusBien(AfEstatusBien idEstatusBien) {
        this.idEstatusBien = idEstatusBien;
    }

    public AfCategoriasBien getIdCatBien() {
        return idCatBien;
    }

    public void setIdCatBien(AfCategoriasBien idCatBien) {
        this.idCatBien = idCatBien;
    }

    public AfCalidadBien getCodigoCalidadBien() {
        return codigoCalidadBien;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setCodigoCalidadBien(AfCalidadBien codigoCalidadBien) {
        this.codigoCalidadBien = codigoCalidadBien;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBien != null ? idBien.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AfBienesDepreciables)) {
            return false;
        }
        AfBienesDepreciables other = (AfBienesDepreciables) object;
        if ((this.idBien == null && other.idBien != null) || (this.idBien != null && !this.idBien.equals(other.idBien))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigoInventario;
    }
    
}
