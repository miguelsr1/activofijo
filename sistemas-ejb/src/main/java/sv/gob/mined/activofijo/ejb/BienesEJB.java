/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.gob.mined.activofijo.ejb;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import sv.gob.mined.activofijo.model.AfBienesDepreciables;
import sv.gob.mined.activofijo.model.AfDepreciaciones;
import sv.gob.mined.activofijo.model.AfDescargos;
import sv.gob.mined.activofijo.model.AfDescargosDetalle;
import sv.gob.mined.activofijo.model.AfEmpleados;
import sv.gob.mined.activofijo.model.AfEstatusBien;
import sv.gob.mined.activofijo.model.AfSolvencias;
import sv.gob.mined.activofijo.model.AfTipoBienes;
import sv.gob.mined.activofijo.model.AfTraslados;
import sv.gob.mined.activofijo.model.AfTrasladosDetalle;
import sv.gob.mined.activofijo.model.AfUnidadesAdministrativas;
import sv.gob.mined.activofijo.model.VwBienes;
import sv.gob.mined.activofijo.model.VwDescargos;
import sv.gob.mined.activofijo.model.VwCorrelativos;
import sv.gob.mined.activofijo.model.VwDatosxCuentas;
import sv.gob.mined.activofijo.model.VwDepreciaciones;
import sv.gob.mined.activofijo.model.VwMunicipio;
import sv.gob.mined.activofijo.model.VwSolvencia;
import sv.gob.mined.activofijo.model.VwVehiculos;
import sv.gob.mined.activofijo.model.dto.DatosUnidad;
import sv.gob.mined.activofijo.model.dto.DatosxCuentas;
import sv.gob.mined.activofijo.model.dto.DatosDepreciacion;
import sv.gob.mined.activofijo.model.dto.DatosSolvencia;
import sv.gob.mined.activofijo.model.dto.DatosVehiculos;


/**
 *
 * @author JISTorres
 */
