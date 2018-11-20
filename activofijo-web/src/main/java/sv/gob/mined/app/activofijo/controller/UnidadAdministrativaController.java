package sv.gob.mined.app.activofijo.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import sv.gob.mined.activofijo.ejb.BienesEJB;
import sv.gob.mined.activofijo.ejb.CatalogosEJB;


import sv.gob.mined.activofijo.model.AfUnidadesActivoFijo;
import sv.gob.mined.activofijo.model.AfUnidadesAdministrativas;
import sv.gob.mined.activofijo.model.AfUnidadesAdministrativasPK;
import sv.gob.mined.app.activofijo.util.JsfUtil;
import sv.gob.mined.seguridad.model.Usuario;
import sv.gob.mined.seguridad.web.controller.LoginController;

/**
 *
 * @author JISTorres
 */
@ManagedBean
@ViewScoped
public class UnidadAdministrativaController implements Serializable {

    @EJB
    public CatalogosEJB cejb;
    @EJB
    public BienesEJB bejb;
    //public List<AfUnidadesAdministrativas> lstUnidadAdm = new ArrayList<AfUnidadesAdministrativas>();
    public AfUnidadesAdministrativas unidad = new AfUnidadesAdministrativas();

    private int rowDrop = 0;
    private String unidadAdm;
    private String codigoUnidad;
    private String nombreUnidad;
    private String direccion;
    private String cargoDir;
    private String telefono;
    private String nombreDir;
    private String nomResponsable;
    private String tipoUni;
    private String unidadAF;
    private String tipoUnidad = "UA";
    private String tipoUsu;
    private String paramUadmin;
    private String paramUAF;

    private boolean actAF = false;
    private boolean actAd = false;
    private Usuario usuDao = new Usuario();
    private List<AfUnidadesAdministrativas> lstUnidadAdm = new ArrayList();
   

    public UnidadAdministrativaController() {

    }

    public BienesEJB getBejb() {
        return bejb;
    }

    @PostConstruct
    public void cargarUnidad() {

        usuDao = ((LoginController) FacesContext.getCurrentInstance().getApplication().getELResolver().
                getValue(FacesContext.getCurrentInstance().getELContext(), null, "loginController")).getUsuario();
        tipoUsu = usuDao.getTipoUsuario().toString();
        
        actAF = true;
        actAd = true;
        tipoUnidad = "UA";
        
        unidadAdm= usuDao.getCodigoEntidad();
        unidadAF =cejb.getUnidadAf(unidadAdm, tipoUnidad);
      
        if (JsfUtil.getRequestParameter("paramUAF") != null) {
            paramUAF = JsfUtil.getRequestParameter("paramUAF");
            paramUadmin = JsfUtil.getRequestParameter("paramUadmin");
            unidad = bejb.getUnidadPk(paramUAF, paramUadmin);
            unidadAF = unidad.getAfUnidadesAdministrativasPK().getUnidadActivoFijo();
            codigoUnidad = unidad.getAfUnidadesAdministrativasPK().getCodigoUnidad();
            nombreUnidad = unidad.getNombreUnidad();
            direccion = unidad.getDireccion();
            nombreDir = unidad.getNombreDirector();
            cargoDir = unidad.getCargoDirector();
            telefono = unidad.getTelefono();
            nomResponsable = unidad.getNombreResponsable();
            tipoUni = unidad.getTipoUnidad();
        }

    }

    public String getParamUadmin() {
        return paramUadmin;
    }

    public void setParamUadmin(String paramUadmin) {
        this.paramUadmin = paramUadmin;
    }

    public String getParamUAF() {
        return paramUAF;
    }

    public void setParamUAF(String paramUAF) {
        this.paramUAF = paramUAF;
    }

    public boolean isActAF() {
        return actAF;
    }

    public void setActAF(boolean actAF) {
        this.actAF = actAF;
    }

    public boolean isActAd() {
        return actAd;
    }

    public void setActAd(boolean actAd) {
        this.actAd = actAd;
    }

