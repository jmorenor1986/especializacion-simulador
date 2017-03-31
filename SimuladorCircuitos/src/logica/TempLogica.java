/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import logica.entidad.Compuerta;
import logica.entidad.Entidad;
import logica.entidad.Linea;
import logica.entidad.Salida;
import logica.entidad.Switch;

/**
 *
 * @author john
 */
public class TempLogica {

    private ArrayList<Entidad> objetosEntidad;
    private ArrayList<Linea> objetoLinea;
    private ArrayList<Linea> objetoSwitch;
    private String mensaje;

    public TempLogica() {
        if (objetoLinea == null) {
            objetoLinea = new ArrayList<>();
        }
        getObjetosEntidad();
        llenaLista();
        mensaje = "OK";
    }

    public void llenaLista() {
        Entidad e = new Compuerta("compuerta", "and");
        e.setNombre("objeto0");
        objetosEntidad.add(e);
        //-------------------------
        e = new Switch("switch", "on");
        e.setSalida(1);
        e.setNombre("objeto1");
        objetosEntidad.add(e);
        //-------------------------
        Linea ln = new Linea("linea", "linea");
        ln.setNombre("objeto2");
        ln.setNombreObjeto1("objeto1");
        ln.setNombreObjeto2("objeto0");
        objetosEntidad.add(ln);
        //-------------------------
        e = new Switch("switch", "off");
        e.setSalida(0);
        e.setNombre("objeto3");
        objetosEntidad.add(e);
        //--------------------------------
        ln = new Linea("linea", "linea");
        ln.setNombre("objeto4");
        ln.setNombreObjeto1("objeto0");
        ln.setNombreObjeto2("objeto3");
        objetosEntidad.add(ln);
        //-------------------------
        e = new Switch("switch", "off");
        e.setSalida(0);
        e.setNombre("objeto5");
        objetosEntidad.add(e);
        //-------------------------
        e = new Compuerta("compuerta", "and");
        e.setNombre("objeto6");
        objetosEntidad.add(e);
        //--------------------------------
        ln = new Linea("linea", "linea");
        ln.setNombre("objeto7");
        ln.setNombreObjeto1("objeto6");
        ln.setNombreObjeto2("objeto5");
        objetosEntidad.add(ln);
        //--------------------------------
        ln = new Linea("linea", "linea");
        ln.setNombre("objeto8");
        ln.setNombreObjeto1("objeto0");
        ln.setNombreObjeto2("objeto6");
        objetosEntidad.add(ln);
        //--------------------------------
        Salida sal = new Salida("", "LampOF");
        sal.setNombre("objeto9");
        objetosEntidad.add(sal);
        //--------------------------------
        ln = new Linea("linea", "linea");
        ln.setNombre("objeto10");
        ln.setNombreObjeto1("objeto6");
        ln.setNombreObjeto2("objeto9");
        objetosEntidad.add(ln);
    }

    public void guardaLineas() {
        for (Entidad item : objetosEntidad) {
            if (item instanceof Linea) {
                objetoLinea.add((Linea) item);
                System.out.println(item.getNombre());
                verificaSwitchLineas((Linea) item);
            }
        }
    }

    public void verificaSwitchLineas(Linea l) {
        for (Entidad item : objetosEntidad) {
            if (item instanceof Switch) {
                if (item.getNombre().equalsIgnoreCase(l.getNombreObjeto1())) {
                    System.out.println("Switch nombre::" + item.getNombre() + "--con linea--" + l.getNombre());
                    break;

                } else if (item.getNombre().equalsIgnoreCase(l.getNombreObjeto2())) {
                    System.out.println("Switch nombre::" + item.getNombre() + "--con linea--" + l.getNombre());
                    break;
                }else{
                    mensaje = "Switch sin lineas";
                }
                           
            }
        }
    }

    public ArrayList<Entidad> getObjetosEntidad() {
        if (objetosEntidad == null) {
            objetosEntidad = new ArrayList<>();
        }
        return objetosEntidad;
    }

    public void setObjetosEntidad(ArrayList<Entidad> objetosEntidad) {
        this.objetosEntidad = objetosEntidad;
    }

    public ArrayList<Linea> getObjetoLinea() {
        return objetoLinea;
    }

    public String getMensaje() {
        return mensaje;
    }
    
    

}
