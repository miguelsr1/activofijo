/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.activofijo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author JISTorres
 */
@Entity
public class VwVehiculos implements Serializable {
@Id
private BigDecimal idRow; 
private Long idBien;
private String codigoInventario;
private String descripcionBien;
private String marca;
@Temporal(TemporalType.TIMESTAMP)
private Date fechaAdquisicion;
private BigDecimal valorAdquisicion;
private String activoFijo;
private String estado;
private String unidadActivoFijo;
private String codigoUnidad;
private String nombreUnidad;
private String estadoRegistro;
private String colorCarroceria;
private String numeroChasis;
private String numeroMotor;
private String numeroPlaca;
private String numeroAsientos;
private String numeroSerie;
private String numeroVin;
private Long idTipoBien;
private String codigoSeccion;
private String codigoCalidadBien;
private long idEstatusBien;
private long idFuente;
private long idProyecto;
@Temporal(TemporalType.TIMESTAMP)
private Date fechaCreacion;
private String usuarioCrea;
private String fuente;
private String nombreProyecto;
private String documentoAdquisicion;
private String proveedor;
private long idFormaAdquisicion;
private String calidad;
private String asignado;
private long idTrasladoDetalle;
private long idTraslado;
private long idDetalleDescargo;
private long descargoId;
private String tipoDescargo;
private String estadoDescargo;
 @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDescargo;
private String codigoDescargo;
private BigDecimal montoDepreciacion;
private String tipoUnidad;

public VwVehiculos() {
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

    public String getDescripcionBien() {
        return descripcionBien;
    }

    public void setDescripcionBien(String descripcionBien) {
        this.descripcionBien = descripcionBien;
    }

    public BigDecimal getIdRow() {
        return idRow;
    }

    public void setIdRow(BigDecimal idRow) {
        this.idRow = idRow;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Date getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public BigDecimal getValorAdquisicion() {
        return valorAdquisicion;
    }

    public void setValorAdquisicion(BigDecimal valorAdquisicion) {
        this.valorAdquisicion = valorAdquisicion;
    }

    public String getActivoFijo() {
        return activoFijo;
    }

    public void setActivoFijo(String activoFijo) {
        this.activoFijo = activoFijo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUnidadActivoFijo() {
        return unidadActivoFijo;
    }

    public void setUnidadActivoFijo(String unidadActivoFijo) {
        this.unidadActivoFijo = unidadActivoFijo;
    }

    public String getCodigoUnidad() {
        return codigoUnidad;
    }

    public void setCodigoUnidad(String codigoUnidad) {
        this.codigoUnidad = codigoUnidad;
    }

    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public String getColorCarroceria() {
        return colorCarroceria;
    }

    public void setColorCarroceria(String colorCarroceria) {
        this.colorCarroceria = colorCarroceria;
    }

    public String getNumeroChasis() {
        return numeroChasis;
    }

    public void setNumeroChasis(String numeroChasis) {
        this.numeroChasis = numeroChasis;
    }

    public String getNumeroMotor() {
        return numeroMotor;
    }

    public void setNumeroMotor(String numeroMotor) {
        this.numeroMotor = numeroMotor;
    }

    public String getNumeroPlaca() {
        return numeroPlaca;
    }

    public void setNumeroPlaca(String numeroPlaca) {
        this.numeroPlaca = numeroPlaca;
    }

    public String getNumeroAsientos() {
        return numeroAsientos;
    }

    public void setNumeroAsientos(String numeroAsientos) {
        this.numeroAsientos = numeroAsientos;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getNumeroVin() {
        return numeroVin;
    }

    public void setNumeroVin(String numeroVin) {
        this.numeroVin = numeroVin;
    }

    public Long getIdTipoBien() {
        return idTipoBien;
    }

    public void setIdTipoBien(Long idTipoBien) {
        this.idTipoBien = idTipoBien;
    }

    public String getCodigoSeccion() {
        return codigoSeccion;
    }

    public void setCodigoSeccion(String codigoSeccion) {
        this.codigoSeccion = codigoSeccion;
    }

    public String getCodigoCalidadBien() {
        return codigoCalidadBien;
    }

    public void setCodigoCalidadBien(String codigoCalidadBien) {
        this.codigoCalidadBien = codigoCalidadBien;
    }

    public long getIdEstatusBien() {
        return idEstatusBien;
    }

    public void setIdEstatusBien(long idEstatusBien) {
        this.idEstatusBien = idEstatusBien;
    }

    public long getIdFuente() {
        return idFuente;
    }

    public void setIdFuente(long idFuente) {
        this.idFuente = idFuente;
    }

    public long getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(long idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getDocumentoAdquisicion() {
        return documentoAdquisicion;
    }

    public void setDocumentoAdquisicion(String documentoAdquisicion) {
        this.documentoAdquisicion = documentoAdquisicion;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public long getIdFormaAdquisicion() {
        return idFormaAdquisicion;
    }

    public void setIdFormaAdquisicion(long idFormaAdquisicion) {
        this.idFormaAdquisicion = idFormaAdquisicion;
    }

    public String getCalidad() {
        return calidad;
    }

    public void setCalidad(String calidad) {
        this.calidad = calidad;
    }

    public String getAsignado() {
        return asignado;
    }

    public void setAsignado(String asignado) {
        this.asignado = asignado;
    }

    public long getIdTrasladoDetalle() {
        return idTrasladoDetalle;
    }

    public void setIdTrasladoDetalle(long idTrasladoDetalle) {
        this.idTrasladoDetalle = idTrasladoDetalle;
    }

    public long getIdTraslado() {
        return idTraslado;
    }

    public void setIdTraslado(long idTraslado) {
        this.idTraslado = idTraslado;
    }

    public long getIdDetalleDescargo() {
        return idDetalleDescargo;
    }

    public void setIdDetalleDescargo(long idDetalleDescargo) {
        this.idDetalleDescargo = idDetalleDescargo;
    }

    public long getDescargoId() {
        return descargoId;
    }

    public void setDescargoId(long descargoId) {
        this.descargoId = descargoId;
    }

    public String getTipoDescargo() {
        return tipoDescargo;
    }

    public void setTipoDescargo(String tipoDescargo) {
        this.tipoDescargo = tipoDescargo;
    }

    public String getEstadoDescargo() {
        return estadoDescargo;
    }

    public void setEstadoDescargo(String estadoDescargo) {
        this.estadoDescargo = estadoDescargo;
    }

    public Date getFechaDescargo() {
        return fechaDescargo;
    }

    public void setFechaDescargo(Date fechaDescargo) {
        this.fechaDescargo = fechaDescargo;
    }

    public String getCodigoDescargo() {
        return codigoDescargo;
    }

    public void setCodigoDescargo(String codigoDescargo) {
        this.codigoDescargo = codigoDescargo;
    }

    public BigDecimal getMontoDepreciacion() {
        return montoDepreciacion;
    }

    public void setMontoDepreciacion(BigDecimal montoDepreciacion) {
        this.montoDepreciacion = montoDepreciacion;
    }

    public String getTipoUnidad() {
        return tipoUnidad;
    }

    public void setTipoUnidad(String tipoUnidad) {
        this.tipoUnidad = tipoUnidad;
    }

    @Override
    public String toString() {
        return "VwVehiculos{" + "codigoInventario=" + codigoInventario + ", descripcionBien=" + descripcionBien + '}';
    }

    
  



}
