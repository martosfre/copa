/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.copa.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author martosfre
 */
@Entity
@Table(name = "equipo", schema = "admin")
@NamedQueries({
    @NamedQuery(name = "Equipo.findAll", query = "SELECT e FROM Equipo e"),
    @NamedQuery(name = "Equipo.findByEquId", query = "SELECT e FROM Equipo e WHERE e.equId = :equId"),
    @NamedQuery(name = "Equipo.findByEquNombre", query = "SELECT e FROM Equipo e WHERE e.equNombre = :equNombre"),
    @NamedQuery(name = "Equipo.findByEquAnioFundacion", query = "SELECT e FROM Equipo e WHERE e.equAnioFundacion = :equAnioFundacion")})
public class Equipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "equ_id")
    private Integer equId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "equ_nombre")
    private String equNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "equ_anio_fundacion")
    private int equAnioFundacion;
    //1) Colocar el atributo CascadeType.ALL en la relación de muchos
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equId", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Jugador> jugadorList;

    public Equipo() {
    }

    public Equipo(Integer equId) {
        this.equId = equId;
    }

    public Equipo(Integer equId, String equNombre, int equAnioFundacion) {
        this.equId = equId;
        this.equNombre = equNombre;
        this.equAnioFundacion = equAnioFundacion;
    }

    public Integer getEquId() {
        return equId;
    }

    public void setEquId(Integer equId) {
        this.equId = equId;
    }

    public String getEquNombre() {
        return equNombre;
    }

    public void setEquNombre(String equNombre) {
        this.equNombre = equNombre;
    }

    public int getEquAnioFundacion() {
        return equAnioFundacion;
    }

    public void setEquAnioFundacion(int equAnioFundacion) {
        this.equAnioFundacion = equAnioFundacion;
    }

    public List<Jugador> getJugadorList() {
        return jugadorList;
    }

    public void setJugadorList(List<Jugador> jugadorList) {
        this.jugadorList = jugadorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (equId != null ? equId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipo)) {
            return false;
        }
        Equipo other = (Equipo) object;
        if ((this.equId == null && other.equId != null) || (this.equId != null && !this.equId.equals(other.equId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.matoosfe.copa.entities.Equipo[ equId=" + equId + " ]";
    }
    
}
