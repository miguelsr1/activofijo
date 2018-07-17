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
public class VwDescargos implements Serializable {
    @Id
    private Long descargoId;
    private String codigoDescargo;
    private String nombreUnidad;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaDescargo;
    private Number bienes;
    private BigDecimal total;
    private String nombreTipoDescargo; 
    private String estado;
    private String desEstado;
    private String unidadActivoFijo;
    private String codigoUnidad;
    private Character tipoDescargo;
    private Date fechaSolicitud;
    private Character tipoUnidad;
    
    
    @Transient
    private Boolean desactivar;
    
    public VwDescargos() {
    }

   
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getDescargoId() {
        return descargoId;
    }

    public void setDescargoId(Long descargoId) {
        this.descargoId = descargoId;
    }

    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
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

    public String getDesEstado() {
        return desEstado;
    }

    public void setDesEstado(String desEstado) {
        this.desEstado = desEstado;
    }

    public void setFechaDescargo(Date fechaDescargo) {
        this.fechaDescargo = fechaDescargo;
    }

    public Number getBienes() {
        return bienes;
    }

    public void setBienes(Number bienes) {
        this.bienes = bienes;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Character getTipoDescargo() {
        return tipoDescargo;
    }

    public void setTipoDescargo(Character tipoDescargo) {
        this.tipoDescargo = tipoDescargo;
    }

    public String getNombreTipoDescargo() {
        return nombreTipoDescargo;
    }

    public void setNombreTipoDescargo(String nombreTipoDescargo) {
        this.nombreTipoDescargo = nombreTipoDescargo;
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

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Character getTipoUnidad() {
        return tipoUnidad;
    }

    public void setTipoUnidad(Character tipoUnidad) {
        this.tipoUnidad = tipoUnidad;
    }

    @Override
    public String toString() {
        return "VwDescargos{" + "descargoId=" + descargoId + ", nombreUnidad=" + nombreUnidad + ", fechaDescargo=" + fechaDescargo + ", bienes=" + bienes + ", total=" + total + ", nombreTipoDescargo=" + nombreTipoDescargo + ", estado=" + estado + ", unidadActivoFijo=" + unidadActivoFijo + ", codigoUnidad=" + codigoUnidad + ", desactivar=" + desactivar + '}';
    }

  
    
    
}