    public String getTipoUsu() {
        return tipoUsu;
    }

    public void setTipoUsu(String tipoUsu) {
        this.tipoUsu = tipoUsu;
    }

    public String getNombreUnidad(String codigo) {
        return (cejb.nomUnidadAf(codigo));
    }

    public String getTipoTraslado(String codigo) {
        return (cejb.tipoTraslado(codigo));
    }

    public String getNombreAdm(String codigo, String tipo) {
        return cejb.NomUnidad(codigo, tipo);
    }

    public String getTipoUnidad(String codigo) {
        return cejb.getTipoUnidad(codigo);
    }

    public String getTipoUnidad() {
        return tipoUnidad;
    }

    public void setTipoUnidad(String tipoUnidad) {
        this.tipoUnidad = tipoUnidad;
    }

    public int getRowDrop() {
        return rowDrop;
    }

    public void setRowDrop(int rowDrop) {
        this.rowDrop = rowDrop;
    }

    public List<AfUnidadesActivoFijo> getLstUnidadAF() {
        return cejb.getUnidadAf();
    }

    public List<AfUnidadesAdministrativas> getLstUnidadAdm() {
        //   activo=true;
        return lstUnidadAdm;
    }

    public String getUnidadAF() {
        return unidadAF;
    }

    public void setUnidadAF(String unidadAF) {
        this.unidadAF = unidadAF;
    }

    public AfUnidadesAdministrativas getUnidad() {
        return unidad;
    }

    public void setUnidad(AfUnidadesAdministrativas unidad) {
        this.unidad = unidad;
    }

    public String getCodigoUnidad() {
        return codigoUnidad;
    }

    public void setCodigoUnidad(String codigoUnidad) {
        this.codigoUnidad = codigoUnidad;
    }

    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCargoDir() {
        return cargoDir;
    }

