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
public class Switch extends Entidad implements Acciones {

    public Switch(String mensaje, String tipo) {
        super(mensaje, tipo);
    }

    @Override
    public void dibujar(Graphics g) {
        if (getTipo().equalsIgnoreCase("ON")) {
            getRelaciones().add(new Relaciones("salida", 1, ""));
            getMensajeLog().setMensaje("Componente con salida 1");
        } else {
            getRelaciones().add(new Relaciones("salida", 0, ""));
            getMensajeLog().setMensaje("Componente con salida 0");
        }

        g.drawImage(new javax.swing.ImageIcon(getClass().getResource("/recursos/lienzo/" + getTipo() + ".png")).getImage(), getPosicionX(), getPosicionY(), null);
        getMensajeLog().setTipo("texto");
    }

    @Override
    public void eliminar() {
        //pendiente si se desea seleccionar el componente a eliminar
    }

    @Override
    public void validarEstado() {
    }

}
