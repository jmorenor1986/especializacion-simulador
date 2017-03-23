/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.entidad.Entidad;
import logica.entidad.FachadaCompuertas;
import logica.entidad.FactoriaCompuertas;
import logica.entidad.Linea;

/**
 *
 * @author john
 */
public class Modelo implements Runnable {

    private Vista ventana;
    private Entidad objeto;
    private int cantidad = 0;
    private Linea objetoLinea;
    private Thread hiloDibujo;
    private boolean pintaLienzo;
    private ArrayList<Entidad> guardaObjetos;
    private String nombreObjeto;

    public Modelo() {
        hiloDibujo = new Thread(this);
    }

    public Vista getVentana() {
        if (ventana == null) {
            ventana = new Vista(this);
        }
        return ventana;
    }

    public void iniciar() {
        getVentana().setSize(1000, 600);
        getVentana().setVisible(true);
        hiloDibujo.start();
        pintaLienzo = true;
        if (guardaObjetos == null) {
            guardaObjetos = new ArrayList<>();
        }
    }

    public void validaObjetoSeleccionado(String nombre) {
        objeto = FactoriaCompuertas.getEntidad(nombre);
        nombreObjeto = nombre;
        getVentana().getLabelLog().setText(objeto.getMensaje());
        getVentana().getLabelLog().setForeground(new java.awt.Color(253, 253, 0));

    }

    public void validaPosicionLienzo(int posicionX, int posicionY) {
        if (objeto == null) {
            getVentana().getLabelLog().setText("Error,debe seleccionar un componente");
            getVentana().getLabelLog().setForeground(new java.awt.Color(253, 253, 0));
        } else {
            FachadaCompuertas fachada = new FachadaCompuertas();
            objeto.setPosicionX(posicionX);
            objeto.setPosicionY(posicionY);
            fachada.dibujar(objeto, getVentana().getLienzo().getGraphics());
            guardaObjetos.add(fachada.getObjetosRepintar());
        }

    }

    public void dibujaLinea(int posicionX, int posicionY) {
        if (cantidad == 0) {
            objetoLinea = (Linea) FactoriaCompuertas.getEntidad(nombreObjeto);
            objetoLinea.setPunto1X(posicionX);
            objetoLinea.setPunto1Y(posicionY);

        }
        if (cantidad == 1) {
            objetoLinea.setPunto2X(posicionX);
            objetoLinea.setPunto2Y(posicionY);
            objetoLinea.dibujar(getVentana().getLienzo().getGraphics());
            cantidad = -1;
            guardaObjetos.add(objetoLinea);

        }
        cantidad++;
    }

    public void iniciaLienzo() {
        getVentana().getLienzo().repaint();
    }

    public void repintarLienzo() {
        iniciaLienzo();
        System.out.println("presentacion.Modelo.repintarLienzo()"+guardaObjetos.size());
        for (Entidad obj : guardaObjetos) {
            FachadaCompuertas fachada = new FachadaCompuertas();
            fachada.dibujar(obj, getVentana().getLienzo().getGraphics());
            System.out.println("posicion"+obj.getPosicionX()+"*****"+obj.getPosicionY());
        }

    }

    public void deshacer() {
        if (guardaObjetos != null && guardaObjetos.size() > 0) {
            guardaObjetos.remove(guardaObjetos.size() - 1);
        }
    }

    @Override
    public void run() {
        while (pintaLienzo) {
            try {
                repintarLienzo();
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
