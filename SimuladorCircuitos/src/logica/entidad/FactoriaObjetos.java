/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.entidad;

/**
 *
 * @author john
 */
public class FactoriaObjetos {

    public static Entidad getEntidad(String tipo) {
        switch (tipo) {
            case "objeto1":
                return new Linea("La linea sirve para unir dos componentes","linea");
            case "objeto2":
                return new Compuerta("Componente AND, tiene dos entradas y una salida", "and");
            default:
                return null;
        }
    }
}
