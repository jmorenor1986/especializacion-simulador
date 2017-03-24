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
public class Compuerta extends Entidad implements Acciones {



    public Compuerta(String mensaje, String tipo) {
        super(mensaje, tipo);
    }

    @Override
    public void dibujar(Graphics g) {
        if (!"linea".equalsIgnoreCase(getTipo())) {
            g.drawImage(new javax.swing.ImageIcon(getClass().getResource("/recursos/lienzo/" + getTipo() + ".png")).getImage(), getPosicionX(), getPosicionY(), null);
        }
    }

    @Override
    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void validarEstado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
