/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.app.activofijo.controller;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import sv.gob.mined.activofijo.ejb.BienesEJB;
import sv.gob.mined.activofijo.model.VwBienes;

/**
 *
 * @author misanchez
 */
@ManagedBean(name = "utilController")
@ApplicationScoped
public class UtilController {
    @EJB
    private BienesEJB bienesEJB;
    
    public VwBienes findVwBienByIdBien(Long idBien){
        return bienesEJB.findVwBienesByIdBien(idBien);
    }
}
