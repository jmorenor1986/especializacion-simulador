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

    private int valorSalida;

    public Switch(String mensaje, String tipo) {
        super(mensaje, tipo);
    }

    @Override
    public void dibujar(Graphics g) {
        if (getTipo().equalsIgnoreCase("ON")) {
            valorSalida = 1;
        } else {
            valorSalida = 0;
        }
        g.drawImage(new javax.swing.ImageIcon(getClass().getResource("/recursos/lienzo/" + getTipo() + ".png")).getImage(), getPosicionX(), getPosicionY(), null);
    }

    @Override
    public void eliminar() {
        //pendiente si se desea seleccionar el componente a eliminar
    }

    @Override
    public void validarEstado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getValorSalida() {
        return valorSalida;
    }

    public void setValorSalida(int valorSalida) {
        this.valorSalida = valorSalida;
    }

}
