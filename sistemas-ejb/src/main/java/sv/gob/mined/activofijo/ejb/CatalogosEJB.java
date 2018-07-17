/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.activofijo.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import sv.gob.mined.activofijo.model.AfBienesDepreciables;
import sv.gob.mined.activofijo.model.AfCalidadBien;
import sv.gob.mined.activofijo.model.AfCategoriasBien;
import sv.gob.mined.activofijo.model.AfEstatusBien;
import sv.gob.mined.activofijo.model.AfFormaAdquisicion;
import sv.gob.mined.activofijo.model.AfFuenteFinanciamiento;
import sv.gob.mined.activofijo.model.AfProyectos;
import sv.gob.mined.activofijo.model.AfSeccionesUnidad;
import sv.gob.mined.activofijo.model.AfSolvencias;
import sv.gob.mined.activofijo.model.AfTipoBienes;
import sv.gob.mined.activofijo.model.AfTipoDescargo;
import sv.gob.mined.activofijo.model.AfTipoTraslados;
import sv.gob.mined.activofijo.model.AfUnidadesActivoFijo;
import sv.gob.mined.activofijo.model.AfUnidadesAdministrativas;
import sv.gob.mined.activofijo.model.VwBienes;

/**
 *
 * @author JISTorres
 */
@Stateless
public class CatalogosEJB {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "activoFijoPU", type = PersistenceContextType.TRANSACTION)
    public EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public String NomUnidad(String codigo, String tipo) {
        Query q = em.createQuery("Select a.nombreUnidad from AfUnidadesAdministrativas a where trim(a.afUnidadesAdministrativasPK.codigoUnidad)=:codigo and a.tipoUnidad=:tipo");
        q.setParameter("codigo", codigo);
        q.setParameter("tipo", tipo);
        if (q.getSingleResult() != null) {
            return q.getSingleResult().toString();
        } else {
            return null;
        }
    }

    public String NomCentro(String codigo) {
        Query q = em.createQuery("Select a.nombreUnidad from AfUnidadesAdministrativas a where trim(a.afUnidadesAdministrativasPK.codigoUnidad)=:codigo and a.tipoUnidad='CE'");
        q.setParameter("codigo", codigo);
        if (q.getSingleResult() != null) {
            return q.getSingleResult().toString();
        } else {
            return null;
        }
    }

    public String NomDirector(String codigo) {
        Query q = em.createQuery("Select a.nombreDirector from AfUnidadesAdministrativas a where trim(a.afUnidadesAdministrativasPK.codigoUnidad)=:codigo and a.tipoUnidad='CE'");
        q.setParameter("codigo", codigo);
        if (q.getSingleResult() != null) {
            return q.getSingleResult().toString();
        } else {
            return null;
        }
    }

    public String tipoTraslado(String codigo){
        Query q= em.createNativeQuery("select t.nombre_tipo_traslado  from af_tipo_traslados t where t.id_tipo_traslado="+codigo);
        if (q.getSingleResult() != null) {
            return q.getSingleResult().toString();
        } else {
            return null;
        }
    }
    
    
    public String nomUnidadAf(String codigo) {
        Query q = em.createNativeQuery("Select NOMBRE_UNIDAD_AF from AF_UNIDADES_ACTIVO_FIJO a where a.UNIDAD_ACTIVO_FIJO='" + codigo + "'");
        //  q.setParameter(1, codigo); 
        if (q.getSingleResult() != null) {
            return q.getSingleResult().toString();
        } else {
            return null;
        }
    }
    
    public List<AfUnidadesActivoFijo> getUnidadAf() {

        Query q = em.createQuery("Select a from AfUnidadesActivoFijo a order by a.unidadActivoFijo", AfUnidadesActivoFijo.class);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return q.getResultList();
        }
    }

    public List<AfUnidadesAdministrativas> getCentroEscolar() {
        Query q = em.createQuery("Select a from AfUnidadesAdministrativas a where a.tipoUnidad='CE' order by 1", AfUnidadesAdministrativas.class);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return q.getResultList();
        }
    }

    public String getTipoUnidad(String codigo) {
        Query q = em.createQuery("Select a.tipoUnidad from AfUnidadesAdministrativas a where trim(a.afUnidadesAdministrativasPK.codigoUnidad)=:codigo");
        q.setParameter("codigo", codigo.trim());
        if (q.getSingleResult() != null) {
            return q.getSingleResult().toString();
        } else {
            return null;
        }
    }

    public String getUnidadAf(String codigo, String tipo) {
        Query q = em.createQuery("Select a.afUnidadesAdministrativasPK.unidadActivoFijo from AfUnidadesAdministrativas a where trim(a.afUnidadesAdministrativasPK.codigoUnidad)=:codigo and a.tipoUnidad=:tipo order by a.afUnidadesAdministrativasPK.unidadActivoFijo");
        q.setParameter("codigo", codigo);
        q.setParameter("tipo", tipo);
        if (q.getSingleResult() != null) {
            return q.getSingleResult().toString();
        } else {
            return null;
        }
    }

    public List<AfUnidadesAdministrativas> getUnidadAdm(String codigo, String tipo) {
        Query q = em.createQuery("Select a from AfUnidadesAdministrativas a where a.tipoUnidad=:tipo and trim(a.afUnidadesAdministrativasPK.unidadActivoFijo)=:codigo order by a.afUnidadesAdministrativasPK.codigoUnidad", AfUnidadesAdministrativas.class);
        q.setParameter("tipo", tipo);
        q.setParameter("codigo", codigo);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return q.getResultList();
        }
    }

    public List<AfTipoTraslados> getTipoTraslado() {
        Query q = em.createQuery("Select a from AfTipoTraslados a where a.idTipoTraslado='3' ", AfTipoTraslados.class);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return q.getResultList();
        }
    }

    public List<AfTipoDescargo> getTipoDescargo() {
        Query q = em.createQuery("Select a from AfTipoDescargo a ", AfTipoDescargo.class);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return q.getResultList();
        }
    }

   

    public AfSolvencias getSolvencias(String Anio, String unidad) {
        Query q = em.createQuery("select a from AfSolvencias a where a.anio=:anio and a.codigoUnidad=:unidad", AfSolvencias.class);
        q.setParameter("anio", Anio);
        q.setParameter("unidad", unidad);
        if (!q.getResultList().isEmpty()) {
            return (AfSolvencias) q.getResultList();
        } else {
            return new AfSolvencias();
        }
    }
    
     public List<AfBienesDepreciables> getBienesDescargo(String codigo, String Inv, String Activo, List<Long> lst) {
        Query q;
        String sqlQuery;
       
            if (Activo.equals("N")) {
                sqlQuery = "Select a from AfBienesDepreciables a where a.idEstatusBien.idEstatusBien='1' and a.valorAdquisicion<600 and trim(a.codigoUnidad)=:codigo and trim(a.codigoInventario) like :inv and a.idBien not in (select b.idBien from AfDescargosDetalle b) and a.idBien not in :temp";
            } else {
                sqlQuery="Select a from AfBienesDepreciables a where a.idEstatusBien.idEstatusBien='1' and a.valorAdquisicion>= 600 and trim(a.codigoUnidad)=:codigo and trim(a.codigoInventario) like :inv and a.idBien not in (select b.idBien from AfDescargosDetalle b) and a.idBien not in :temp";
            }
            q=em.createQuery(sqlQuery,AfBienesDepreciables.class);
            q.setParameter("codigo", codigo);
            q.setParameter("inv", "%" + Inv + "%");
            q.setParameter("temp", lst);
            if (q.getResultList().isEmpty()) {
                return null;
            } else {
                return q.getResultList();
            }
       
    }
    
     public List<AfBienesDepreciables> getBienesTraslado(String codigo, String Inv,List<Long> lst) {
        Query q = em.createQuery("Select a from AfBienesDepreciables a where a.idEstatusBien.idEstatusBien='1' and trim(a.codigoUnidad)=:codigo and trim(a.codigoInventario) like :inv and a.idBien not in (select b.idBien from AfTrasladosDetalle b) and a.idBien not in :temp", AfBienesDepreciables.class);
        q.setParameter("codigo", codigo);
        q.setParameter("inv", "%" + Inv + "%");
        q.setParameter("temp", lst);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return q.getResultList();
        }

    }

    public List<AfBienesDepreciables> getBienes(String codigo, String Inv) {
        Query q = em.createQuery("Select a from AfBienesDepreciables a where a.idEstatusBien.idEstatusBien='1' and trim(a.codigoUnidad)=:codigo and trim(a.codigoInventario) like :inv and a.idBien not in (select b.idBien from AfTrasladosDetalle b) ", AfBienesDepreciables.class);
        q.setParameter("codigo", codigo);
        q.setParameter("inv", "%" + Inv + "%");
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return q.getResultList();
        }

    }

    public List<VwBienes> getBienesTrasladar(Long Id) {
        Query q = em.createNativeQuery("Select * from Vw_Bienes a where  a.id_Bien=" + Id, VwBienes.class);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return q.getResultList();
        }

    }

    public Integer getBuscarCorrelativo(String uaf, String uadm, String tipo, String correlativo) {
        Query q = em.createQuery("Select count(a) from AfBienesDepreciables a where trim(a.unidadActivoFijo)=:uaf and trim(a.codigoUnidad)=:uadm and a.idTipoBien=:tipo and a.correlativo=:correlativo");
        q.setParameter("uaf", uaf);
        q.setParameter("uadm", uadm);
        q.setParameter("tipo", Long.parseLong(tipo));
        q.setParameter("correlativo", correlativo);
        if (!q.getResultList().isEmpty()) {
            return ((Long) q.getSingleResult()).intValue();
        } else {
            return 0;
        }
    }

    public Integer getCorrelativo(String uaf, String uadm, String tipo) {
        Query q = em.createQuery("Select max(a.correlativo)+1 from AfBienesDepreciables a where trim(a.unidadActivoFijo)=:uaf and trim(a.codigoUnidad)=:uadm and a.idTipoBien=:tipo");
        q.setParameter("uaf", uaf);
        q.setParameter("uadm", uadm);
        q.setParameter("tipo", Long.parseLong(tipo));
        if (!q.getResultList().isEmpty()) {
            if (q.getSingleResult() == null) {
                return 1;
            } else {
                return (Integer.parseInt(q.getSingleResult().toString()));
            }
        } else {
            return 1;
        }
    }

    public List<AfCategoriasBien> getCategoria() {
        Query q = em.createQuery("Select a from AfCategoriasBien a order by a.idCatBien", AfCategoriasBien.class);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return q.getResultList();
        }
    }

    public Long getCategoria(Long tipoBien) {
        Query q = em.createQuery("Select a.idCatBien.idCatBien from AfTipoBienes  a where a.idTipoBien=:tipo order by a.idTipoBien");
        q.setParameter("tipo", tipoBien);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return (Long) q.getSingleResult();
        }
    }

    public AfCategoriasBien getCatBien(Long id) {
        Query q = em.createQuery("Select a from AfCategoriasBien a where a.idCatBien=:id", AfCategoriasBien.class);
        q.setParameter("id", id);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return (AfCategoriasBien) q.getSingleResult();
        }
    }
    public Long getIdbyInv(String inventario){
        Query q = em.createQuery("select a.idBien from AfBienesDepreciables a  where a.codigoInventario = :codigo", AfBienesDepreciables.class);
        q.setParameter("codigo", inventario);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return (Long) q.getSingleResult();
        }
    }
    
    
    public String getDesBien(Long tipo) {
        Query q = em.createQuery("select a.nombreTipoBien from AfTipoBienes a  where a.idTipoBien = :codigo", AfTipoBienes.class);
        q.setParameter("codigo", tipo);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return q.getSingleResult().toString();
        }
    }

    public List<AfTipoBienes> getDesTipo(String tipo) {
        Query q = em.createQuery("select a from AfTipoBienes a  where  a.nombreTipoBien like :tipo or a.codigoTipoBien like :codigo", AfTipoBienes.class);
        q.setParameter("tipo", "%" + tipo + "%");
        q.setParameter("codigo", "%" + tipo + "%");
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return q.getResultList();
        }
    }

    public List<AfTipoBienes> getTipoBien() {
        Query q = em.createQuery("select a from AfTipoBienes a order by a.codigoTipoBien", AfTipoBienes.class);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return q.getResultList();
        }
    }

    public List<AfTipoBienes> getTipoBien(Long cat) {
        Query q = em.createQuery("select a from AfTipoBienes a where a.idCatBien.idCatBien=:cat order by a.nombreTipoBien", AfTipoBienes.class);
        q.setParameter("cat", cat);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return q.getResultList();
        }
    }

    public Long tipoBien(Long cat, Long codigo) {
        Query q = em.createQuery("select a.idTipoBien from AfTipoBienes a where a.idCatBien.idCatBien=:cat and a.IdTipoBien=:codigo order by a.nombreTipoBien", AfTipoBienes.class);
        q.setParameter("cat", cat);
        q.setParameter("codigo", codigo);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return Long.valueOf(q.getSingleResult().toString());
        }
    }

    public AfTipoBienes getTBien(Long id) {
        Query q = em.createQuery("select a from AfTipoBienes a where a.idTipoBien=:id order by a.nombreTipoBien,a.idCatBien.idCatBien", AfTipoBienes.class);
        q.setParameter("id", id);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return (AfTipoBienes) q.getSingleResult();
        }
    }

    public List<AfSeccionesUnidad> getSeccion(String unidad, String uaf) {
        Query q = em.createQuery("select a from AfSeccionesUnidad a where a.afUnidadesAdministrativas.afUnidadesAdministrativasPK.codigoUnidad=:unidad and a.afUnidadesAdministrativas.afUnidadesAdministrativasPK.unidadActivoFijo=:uaf ", AfSeccionesUnidad.class);
        q.setParameter("unidad", unidad);
        q.setParameter("uaf", uaf);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return q.getResultList();
        }
    }

    public List<AfSeccionesUnidad> getSeccion() {
        Query q = em.createQuery("select a from AfSeccionesUnidad a", AfSeccionesUnidad.class);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return q.getResultList();
        }
    }

    public List<AfEstatusBien> getEstatusBien() {
        Query q = em.createQuery("select a from AfEstatusBien a order by a.idEstatusBien", AfEstatusBien.class);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return q.getResultList();
        }
    }

    public AfEstatusBien getEstatusBien(Long id) {
        Query q = em.createQuery("select a from AfEstatusBien a where a.idEstatusBien=:id", AfEstatusBien.class);
        q.setParameter("id", id);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return (AfEstatusBien) q.getSingleResult();
        }
    }

    public List<AfCalidadBien> getCalidadBien() {
        Query q = em.createQuery("select a from AfCalidadBien a order by a.codigoCalidadBien", AfCalidadBien.class);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return q.getResultList();
        }
    }

    public AfCalidadBien getCalidadBien(Long id) {
        Query q = em.createQuery("select a from AfCalidadBien a where a.codigoCalidadBien=:id order by a.codigoCalidadBien", AfCalidadBien.class);
        q.setParameter("id", id);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return (AfCalidadBien) q.getSingleResult();
        }
    }

    public List<AfFormaAdquisicion> getFormaAdquisicion() {
        Query q = em.createQuery("select a from AfFormaAdquisicion a order by a.idFormaAdquisicion", AfFormaAdquisicion.class);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return q.getResultList();
        }
    }

    public AfFormaAdquisicion getFormaAdquisicion(Long id) {
        Query q = em.createQuery("select a from AfFormaAdquisicion a where a.idFormaAdquisicion=:id order by a.idFormaAdquisicion", AfFormaAdquisicion.class);
        q.setParameter("id", id);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return (AfFormaAdquisicion) q.getSingleResult();
        }
    }

    public List<AfFuenteFinanciamiento> getFuenteCE() {
        Query q = em.createQuery("select a from AfFuenteFinanciamiento a where a.idFuente in (2,3,5) order by a.idFuente asc", AfFuenteFinanciamiento.class);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return q.getResultList();
        }
    }

    public List<AfFuenteFinanciamiento> getFuente() {
        Query q = em.createQuery("select a from AfFuenteFinanciamiento a order by a.idFuente asc", AfFuenteFinanciamiento.class);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return q.getResultList();
        }
    }

    public AfFuenteFinanciamiento getFuente(Long id) {
        Query q = em.createQuery("select a from AfFuenteFinanciamiento a where a.idFuente=:id order by a.idFuente", AfFuenteFinanciamiento.class);
        q.setParameter("id", id);
        if (q.getResultList().isEmpty()) {
            return new AfFuenteFinanciamiento();
        } else {
            return (AfFuenteFinanciamiento) q.getSingleResult();
        }
    }

    public List<AfProyectos> getProyectosCE() {
        Query q = em.createQuery("select a from AfProyectos a where a.codigoProyecto in ('5813','5657') order by a.idProyecto", AfProyectos.class);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return q.getResultList();
        }
    }

    public List<AfProyectos> getProyectos() {
        Query q = em.createQuery("select a from AfProyectos a order by a.idProyecto desc", AfProyectos.class);
        return q.getResultList();
    }

    public String getProyecto(String codigo) {
        Query q = em.createNativeQuery("select a.id_proyecto from af_proyectos a where a.codigo_proyecto=" + codigo);

        if (q.getSingleResult() != null) {
            return q.getSingleResult().toString();
        } else {
            return null;
        }

    }

    public AfProyectos getProyectos(Long id) {
        Query q = em.createQuery("select a from AfProyectos a where a.idProyecto order by a.idProyecto", AfProyectos.class);
        q.setParameter("id", id);
        if (q.getResultList().isEmpty()) {
            return new AfProyectos();
        } else {
            return (AfProyectos) q.getSingleResult();
        }
    }

    public String getVidaUtil(Integer idCat) {
        Query q = em.createQuery("select a from AfCategoriasBien a where a.idCatBien=:idCat");
        q.setParameter("idCat", idCat);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return q.getSingleResult().toString();
        }
    }

}
