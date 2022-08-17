/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.copa.beans;

import com.matoosfe.copa.beans.util.AbstractManagedBean;
import com.matoosfe.copa.controllers.EquipoController;
import com.matoosfe.copa.controllers.ParroquiaController;
import com.matoosfe.copa.entities.Equipo;
import com.matoosfe.copa.entities.Jugador;
import com.matoosfe.copa.entities.Parroquia;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author martosfre
 *///CDI
@Named
@ViewScoped
public class EquipoBean extends AbstractManagedBean implements Serializable {

    @Getter
    @Setter
    private Equipo equipo;
    @Getter
    @Setter
    private Equipo equipoSel;
    @Getter
    @Setter
    private List<Equipo> listaEquipos;
    @Getter
    @Setter
    private List<Equipo> equiposSeleccionados;

    @Inject
    private EquipoController adminEquipo;

    @Inject
    private ParroquiaController adminParroquia;

    public EquipoBean() {
        this.equipo = new Equipo();
        this.listaEquipos = new ArrayList<>();
    }

    /**
     * Método para cargar los equipos
     */
    private void cargarEquipos() {
        try {
            this.listaEquipos = adminEquipo.consultarEquipos();
        } catch (Exception e) {
            anadirError("Error al cargar los equipos:" + e.getMessage());
        }
    }

    /**
     * Método para guardar o actualizar un equipo
     */
    public void guardar() {
        try {
            if (equipo.getEquId() == null) {
                adminEquipo.guardar(equipo);
                anadirInfo("Equipo guardado correctamente");
            } else {
                adminEquipo.actualizar(equipo);
                anadirInfo("Equipo actualizado correctamente");
            }
            cargarEquipos();
            resetearFormulario();
        } catch (Exception e) {
            anadirError("Error:" + e.getMessage());
        }
    }

    /**
     * Selecccionar una fila
     *
     * @param ev
     */
    public void seleccionarFila(SelectEvent<Equipo> ev) {
        this.equipoSel = ev.getObject();
        this.equipo = equipoSel;
    }

    /**
     * Método para eliminar
     */
    public void eliminar() {
        if (equipoSel != null) {
            adminEquipo.eliminar(equipoSel);
            anadirInfo("Registro eliminado correctamente");
            cargarEquipos();
            resetearFormulario();
        } else {
            anadirError("Se debe seleccionar un equipo!!!");
        }
    }

