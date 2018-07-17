/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.activofijo.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import sv.gob.mined.activofijo.model.AfDepreciaciones;
import sv.gob.mined.activofijo.model.VwDatosxCuentas;



/**
 *
 * @author JISTorres
 */
@Entity
@XmlRootElement
@SqlResultSetMapping(name = "defaultDatosDepreciacionDto",
        entities = @EntityResult(entityClass = DatosDepreciacion.class))
public class DatosDepreciacion implements Serializable {
    @Id
    private BigDecimal idRow;
    private String institucion;
    private String unidad;
    private String fuente;
    private String fecReporte;
    private String usuario;
    private String numeroInventario;
    private String descripcionBien;
    private String categoriaBien;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAdquisicion;
    private String vidaUtil;
    private BigDecimal precio;
    private BigDecimal valorActual;
    
   
    
    @Transient
    private List <AfDepreciaciones> lstDatos= new ArrayList<AfDepreciaciones>();

    

    public DatosDepreciacion() {
    }

    public BigDecimal getIdRow() {
        return idRow;
    }

    public void setIdRow(BigDecimal idRow) {
        this.idRow = idRow;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getFecReporte() {
        return fecReporte;
    }

    public void setFecReporte(String fecReporte) {
        this.fecReporte = fecReporte;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNumeroInventario() {
        return numeroInventario;
    }

    public void setNumeroInventario(String numeroInventario) {
        this.numeroInventario = numeroInventario;
    }

    public String getDescripcionBien() {
        return descripcionBien;
    }

    public void setDescripcionBien(String descripcionBien) {
        this.descripcionBien = descripcionBien;
    }

    public String getCategoriaBien() {
        return categoriaBien;
    }

    public void setCategoriaBien(String categoriaBien) {
        this.categoriaBien = categoriaBien;
    }

    public Date getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public String getVidaUtil() {
        return vidaUtil;
    }

    public void setVidaUtil(String vidaUtil) {
        this.vidaUtil = vidaUtil;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getValorActual() {
        return valorActual;
    }

    public void setValorActual(BigDecimal valorActual) {
        this.valorActual = valorActual;
    }

    public List<AfDepreciaciones> getLstDatos() {
        return lstDatos;
    }

    public void setLstDatos(List<AfDepreciaciones> lstDatos) {
        this.lstDatos = lstDatos;
    }

   
    
}
