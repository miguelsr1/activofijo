package sv.gob.mined.app.activofijo.controller;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
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
import sv.gob.mined.activofijo.ejb.ReportesEJB;
import sv.gob.mined.activofijo.model.AfBienesDepreciables;
import sv.gob.mined.activofijo.model.AfEmpleados;

import sv.gob.mined.activofijo.model.AfUnidadesActivoFijo;
import sv.gob.mined.activofijo.model.AfUnidadesAdministrativas;
import sv.gob.mined.app.activofijo.util.JsfUtil;
import sv.gob.mined.app.activofijo.util.UtilReport;
import static sv.gob.mined.app.activofijo.util.UtilReport.PATH_IMAGENES;
import sv.gob.mined.seguridad.model.Usuario;
import sv.gob.mined.seguridad.web.controller.LoginController;

/**
 *
 * @author JISTorres
 */
@ManagedBean
@ViewScoped
public class EmpleadosController implements Serializable {

    @EJB
    public CatalogosEJB cejb;
    @EJB
    public BienesEJB bejb;
     @EJB
    public ReportesEJB reb;
    public AfEmpleados emp= new AfEmpleados();
    
    private int rowDrop = 0;
    public String unidadAdm;
    public String nombres;
    public String apellidos;
    public String cargo;
    public String unidadAF;
    public String tipoUnidad="UA";
    private String tipoUsu;
    private Long idEmpleado;
    private String nomRespon;
    private String cargoRespon;
    private boolean actAF=false;
    private boolean actAd=false;
    private Usuario usuDao = new Usuario();
    private List<AfEmpleados> lstEmpleados = new ArrayList<>();
    private List<AfUnidadesAdministrativas> lstUnidadAdm = new ArrayList();
    private List<AfBienesDepreciables> lstBienes = new ArrayList<>();
    
    public EmpleadosController() {

    }

    public BienesEJB getBejb() {
        return bejb;
    }

    @PostConstruct
    public void cargarUnidad() {
        
         usuDao = ((LoginController) FacesContext.getCurrentInstance().getApplication().getELResolver().
                getValue(FacesContext.getCurrentInstance().getELContext(), null, "loginController")).getUsuario();
        tipoUsu = usuDao.getTipoUsuario().toString();
        if (tipoUsu.equals("I")){
              actAF = false;
              actAd=false;
              tipoUnidad="UA";
            
          }else {
            if (tipoUsu.equals("D")){
                actAF = true;
                actAd=false;
                tipoUnidad="UA";
            }else{
                if (tipoUsu.equals("A")){
                    actAF = true;
                    actAd=true;
                    tipoUnidad="UA";
                }
                else{
                    actAF = true;
                    actAd=true;
                    tipoUnidad="CE";
                }
          }
        }
        unidadAdm = usuDao.getCodigoEntidad();
        unidadAF = cejb.getUnidadAf(usuDao.getCodigoEntidad(), tipoUnidad); 
        lstUnidadAdm = cejb.getUnidadAdm(unidadAF, tipoUnidad);
        lstEmpleados = cejb.getEmpleadosAdm(unidadAdm, unidadAF);
        
        if (JsfUtil.getRequestParameter("idEmpleado")!=null){ 
            emp= bejb.getEmpAdmin(JsfUtil.getRequestParameter("idEmpleado"));
            unidadAF =emp.getUnidadActivoFijo();
            unidadAdm = emp.getCodigoUnidad();
            cargo=emp.getCargo();
            nombres =emp.getNombres();
            apellidos=emp.getApellidos();
        }
      
    }

    public String getNomRespon() {
        return nomRespon;
    }

    public void setNomRespon(String nomRespon) {
        this.nomRespon = nomRespon;
    }

    public String getCargoRespon() {
        return cargoRespon;
    }

