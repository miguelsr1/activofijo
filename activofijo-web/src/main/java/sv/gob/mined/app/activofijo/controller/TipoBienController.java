package sv.gob.mined.app.activofijo.controller;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
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
import sv.gob.mined.activofijo.model.AfCategoriasBien;
import sv.gob.mined.activofijo.model.AfClasificacionBien;
import sv.gob.mined.activofijo.model.AfTipoBienes;

import sv.gob.mined.app.activofijo.util.JsfUtil;
import sv.gob.mined.seguridad.model.Usuario;
import sv.gob.mined.seguridad.web.controller.LoginController;

/**
 *
 * @author JISTorres
 */
@ManagedBean
@ViewScoped
public class TipoBienController implements Serializable {

    @EJB
    public CatalogosEJB cejb;
    @EJB
    public BienesEJB bejb;
    //public List<AfUnidadesAdministrativas> lstUnidadAdm = new ArrayList<AfUnidadesAdministrativas>();
    public AfTipoBienes tipoBien= new AfTipoBienes();
    
    private int rowDrop = 0;
    private String estadoTipo;
    
    private Long idTipoBien;
    private Long cat;
    private Long clas=1l;
    private String codigo;
    private String descripcion;
    private Usuario usuDao = new Usuario();
    private List<AfTipoBienes> lstTipoBien = new ArrayList<>();
    private List<AfCategoriasBien> lstCategoria = new ArrayList<>();
    private List<AfClasificacionBien> lstClasificacion= new ArrayList<>();
    public TipoBienController() {

    }

    public BienesEJB getBejb() {
        return bejb;
    }

    @PostConstruct
    public void cargarUnidad() {
        
         usuDao = ((LoginController) FacesContext.getCurrentInstance().getApplication().getELResolver().
                getValue(FacesContext.getCurrentInstance().getELContext(), null, "loginController")).getUsuario();
         if (JsfUtil.getRequestParameter("idTipoBien")!=null){ 
             tipoBien = cejb.getTBien(Long.parseLong(JsfUtil.getRequestParameter("idTipoBien")));
             codigo = tipoBien.getCodigoTipoBien();
             descripcion = tipoBien.getNombreTipoBien();
             clas = tipoBien.getIdClasifBien();
             cat = tipoBien.getIdCatBien().getIdCatBien();
             
         }
          lstCategoria = cejb.getCategoriaxCla(clas);
       
      }
    
   

    public Long getClas() {
        return clas;
    }

    public void setClas(Long clas) {
        this.clas = clas;
    }

  
    public List<AfTipoBienes> getLstTipoBien() {
        return lstTipoBien;
    }

    public void setLstTipoBien(List<AfTipoBienes> lstTipoBien) {
        this.lstTipoBien = lstTipoBien;
    }

    public List<AfClasificacionBien> getLstClasificacion() {
        lstClasificacion = cejb.getClasificacion();
        return lstClasificacion;
    }

    public void setLstClasificacion(List<AfClasificacionBien> lstClasificacion) {
        this.lstClasificacion = lstClasificacion;
    }
            
    
    public List<AfCategoriasBien> getLstCategoria() {
        lstCategoria =cejb.getCategoria();
        return lstCategoria;
    }

    public void setLstCategoria(List<AfCategoriasBien> lstCategoria) {
        this.lstCategoria = lstCategoria;
    }

  

   public String getNombreUnidad(String codigo){
        return (cejb.nomUnidadAf(codigo)) ;   
   }
   
   public String getTipoTraslado(String codigo){
       return (cejb.tipoTraslado(codigo));
   }
   
   public String getNombreAdm(String codigo,String tipo){
          return cejb.NomUnidad(codigo,tipo);
   }
   
   public String getTipoUnidad(String codigo){
       return cejb.getTipoUnidad(codigo);
   }

    public String getEstadoTipo() {
        return estadoTipo;
    }

    public void setEstadoTipo(String estadoTipo) {
        this.estadoTipo = estadoTipo;
    }

    public Long getCat() {
        return cat;
    }

