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
public class FachadaCompuertas {

    private Entidad objetosRepintar;

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
    

    //metodo que verifica que no se pueda 
    public boolean validarPosicionComponente(Entidad e, int posicionX, int posicionY){
        
        return false;
    }
    
    public void validaExtremosLinea(){
        
    }

    public Entidad getObjetosRepintar() {
        return objetosRepintar;
    }

    public void setObjetosRepintar(Entidad objetosRepintar) {
        this.objetosRepintar = objetosRepintar;
    }

}
