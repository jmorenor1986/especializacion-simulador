/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Logica;
import logica.entidad.Entidad;
import logica.FachadaCompuertas;
import logica.FactoriaCompuertas;
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
    private BufferedImage dobleBuffer;
    private Canvas lienzo;
    //private Logica logica;
    private Logica logica;

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
        getVentana().setResizable(false);
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

    public void dibujaBackground() {
        getVentana().getLienzo().getGraphics().drawImage(new javax.swing.ImageIcon(getClass().getResource("/recursos/lienzo/cuaderno.jpg")).getImage(), 0, 0, null);
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
            objetoLinea.setNombreObjetoEntrada(FachadaCompuertas.nombreObjetoEntrada);
            objetoLinea.setNombreObjetoSalida(FachadaCompuertas.nombreObjetoSalida);
            fachada.dibujar(objetoLinea, getVentana().getLienzo().getGraphics());
            fachada.getObjetosRepintar().setNombre("objeto" + consecutivoNombre);
            consecutivoNombre++;
            cantidad = -1;

            guardaObjetos.add(fachada.getObjetosRepintar());

        }
        cantidad++;
    }

    //metodo que valida la posicion del componente
    public boolean validaComponente(int posicionX, int posicionY) {
        boolean validador;
        validador = fachada.validarPosicionComponente(guardaObjetos, posicionX, posicionY, objeto.getTipo(), cantidad);
        getVentana().getLabelLog().setText(FachadaCompuertas.text);
        if (validador) {
            getVentana().getLabelLog().setForeground(Color.blue);
        } else {
            getVentana().getLabelLog().setForeground(Color.red);
            try {
                if (FachadaCompuertas.text.contains("OK")) {
                    getVentana().getLabelLog().setText("Punto invalido");
                }
            } catch (Exception e) {
                System.out.println("punto invalido");
            }

        }
        return validador;
    }

    public void validaFormaCircuito() {
        getLogica().setObjetosEntidad(guardaObjetos);
        getLogica().guardaObjetos();
        if (getLogica().getObjetoSwitch().isEmpty()) {
            mensaje.setMensaje("En el circuito no existe por lo menos un switch");
            mensaje.setTipo("error");

        } else if (getLogica().getObjetoLinea().isEmpty()) {
            mensaje.setMensaje("En el circuito no existe por lo menos una conexión");
            mensaje.setTipo("error");
        } else {
            Entidad ent = getLogica().verificaEstructuraCircuito();
            if (ent == null) {
                getLogica().iniciaVerificacion();
                if (getLogica().getMensajeLog().getMensaje().startsWith("OK")) {
                    mensaje = getLogica().getMensajeLog();
                }
            } else {
                mensaje.setMensaje("Circuito mal formado revise conexión de una entidad de tipo " + ent.getTipo());
                mensaje.setTipo("error");
            }

        }

        getVentana().getLabelLog().setText(mensaje.getMensaje());
        getVentana().getLabelLog().setForeground(mensaje.getColor());
        guardaObjetos = getLogica().getObjetosEntidad();
    }

    public void realizaSimulacion() {
        validaFormaCircuito();
    }

    public void nuevoLienzo() {
        lienzo.repaint();
        guardaObjetos=new ArrayList<>();
    }

    public void repintarLienzo() {
        lienzo = getVentana().getLienzo();
        dobleBuffer = new BufferedImage(lienzo.getWidth(), lienzo.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (Entidad obj : guardaObjetos) {
            fachada.dibujar(obj, dobleBuffer.getGraphics());
            System.out.println("posicion" + obj.getPosicionX() + "*****" + obj.getPosicionY());
        }

        Graphics g = lienzo.getGraphics();
        g.drawImage(dobleBuffer, 0, 0, lienzo);
    }

    public void deshacer() {
        lienzo.repaint();
        if (guardaObjetos != null && guardaObjetos.size() > 0) {
            guardaObjetos.remove(guardaObjetos.size() - 1);
        }

    }

    @Override
    public void run() {
        while (pintaLienzo) {
            try {
                repintarLienzo();
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Logica getLogica() {
        if (logica == null) {
            logica = new Logica();
        }
        return logica;
    }

}
