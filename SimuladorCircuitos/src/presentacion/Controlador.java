/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;

/**
 *
 * @author john
 */
public class Controlador implements MouseListener {

    private final Vista ventana;

    public Controlador(Vista aThis) {
        ventana = aThis;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("clase::" + e.getSource().getClass());
        if ("class java.awt.Canvas".equalsIgnoreCase(e.getSource().getClass().toString())) {
            ventana.getModelo().validaPosicionLienzo(e.getX(), e.getY());

        } else {
            JLabel etiqueta;
            etiqueta = (JLabel) e.getSource();
            ventana.getModelo().validaObjetoSeleccionado(etiqueta.getName());
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
