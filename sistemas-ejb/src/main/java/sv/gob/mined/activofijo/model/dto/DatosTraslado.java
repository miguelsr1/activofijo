/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.activofijo.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import sv.gob.mined.activofijo.model.VwBienes;

/**
 *
 * @author JISTorres
 */
public class DatosTraslado {
    private String institucion;
    private String unidad;
    private Date fecReporte;
    private String usuario;
    
    private List <VwBienes> lstDatos= new ArrayList<VwBienes>();

    public DatosTraslado() {
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

   
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getFecReporte() {
        return fecReporte;
    }

    public void setFecReporte(Date fecReporte) {
        this.fecReporte = fecReporte;
    }

   

    public List<VwBienes> getLstDatos() {
        return lstDatos;
    }

    public void setLstDatos(List<VwBienes> lstDatos) {
        this.lstDatos = lstDatos;
    }
    
    
    
    
}
