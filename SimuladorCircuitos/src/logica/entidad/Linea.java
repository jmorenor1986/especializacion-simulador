/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.entidad;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

/**
 *
 * @author john
 */
public class Linea extends Entidad{

    
    private int punto1X, punto1Y,punto2X,punto2Y;

    public Linea(String mensaje,String tipo) {
        super(mensaje,tipo);
    }

    @Override
    public void dibujar(BufferStrategy bf) {
        super.dibujar(bf); 
        Graphics lapiz = bf.getDrawGraphics();
        lapiz.setColor(Color.WHITE);
        lapiz.drawLine(punto1X, punto1Y, punto2X, punto2Y);
        
        
    }

    
    public int getPunto1X() {
        return punto1X;
    }

    public void setPunto1X(int punto1X) {
        this.punto1X = punto1X;
    }

    public int getPunto1Y() {
        return punto1Y;
    }

    public void setPunto1Y(int punto1Y) {
        this.punto1Y = punto1Y;
    }

    public int getPunto2X() {
        return punto2X;
    }

    public void setPunto2X(int punto2X) {
        this.punto2X = punto2X;
    }

    public int getPunto2Y() {
        return punto2Y;
    }

    public void setPunto2Y(int punto2Y) {
        this.punto2Y = punto2Y;
    }



    
    

 
    
    
  

}