    public void setCargoDir(String cargoDir) {
        this.cargoDir = cargoDir;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreDir() {
        return nombreDir;
    }

    public void setNombreDir(String nombreDir) {
        this.nombreDir = nombreDir;
    }

    public String getNomResponsable() {
        return nomResponsable;
    }

    public void setNomResponsable(String nomResponsable) {
        this.nomResponsable = nomResponsable;
    }

    public String getTipoUni() {
        return tipoUni;
    }

    public void setTipoUni(String tipoUni) {
        this.tipoUni = tipoUni;
    }

    public String getUnidadAdm() {
        return unidadAdm;
    }

    public void setUnidadAdm(String unidadAdm) {
        this.unidadAdm = unidadAdm;
    }

    public void filtrarUnidadesAdm() {
        lstUnidadAdm = cejb.getUnidadAdm(unidadAF, tipoUnidad);
    }

    public void unidadId(SelectEvent event) {
        lstUnidadAdm = cejb.getUnidadAdm(paramUadmin, JsfUtil.getVariableSession("usuario").toString());
    }

    public void buscarUnidad() {
        String condicion = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if (!unidadAF.equals("0")) {
            condicion = condicion + " a.unidad_activo_fijo='" + unidadAF + "' and ";
        }
        if (!codigoUnidad.isEmpty() && !codigoUnidad.equals("")) {
            condicion = condicion + " a.codigo_unidad='" + codigoUnidad + "' and ";
        }
        if (!nombreUnidad.isEmpty() && !nombreUnidad.equals("")) {
            condicion = condicion + " a.nombre_unidad like '%" + nombreUnidad + "%' and ";
        }
        if (!cargoDir.isEmpty() && !cargoDir.equals("")) {
            condicion = condicion + " a.cargo_director='" + cargoDir + "' and ";
        }
        if (!nombreDir.isEmpty() && !nombreDir.equals("")) {
            condicion = condicion + " a.nombre_director like '%" + nombreDir + "%' and ";
        }
        if (!telefono.isEmpty() && !telefono.equals("")) {
            condicion = condicion + " a.telefono like '%" + telefono + "%' and ";
        }
        if (!nomResponsable.isEmpty() && !nomResponsable.equals("")) {
            condicion = condicion + " a.nombre_responsable like '%" + nomResponsable + "%' and ";
        }
        if (!tipoUni.isEmpty() && !tipoUni.equals("PR")) {
            condicion = condicion + " a.tipo_Unidad like '" + tipoUni + "' and ";
        }

        if (!condicion.equals("")) {
            condicion = condicion.substring(0, condicion.length() - 4);
        }

        lstUnidadAdm = bejb.buscarUnidadesAdmin(condicion);
    }

    public void limpiarFiltro() {
        codigoUnidad = "";
        nombreUnidad = "";
        cargoDir = "";
        direccion = "";
        nombreDir = "";
        telefono = "";
        nomResponsable = "";
        tipoUni = "";
    }

    public String obtenerNombreUnidad(String codigo) {
        return cejb.NomUnidad(codigo, "UA");
    }

    public void guardarUnidadAdm() {
            unidad = cejb.getUnidadAdmin(unidadAF,unidadAdm);
         if (unidad.getAfUnidadesAdministrativasPK()!=null){   
            unidad.setNombreUnidad(nombreUnidad);
            unidad.setDireccion(direccion);
            unidad.setNombreDirector(nombreDir);
            unidad.setCargoDirector(cargoDir);
            unidad.setTelefono(telefono);
            unidad.setNombreResponsable(nomResponsable);
            unidad.setTipoUnidad(tipoUnidad);     

            bejb.editarUnidadAdmin(unidad, JsfUtil.getVariableSession("usuario").toString());
        } else {
            AfUnidadesAdministrativasPK pk=new AfUnidadesAdministrativasPK();
            pk.setUnidadActivoFijo(unidadAF);
            pk.setCodigoUnidad(unidadAdm);
            
            unidad.setAfUnidadesAdministrativasPK(pk);
            unidad.setNombreUnidad(nombreUnidad);
            unidad.setDireccion(direccion);
            unidad.setNombreDirector(nombreDir);
            unidad.setCargoDirector(cargoDir);
            unidad.setTelefono(telefono);
            unidad.setNombreResponsable(nomResponsable);
            unidad.setTipoUnidad(tipoUnidad);

            bejb.guardarUnidadAdmin(unidad, JsfUtil.getVariableSession("usuario").toString());
        }
        JsfUtil.redireccionar("/app/manttoAf/buscarUnidadesAdmin.mined?faces-redirect=true");
        //   JsfUtil.redireccionar("buscarTrasladosCE.mined?faces-redirect=true");
    }

    public void eliminarUnidadAdm() {

        if (bejb.getUnidadBienes(paramUAF, paramUadmin)){
            unidad = cejb.getUnidadAdmin(paramUAF, paramUadmin);
            bejb.eliminarUnidad(unidad);
            //bejb.removeUnidadAdm(unidad);
            RequestContext.getCurrentInstance().execute("PF('dlg1').hide()");
            buscarUnidad();

        JsfUtil.mensajeEliminarUnidadAdm();
       }else{
           RequestContext.getCurrentInstance().execute("PF('dlg1').hide()");
           JsfUtil.mensajeError("Unidad tiene bienes asociados");
       }
          

    }
        
     public void nuevoUnidad() {

        JsfUtil.redireccionar("/app/manttoAf/nuevaUnidad.mined?faces-redirect=true");
    }

    public void editarUnidad() {
        JsfUtil.redireccionar("/app/manttoAf/nuevaUnidad.mined?faces-redirect=true&paramUAF=" + paramUAF + "&paramUadmin=" + paramUadmin);
    }
     public void generarXls_reporteCatalogo() throws IOException {
        Workbook libro = new HSSFWorkbook();
        Sheet hoja = libro.createSheet("Reporte Unidades Administrativas");

        HSSFFont font = (HSSFFont) libro.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.BLACK.index);
        font.setFontName(HSSFFont.FONT_ARIAL);

        HSSFFont font1 = (HSSFFont) libro.createFont();
        font1.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        font1.setColor(HSSFColor.BLACK.index);
        font1.setFontName(HSSFFont.FONT_ARIAL);

        HSSFCellStyle style = (HSSFCellStyle) libro.createCellStyle();
        style.setFont(font);

        HSSFCellStyle style2 = (HSSFCellStyle) libro.createCellStyle();
        style2.setFont(font1);
        short alig = 2;
        style.setAlignment(alig);

        //fila = hoja.createRow((short) 1);
        Row fila = hoja.createRow((short) 1);
        Cell celdaEn1 = fila.createCell((short) 1);
        celdaEn1.setCellStyle(style);
        celdaEn1.setCellValue("MINISTERIO DE EDUCACIÓN");
        hoja.addMergedRegion(new CellRangeAddress(1, 1, 1, 5));

        fila = hoja.createRow((short) 3);
        Cell celdaEn4 = fila.createCell((short) 1);
        celdaEn4.setCellStyle(style);
        celdaEn4.setCellValue("CATALOGO DE UNIDADES ADMINISTRATIVAS ");
        hoja.addMergedRegion(new CellRangeAddress(3, 3, 1, 5));

        fila = hoja.createRow((short) 5);
        Cell celdaEn5 = fila.createCell((short) 1);
        celdaEn5.setCellStyle(style);
        celdaEn5.setCellValue("UNIDAD DE ACTIVO FIJO");

        Cell celdaEn6 = fila.createCell((short) 2);
        celdaEn6.setCellStyle(style);
        celdaEn6.setCellValue("CÓDIGO");

        Cell celdaEn7 = fila.createCell((short) 3);
        celdaEn7.setCellStyle(style);
        celdaEn7.setCellValue("NOMBRE UNIDAD");

        Cell celdaEn8 = fila.createCell((short) 4);
        celdaEn8.setCellStyle(style);
        celdaEn8.setCellValue("NOMBRE RESPONSABLE");

       int x = 6;
       
        for (AfUnidadesAdministrativas object : lstUnidadAdm) {
            fila = hoja.createRow((short) x);

            Cell celda4 = fila.createCell((short) 1);
            celda4.setCellStyle(style2);
            celda4.setCellValue(getNombreUnidad(object.getAfUnidadesAdministrativasPK().getUnidadActivoFijo()));

            Cell celda5 = fila.createCell((short) 2);
            celda5.setCellStyle(style2);
            celda5.setCellValue(object.getAfUnidadesAdministrativasPK().getCodigoUnidad());

            Cell celda6 = fila.createCell((short) 3);
            celda6.setCellStyle(style2);
            celda6.setCellValue(getNombreAdm(object.getAfUnidadesAdministrativasPK().getCodigoUnidad(),object.getTipoUnidad()));
            
            Cell celda7 = fila.createCell((short) 4);
            celda7.setCellStyle(style2);
            celda7.setCellValue(object.getNombreResponsable());
            
            x++;
        }
       

        hoja.autoSizeColumn((short) 0);
        hoja.autoSizeColumn((short) 1);
        hoja.autoSizeColumn((short) 2);
        hoja.autoSizeColumn((short) 3);
        hoja.autoSizeColumn((short) 4);
        hoja.autoSizeColumn((short) 5);
        hoja.autoSizeColumn((short) 6);

        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();

        libro.write(outByteStream);

        byte[] outArray = outByteStream.toByteArray();
        response.setContentType("application / ms - excel");
        response.setContentLength(outArray.length);
        response.setHeader("Expires:", "0"); // eliminates browser caching
        //String patron = "dd-MM-yyyy";
        //SimpleDateFormat formato = new SimpleDateFormat(patron);
        response.setHeader("Content-Disposition", "attachment;filename = ListadoUnidades.xls");
        OutputStream outStream = response.getOutputStream();
        outStream.write(outArray);
        outStream.flush();
        fc.responseComplete();
    }
    
}
