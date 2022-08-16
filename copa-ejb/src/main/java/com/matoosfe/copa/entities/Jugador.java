/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.copa.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 *
 * @author martosfre
 */
@Entity
@Table(name = "jugador", schema = "admin")
@NamedQueries({
    @NamedQuery(name = "Jugador.findAll", query = "SELECT j FROM Jugador j"),
    @NamedQuery(name = "Jugador.findByJugId", query = "SELECT j FROM Jugador j WHERE j.jugId = :jugId"),
    @NamedQuery(name = "Jugador.findByJugNombre", query = "SELECT j FROM Jugador j WHERE j.jugNombre = :jugNombre"),
    @NamedQuery(name = "Jugador.findByJugApellidoPaterno", query = "SELECT j FROM Jugador j WHERE j.jugApellidoPaterno = :jugApellidoPaterno"),
    @NamedQuery(name = "Jugador.findByJugApellidoMaterno", query = "SELECT j FROM Jugador j WHERE j.jugApellidoMaterno = :jugApellidoMaterno"),
    @NamedQuery(name = "Jugador.findByJugFechaNacimiento", query = "SELECT j FROM Jugador j WHERE j.jugFechaNacimiento = :jugFechaNacimiento"),
    @NamedQuery(name = "Jugador.findByJugTelefonoCelular", query = "SELECT j FROM Jugador j WHERE j.jugTelefonoCelular = :jugTelefonoCelular"),
    @NamedQuery(name = "Jugador.findByJugCorreo", query = "SELECT j FROM Jugador j WHERE j.jugCorreo = :jugCorreo"),
    @NamedQuery(name = "Jugador.findByJugDireccion", query = "SELECT j FROM Jugador j WHERE j.jugDireccion = :jugDireccion")})
public class Jugador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "jug_id")
    private Integer jugId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "jug_nombre")
    private String jugNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "jug_apellido_paterno")
    private String jugApellidoPaterno;
    @Size(max = 50)
    @Column(name = "jug_apellido_materno")
    private String jugApellidoMaterno;
    @Basic(optional = false)
    @NotNull
    @Column(name = "jug_fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date jugFechaNacimiento;
    @Lob
    @Column(name = "jug_foto")
    private byte[] jugFoto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "jug_telefono_celular")
    private String jugTelefonoCelular;
    @Size(max = 50)
    @Column(name = "jug_correo")
    private String jugCorreo;
    @Size(max = 300)
    @Column(name = "jug_direccion")
    private String jugDireccion;
    @JoinColumn(name = "equ_id", referencedColumnName = "equ_id")
    @ManyToOne(optional = false)
    private Equipo equId;
    @JoinColumn(name = "parr_id", referencedColumnName = "parr_id")
    @ManyToOne(optional = false)
    private Parroquia parrId;
    @Transient
    private String localidad;

    public Jugador() {
    }

    public Jugador(Integer jugId) {
        this.jugId = jugId;
    }

    public Jugador(Integer jugId, String jugNombre, String jugApellidoPaterno, Date jugFechaNacimiento, String jugTelefonoCelular) {
        this.jugId = jugId;
        this.jugNombre = jugNombre;
        this.jugApellidoPaterno = jugApellidoPaterno;
        this.jugFechaNacimiento = jugFechaNacimiento;
        this.jugTelefonoCelular = jugTelefonoCelular;
    }

    public Integer getJugId() {
        return jugId;
    }

    public void setJugId(Integer jugId) {
        this.jugId = jugId;
    }

    public String getJugNombre() {
        return jugNombre;
    }

    public void setJugNombre(String jugNombre) {
        this.jugNombre = jugNombre;
    }

    public String getJugApellidoPaterno() {
        return jugApellidoPaterno;
    }

    public void setJugApellidoPaterno(String jugApellidoPaterno) {
        this.jugApellidoPaterno = jugApellidoPaterno;
    }

    public String getJugApellidoMaterno() {
        return jugApellidoMaterno;
    }

    public void setJugApellidoMaterno(String jugApellidoMaterno) {
        this.jugApellidoMaterno = jugApellidoMaterno;
    }

    public Date getJugFechaNacimiento() {
        return jugFechaNacimiento;
    }

    public void setJugFechaNacimiento(Date jugFechaNacimiento) {
        this.jugFechaNacimiento = jugFechaNacimiento;
    }

    public byte[] getJugFoto() {
        return jugFoto;
    }

    public void setJugFoto(byte[] jugFoto) {
        this.jugFoto = jugFoto;
    }

    public String getJugTelefonoCelular() {
        return jugTelefonoCelular;
    }

    public void setJugTelefonoCelular(String jugTelefonoCelular) {
        this.jugTelefonoCelular = jugTelefonoCelular;
    }

    public String getJugCorreo() {
        return jugCorreo;
    }

    public void setJugCorreo(String jugCorreo) {
        this.jugCorreo = jugCorreo;
    }

    public String getJugDireccion() {
        return jugDireccion;
    }

    public void setJugDireccion(String jugDireccion) {
        this.jugDireccion = jugDireccion;
    }

    public Equipo getEquId() {
        return equId;
    }

    public void setEquId(Equipo equId) {
        this.equId = equId;
    }

    public Parroquia getParrId() {
        return parrId;
    }

    public void setParrId(Parroquia parrId) {
        this.parrId = parrId;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jugId != null ? jugId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jugador)) {
            return false;
        }
        Jugador other = (Jugador) object;
        if ((this.jugId == null && other.jugId != null) || (this.jugId != null && !this.jugId.equals(other.jugId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.matoosfe.copa.entities.Jugador[ jugId=" + jugId + " ]";
    }

}
