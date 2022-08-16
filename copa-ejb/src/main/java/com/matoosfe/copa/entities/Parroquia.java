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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "parroquia", schema = "admin")

@NamedQueries({
    @NamedQuery(name = "Parroquia.findAll", query = "SELECT p FROM Parroquia p"),
    @NamedQuery(name = "Parroquia.findByParrId", query = "SELECT p FROM Parroquia p WHERE p.parrId = :parrId"),
    @NamedQuery(name = "Parroquia.findByParrNombre", query = "SELECT p FROM Parroquia p WHERE p.parrNombre = :parrNombre"),
    @NamedQuery(name = "Parroquia.findByParrCodigo", query = "SELECT p FROM Parroquia p WHERE p.parrCodigo = :parrCodigo")})
public class Parroquia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "parr_id")
    private Integer parrId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "parr_nombre")
    private String parrNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "parr_codigo")
    private String parrCodigo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parrId")
    private List<Jugador> jugadorList;
    @JoinColumn(name = "can_id", referencedColumnName = "can_id")
    @ManyToOne(optional = false)
    private Canton canId;

    public Parroquia() {
    }

    public Parroquia(Integer parrId) {
        this.parrId = parrId;
    }

    public Parroquia(Integer parrId, String parrNombre, String parrCodigo) {
        this.parrId = parrId;
        this.parrNombre = parrNombre;
        this.parrCodigo = parrCodigo;
    }

    public Integer getParrId() {
        return parrId;
    }

    public void setParrId(Integer parrId) {
        this.parrId = parrId;
    }

    public String getParrNombre() {
        return parrNombre;
    }

    public void setParrNombre(String parrNombre) {
        this.parrNombre = parrNombre;
    }

    public String getParrCodigo() {
        return parrCodigo;
    }

    public void setParrCodigo(String parrCodigo) {
        this.parrCodigo = parrCodigo;
    }

    public List<Jugador> getJugadorList() {
        return jugadorList;
    }

    public void setJugadorList(List<Jugador> jugadorList) {
        this.jugadorList = jugadorList;
    }

    public Canton getCanId() {
        return canId;
    }

    public void setCanId(Canton canId) {
        this.canId = canId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parrId != null ? parrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parroquia)) {
            return false;
        }
        Parroquia other = (Parroquia) object;
        if ((this.parrId == null && other.parrId != null) || (this.parrId != null && !this.parrId.equals(other.parrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.matoosfe.copa.entities.Parroquia[ parrId=" + parrId + " ]";
    }
    
}
