/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import logica.entidad.Compuerta;
import logica.entidad.Entidad;
import logica.entidad.FactoriaObjetos;
import logica.entidad.Linea;

/**
 *
 * @author john
 */
public class Modelo {

    private Vista ventana;
    private Entidad objeto;
    private int cantidad = 0;
    private Linea objetoLinea;
    private Compuerta compuerta;

    public Vista getVentana() {
        if (ventana == null) {
            ventana = new Vista(this);
        }
        return ventana;
    }

    public void iniciar() {
        getVentana().setSize(1000, 600);
        getVentana().setVisible(true);
    }

    public void validaObjetoSeleccionado(String nombre) {
        objeto = FactoriaObjetos.getEntidad(nombre);
        getVentana().getLabelLog().setText(objeto.getMensaje());
        getVentana().getLabelLog().setForeground(new java.awt.Color(253, 253, 0));

    }

    public void validaPosicionLienzo(int posicionX, int posicionY) {
        if (objeto == null) {
            getVentana().getLabelLog().setText("Error,debe seleccionar un componente");
            getVentana().getLabelLog().setForeground(new java.awt.Color(253, 253, 0));
        } else {

            if ("linea".equalsIgnoreCase(objeto.getTipo())) {
                dibujaLinea(posicionX, posicionY);
            } else {
                dibujaCompuerta(posicionX, posicionY);
            }

        }

    }

    public void dibujaLinea(int posicionX, int posicionY) {
        if (cantidad == 0) {
            objetoLinea = (Linea) objeto;
            objetoLinea.setPunto1X(posicionX);
            objetoLinea.setPunto1Y(posicionY);

        }
        if (cantidad == 1) {
            objetoLinea.setPunto2X(posicionX);
            objetoLinea.setPunto2Y(posicionY);
            objetoLinea.dibujar(getVentana().getLienzo().getGraphics());
            cantidad = -1;

        }
        cantidad++;
    }
    
    public void dibujaCompuerta(int posicionX, int posicionY){
        compuerta = (Compuerta) objeto;
        compuerta.setPosicionX(posicionX);
        compuerta.setPosicionY(posicionY);
        compuerta.dibujar(getVentana().getLienzo().getGraphics());
    }
    
    public void iniciaLienzo(){
        getVentana().getLienzo().repaint();
    }

}
