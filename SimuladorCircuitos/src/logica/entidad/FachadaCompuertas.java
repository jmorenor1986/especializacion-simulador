/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.entidad;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author john
 */
public class FachadaCompuertas {

    private Entidad objetosRepintar;
    private ValidarTabla objetoTabla;
    public static String text, nombreObjeto, nombreObjeto1, nombreObjeto2;
    public static boolean estadoLineaUno;

    public void dibujar(Entidad e, Graphics g) {
        if (e instanceof Switch) {
            Switch sw = (Switch) e;
            sw.dibujar(g);
            objetosRepintar = sw;
        } else if (e instanceof Compuerta) {
            Compuerta cmp = (Compuerta) e;
            cmp.dibujar(g);
            objetosRepintar = cmp;
        } else if (e instanceof Linea) {
            Linea ln = (Linea) e;
            ln.dibujar(g);
            objetosRepintar = ln;
        } else if (e instanceof Salida) {
            Salida sal = (Salida) e;
            sal.dibujar(g);
            objetosRepintar = sal;
        }
    }

    public boolean validarPosicionComponente(ArrayList<Entidad> guardaObjetos, int posicionX, int posicionY, String tipo, int cantidad) {
        objetoTabla = new ValidarTabla();
        boolean validador = false;
        if (guardaObjetos.isEmpty()) {
            if ("linea".equals(tipo)) {
                text = "Para iniciar por favor seleccionar un elemento diferente a la linea";
                return false;
            } else {
                return true;
            }
        }
        switch (tipo) {
            case "and":
                for (Entidad ent : guardaObjetos) {
                    validador = objetoTabla.validarPosicionAnd(ent, posicionX, posicionY);
                    if (validador) {
                        return true;
                    }
                }
                break;
            case "linea":
                for (Entidad ent : guardaObjetos) {
                    validador = objetoTabla.validarPosicionLinea(ent, posicionX, posicionY, cantidad);

                    if (validador) {
                        return true;
                    }
                }
                break;
            case "on":
                for (Entidad ent : guardaObjetos) {
                    validador = objetoTabla.validarPosicionSwitch(ent, posicionX, posicionY);

                    if (validador) {
                        return true;
                    }
                }

                break;
            case "off":
                for (Entidad ent : guardaObjetos) {
                    validador = objetoTabla.validarPosicionSwitch(ent, posicionX, posicionY);

                    if (validador) {
                        return true;
                    }
                }

                break;
            case "lampOFF":
                for (Entidad ent : guardaObjetos) {
                    validador = objetoTabla.validarPosicionSwitch(ent, posicionX, posicionY);

                    if (validador) {
                        return true;
                    }
                }

                break;

        }
        return false;
    }

    public void validaExtremosLinea() {

    }

    public void validarFuncionamientoCircuito() {

    }

    public Entidad getObjetosRepintar() {
        return objetosRepintar;
    }

    public void setObjetosRepintar(Entidad objetosRepintar) {
        this.objetosRepintar = objetosRepintar;
    }

}
