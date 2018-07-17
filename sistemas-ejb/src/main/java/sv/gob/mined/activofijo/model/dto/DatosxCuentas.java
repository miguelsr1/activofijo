/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.activofijo.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import sv.gob.mined.activofijo.model.VwDatosxCuentas;



/**
 *
 * @author JISTorres
 */
public class DatosxCuentas {
     private String institucion;
    private String unidad;
    private String fuente;
    private String fecReporte;
    private String usuario;
    
    private List <VwDatosxCuentas> lstDatos= new ArrayList<VwDatosxCuentas>();

    

    public DatosxCuentas() {
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

    public List<VwDatosxCuentas> getLstDatos() {
        return lstDatos;
    }

    public void setLstDatos(List<VwDatosxCuentas> lstDatos) {
        this.lstDatos = lstDatos;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

   
    
}
