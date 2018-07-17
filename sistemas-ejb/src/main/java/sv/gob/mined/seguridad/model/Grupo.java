/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.seguridad.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author misanchez
 */
@Entity
@Table(name = "GRUPO", schema = "SEGURIDAD_V2")
public class Grupo implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_GRUPO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grupo")
    @SequenceGenerator(name = "grupo", sequenceName = "SEQ_GRUPO", allocationSize = 1, initialValue = 1)
    private BigDecimal idGrupo;
    @Basic(optional = false)
    @Column(name = "NOMBRE_GRUPO")
    private String nombreGrupo;
    @OneToMany(mappedBy = "idGrupo", fetch = FetchType.LAZY)
    private List<GrupoAplicacion> grupoAplicacionList;

    public Grupo() {
    }

    public Grupo(BigDecimal idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Grupo(BigDecimal idGrupo, String nombreGrupo) {
        this.idGrupo = idGrupo;
        this.nombreGrupo = nombreGrupo;
    }

    public BigDecimal getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(BigDecimal idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public List<GrupoAplicacion> getGrupoAplicacionList() {
        return grupoAplicacionList;
    }

    public void setGrupoAplicacionList(List<GrupoAplicacion> grupoAplicacionList) {
        this.grupoAplicacionList = grupoAplicacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupo != null ? idGrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupo)) {
            return false;
        }
        Grupo other = (Grupo) object;
        if ((this.idGrupo == null && other.idGrupo != null) || (this.idGrupo != null && !this.idGrupo.equals(other.idGrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreGrupo;
    }
    
}
