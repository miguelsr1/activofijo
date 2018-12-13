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
import javax.persistence.Transient;

/**
 *
 * @author JISTorres
 */
@Entity
public class VwBienes implements Serializable {
    @Id
    private Long idBien;
    private String codigoInventario;
    private String categoria;
    private String descripcionBien;
    private String Marca;
    private String modelo;
    private String numeroSerie;
    private String estado;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaAdquisicion;
    private BigDecimal valorAdquisicion;
    private Character activoFijo;
    private String fuente;
    private String nombreProyecto;
    private String documentoAdquisicion;
    private String proveedor;
    private String usuarioCreacion;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaCreacion;
    private String unidadActivoFijo;
    private String codigoUnidad;
    private String estadoRegistro;
    private Long idCatBien;
    private String codigoTipoBien;
    private String codigoSeccion;
    private Long codigoCalidadBien;
    private Long idEstatusBien;
    private Long idFuente;
    private Long idProyecto;
    private Long idFormaAdquisicion;
    private String calidad;
    private String asignado;
    private Long idTrasladoDet;
    private Long idTraslado;
    private Long idDetalleDescargo;
    private Long descargoId;
    private Character tipoDescargo;
    private Character estadoDescargo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaDescargo;
    private String codigoDescargo;
    private BigDecimal montoDepreciacion;
    
    @Transient
    private Boolean desactivar;
    
    public VwBienes() {
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCodigoDescargo() {
        return codigoDescargo;
    }

    public void setCodigoDescargo(String codigoDescargo) {
        this.codigoDescargo = codigoDescargo;
    }

    public String getDescripcionBien() {
        return descripcionBien;
    }

    public void setDescripcionBien(String descripcionBien) {
        this.descripcionBien = descripcionBien;
    }

     public Boolean getDesactivar() {
         return valorAdquisicion.intValue()>600;
         
       // return activoFijo.equals('1');
    }

    public void setDesactivar(Boolean desactivar) {
        this.desactivar = desactivar;
    }
    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
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

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getUnidadActivoFijo() {
        return unidadActivoFijo;
    }

    public void setUnidadActivoFijo(String unidadActivoFijo) {
        this.unidadActivoFijo = unidadActivoFijo;
    }

    public Long getIdFormaAdquisicion() {
        return idFormaAdquisicion;
    }

    public void setIdFormaAdquisicion(Long idFormaAdquisicion) {
        this.idFormaAdquisicion = idFormaAdquisicion;
    }

    public String getCodigoUnidad() {
        return codigoUnidad;
    }

    public void setCodigoUnidad(String codigoUnidad) {
        this.codigoUnidad = codigoUnidad;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public Long getIdCatBien() {
        return idCatBien;
    }

    public void setIdCatBien(Long idCatBien) {
        this.idCatBien = idCatBien;
    }

    public String getCodigoTipoBien() {
        return codigoTipoBien;
    }

    public void setCodigoTipoBien(String codigoTipoBien) {
        this.codigoTipoBien = codigoTipoBien;
    }

  

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public String getCodigoSeccion() {
        return codigoSeccion;
    }

    public void setCodigoSeccion(String codigoSeccion) {
        this.codigoSeccion = codigoSeccion;
    }

    public Long getCodigoCalidadBien() {
        return codigoCalidadBien;
    }

    public void setCodigoCalidadBien(Long codigoCalidadBien) {
        this.codigoCalidadBien = codigoCalidadBien;
    }

    public Long getIdEstatusBien() {
        return idEstatusBien;
    }

    public void setIdEstatusBien(Long idEstatusBien) {
        this.idEstatusBien = idEstatusBien;
    }

   
    public Long getIdFuente() {
        return idFuente;
    }

    public void setIdFuente(Long idFuente) {
        this.idFuente = idFuente;
    }

    public Long getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Long idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public Character getActivoFijo() {
        return activoFijo;
    }

    public void setActivoFijo(Character activoFijo) {
        this.activoFijo = activoFijo;
    }
   
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public Long getIdTrasladoDet() {
        return idTrasladoDet;
    }

    public void setIdTrasladoDet(Long idTrasladoDet) {
        this.idTrasladoDet = idTrasladoDet;
    }

    public Long getIdTraslado() {
        return idTraslado;
    }

    public void setIdTraslado(Long idTraslado) {
        this.idTraslado = idTraslado;
    }

    public Long getIdDetalleDescargo() {
        return idDetalleDescargo;
    }

    public void setIdDetalleDescargo(Long idDetalleDescargo) {
        this.idDetalleDescargo = idDetalleDescargo;
    }

    public Long getDescargoId() {
        return descargoId;
    }

    public void setDescargoId(Long descargoId) {
        this.descargoId = descargoId;
    }

    public Character getTipoDescargo() {
        return tipoDescargo;
    }

    public void setTipoDescargo(Character tipoDescargo) {
        this.tipoDescargo = tipoDescargo;
    }

    public Character getEstadoDescargo() {
        return estadoDescargo;
    }

    public void setEstadoDescargo(Character estadoDescargo) {
        this.estadoDescargo = estadoDescargo;
    }

    public Date getFechaDescargo() {
        return fechaDescargo;
    }

    public void setFechaDescargo(Date fechaDescargo) {
        this.fechaDescargo = fechaDescargo;
    }

    public BigDecimal getMontoDepreciacion() {
        return montoDepreciacion;
    }

    public void setMontoDepreciacion(BigDecimal montoDepreciacion) {
        this.montoDepreciacion = montoDepreciacion;
    }

    @Override
    public String toString() {
        return codigoInventario;
    }
    
    
}
