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
public class Salida extends Entidad implements Acciones {

    
   
    private Integer entrada;
    private String imagen;

    public Salida(String mensaje, String tipo) {
        super(mensaje, tipo);
    }

    @Override
    public void dibujar(Graphics g) {
        if (entrada == null) {
            imagen = "/recursos/lienzo/lampOFF.png";
            getMensajeLog().setTipo("alerta");
            getMensajeLog().setMensaje("El componente no tiene conectado ninguna linea");
        } else {
            switch (entrada) {
                case 1:
                    imagen = "/recursos/lienzo/lampON.png";
                    getMensajeLog().setTipo("texto");
                    getMensajeLog().setMensaje("Salida ON");
                    break;
                case 0:
                    imagen = "/recursos/lienzo/lampOFF.png";
                    getMensajeLog().setTipo("texto");
                    getMensajeLog().setMensaje("Salida OFF");
                    break;
                default:

                    break;
            }
        }
        g.drawImage(new javax.swing.ImageIcon(getClass().getResource(imagen)).getImage(), getPosicionX(), getPosicionY(), null);

    }

    @Override
    public void eliminar() {
    }

    @Override
    public void validarEstado() {
    }

    public Integer getEntrada() {
        return entrada;
    }

    public void setEntrada(Integer entrada) {
        this.entrada = entrada;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}