    /**
     * Método para guardar un equipo y sus jugadores
     */
    public void guardarEquipoJugador() {
        try {
            Parroquia parroquia = adminParroquia.consultarPorId(1);

            Equipo equipoTx = new Equipo();
            equipoTx.setEquAnioFundacion(2022);
            equipoTx.setEquNombre("América");

            List<Jugador> listaJugadores = new ArrayList<>();

            Jugador jugA = new Jugador();
            jugA.setJugNombre("Mario");
            jugA.setJugApellidoPaterno("Vinueza");
            jugA.setJugFechaNacimiento(Date.from(LocalDate.of(1986, Month.MARCH, 10).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
            jugA.setJugTelefonoCelular("0998282828");
            jugA.setEquId(equipoTx);
            jugA.setParrId(parroquia);
            listaJugadores.add(jugA);

            Jugador jugB = new Jugador();
//            jugB.setJugNombre("Eduardo");
            jugB.setJugApellidoPaterno("Vaca");
            jugB.setJugFechaNacimiento(Date.from(LocalDate.of(1986, Month.MARCH, 10).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
            jugB.setJugTelefonoCelular("0998282828");
            jugB.setEquId(equipoTx);
            jugB.setParrId(parroquia);
            listaJugadores.add(jugB);

//            String mensaje = adminEquipo.guardar(equipoTx, listaJugadores);
//            anadirInfo(mensaje);
            String mensajeJug = adminEquipo.guardarJug(equipoTx, listaJugadores);
            anadirInfo(mensajeJug);

        } catch (Exception e) {
            anadirError("Error:" + e.getMessage());
        }
    }

    /**
     * Método para guardar un maestro detalle
     */
    public void guardarEquipoJugadorMD() {
        try {
            Parroquia parroquia = adminParroquia.consultarPorId(1);

            Equipo equipoTx = new Equipo();
            equipoTx.setEquAnioFundacion(2022);
            equipoTx.setEquNombre("Aucas");

            List<Jugador> listaJugadores = new ArrayList<>();

            Jugador jugA = new Jugador();
            jugA.setJugNombre("La Tuca");
            jugA.setJugApellidoPaterno("Ordoñez");
            jugA.setJugFechaNacimiento(Date.from(LocalDate.of(1986, Month.MARCH, 10).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
            jugA.setJugTelefonoCelular("0998282828");
            //2)Setear la clase padre
            jugA.setEquId(equipoTx);
            jugA.setParrId(parroquia);
            listaJugadores.add(jugA);

            Jugador jugB = new Jugador();
            jugB.setJugNombre("Juan Carlos");
            jugB.setJugApellidoPaterno("Figueroa");
            jugB.setJugFechaNacimiento(Date.from(LocalDate.of(1986, Month.MARCH, 10).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
            jugB.setJugTelefonoCelular("0998282828");
            //2)Setear la clase padre
            jugB.setEquId(equipoTx);
            jugB.setParrId(parroquia);
            listaJugadores.add(jugB);

            //3)Cargar los hijos al padre
            equipoTx.setJugadorList(listaJugadores);

            //4)Enviar a guardar el padre
            adminEquipo.guardar(equipoTx);
            anadirInfo("Equipo con jugadores guardado correctamente");
        } catch (Exception e) {
            anadirError("Error:" + e.getMessage());
        }
    }

    /**
     * Método para guardar equipo con jugadores desde un archivo csv
     *
     * @param file
     */
    public void guardarEquipoJugadorCSV(FileUploadEvent csv) {
        try {
            InputStream fis = new ByteArrayInputStream(csv.getFile().getContent());
            BufferedReader lector = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] valores = linea.split(",");
                Equipo equipoTx = new Equipo();
                equipoTx.setEquNombre(valores[0]);
                equipoTx.setEquAnioFundacion(Integer.parseInt(valores[1]));
                adminEquipo.guardar(equipoTx);
                cargarEquipos();
//                Arrays.stream(valores).forEach(v -> System.out.println("V:" + v));
            }
            anadirInfo("Equipos registrados correctamente");
        } catch (IOException | NumberFormatException e) {
            anadirError("Error al procesar CSV:" + e.getMessage());
        }

    }

    /**
     * Método para exportar selección
     */
    public void exportarSeleccion() {
        try {
            Workbook wb = new HSSFWorkbook();
            Sheet hoja = wb.createSheet("Equipos");
            if (equiposSeleccionados != null) {
                final AtomicInteger i = new AtomicInteger(-1);
                equiposSeleccionados.forEach(eq -> {
                    Row row = hoja.createRow(i.incrementAndGet());
                    Cell celNombre = row.createCell(0, CellType.STRING);
                    celNombre.setCellValue(eq.getEquNombre());
                    Cell celApellido = row.createCell(1, CellType.STRING);
                    celApellido.setCellValue(eq.getEquAnioFundacion());
                });
                HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                respuesta.addHeader("Content-Disposition", "inline; filename=equipos.xls");
                respuesta.setContentType("application/vnd.ms-excel");
                wb.write(respuesta.getOutputStream());

                FacesContext.getCurrentInstance().responseComplete();

            } else {
                anadirInfo("No se ha seleccionado ningún equipo");
            }

        } catch (Exception e) {
            anadirError("Error:" + e.getMessage());
        }
    }

    /**
     * Método para limpiar el formulario
     */
    public void resetearFormulario() {
        this.equipo = new Equipo();
        this.equipoSel = null;
    }

    @PostConstruct
    public void inicializar() {
        cargarEquipos();
    }

}
