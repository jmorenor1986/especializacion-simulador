/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.awt.Color;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.entidad.Entidad;
import logica.entidad.FachadaCompuertas;
import logica.entidad.FactoriaCompuertas;
import logica.entidad.Linea;
import logica.entidad.MensajeLog;

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
    private FachadaCompuertas fachada;
    private MensajeLog mensaje;
    private int consecutivoNombre = 0;

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
        if (fachada == null) {
            fachada = new FachadaCompuertas();
        }
        if (mensaje == null) {
            mensaje = new MensajeLog();
        }
    }

    public void validaObjetoSeleccionado(String nombre) {
        objeto = FactoriaCompuertas.getEntidad(nombre);
        nombreObjeto = nombre;
        mensaje.setTipo("texto");
        getVentana().getLabelLog().setText(objeto.getMensaje());
        getVentana().getLabelLog().setForeground(mensaje.getColor());

    }

    public void dibujaComponente(int posicionX, int posicionY) {
        if (objeto == null) {
            mensaje.setTipo("error");
            mensaje.setMensaje("Error,debe seleccionar un componente");
            getVentana().getLabelLog().setText(mensaje.getMensaje());
            getVentana().getLabelLog().setForeground(mensaje.getColor());
        } else {
            if (validaComponente(posicionX, posicionY)) {
                if (!"linea".equalsIgnoreCase(objeto.getTipo())) {
                    objeto.setPosicionX(posicionX);
                    objeto.setPosicionY(posicionY);
                    fachada.dibujar(objeto, getVentana().getLienzo().getGraphics());
                    fachada.getObjetosRepintar().setNombre("objeto" + consecutivoNombre);
                    consecutivoNombre++;
                    guardaObjetos.add(fachada.getObjetosRepintar());
                    getVentana().getLabelLog().setText(fachada.getObjetosRepintar().getMensajeLog().getMensaje());
                    getVentana().getLabelLog().setForeground(fachada.getObjetosRepintar().getMensajeLog().getColor());
                    objeto = FactoriaCompuertas.getEntidad(nombreObjeto);
                } else {
                    dibujaLinea(posicionX, posicionY);
                }
            }

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
            fachada.dibujar(objetoLinea, getVentana().getLienzo().getGraphics());
            fachada.getObjetosRepintar().setNombre("objeto" + consecutivoNombre);
            consecutivoNombre++;
            cantidad = -1;
            guardaObjetos.add(fachada.getObjetosRepintar());

        }
        cantidad++;
    }

    public boolean validaComponente(int posicionX, int posicionY) {
        for (Entidad ent : guardaObjetos) {
            if (!fachada.validarPosicionComponente(ent, posicionX, posicionY)) {
                //false que no se puede pintar
                getVentana().getLabelLog().setText("Error, no se puede dibujar un componente sobre otro");
                getVentana().getLabelLog().setForeground(Color.RED);
                return false;
            }
        }
        return true;
    }

    public void iniciaLienzo() {
        getVentana().getLienzo().repaint();
    }

    public void repintarLienzo() {
        iniciaLienzo();
        for (Entidad obj : guardaObjetos) {
            fachada.dibujar(obj, getVentana().getLienzo().getGraphics());
            System.out.println("posicion" + obj.getPosicionX() + "*****" + obj.getPosicionY());
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
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
