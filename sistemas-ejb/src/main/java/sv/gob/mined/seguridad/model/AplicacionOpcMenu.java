/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.seguridad.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author misanchez
 */
@Entity
@Table(name = "APLICACION_OPC_MENU", schema = "SEGURIDAD_V2")
public class AplicacionOpcMenu implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_APP_OPC_MENU")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_menu_opc")
    @SequenceGenerator(name = "app_menu_opc", sequenceName = "SEQ_APP_MENU_OPC", allocationSize = 1, initialValue = 1)
    private BigDecimal idAppOpcMenu;
    @JoinColumn(name = "ID_OPC_MENU", referencedColumnName = "ID_OPC_MENU")
    @ManyToOne(fetch = FetchType.EAGER)
    private OpcionMenu idOpcMenu;
    @JoinColumn(name = "ID_GRUPO_APP", referencedColumnName = "ID_GRUPO_APP")
    @ManyToOne(fetch = FetchType.EAGER)
    private GrupoAplicacion idGrupoApp;
    @JoinColumn(name = "ID_APLICACION", referencedColumnName = "ID_APLICACION")
    @ManyToOne(fetch = FetchType.EAGER)
    private Aplicacion idAplicacion;

    public AplicacionOpcMenu() {
    }

    public AplicacionOpcMenu(BigDecimal idAppOpcMenu) {
        this.idAppOpcMenu = idAppOpcMenu;
    }

    public BigDecimal getIdAppOpcMenu() {
        return idAppOpcMenu;
    }

    public void setIdAppOpcMenu(BigDecimal idAppOpcMenu) {
        this.idAppOpcMenu = idAppOpcMenu;
    }

    public OpcionMenu getIdOpcMenu() {
        return idOpcMenu;
    }

    public void setIdOpcMenu(OpcionMenu idOpcMenu) {
        this.idOpcMenu = idOpcMenu;
    }

    public GrupoAplicacion getIdGrupoApp() {
        return idGrupoApp;
    }

    public void setIdGrupoApp(GrupoAplicacion idGrupoApp) {
        this.idGrupoApp = idGrupoApp;
    }

    public Aplicacion getIdAplicacion() {
        return idAplicacion;
    }

    public void setIdAplicacion(Aplicacion idAplicacion) {
        this.idAplicacion = idAplicacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAppOpcMenu != null ? idAppOpcMenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AplicacionOpcMenu)) {
            return false;
        }
        AplicacionOpcMenu other = (AplicacionOpcMenu) object;
        if ((this.idAppOpcMenu == null && other.idAppOpcMenu != null) || (this.idAppOpcMenu != null && !this.idAppOpcMenu.equals(other.idAppOpcMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.gob.mined.seguridad.model.AplicacionOpcMenu[ idAppOpcMenu=" + idAppOpcMenu + " ]";
    }
    
}
