/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

/**
 *
 * @author john
 */
public class Controlador implements MouseListener, ActionListener {

    private final Vista ventana;

    public Controlador(Vista aThis) {
        ventana = aThis;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if ("class java.awt.Canvas".equalsIgnoreCase(e.getSource().getClass().toString())) {
            ventana.getModelo().dibujaComponente(e.getX(), e.getY());

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("javax.swing.JMenuItem".equalsIgnoreCase(e.getSource().getClass().getName())) {
            JMenuItem item = (JMenuItem) e.getSource();
            if ("simular".equalsIgnoreCase(item.getText())) {
                ventana.getModelo().realizaSimulacion();
            }
            if ("deshacer".equalsIgnoreCase(item.getText())) {
                ventana.getModelo().deshacer();
            }
        }

    }

}
