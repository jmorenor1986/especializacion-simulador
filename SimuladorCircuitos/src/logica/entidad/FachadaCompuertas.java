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
    
    public  void dibujar(Entidad e, Graphics g) {
        if(e instanceof Switch){
            Switch sw = (Switch) e;
            sw.dibujar(g);
            objetosRepintar = sw;
        }
    }



    public Entidad getObjetosRepintar() {
        return objetosRepintar;
    }

    public void setObjetosRepintar(Entidad objetosRepintar) {
        this.objetosRepintar = objetosRepintar;
    }
    
    
}