@Stateless
public class BienesEJB {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "activoFijoPU", type = PersistenceContextType.TRANSACTION)
    public EntityManager em;

    public AfEmpleados getEmpAdmin(String Id){
        Query q = em.createNativeQuery("select * from  Af_empleados a where a.id_empleado=" + Id, AfEmpleados.class);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return (AfEmpleados) q.getResultList().get(0);
        }
    }
    public List<AfTipoBienes> getTipoxId(Long Id){
        Query q = em.createNativeQuery("select * from  Af_Tipo_bienes a where a.id_tipo_bien=" + Id, AfTipoBienes.class);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return q.getResultList();
        }

    }
    public AfTraslados getTraslado(Long Id) {
        Query q = em.createNativeQuery("select * from  Af_Traslados a where a.id_traslado=" + Id, AfTraslados.class);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return (AfTraslados) q.getResultList().get(0);
        }

    }

    public AfDescargos getDescargos(Long Id) {
        Query q = em.createNativeQuery("select * from  Af_descargos a where a.descargo_id=" + Id, AfDescargos.class);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return (AfDescargos) q.getResultList().get(0);
        }

    }

    public AfDescargosDetalle getDescargoDet(Long Id) {
        Query q = em.createNativeQuery("select a.* from  Af_descargos_detalle a where a.descargo_id=" + Id, AfDescargosDetalle.class);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return (AfDescargosDetalle) q.getResultList().get(0);
        }
    }

    public Integer getBienesDescargo(Long Id) {
        Query q = em.createNativeQuery("select count(*) from  Af_descargos_detalle a where a.id_descargo=" + Id);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return (Integer) q.getResultList().get(0);
        }
    }

    public Integer getBienesTraslado(Long Id) {
        Query q = em.createNativeQuery("select count(*) from  Af_traslados_detalle a where a.id_traslado=" + Id);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return (Integer) q.getResultList().get(0);
        }

    }

    public void guardarDescargo(AfDescargos tras, String usu) {
        //em.getTransaction().begin();
        try {
            

            if (tras.getDescargoId() != null) {
                tras.setUsuarioModifica(usu);
                tras.setFechaModifica(new Date());
                em.merge(tras);
            } else {
                tras.setUsuarioCrea(usu);
                tras.setFechaSolicitud(new Date());
                tras.setFechaCreacion(new Date());
                em.persist(tras);
            }
        } catch (Exception e) {
           System.out.println("Error al Almacenar el descargo "+ e);
      }
    }
    
    public void editarUnidadAdmin(AfUnidadesAdministrativas adm,String usu){
       try{
            adm.setUsuarioModifica(usu);
            adm.setFechaModifica(new Date());
            em.merge(adm);
     }catch( Exception e){
      System.out.println("Error al Almacenar Unidad administrativa "+ e);
     }  
    }  
    
    public void guardarUnidadAdmin(AfUnidadesAdministrativas adm,String usu){
    try{
        adm.setUsuarioAdicion(usu);
        adm.setFechaAdicion(new Date());
       em.persist(adm);
    }catch( Exception e){
      System.out.println("Error al Almacenar el empleado "+ e);
     }    
    } 

    public void guardarEmpleado(AfEmpleados emp,String usu){
    try{
    
        if(emp.getIdEmpleado()!=null){
            emp.setUsuarioMod(usu);
            emp.setFechaMod(new Date());
            em.merge(emp);
        }else{
            emp.setUsuarioCrea(usu);
            emp.setFechaCrea(new Date());
            em.persist(emp);
        }
        
    }catch( Exception e){
      System.out.println("Error al Almacenar el empleado "+ e);
     }    
    } 
    public void inactivarTipoBien(AfTipoBienes tipo,String usu){
    try{
        
            tipo.setUsuarioModifica(usu);
            tipo.setFechaModifica(new Date());
            em.merge(tipo);
        
    }catch( Exception e){
      System.out.println("Error al Almacenar el tipo de bien "+ e);
     }    
    }   
    
    
    public void guardarTipoBien(AfTipoBienes tipo,String usu){
    try{
    
        if(tipo.getIdTipoBien()!=null){
            tipo.setUsuarioModifica(usu);
            tipo.setFechaModifica(new Date());
            em.merge(tipo);
        }else{
            tipo.setUsuarioAdicion(usu);
            tipo.setFechaAdicion(new Date());
            em.persist(tipo);
        }
        
    }catch( Exception e){
      System.out.println("Error al Almacenar el tipo de bien "+ e);
     }    
    }   
    
    
    public void guardarTraslado(AfTraslados tras, String usu) {
     //   em.getTransaction().begin();
     try {
        if (tras.getIdTraslado() != null) {
            if (tras.getEstado()=='1'){
                tras.setFechaAutoriza(new Date());
            }
            tras.setUsuarioModifica(usu);
            tras.setFechaModifica(new Date());
            em.merge(tras);
        } else {
            tras.setUsuarioCrea(usu);
            tras.setFechaCreacion(new Date());
            tras.setFechaSolicitud(new Date());
            em.persist(tras);
        }
     }catch( Exception e){
      System.out.println("Error al Almacenar el traslado "+ e);
     }
     //   em.getTransaction().commit();
    }

    public AfEstatusBien obtenerEstatus(Long idEstatus) {
        Query q = em.createNativeQuery("select a.* from Af_estatus_bien a where a.id_estatus_bien=" + idEstatus, AfEstatusBien.class);
        if (q.getSingleResult() == null) {
            return null;
        } else {
            return (AfEstatusBien) q.getSingleResult();
        }

    }
    public List<VwDepreciaciones> repDepreciacion(String condicion){
        
        Query q = em.createNativeQuery("select d.anio,d.id_bien from af_depreciaciones d  "+condicion+" order by d.id_bien,d.anio,d.mes",VwDepreciaciones.class);
        if (q.getResultList() == null) {
            return null;
        } else {
            return q.getResultList();
        }
  
         
    }

    public Integer calcularDepreciacion(String idBien, String Anio, String mes, String fuente, String proyecto) {
        Integer bienes = 0;
        try {
        //    em.getTransaction().begin();
            java.sql.Connection connection = em.unwrap(java.sql.Connection.class);

            CallableStatement cstmt = connection.prepareCall("{call PRD_DEPRECIACION(?1, ?2, ?3, ?4, ?5,?6)}");
            cstmt.setString("pBien", idBien);
            cstmt.setString("pAnio", Anio);
            cstmt.setString("pMes", mes);
            cstmt.setString("pFuente", fuente);
            cstmt.setString("pProyecto", proyecto);
            cstmt.registerOutParameter("pBienes", java.sql.Types.INTEGER);
            cstmt.execute();
            bienes = cstmt.getInt("pBienes");
          //  System.out.println("NumBienes: " + bienes);

          //  em.getTransaction().commit();
            cstmt.close();
            
        } catch (Exception e) {
             System.out.println("Error al realizar cálculo de Depreciación "+ e);
        }
       return bienes;
    }
    public void removeTraslado(Long idTd){
         AfTraslados td;
         AfTrasladosDetalle tdet ;
         
        try{
            Query q = em.createNativeQuery("select * from af_traslados where id_traslado=" + idTd, AfTraslados.class);
            if (!q.getResultList().isEmpty() && q.getResultList() != null) {
                td = (AfTraslados) q.getResultList().get(0);
                
                q = em.createNativeQuery("select * from af_traslados_detalle where id_traslado=" + idTd, AfTrasladosDetalle.class);
                if (!q.getResultList().isEmpty() && q.getResultList() != null) {
                     tdet = (AfTrasladosDetalle) q.getResultList().get(0);
                   try {
                      em.remove(tdet);
                      em.remove(td);
                    }catch(Exception e){
                        System.out.println("Error al eliminar traslados "+ e);
                    } 
                } else {
                    try{
                       em.remove(td);
                    }catch(Exception e){
                        System.out.println("Error al eliminar traslado detalle "+ e);
                    }       
                }
            }
           }catch(Exception e){
             System.out.println("Error al eliminar traslado "+ e);
           }   
    }

    public void removeBien(Long idBien) {
        AfBienesDepreciables bd ;
        AfDescargosDetalle dd ;
        AfDescargos d ;
        AfTraslados t ;
        AfTrasladosDetalle td ;
        AfDepreciaciones depre;
try{
        Query q = em.createNativeQuery("select * from af_bienes_depreciables A  where a.id_bien=" + idBien, AfBienesDepreciables.class);
        bd = (AfBienesDepreciables) q.getResultList().get(0);

        q = em.createNativeQuery("select * from af_descargos_detalle where id_bien=" + idBien, AfDescargosDetalle.class);
        if (!q.getResultList().isEmpty() && q.getResultList() != null) {
            dd = (AfDescargosDetalle) q.getResultList().get(0);

            q = em.createNativeQuery("select * from af_descargos where descargo_id=" + dd.getDescargoId().getDescargoId(), AfDescargos.class);
            d = (AfDescargos) q.getResultList().get(0);

            q = em.createNativeQuery("select count(*) from af_descargos_detalle where descargo_id=" + dd.getDescargoId().getDescargoId());
            if (Integer.valueOf(q.getSingleResult().toString()) == 1) {
               try{ 
                em.remove(dd);
                em.remove(d);
               }catch(Exception e){
                System.out.println("Error al eliminar descargos "+ e);
            } 
            } else {
              try{
                em.remove(dd);
              }catch(Exception e){
                System.out.println("Error al eliminar descargos detalle "+ e);
            } 
            }
        }
        q = em.createNativeQuery("select * from af_traslados_detalle where id_bien=" + idBien, AfTrasladosDetalle.class);
        if (!q.getResultList().isEmpty() && q.getResultList() != null) {
            td = (AfTrasladosDetalle) q.getResultList().get(0);

            q = em.createNativeQuery("select * from af_traslados where id_traslado=" + td.getIdTraslado(), AfTraslados.class);
            t = (AfTraslados) q.getResultList().get(0);

            q = em.createNativeQuery("select count(*) from af_traslados_detalle where id_traslado=" + td.getIdTraslado());
            if (Integer.valueOf(q.getSingleResult().toString()) >= 1) {
               try {
                em.remove(td);
                    em.remove(t);
              }catch(Exception e){
                System.out.println("Error al eliminar traslados "+ e);
               } 
                    
            } else {
                try{
                em.remove(td);
              }catch(Exception e){
                System.out.println("Error al eliminar traslado detalle "+ e);
               } 
             

            }
        }
        q = em.createNativeQuery("delete af_Depreciaciones where id_bien= "+idBien);
        q.executeUpdate();
        
       em.remove(bd);
    }catch( Exception e){
    System.out.println("Error al eliminar bien "+ e);
}
    }

    public void removeDetalleTraslado(Long idTras, Long idBien) {
      try{
        AfTrasladosDetalle td = new AfTrasladosDetalle();
        Query q = em.createNativeQuery("select * from af_traslados_detalle A  where a.id_traslado=" + idTras + " and id_Bien=" + idBien, AfTrasladosDetalle.class);
        td = (AfTrasladosDetalle) q.getResultList().get(0);
        em.remove(td);
        }catch(Exception e){
                System.out.println("Error al eliminar traslados detalle "+ e);
        } 
    }

    public void removeDetalleDescargo(Long idDes, Long idBien) {
      try{
        AfDescargosDetalle td = new AfDescargosDetalle();
        Query q = em.createNativeQuery("select * from af_descargos_detalle A  where a.descargo_id=" + idDes + " and id_Bien=" + idBien, AfDescargosDetalle.class);
        td = (AfDescargosDetalle) q.getResultList().get(0);
        em.remove(td);
       }catch(Exception e){
                System.out.println("Error al eliminar descargos detalle "+ e);
      } 
             
    }

    public void actualizarEstadoBien(List<VwBienes> vb, String estado) {
    try{
        for (VwBienes object : vb) {
            AfBienesDepreciables b = buscarBien(object.getIdBien());
            if (estado.equals("P")) {
                b.setIdEstatusBien(obtenerEstatus(Long.parseLong("2")));
            } else {
                if (estado.equals("D")) {
                    b.setIdEstatusBien(obtenerEstatus(Long.parseLong("3")));
                } else {
                    if (!estado.equals("S")) {
                         b.setIdEstatusBien(obtenerEstatus(Long.parseLong("1")));
                    }     
                }
            }
            em.merge(b);
        }
         }catch(Exception e){
                System.out.println("Error al actualizar estados de bien "+ e);
               } 
    }

    public void actualizarBienTraslado(List<VwBienes> vb,String unidad,String uAF) {
      try{ 
        for (VwBienes object : vb) {
            AfBienesDepreciables b = buscarBien(object.getIdBien());
            b.setCodigoUnidad(unidad);
            b.setUnidadActivoFijo(uAF);
            em.merge(b);
        }
     }catch(Exception e){
                System.out.println("Error al actualizar estados de bien "+ e);
               } 
    }
    
    
    
    public void guardarTrasladoDetalle(List<VwBienes> vb, AfTraslados des) {
       try{
        for (VwBienes object : vb) {
            AfTrasladosDetalle td = buscarDetalleTras(object.getIdTraslado(),object.getIdTrasladoDet());
            if (td.getIdTrasladoDetalle()== null) { //nuevos
                 td.setIdTraslado(des.getIdTraslado());
                 td.setIdBien(object.getIdBien());
                em.persist(td);
            } 
        }
        
      }catch(Exception e){
                System.out.println("Error al guardar traslado detalle "+ e);
               } 
    }

    public AfTrasladosDetalle buscarDetalleTras(Long idDes,Long desDet){
        AfTrasladosDetalle td = new AfTrasladosDetalle();
        if (idDes!=null){
            Query q = em.createNativeQuery("select * from af_traslados_detalle A  where a.id_traslado=" + idDes + " and id_traslado_detalle=" + desDet, AfTrasladosDetalle.class);
            if (q.getResultList()!=null) {
                td = (AfTrasladosDetalle) q.getResultList().get(0);
        }
        }
       return td;
        
    }
    
    public AfDescargosDetalle buscarDetalle(Long idDes,Long desDet){
        AfDescargosDetalle td = new AfDescargosDetalle();
        if (idDes!=null){
            Query q = em.createNativeQuery("select * from af_descargos_detalle A  where a.descargo_id=" + idDes + " and id_detalle_descargo=" + desDet, AfDescargosDetalle.class);
            if (q.getResultList()!=null) {
                td = (AfDescargosDetalle) q.getResultList().get(0);
        }
        }
       return td;
        
    }
    
    public void guardarDescargoDetalle(List<VwBienes> vb, AfDescargos des) {
      try{
        for (VwBienes object : vb) {
            AfDescargosDetalle td = buscarDetalle(object.getDescargoId(),object.getIdDetalleDescargo());
            if (td.getIdDetalleDescargo() == null) { //nuevos
                 td.setDescargoId(des);
                 td.setIdBien(BigInteger.valueOf(object.getIdBien()));
                 td.setCodigoUnidad(object.getCodigoUnidad());
                 td.setIdTipoDescargo(des.getIdTipoDescargo());
           
                em.persist(td);
            } 
        }
    }catch(Exception e){
                System.out.println("Error al guardar descargo detalle "+ e);
               } 
    }

    public void guardarSol(AfSolvencias sol, String usu) {
    try{
        if (sol.getIdSolvencia() != null) {
            sol.setUsuarioModifica(usu);
            sol.setFechaModifica(new Date());
            em.merge(sol);
        } else {
            sol.setUsuarioCreo(usu);
            sol.setFechaSolvencia(new Date());
            em.persist(sol);
        }
    }catch(Exception e){
                System.out.println("Error al guardar solvencia "+ e);
               } 
    }

    public void guardarBien(AfBienesDepreciables bienes, String usu) {
    try{
        if (bienes.getIdBien() != null) {
            bienes.setUsuarioModif(usu);
            bienes.setFechaModif(new Date());
            em.merge(bienes);
            // JsfUtil.mensajeUpdate();
        } else {
            bienes.setUsuarioCrea(usu);
            bienes.setFechaCreacion(new Date());
            em.persist(bienes);
            //JsfUtil.mensajeInsert();
        }
     }catch(Exception e){
                System.out.println("Error al guardar bien "+ e);
               } 
    }

    public List<VwDepreciaciones> depreciacionAnio(String Anio, String mes) {
        List<VwDepreciaciones> lstDepre = new ArrayList();
        Query q = em.createNativeQuery("select a.* from vw_depreciaciones a where a.anio=" + Anio + " and mes=nvl(" + mes + ",mes) order by a.mes ");
        List lst = q.getResultList();
        for (Object object : lst) {
            Object[] datos = (Object[]) object;
            VwDepreciaciones datDepre = new VwDepreciaciones();
            datDepre.setAnio(datos[0].toString());
            datDepre.setMes(datos[1].toString());
            datDepre.setBienesDepreciados(Integer.valueOf(datos[2].toString()));
            lstDepre.add(datDepre);
        }
        return lstDepre;
    }

    public List<VwSolvencia> buscarSolEmitidas(String condicion){
        String condicion2="";
        
       if (!condicion.equals("")) {
           condicion2 = "where "+condicion+" and fecha_solvencia is not null";
        }else{
           condicion2 = "where  fecha_solvencia is not null";
        }
        String sql=" select rownum as idRow, CODIGO_ENTIDAD as codigoEntidad, UNIDAD_ACTIVO_FIJO as unidadActivoFijo,NOMBRE_MUNICIPIO as nombreMunicipio,NOMBRE as nombre,"+
                   " FECHA_SOLVENCIA as fechaSolvencia, ANIO as anio, nvl(NUMBIENES,0) as numBienes, nvl(COSTO,0) as costo,codigo_municipio as codigoMunicipio,fecha_actualizacion as fechaActualizacion,tipo_unidad as tipoUnidad "+
                   " from  vw_solvencia "+condicion2;
         Query q = em.createNativeQuery(sql,VwSolvencia.class);
        
          return q.getResultList();
    }
    
    public List<VwSolvencia> buscarEntidades(String condicion){
        String condicion2="";
        
        if (!condicion.equals("")) {
           condicion2 = "where "+condicion;
        }
        String sql=" select   rownum as idRow, CODIGO_ENTIDAD as codigoEntidad, UNIDAD_ACTIVO_FIJO as unidadActivoFijo,NOMBRE_MUNICIPIO as nombreMunicipio,NOMBRE as nombre,"+
                   " FECHA_SOLVENCIA as fechaSolvencia, ANIO as anio, nvl(NUMBIENES,0) as numBienes, nvl(COSTO,0) as costo,codigo_municipio as codigoMunicipio,fecha_actualizacion as fechaActualizacion,tipo_unidad as tipoUnidad "+
                   " from  vw_solvencia "+condicion2;
         Query q = em.createNativeQuery(sql,VwSolvencia.class);
        
          return q.getResultList();
    }
    
    public List<VwMunicipio> buscarMunicipios(String unidad){
        String sql="select rownum as idRow,codigo_departamento as codigoDepartamento,codigo_municipio as codigoMunicipio,nombre_municipio as nombreMunicipio,unidad_activo_fijo as unidadActivoFijo "+
                   "from Vw_municipio where unidad_activo_fijo='"+unidad+"'";
        Query q = em.createNativeQuery(sql,VwMunicipio.class);
        
          return q.getResultList();
        
    }
    
    
    public List<VwCorrelativos> buscarCorrelativos(String condicion) {
        List<VwCorrelativos> lstCorrelativos = new ArrayList();
        Query q = em.createNativeQuery("select A.* from Vw_Correlativos A " + condicion + " order by A.codigo_tipo_bien");
        List lst = q.getResultList();
        for (Object object : lst) {
            Object[] datos = (Object[]) object;
            VwCorrelativos datPlan = new VwCorrelativos();
            datPlan.setCorrelativo(datos[0].toString());
            datPlan.setIdTipoBien(Long.parseLong(datos[1].toString()));
            datPlan.setCodigoTipoBien(datos[2].toString());
            datPlan.setNombreTipoBien(datos[3].toString());
            datPlan.setIdCatBien(Long.parseLong(datos[4].toString()));
            datPlan.setCodigoUnidad(datos[5].toString());
            datPlan.setUnidadActivoFijo(datos[6].toString());
            datPlan.setNombreUnidadAf(datos[7].toString());
            datPlan.setNombreUnidad(datos[8].toString());

            lstCorrelativos.add(datPlan);
        }
        return lstCorrelativos;
    }

    public List<AfDepreciaciones> buscarDepre(String condicion){
        
         String sqlQuery = "select d.* from Af_depreciaciones d "+ condicion+" order by d.anio,d.mes " ;

        Query q = em.createNativeQuery(sqlQuery, AfDepreciaciones.class);
        return q.getResultList();
    }
    
    public List<VwDatosxCuentas> buscarDatos(String condicion){
        String condicion2 = " where a.id_estatus_bien in (1,2) ";
        //System.out.println(condicion);
        if (!condicion.equals("")) {
            condicion2 = condicion2+" and "+ condicion;
        }

        List<VwDatosxCuentas> lstBienes = new ArrayList<VwDatosxCuentas>();
      
        Query q = em.createNativeQuery("select a.CATEGORIA,sum(a.valor_adquisicion),sum(a.valor_adquisicion)*0.10 valor_residual,nvl(sum(a.monto_depreciacion) ,0) depre_acumulada,(sum(a.valor_adquisicion)- sum(a.valor_adquisicion)*0.10)-nvl(sum(a.monto_depreciacion) ,0) pendiente " +
                                        " from vw_bienes a " +condicion2+"  group by a.CATEGORIA");
        List lst = q.getResultList();
    
        for (Object object : lst) {
      
            Object[] datos = (Object[]) object;
            VwDatosxCuentas datPlan = new VwDatosxCuentas();
            datPlan.setCategoria(datos[0].toString());
            if (datos[1] != null) {
                datPlan.setPrecio((BigDecimal) datos[1]);
            } else {
                datPlan.setPrecio(null);
            }
            if (datos[2] != null) {
                datPlan.setValorRes((BigDecimal) datos[2]);
            } else {
                datPlan.setValorRes(null);
            }
            if (datos[3] != null) {
                datPlan.setDepreAcumulada((BigDecimal) datos[3]);
            } else {
                datPlan.setDepreAcumulada(null);
            }
            if (datos[4] != null) {
                datPlan.setPendienteDepre((BigDecimal) datos[4]);
            } else {
                datPlan.setPendienteDepre(null);
            }
           lstBienes.add(datPlan);
        }
           return lstBienes;
    }
    
    public List<VwVehiculos> buscarVehiculos(String condicion){
        String condicion2 = "";
       // System.out.println(condicion);
        if (!condicion.equals("")) {
            condicion2 = " where " + condicion;
        }
        String sql=" select rownum as idRow,a.CODIGO_INVENTARIO as codigoInventario,a.DESCRIPCION_BIEN as descripcionBien,a.MARCA as marca, " +
                   "      a.NUMERO_PLACA as numeroPlaca,a.NUMERO_MOTOR as numeroMotor,a.NUMERO_CHASIS as numeroChasis,a.COLOR_CARROCERIA as colorCarroceria, " +
                   "      a.CALIDAD as calidad,a.FECHA_ADQUISICION as fechaAdquisicion,a.VALOR_ADQUISICION as valorAdquisicion,a.NOMBRE_UNIDAD as nombreUnidad, " +
                   "      a.ASIGNADO as asignado  from vw_Vehiculos a "+condicion2;
         Query q = em.createNativeQuery(sql,VwVehiculos.class);
        
      // Query q = em.createNativeQuery("select a.* from vw_Vehiculos a " + condicion2 ,VwVehiculos.class);
       return q.getResultList();
    }
    public List<AfTipoBienes> buscarTipoBien(String condicion){
         String condicion2 = "";
       // System.out.println(condicion);
        if (!condicion.equals("")) {
            condicion2 = " where " + condicion;
        }
        
        String Sql ="select * from af_tipo_bienes a "+condicion2;
        Query q = em.createNativeQuery(Sql,AfTipoBienes.class);
        if (!q.getResultList().isEmpty()){
            return q.getResultList();
        }else{
            return null;
        }
    }
    
    public List<AfUnidadesAdministrativas> buscarUnidadesAdmin(String condicion){
         String condicion2 = "";
       // System.out.println(condicion);
        if (!condicion.equals("")) {
            condicion2 = " where " + condicion;
        }
        
        String Sql ="select * from af_unidades_administrativas a "+condicion2;
        Query q = em.createNativeQuery(Sql,AfUnidadesAdministrativas.class);
        if (!q.getResultList().isEmpty()){
            return q.getResultList();
        }else{
            return null;
        }
    }
    
    public List<AfEmpleados> buscarEmpleados(String condicion){
         String condicion2 = "";
       // System.out.println(condicion);
        if (!condicion.equals("")) {
            condicion2 = " where " + condicion;
        }
        
        String Sql ="select * from af_Empleados a "+condicion2;
        Query q = em.createNativeQuery(Sql,AfEmpleados.class);
        if (!q.getResultList().isEmpty()){
            return q.getResultList();
        }else{
            return null;
        }
    }
    
    public List<VwBienes> buscarBien(String condicion) {
        String condicion2 = "";
       // System.out.println(condicion);
        if (!condicion.equals("")) {
            condicion2 = " where " + condicion;
        }

        List<VwBienes> lstBienes = new ArrayList<VwBienes>();
        Query q = em.createNativeQuery("select A.* from Vw_Bienes A " + condicion2 + " order by A.codigo_inventario");
        List lst = q.getResultList();
        for (Object object : lst) {
            Object[] datos = (Object[]) object;
            VwBienes datPlan = new VwBienes();
            datPlan.setIdBien(Long.parseLong(datos[0].toString()));
            if (datos[1] != null) {
                datPlan.setCodigoInventario(datos[1].toString());
            } else {
                datPlan.setCodigoInventario(null);
            }
            if (datos[2] != null) {
                datPlan.setCategoria(datos[2].toString());
            } else {
                datPlan.setCategoria(null);
            }
            if (datos[3] != null) {
                datPlan.setDescripcionBien(datos[3].toString());
            } else {
                datPlan.setDescripcionBien(null);
            }
            if (datos[4] != null) {
                datPlan.setMarca(datos[4].toString());
            } else {
                datPlan.setMarca(null);
            }
            if (datos[5] != null) {
                datPlan.setFechaAdquisicion((Date) datos[5]);
            } else {
                datPlan.setFechaAdquisicion(null);
            }
            if (datos[6] != null) {
                datPlan.setValorAdquisicion((BigDecimal) datos[6]);
            } else {
                datPlan.setValorAdquisicion(null);
            }
            if (datos[7] != null) {
                datPlan.setModelo(datos[7].toString());
            } else {
                datPlan.setModelo(null);
            }
            if (datos[8] != null) {
                datPlan.setNumeroSerie(datos[8].toString());
            } else {
                datPlan.setNumeroSerie(null);
            }
            if (datos[9] != null) {
                datPlan.setActivoFijo(datos[9].toString().charAt(0));
            } else {
                datPlan.setActivoFijo(null);
            }
            if (datos[10] != null) {
                datPlan.setEstado(datos[10].toString());
            } else {
                datPlan.setEstado(null);
            }
            if (datos[11] != null) {
                datPlan.setUnidadActivoFijo(datos[11].toString());
            } else {
                datPlan.setUnidadActivoFijo(null);
            }
            if (datos[12] != null) {
                datPlan.setCodigoUnidad(datos[12].toString());
            } else {
                datPlan.setCodigoUnidad(null);
            }
            if (datos[13] != null) {
                datPlan.setEstadoRegistro(datos[13].toString());
            } else {
                datPlan.setEstadoRegistro(null);
            }
            if (datos[14] != null) {
                datPlan.setIdCatBien(Long.parseLong(datos[14].toString()));
            } else {
                datPlan.setIdCatBien(null);
            }
            if (datos[15] != null) {
                datPlan.setCodigoTipoBien(datos[15].toString());
            } else {
                datPlan.setCodigoTipoBien(null);
            }
            if (datos[16] != null) {
                datPlan.setCodigoSeccion(datos[16].toString());
            } else {
                datPlan.setCodigoSeccion(null);
            }
            if (datos[17] != null) {
                datPlan.setCodigoCalidadBien(Long.parseLong(datos[17].toString()));
            } else {
                datPlan.setCodigoCalidadBien(null);
            }
            if (datos[18] != null) {
                datPlan.setIdEstatusBien(Long.parseLong(datos[18].toString()));
            } else {
                datPlan.setIdEstatusBien(null);
            }
            if (datos[19] != null) {
                datPlan.setIdFuente(Long.parseLong(datos[19].toString()));
            } else {
                datPlan.setIdFuente(null);
            }
            if (datos[20] != null) {
                datPlan.setIdProyecto(Long.parseLong(datos[20].toString()));
            } else {
                datPlan.setIdProyecto(null);
            }
            if (datos[21] != null) {
                datPlan.setFechaCreacion((Date) datos[21]);
            } else {
                datPlan.setFechaCreacion(null);
            }
            if (datos[22] != null) {
                datPlan.setUsuarioCreacion(datos[22].toString());
            } else {
                datPlan.setUsuarioCreacion("");
            }

            if (datos[23] != null) {
                datPlan.setFuente(datos[23].toString());
            } else {
                datPlan.setFuente("");
            }
            if (datos[24] != null) {
                datPlan.setNombreProyecto(datos[24].toString());
            } else {
                datPlan.setNombreProyecto("");
            }
            if (datos[25] != null) {
                datPlan.setDocumentoAdquisicion(datos[25].toString());
            } else {
                datPlan.setDocumentoAdquisicion("");
            }
            if (datos[26] != null) {
                datPlan.setProveedor(datos[26].toString());
            } else {
                datPlan.setProveedor("");
            }
            if (datos[27] != null) {
                datPlan.setIdFormaAdquisicion(Long.parseLong(datos[27].toString()));
            } else {
                datPlan.setIdFormaAdquisicion(null);
            }
            if (datos[28] != null) {
                datPlan.setCalidad(datos[28].toString());
            } else {
                datPlan.setCalidad("");
            }
            if (datos[29] != null) {
                datPlan.setAsignado(datos[29].toString());
            } else {
                datPlan.setAsignado("");
            }
            if (datos[30] != null) {
                datPlan.setIdTrasladoDet(Long.parseLong(datos[30].toString()));
            } else {
                datPlan.setIdTrasladoDet(null);
            }
            if (datos[31] != null) {
                datPlan.setIdTraslado(Long.parseLong(datos[31].toString()));
            } else {
                datPlan.setIdTraslado(null);
            }
            if (datos[32] != null) {
                datPlan.setIdDetalleDescargo(Long.parseLong(datos[32].toString()));
            } else {
                datPlan.setIdDetalleDescargo(null);
            }
            if (datos[33] != null) {
                datPlan.setDescargoId(Long.parseLong(datos[33].toString()));
            } else {
                datPlan.setDescargoId(null);
            }
            if (datos[34] != null) {
                datPlan.setTipoDescargo(datos[34].toString().charAt(0));
            } else {
                datPlan.setTipoDescargo(null);
            }
            if (datos[35] != null) {
                datPlan.setEstadoDescargo(datos[35].toString().charAt(0));
            } else {
                datPlan.setEstadoDescargo(null);
            }
            if (datos[36] != null) {
                datPlan.setFechaDescargo((Date) datos[36]);
            } else {
                datPlan.setFechaDescargo(null);
            }
            if (datos[37] != null) {
                datPlan.setCodigoDescargo(datos[37].toString());
            } else {
                datPlan.setCodigoDescargo(null);
            }

            if (datos[38] != null) {
                datPlan.setMontoDepreciacion((BigDecimal) datos[38]);
            } else {
                datPlan.setMontoDepreciacion(null);
            }

            lstBienes.add(datPlan);
        }

        return lstBienes;
    }

    public List<AfTraslados> buscarTraslado(String condicion) {
        if (condicion.trim().equals("where")) {
            condicion = null;
        }
        Query q = em.createNativeQuery("select * from Af_Traslados a " + condicion+" order by a.fecha_traslado", AfTraslados.class);
        if (!q.getResultList().isEmpty()){
           return q.getResultList();
        }else{
            return null;
        }
    }

    public List<VwDescargos> buscarDescargos(String condicion) {

        String Sql = "select * from vw_descargos a " + condicion;

        List<VwDescargos> lst = new ArrayList<VwDescargos>();
        Query q = em.createNativeQuery(Sql);

        for (Object dato : q.getResultList()) {
            Object[] ob = (Object[]) dato;
            VwDescargos d = new VwDescargos();
            d.setDescargoId(Long.valueOf(ob[0].toString()));
            if (ob[1]==null){
                d.setCodigoDescargo(null);
            }else { d.setCodigoDescargo(ob[1].toString());}
            d.setNombreUnidad(ob[2].toString());
            if (ob[3]!=null){  d.setFechaDescargo((Date) ob[3]);}
            else {d.setFechaDescargo(null);}
            d.setBienes((BigDecimal) ob[4]);
            d.setTotal((BigDecimal) ob[5]);
            d.setNombreTipoDescargo(ob[6].toString());
            d.setEstado(ob[7].toString());
            d.setDesEstado(ob[8].toString());
            d.setUnidadActivoFijo(ob[9].toString());
            d.setCodigoUnidad(ob[10].toString());
            d.setTipoDescargo( ob[11].toString().charAt(0));
            if (ob[12]!=null){  d.setFechaSolicitud((Date) ob[12]);}
            else {d.setFechaSolicitud(null);}
            d.setTipoUnidad( ob[13].toString().charAt(0));
            lst.add(d);
        }
        return lst;

    }

    public List<AfDescargos> findDescargos(String condicion) {

        String sqlQuery = "select unique d.* from Af_descargos d inner join AF_DESCARGOS_DETALLE dt on d.DESCARGO_ID = dt.DESCARGO_ID "
                + " inner join AF_BIENES_DEPRECIABLES b on b.ID_BIEN=dt.ID_BIEN ";

        Query q = em.createNativeQuery(sqlQuery + condicion, AfDescargos.class);
        return q.getResultList();
    }

    public AfBienesDepreciables buscarBien(Long id) {
        Query q = em.createNativeQuery("select * from af_bienes_depreciables where id_bien=?1", AfBienesDepreciables.class);
        q.setParameter(1, id);
        if (q.getSingleResult() != null) {
            return (AfBienesDepreciables) q.getSingleResult();
        } else {
            return new AfBienesDepreciables();
        }

    }
   
    public List<AfEmpleados> empId(Long id) {
        Query q = em.createNativeQuery("select * from af_empleados where id_empleado=?1", AfEmpleados.class);
        q.setParameter(1, id);
        return q.getResultList();
    }
    public List<AfBienesDepreciables> bienId(Long id) {
        Query q = em.createNativeQuery("select * from af_bienes_depreciables where id_bien=?1", AfBienesDepreciables.class);
        q.setParameter(1, id);
        return q.getResultList();
    }

    public List<AfBienesDepreciables> bienesId(Long id) {
        Query q = em.createNativeQuery("select * from Af_Bienes_Depreciables a where a.id_Bien=" + id, AfBienesDepreciables.class);
        return q.getResultList();

    }

    public List<AfTraslados> getTrasladoById(Long Id) {
        Query q = em.createNativeQuery("select * from Af_Traslados a where a.id_traslado=" + Id);
        return q.getResultList();

    }

    public AfTrasladosDetalle getTrasladoDetalleBien(Long Id) {
        Query q = em.createNativeQuery("select * from af_traslados_detalle a where a.id_bien =" + Id, AfTrasladosDetalle.class);
        if (q.getResultList() != null) {
            return (AfTrasladosDetalle) q.getResultList();
        } else {
            return new AfTrasladosDetalle();
        }

    }

    public List<AfDescargos> getDescargoById(Long Id) {
        Query q = em.createNativeQuery("select * from Af_descargos a where a.descargo_id=" + Id);
        return q.getResultList();

    }

    public AfDescargosDetalle getDescargoDetalleBien(Long Id) {
        Query q = em.createQuery("select a from AfDescargosDetalle a where a.idBien =" + Id, AfDescargosDetalle.class);
        if (q.getSingleResult() != null) {
            return (AfDescargosDetalle) q.getResultList();
        } else {
            return new AfDescargosDetalle();
        }

    }

    public AfDepreciaciones getDepreciacionesBien(Long Id) {
        Query q = em.createNativeQuery("select * from AfDepreciaciones a where a.id_bien =" + Id, AfDepreciaciones.class);
        if (q.getResultList() != null) {
            return (AfDepreciaciones) q.getResultList();
        } else {
            return new AfDepreciaciones();
        }

    }

    public AfDescargosDetalle getDescargoDetalleId(Long Id) {
        Query q = em.createNativeQuery("select * from AfDescargosDetalle a where a.descargo_id =" + Id, AfDescargosDetalle.class);
        if (q.getResultList() != null) {
            return (AfDescargosDetalle) q.getResultList();
        } else {
            return new AfDescargosDetalle();
        }

    }
