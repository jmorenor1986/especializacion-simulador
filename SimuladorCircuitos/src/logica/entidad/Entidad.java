/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.entidad;

import java.awt.Graphics;

/**
 *
 * @author john
 */
public class Entidad implements Acciones {

    private String estado;
    private String nombre;
    private String tipo;
    private String mensaje;

    public Entidad(String mensaje, String tipo) {
        this.mensaje = mensaje;
        this.tipo = tipo;
    }

    @Override
    public void dibujar(Graphics g) {
    }

    @Override
    public void eliminar() {
    }

    @Override
    public void validarEstado() {
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
