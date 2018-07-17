/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.seguridad.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author misanchez
 */
@Entity
@Table(name = "OPCION_MENU", schema = "SEGURIDAD_V2")
public class OpcionMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_OPC_MENU")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "opcion_menu")
    @SequenceGenerator(name = "opcion_menu", sequenceName = "SEQ_OPCION_MENU", allocationSize = 1, initialValue = 1)
    private BigDecimal idOpcMenu;
    @Column(name = "CODIGO_OPC_MENU")
    private String codigoOpcMenu;
    @Basic(optional = false)
    @Column(name = "NOMBRE_OPCION")
    private String nombreOpcion;
    @Column(name = "URL")
    private String url;
    @Column(name = "TIPO")
    private Character tipo;
    @Column(name = "ICONO")
    private String icono;
    @Basic(optional = false)
    @Column(name = "ORDEN")
    private BigInteger orden;
    @Basic(optional = false)
    @Column(name = "USUARIO_INSERCION")
    private String usuarioInsercion;
    @Basic(optional = false)
    @Column(name = "FECHA_INSERCION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInsercion;
    @Column(name = "USUARIO_MODIFICACION")
    private String usuarioModificacion;
    @Column(name = "FECHA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Basic(optional = false)
    @Column(name = "ESTADO_ELIMINACION")
    private BigInteger estadoEliminacion;
    @OneToMany(mappedBy = "idOpcMenu", fetch = FetchType.LAZY)
    private List<AplicacionOpcMenu> aplicacionOpcMenuList;
    @OneToMany(mappedBy = "idOpcMenuPadre", fetch = FetchType.LAZY)
    @OrderBy("orden ASC")
    private List<OpcionMenu> opcionMenuList;
    @JoinColumn(name = "ID_OPC_MENU_PADRE", referencedColumnName = "ID_OPC_MENU")
    @ManyToOne(fetch = FetchType.EAGER)
    private OpcionMenu idOpcMenuPadre;
    @Transient
    private Boolean eliminar = false;

    public OpcionMenu() {
    }

    public OpcionMenu(BigDecimal idOpcMenu) {
        this.idOpcMenu = idOpcMenu;
    }

    public OpcionMenu(BigDecimal idOpcMenu, String nombreOpcion, BigInteger orden, String usuarioInsercion, Date fechaInsercion, BigInteger estadoEliminacion) {
        this.idOpcMenu = idOpcMenu;
        this.nombreOpcion = nombreOpcion;
        this.orden = orden;
        this.usuarioInsercion = usuarioInsercion;
        this.fechaInsercion = fechaInsercion;
        this.estadoEliminacion = estadoEliminacion;
    }

    public BigDecimal getIdOpcMenu() {
        return idOpcMenu;
    }

    public void setIdOpcMenu(BigDecimal idOpcMenu) {
        this.idOpcMenu = idOpcMenu;
    }

    public String getCodigoOpcMenu() {
        return codigoOpcMenu;
    }

    public void setCodigoOpcMenu(String codigoOpcMenu) {
        this.codigoOpcMenu = codigoOpcMenu;
    }

    public String getNombreOpcion() {
        return nombreOpcion;
    }

    public void setNombreOpcion(String nombreOpcion) {
        this.nombreOpcion = nombreOpcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Character getTipo() {
        return tipo;
    }

    public void setTipo(Character tipo) {
        this.tipo = tipo;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public BigInteger getOrden() {
        return orden;
    }

    public void setOrden(BigInteger orden) {
        this.orden = orden;
    }

    public String getUsuarioInsercion() {
        return usuarioInsercion;
    }

    public void setUsuarioInsercion(String usuarioInsercion) {
        this.usuarioInsercion = usuarioInsercion;
    }

    public Date getFechaInsercion() {
        return fechaInsercion;
    }

    public void setFechaInsercion(Date fechaInsercion) {
        this.fechaInsercion = fechaInsercion;
    }

    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public BigInteger getEstadoEliminacion() {
        return estadoEliminacion;
    }

    public void setEstadoEliminacion(BigInteger estadoEliminacion) {
        this.estadoEliminacion = estadoEliminacion;
    }

    public List<AplicacionOpcMenu> getAplicacionOpcMenuList() {
        return aplicacionOpcMenuList;
    }

    public void setAplicacionOpcMenuList(List<AplicacionOpcMenu> aplicacionOpcMenuList) {
        this.aplicacionOpcMenuList = aplicacionOpcMenuList;
    }

    public List<OpcionMenu> getOpcionMenuList() {
        return opcionMenuList;
    }

    public void setOpcionMenuList(List<OpcionMenu> opcionMenuList) {
        this.opcionMenuList = opcionMenuList;
    }

    public OpcionMenu getIdOpcMenuPadre() {
        return idOpcMenuPadre;
    }

    public void setIdOpcMenuPadre(OpcionMenu idOpcMenuPadre) {
        this.idOpcMenuPadre = idOpcMenuPadre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOpcMenu != null ? idOpcMenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OpcionMenu)) {
            return false;
        }
        OpcionMenu other = (OpcionMenu) object;
        if ((this.idOpcMenu == null && other.idOpcMenu != null) || (this.idOpcMenu != null && !this.idOpcMenu.equals(other.idOpcMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreOpcion;
    }

    public Boolean getEliminar() {
        return eliminar;
    }

    public void setEliminar(Boolean eliminar) {
        this.eliminar = eliminar;
    }
}