public List<DatosVehiculos> getLista(String UnidadAF,String codUnidad, String fecReporte, List<VwVehiculos> lstBienes, String usuario) {
        List<DatosVehiculos> lst = new ArrayList();
        String Sql;
        if (codUnidad.equals("0")) {
            if (!UnidadAF.equals("0")){  
                 Sql = "select '' institucion,af.NOMBRE_UNIDAD_AF DIRECCION "
                    + "from AF_UNIDADES_ACTIVO_FIJO af "
                    + "where trim(UNIDAD_ACTIVO_FIJO)='" + UnidadAF + "'";
              
                    Query q = em.createNativeQuery(Sql);

                    for (Object dato : q.getResultList()) {
                        Object[] ob = (Object[]) dato;

                        DatosVehiculos d = new DatosVehiculos();
                        d.setInstitucion(null);
                        d.setUnidad(ob[1].toString());
                        d.setFecReporte(fecReporte);
                        d.setUsuario(usuario);

                        d.setLstDatos(lstBienes);
                        lst.add(d);
                        }
            }
            else{
                   DatosVehiculos d = new DatosVehiculos();
                        d.setInstitucion(null);
                        d.setUnidad(null);
                        d.setFecReporte(fecReporte);
                        d.setUsuario(usuario);

                        d.setLstDatos(lstBienes);
                        lst.add(d);
            }
        } else {
            Sql = "select trim(ua.CODIGO_UNIDAD)||' - '||ua.NOMBRE_UNIDAD institucion,af.NOMBRE_UNIDAD_AF DIRECCION "
                    + "from AF_UNIDADES_ADMINISTRATIVAS ua inner join AF_UNIDADES_ACTIVO_FIJO af on af.UNIDAD_ACTIVO_FIJO=ua.UNIDAD_ACTIVO_FIJO "
                    + "where trim(CODIGO_UNIDAD)='" + codUnidad + "'";
            Query q = em.createNativeQuery(Sql);

            for (Object dato : q.getResultList()) {
                Object[] ob = (Object[]) dato;
                DatosVehiculos d = new DatosVehiculos();
                d.setInstitucion(ob[0].toString());
                d.setUnidad(ob[1].toString());
                d.setFecReporte(fecReporte);
                d.setUsuario(usuario);

                d.setLstDatos(lstBienes);
                lst.add(d);
            }
        }
        return lst;
    }

    public List<DatosUnidad> getLst(String UnidadAF,String codUnidad, String fecReporte, List<VwBienes> lstBienes, String usuario) {
        List<DatosUnidad> lst = new ArrayList();
        String Sql;
        if (codUnidad.equals("0")) {
            if (!UnidadAF.equals("0")){  
                 Sql = "select '' institucion,af.NOMBRE_UNIDAD_AF DIRECCION "
                    + "from AF_UNIDADES_ACTIVO_FIJO af "
                    + "where trim(UNIDAD_ACTIVO_FIJO)='" + UnidadAF + "'";
              
                    Query q = em.createNativeQuery(Sql);

                    for (Object dato : q.getResultList()) {
                        Object[] ob = (Object[]) dato;

                        DatosUnidad d = new DatosUnidad();
                        d.setInstitucion(null);
                        d.setUnidad(ob[1].toString());
                        d.setFecReporte(fecReporte);
                        d.setUsuario(usuario);

                        d.setLstDatos(lstBienes);
                        lst.add(d);
                        }
            }
            else{
                   DatosUnidad d = new DatosUnidad();
                        d.setInstitucion(null);
                        d.setUnidad(null);
                        d.setFecReporte(fecReporte);
                        d.setUsuario(usuario);

                        d.setLstDatos(lstBienes);
                        lst.add(d);
            }
        } else {
            Sql = "select trim(ua.CODIGO_UNIDAD)||' - '||ua.NOMBRE_UNIDAD institucion,af.NOMBRE_UNIDAD_AF DIRECCION "
                    + "from AF_UNIDADES_ADMINISTRATIVAS ua inner join AF_UNIDADES_ACTIVO_FIJO af on af.UNIDAD_ACTIVO_FIJO=ua.UNIDAD_ACTIVO_FIJO "
                    + "where trim(CODIGO_UNIDAD)='" + codUnidad + "'";
            Query q = em.createNativeQuery(Sql);

            for (Object dato : q.getResultList()) {
                Object[] ob = (Object[]) dato;
                DatosUnidad d = new DatosUnidad();
                d.setInstitucion(ob[0].toString());
                d.setUnidad(ob[1].toString());
                d.setFecReporte(fecReporte);
                d.setUsuario(usuario);

                d.setLstDatos(lstBienes);
                lst.add(d);
            }
        }
        return lst;
    }
    
    public List<DatosDepreciacion> getLstdepre(Long idBien, List<AfDepreciaciones> lstDepre,String fecRep,String usuario){

        List<DatosDepreciacion> lst;
      
         Query q = em.createNamedQuery("BusquedaDepreciacionByBien",DatosDepreciacion.class);
         q.setParameter(1,idBien);
          
         lst=q.getResultList();
         lst.get(0).setLstDatos(lstDepre);
         lst.get(0).setFecReporte(fecRep);
         lst.get(0).setUsuario(usuario);
        return lst;
    }
    
    public List<DatosSolvencia> getLstSol(String UnidadAF,String codUnidad, String fecReporte, List<VwSolvencia> lstSolvencias, String usuario) {
        List<DatosSolvencia> lst = new ArrayList<>();
        String Sql;
        if (codUnidad.equals("0")) {
            if (!UnidadAF.equals("0")){  
                 Sql = "select '' institucion,af.NOMBRE_UNIDAD_AF DIRECCION "
                    + "from AF_UNIDADES_ACTIVO_FIJO af "
                    + "where trim(UNIDAD_ACTIVO_FIJO)='" + UnidadAF + "'";
              
                    Query q = em.createNativeQuery(Sql);

                    for (Object dato : q.getResultList()) {
                        Object[] ob = (Object[]) dato;

                        DatosSolvencia d = new DatosSolvencia();
                        d.setInstitucion(null);
                        d.setUnidad(ob[1].toString());
                        d.setFecReporte(fecReporte);
                        d.setUsuario(usuario);

                        d.setLstDatos(lstSolvencias);
                        lst.add(d);
                        }
            }
            else{
                   DatosSolvencia d = new DatosSolvencia();
                        d.setInstitucion(null);
                        d.setUnidad(null);
                        d.setFecReporte(fecReporte);
                        d.setUsuario(usuario);

                        d.setLstDatos(lstSolvencias);
                        lst.add(d);
            }
        } else {
            Sql = "select trim(ua.CODIGO_UNIDAD)||' - '||ua.NOMBRE_UNIDAD institucion,af.NOMBRE_UNIDAD_AF DIRECCION "
                    + "from AF_UNIDADES_ADMINISTRATIVAS ua inner join AF_UNIDADES_ACTIVO_FIJO af on af.UNIDAD_ACTIVO_FIJO=ua.UNIDAD_ACTIVO_FIJO "
                    + "where trim(CODIGO_UNIDAD)='" + codUnidad + "'";
            Query q = em.createNativeQuery(Sql);

            for (Object dato : q.getResultList()) {
                Object[] ob = (Object[]) dato;
                DatosSolvencia d = new DatosSolvencia();
                d.setInstitucion(ob[0].toString());
                d.setUnidad(ob[1].toString());
                d.setFecReporte(fecReporte);
                d.setUsuario(usuario);

                d.setLstDatos(lstSolvencias);
                lst.add(d);
            }
        }
        return lst;
    }
    
   public List<DatosxCuentas> getLstdatos(String UnidadAF,String codUnidad, String fecReporte,String fuente, List<VwDatosxCuentas> lstBienes, String usuario) {
        List<DatosxCuentas> lst = new ArrayList<DatosxCuentas>();
        if (codUnidad.equals("0")) {
            if (!UnidadAF.equals("0")){ 
            String Sql = "select '' institucion,af.NOMBRE_UNIDAD_AF DIRECCION "
                    + "from AF_UNIDADES_ACTIVO_FIJO af "
                    + "where trim(UNIDAD_ACTIVO_FIJO)='" + UnidadAF + "'";
            Query q = em.createNativeQuery(Sql);

            for (Object dato : q.getResultList()) {
                Object[] ob = (Object[]) dato;
              
                DatosxCuentas d = new DatosxCuentas();
                d.setInstitucion(null);
                d.setUnidad(ob[1].toString());
                d.setFecReporte(fecReporte);
                d.setUsuario(usuario);

                d.setLstDatos(lstBienes);
                lst.add(d);
                }
            }
            else{
                   DatosxCuentas d = new DatosxCuentas();
                        d.setInstitucion(null);
                        d.setUnidad(null);
                        d.setFecReporte(fecReporte);
                        d.setUsuario(usuario);

                        d.setLstDatos(lstBienes);
                        lst.add(d);
            }
        } else {
            String Sql = "select trim(ua.CODIGO_UNIDAD)||' - '||ua.NOMBRE_UNIDAD institucion,af.NOMBRE_UNIDAD_AF DIRECCION "
                    + "from AF_UNIDADES_ADMINISTRATIVAS ua inner join AF_UNIDADES_ACTIVO_FIJO af on af.UNIDAD_ACTIVO_FIJO=ua.UNIDAD_ACTIVO_FIJO "
                    + "where trim(CODIGO_UNIDAD)='" + codUnidad + "'";
            Query q = em.createNativeQuery(Sql);

            for (Object dato : q.getResultList()) {
                Object[] ob = (Object[]) dato;
                DatosxCuentas d = new DatosxCuentas();
                d.setInstitucion(ob[0].toString());
                d.setUnidad(ob[1].toString());
                d.setFecReporte(fecReporte);
                d.setUsuario(usuario);

                d.setLstDatos(lstBienes);
                lst.add(d);
            }
        }
        return lst;
    }
}
