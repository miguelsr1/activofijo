/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.activofijo.model.dto;

import java.util.ArrayList;
import java.util.List;
import sv.gob.mined.activofijo.model.AfBienesDepreciables;

/**
 *
 * @author JISTorres
 */
public class DatosBienxEmpleado {
    private String institucion;
    private String unidad;
    private String fecReporte;
    private String usuario;
    private String cargoRes;
    private String Responsable;
    private String nomEmpleado;
    private String cargoEmpleado;
    private String hora;
    
    private List <AfBienesDepreciables> lstDatos= new ArrayList<AfBienesDepreciables>();

    public DatosBienxEmpleado() {
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

    public String getCargoRes() {
        return cargoRes;
    }

    public void setCargoRes(String cargoRes) {
        this.cargoRes = cargoRes;
    }

    public String getResponsable() {
        return Responsable;
    }

    public void setResponsable(String Responsable) {
        this.Responsable = Responsable;
    }

    public List<AfBienesDepreciables> getLstDatos() {
        return lstDatos;
    }

    public void setLstDatos(List<AfBienesDepreciables> lstDatos) {
        this.lstDatos = lstDatos;
    }

    public String getNomEmpleado() {
        return nomEmpleado;
    }

    public void setNomEmpleado(String nomEmpleado) {
        this.nomEmpleado = nomEmpleado;
    }

    public String getCargoEmpleado() {
        return cargoEmpleado;
    }

    public void setCargoEmpleado(String cargoEmpleado) {
        this.cargoEmpleado = cargoEmpleado;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    
    
}
