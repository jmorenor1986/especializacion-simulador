/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.entidad;

/**
 *
 * @author Asesoftware
 */
public class ValidarTabla {
    //validadores entrada 1 punto 1 linea

    private int maxxentrada1 = 0, minxentrada1, maxyentrada1, minyentrada1;
    //validadores entrada 2 punto 1 linea
    private int maxxentrada2, minxentrada2, maxyentrada2, minyentrada2;
    //validadores salida 1 punto 1 linea
    private int maxxsalida1, minxsalida1, maxysalida1, minysalida1;

    public void calcularPosiciones(int actualx, int actualy) {
        //validadores entrada 1 punto 1 linea
        maxxentrada1 = actualx + 5;
        minxentrada1 = actualx - 5;
        maxyentrada1 = actualy + 10;
        minyentrada1 = actualy + 5;
        //validadores entrada 2 punto 1 linea
        maxxentrada2 = actualx + 5;
        minxentrada2 = actualx - 5;
        maxyentrada2 = actualy + 37;
        minyentrada2 = actualy + 27;
        //validadores salida 1 punto 1 linea
        maxxsalida1 = actualx + 105;
        minxsalida1 = actualx + 95;
        maxysalida1 = actualy + 25;
        minysalida1 = actualy + 15;
    }

    public boolean validarPosicionAnd(Entidad ent, int posicionX, int posicionY) {
        int emaxx = 0, eminx = 0, emaxy = 0, eminy = 0;
        if ("and".equals(ent.getTipo())) {
            emaxx = ent.getPosicionX() + 100;
            eminx = ent.getPosicionX() - 100;
            emaxy = ent.getPosicionY() + 50;
            eminy = ent.getPosicionY() - 50;
        }
        if ((posicionX >= eminx) && (posicionX <= emaxx) && (posicionY >= eminy) && (posicionY <= emaxy)) {
            FachadaCompuertas.text = "No se debe colocar un elemento sobre otro";
            return false;
        } else {
            return true;
        }
    }

    public boolean validarPosicionLinea(Entidad ent, int posicionX, int posicionY, int cantidad) {
        boolean validador = false;
        calcularPosiciones(ent.getPosicionX(), ent.getPosicionY());
        if ((posicionX >= minxentrada1) && (posicionX <= maxxentrada1) && (posicionY >= minyentrada1) && (posicionY <= maxyentrada1)) {
            validador = validarInput(ent.isIsInput1(), ent.isIsOutput1(), cantidad, ent.getNombre());
            if (!ent.isIsInput1()) {
                ent.setIsInput1(validador);
            }
            return validador;
        } else if ((posicionX >= minxentrada2) && (posicionX <= maxxentrada2) && (posicionY >= minyentrada2) && (posicionY <= maxyentrada2)) {
            validador = validarInput(ent.isIsInput2(), ent.isIsOutput1(), cantidad, ent.getNombre());
            if (!ent.isIsInput2()) {
                ent.setIsInput2(validador);
            }
            return validador;
        } else if ((posicionX >= minxsalida1) && (posicionX <= maxxsalida1) && (posicionY >= minysalida1) && (posicionY <= maxysalida1)) {
            validador = validarOutput(ent.isIsInput1(), ent.isIsInput2(), ent.isIsOutput1(), cantidad, ent.getNombre());
            if (!ent.isIsOutput1()) {
                ent.setIsOutput1(validador);
            }
            return validador;
        } else {
            return validador;
        }
    }

    public boolean validarInput(boolean isInput, boolean isOutput, int cantidad, String nombreObjeto) {
        if (isInput) {
            FachadaCompuertas.text = "Entrada ocupada";
            return false;
        } else {
            if (cantidad == 0) {
                FachadaCompuertas.text = "Entrada OK";
                FachadaCompuertas.estadoLineaUno = false;
                FachadaCompuertas.nombreObjeto = nombreObjeto;
                FachadaCompuertas.nombreObjeto1 = nombreObjeto;
                return true;
            } else {
                if (FachadaCompuertas.nombreObjeto.equals(nombreObjeto)) {
                    FachadaCompuertas.text = "No se puede conectar el mismo objeto";
                    return false;
                } else if (FachadaCompuertas.estadoLineaUno) {
                    FachadaCompuertas.text = "Conexión OK";
                    FachadaCompuertas.nombreObjeto2 = nombreObjeto;
                    return true;
                } else {
                    FachadaCompuertas.text = "No se puede conectar entrada con entrada";
                    return false;
                }
            }
        }
    }

    public boolean validarOutput(boolean isInput1, boolean isInput2, boolean isOutput, int cantidad, String nombreObjeto) {
        if (isOutput) {
            FachadaCompuertas.text = "Salida ocupada";
            return false;
        } else {
            if (cantidad == 0) {
                FachadaCompuertas.text = "Salida OK";
                FachadaCompuertas.nombreObjeto = nombreObjeto;
                FachadaCompuertas.nombreObjeto1 = nombreObjeto;
                FachadaCompuertas.estadoLineaUno = true;
                return true;
            } else {
                if (FachadaCompuertas.estadoLineaUno) {
                    FachadaCompuertas.text = "No se puede conectar salida con salida";
                    return false;
                } else {
                    if (FachadaCompuertas.nombreObjeto.equals(nombreObjeto)) {
                        FachadaCompuertas.text = "No se puede conectar el mismo objeto";
                        return false;
                    } else {
                        FachadaCompuertas.text = "Conexión OK";
                        FachadaCompuertas.nombreObjeto2 = nombreObjeto;
                        return true;
                    }
                }
            }
        }
    }
}