    public void setCargoRespon(String cargoRespon) {
        this.cargoRespon = cargoRespon;
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

  
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTipoUsu() {
        return tipoUsu;
    }

    public void setTipoUsu(String tipoUsu) {
        this.tipoUsu = tipoUsu;
    }

    public AfEmpleados getEmp() {
        return emp;
    }

    public void setEmp(AfEmpleados emp) {
        this.emp = emp;
    }

    public List<AfBienesDepreciables> getLstBienes() {
        return lstBienes;
    }

    public void setLstBienes(List<AfBienesDepreciables> lstBienes) {
        this.lstBienes = lstBienes;
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

   

    public String getUnidadAdm() {
        return unidadAdm;
    }

    public void setUnidadAdm(String unidadAdm) {
        this.unidadAdm = unidadAdm;
    }

    public void filtrarUnidadesAdm(){
       lstUnidadAdm=cejb.getUnidadAdm(unidadAF,tipoUnidad);
    }

    public void filtrarEmpleados(){
       lstEmpleados=cejb.getEmpleadosAdm(unidadAdm,unidadAF);
    }

    
    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public void empleadoId(SelectEvent event) {
        lstEmpleados = bejb.empId(emp.getIdEmpleado());
    }
    public List<AfEmpleados> getLstEmpleados() {
        return lstEmpleados;
    }

    public void setLstEmpleados(List<AfEmpleados> lstEmpleados) {
        this.lstEmpleados = lstEmpleados;
    }
    
    
    public void buscarEmpleados(){
        String condicion="";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
         if (!unidadAF.equals("0")) { condicion=condicion+ " a.unidad_activo_fijo='"+unidadAF+"' and ";}
        if (!unidadAdm.equals("0")) { condicion=condicion+ " a.codigo_unidad='"+unidadAdm+"' and ";}
        if (!cargo.isEmpty() && !cargo.equals("")) { condicion=condicion+ " a.cargo='"+cargo+"' and ";}
        if (!nombres.isEmpty() && !nombres.equals("")) { condicion=condicion+ " a.nombres like '%"+nombres+"%' and ";}
        if (!apellidos.isEmpty() && !apellidos.equals("")) { condicion=condicion+ " a.apellidos like '%"+apellidos+"%' and ";}
        if (!condicion.equals("")){
            condicion =condicion.substring(0,condicion.length()-4);
        }
        
        lstEmpleados = bejb.buscarEmpleados(condicion);
    }
    
      public void buscarBienesEmpleado(){
        String condicion=" a.id_empleado= "+idEmpleado+" and ";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        
        if (!unidadAF.equals("0")) { condicion=condicion+ " a.unidad_activo_fijo='"+unidadAF+"' and ";}
        if (!unidadAdm.equals("0")) { condicion=condicion+ " a.codigo_unidad='"+unidadAdm+"' and ";}
        
        if (!condicion.equals("")){
            condicion =condicion.substring(0,condicion.length()-4);
        }
        
        lstBienes = bejb.buscarBienes(condicion);
    }
    
    public void limpiarFiltro(){
        nombres="";
        apellidos="";
        cargo="";
        
           
    }    
    public String obtenerNombreUnidad(String codigo){
        return cejb.NomUnidad(codigo,"UA");
    }
    
  public void imprimirBienes() throws IOException, JRException {
       List<JasperPrint> jasperPrintList = new ArrayList();
     
        HashMap param = new HashMap();
        SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es"));
       
        Date fecRep = cejb.getFechaActual();
       
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        param.put("p_RutaImg", ctx.getRealPath(PATH_IMAGENES));
        
         JasperPrint jp= reb.getRpt(param, EmpleadosController.class.getClassLoader().getResourceAsStream(("reportes" + File.separator + "rep_actaResponsabilidad.jasper")),bejb.getLstEmp(unidadAF, unidadAdm, formateador.format(fecRep),nomRespon,cargoRespon,idEmpleado,lstBienes, usuDao.getLogin()));
       
        jasperPrintList.add(jp);
     
        UtilReport.generarReporte(jasperPrintList, "actaResponsabilidad");

    }

    
    public void guardarEmpleado(){
     

         emp.setUnidadActivoFijo(unidadAF);
         emp.setCodigoUnidad(unidadAdm);
         emp.setNombres(nombres);
         emp.setApellidos(apellidos);
         emp.setCargo(cargo);
         bejb.guardarEmpleado(emp, JsfUtil.getVariableSession("usuario").toString());

         JsfUtil.redireccionar("/app/manttoAf/EmpleadosAdministrativos.mined?faces-redirect=true");
       //   JsfUtil.redireccionar("buscarTrasladosCE.mined?faces-redirect=true");
     }
     
    public void eliminarEmpleado() {
       
       if (bejb.bienesEmpleado(idEmpleado)){
            emp = bejb.getEmpAdmin(idEmpleado.toString());
         
            bejb.removeEmpleado(emp);
            RequestContext.getCurrentInstance().execute("PF('dlg1').hide()");
           buscarEmpleados();

        JsfUtil.mensajeEliminarEmpleado();
       }else{
           JsfUtil.mensajeError("Empleado tiene bienes asignados");
       }
          

    }

    
     public void nuevoEmpleado() {
       
        JsfUtil.redireccionar("/app/manttoAf/NuevosEmpleados.mined?faces-redirect=true");
    }
    public void editarEmpleado() {
        JsfUtil.redireccionar("/app/manttoAf/NuevosEmpleados.mined?faces-redirect=true&idEmpleado="+idEmpleado);
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
        if (!unidadAdm.equals("0")) {
            celdaEn2.setCellValue(cejb.NomUnidad(unidadAdm, tipoUnidad));
        } else {
            celdaEn2.setCellValue("");
        }
        hoja.addMergedRegion(new CellRangeAddress(2, 2, 1, 5));

        fila = hoja.createRow((short) 3);
        Cell celdaEn4 = fila.createCell((short) 1);
        celdaEn4.setCellStyle(style);
        celdaEn4.setCellValue("CATALOGO DE EMPLEADOS ");
        hoja.addMergedRegion(new CellRangeAddress(3, 3, 1, 5));

        fila = hoja.createRow((short) 5);
        Cell celdaEn5 = fila.createCell((short) 1);
        celdaEn5.setCellStyle(style);
        celdaEn5.setCellValue("NOMBRE");

        Cell celdaEn6 = fila.createCell((short) 2);
        celdaEn6.setCellStyle(style);
        celdaEn6.setCellValue("CARGO");

        Cell celdaEn7 = fila.createCell((short) 3);
        celdaEn7.setCellStyle(style);
        celdaEn7.setCellValue("UNIDAD");

        
       int x = 6;
       
        for (AfEmpleados object : lstEmpleados) {
            fila = hoja.createRow((short) x);

            Cell celda4 = fila.createCell((short) 1);
            celda4.setCellStyle(style2);
            celda4.setCellValue(object.getNombres()+" "+ object.getApellidos());

            Cell celda5 = fila.createCell((short) 2);
            celda5.setCellStyle(style2);
            celda5.setCellValue(object.getCargo());

            Cell celda6 = fila.createCell((short) 3);
            celda6.setCellStyle(style2);
            celda6.setCellValue(obtenerNombreUnidad(object.getCodigoUnidad()));
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
        response.setHeader("Content-Disposition", "attachment;filename = ListadoEmpleados.xls");
        OutputStream outStream = response.getOutputStream();
        outStream.write(outArray);
        outStream.flush();
        fc.responseComplete();
    }
    
}
