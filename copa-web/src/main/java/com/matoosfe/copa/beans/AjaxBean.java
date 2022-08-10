/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.copa.beans;

import java.io.FileOutputStream;
import java.io.OutputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author martosfre
 */
@ManagedBean
@RequestScoped
public class AjaxBean {

    private String nombre;
    private String apellido;

    public AjaxBean() {
        this.nombre = "Marco";
        this.apellido = "Toscano";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void cambiarNombre() {
        this.nombre = "Xavier";
        this.apellido = "Zabala";
    }

    public void exportarExcel() {
        try {
            Workbook wb = new HSSFWorkbook();
            Sheet hoja = wb.createSheet("datosPersona");
            Row row = hoja.createRow(0);
            Cell celNombre = row.createCell(0, CellType.STRING);
            celNombre.setCellValue(nombre);
            Cell celApellido = row.createCell(1, CellType.STRING);
            celApellido.setCellValue(apellido);

//            try ( OutputStream fileOut = new FileOutputStream("/Users/martosfre/Downloads/personas.xls")) {
//                wb.write(fileOut);
//            }
            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            respuesta.addHeader("Content-Disposition", "inline; filename=personasWeb.xls");
            respuesta.setContentType("application/vnd.ms-excel");
            wb.write(respuesta.getOutputStream());

            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
            e.printStackTrace();
        }
    }
}