    public void setCat(Long cat) {
        this.cat = cat;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
   
   
    public int getRowDrop() {
        return rowDrop;
    }

    public void setRowDrop(int rowDrop) {
        this.rowDrop = rowDrop;
    }

   
    public Long getIdTipoBien() {
        return idTipoBien;
    }

    public void setIdTipoBien(Long idTipoBien) {
        this.idTipoBien = idTipoBien;
    }

    public void buscarTipo(){
        String condicion="";
        
        if (clas!=0) { condicion=condicion+ " a.id_Clasif_bien="+clas+" and ";}
         if (!estadoTipo.equals("2")) { condicion=condicion+ " a.estado_tipo="+estadoTipo+" and ";}
        if (cat!=0) { condicion=condicion+ " a.id_cat_bien='"+cat+"' and ";}
        if (!codigo.isEmpty() && !codigo.equals("")) { condicion=condicion+ " a.codigo_tipo_bien='"+codigo+"' and ";}
        if (!descripcion.isEmpty() && !descripcion.equals("")) { condicion=condicion+ " a.nombre_tipo_bien like '%"+descripcion+"%' and ";}
       
        if (!condicion.equals("")){
            condicion =condicion.substring(0,condicion.length()-4);
        }
        
        lstTipoBien = bejb.buscarTipoBien(condicion);
    }
    
    public void limpiarFiltro(){
       codigo="";
       descripcion="";
       clas=0l;
       cat=0l;
       estadoTipo="2";
    }    
    
    public void tipoId(SelectEvent event) {
        lstTipoBien = bejb.getTipoxId(tipoBien.getIdTipoBien());
    }
    
    public String obtenerClasificacion(Long id){
        return cejb.nomClasificacion(id);
    }
    public String obtenerCategoria(Long clas,Long id){
        return cejb.nomCategoria(clas,id);
    }
    
    public void abrirDialog() {
       RequestContext.getCurrentInstance().execute("PF('dlg1').show()");
     
    }
    public void eliminar(){
        
         tipoBien= cejb.getTBien(idTipoBien);
         if (bejb.bienesTipo(idTipoBien)){
            bejb.removeTipoBien(tipoBien);
         }else {
            if (tipoBien.getEstadoTipo().toString().equals("1"))
              tipoBien.setEstadoTipo('0');
             else{
                 tipoBien.setEstadoTipo('1');
             }
         
            bejb.inactivarTipoBien(tipoBien, JsfUtil.getVariableSession("usuario").toString());
         }
         JsfUtil.redireccionar("/app/manttoAf/buscarTipoBien.mined?faces-redirect=true");
       //   JsfUtil.redireccionar("buscarTrasladosCE.mined?faces-redirect=true");
     }
    
    public void guardarTipo(){
     
         tipoBien.setCodigoTipoBien(codigo);
         tipoBien.setNombreTipoBien(descripcion);
         tipoBien.setIdCatBien(cejb.getCatBien(cat));
         tipoBien.setIdClasifBien(cejb.getClasBien(clas).getIdClasifBien());
         if (tipoBien.getIdTipoBien()!=null){
             tipoBien.setEstadoTipo('1');
         }
         
         bejb.guardarTipoBien(tipoBien, JsfUtil.getVariableSession("usuario").toString());

         JsfUtil.redireccionar("/app/manttoAf/buscarTipoBien.mined?faces-redirect=true");
       //   JsfUtil.redireccionar("buscarTrasladosCE.mined?faces-redirect=true");
     }
        
     public void nuevoTipoBien() {
       
        JsfUtil.redireccionar("/app/manttoAf/mantoTipoBien.mined?faces-redirect=true");
    }
    public void editarTipoBien() {
        JsfUtil.redireccionar("/app/manttoAf/mantoTipoBien.mined?faces-redirect=true&idTipoBien="+idTipoBien);
    }
      public void generarXls_reporteCatalogo() throws IOException {
        Workbook libro = new HSSFWorkbook();
        Sheet hoja = libro.createSheet("Reporte Tipo Bien");

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

        fila = hoja.createRow((short) 2);
        Cell celdaEn2 = fila.createCell((short) 1);
        celdaEn2.setCellStyle(style);
        hoja.addMergedRegion(new CellRangeAddress(2, 2, 1, 5));
        fila = hoja.createRow((short) 3);
        Cell celdaEn4 = fila.createCell((short) 1);
        celdaEn4.setCellStyle(style);
        celdaEn4.setCellValue("CATALOGO DE TIPO DE BIENES ");
        hoja.addMergedRegion(new CellRangeAddress(3,3 , 1, 5));

        fila = hoja.createRow((short) 5);
        Cell celdaEn5 = fila.createCell((short) 1);
        celdaEn5.setCellStyle(style);
        celdaEn5.setCellValue("CÓDIGO");

        Cell celdaEn6 = fila.createCell((short) 2);
        celdaEn6.setCellStyle(style);
        celdaEn6.setCellValue("DESCRIPCIÓN");

        Cell celdaEn7 = fila.createCell((short) 3);
        celdaEn7.setCellStyle(style);
        celdaEn7.setCellValue("CLASIFICACIÓN");

        Cell celdaEn8 = fila.createCell((short) 4);
        celdaEn8.setCellStyle(style);
        celdaEn8.setCellValue("CATEGORIA");

       int x = 6;
       
        for (AfTipoBienes object : lstTipoBien) {
            fila = hoja.createRow((short) x);

            Cell celda4 = fila.createCell((short) 1);
            celda4.setCellStyle(style2);
            celda4.setCellValue(object.getCodigoTipoBien());

            Cell celda5 = fila.createCell((short) 2);
            celda5.setCellStyle(style2);
            celda5.setCellValue(object.getNombreTipoBien());

            Cell celda6 = fila.createCell((short) 3);
            celda6.setCellStyle(style2);
            celda6.setCellValue(obtenerClasificacion(object.getIdClasifBien()));
            
            Cell celda7 = fila.createCell((short) 4);
            celda7.setCellStyle(style2);
            celda6.setCellValue(obtenerCategoria(object.getIdClasifBien(),object.getIdCatBien().getIdCatBien()));
            
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
        response.setHeader("Content-Disposition", "attachment;filename = ListadoTipoBiens.xls");
        OutputStream outStream = response.getOutputStream();
        outStream.write(outArray);
        outStream.flush();
        fc.responseComplete();
    }
}
