/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.activofijo.model.dto;

import java.util.ArrayList;
import java.util.List;
import sv.gob.mined.activofijo.model.VwVehiculos;

/**
 *
 * @author JISTorres
 */
public class DatosVehiculos {
    private String institucion;
    private String unidad;
    private String fecReporte;
    private String usuario;
    
    private List <VwVehiculos> lstDatos= new ArrayList();

    public DatosVehiculos() {
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

    public String getFecReporte() {
        return fecReporte;
    }

    public void setFecReporte(String fecReporte) {
        this.fecReporte = fecReporte;
    }

    public List<VwVehiculos> getLstDatos() {
        return lstDatos;
    }

    public void setLstDatos(List<VwVehiculos> lstDatos) {
        this.lstDatos = lstDatos;
    }

    

   

  
    
    
    
}
