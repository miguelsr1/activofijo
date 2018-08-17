/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.activofijo.ejb;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author JISTorres
 */
@Stateless
public class ReportesEJB {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "activoFijoPU", type = PersistenceContextType.TRANSACTION)
    public EntityManager em;

    public byte[] getRpt(InputStream input, HashMap map) throws JRException {
        Connection conn = em.unwrap(java.sql.Connection.class);
        byte[] content = JasperRunManager.runReportToPdf(input, map, conn);
        return content;
    }

    public JasperPrint getRpt(HashMap map, InputStream input) {
        try {
            JasperPrint jp;
            Connection conn = em.unwrap(java.sql.Connection.class);
            jp = JasperFillManager.fillReport(input, map, conn);
            return jp;
        } catch (JRException ex) {
            Logger.getLogger(ReportesEJB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
       public JasperPrint getRpt(HashMap map, InputStream input, List lst) {
        try {
            JasperPrint jp;

            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(lst);

            jp = JasperFillManager.fillReport(input, map, ds);
            return jp;
        } catch (JRException ex) {
            Logger.getLogger(ReportesEJB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }


}
