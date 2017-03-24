/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.entidad;

import java.util.ArrayList;

/**
 *
 * @author john
 */
public class Entidad {

    private String estado;
    private String nombre;
    private String tipo;
    private String mensaje;
    private int posicionX;
    private int posicionY;
    private MensajeLog mensajeLog;
    private ArrayList<Relaciones> relaciones;

    public Entidad(String mensaje, String tipo) {
        this.mensaje = mensaje;
        this.tipo = tipo;
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

    public int getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }

    public MensajeLog getMensajeLog() {
        if (mensajeLog == null) {
            mensajeLog = new MensajeLog();
        }
        return mensajeLog;
    }

    public void setMensajeLog(MensajeLog mensajeLog) {
        this.mensajeLog = mensajeLog;
    }

    public ArrayList<Relaciones> getRelaciones() {
        if (relaciones == null) {
            relaciones = new ArrayList<>();
        }
        return relaciones;
    }

    public void setRelaciones(ArrayList<Relaciones> relaciones) {
        this.relaciones = relaciones;
    }

}
