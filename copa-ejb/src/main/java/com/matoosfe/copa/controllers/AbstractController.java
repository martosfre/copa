/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.copa.controllers;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author martosfre
 * @param <T>
 */
public abstract class AbstractController<T> {

    private Class<T> tabla;

    public AbstractController(Class<T> tabla) {
        this.tabla = tabla;
    }

    protected abstract EntityManager getEntityManager();

    public void guardar(T registro) {
        getEntityManager().persist(registro);
    }

    public void actualizar(T registro) {
        getEntityManager().merge(registro);
    }

    public void eliminar(T registro) {
        getEntityManager().remove(getEntityManager().merge(registro));
    }

    public List<T> consultarTodos() {
        return getEntityManager().createQuery("Select t from " + tabla.getSimpleName() + " t", tabla).getResultList();
    }
    
    public T consultarPorId(Integer id){
        return getEntityManager().find(tabla, id);
    }
}
