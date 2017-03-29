package logica;

import java.util.ArrayList;
import java.util.List;
import logica.entidad.Compuerta;
import logica.entidad.Entidad;
import logica.entidad.Linea;
import logica.entidad.MensajeLog;
import logica.entidad.Salida;
import logica.entidad.Switch;

public class Logica {

    private MensajeLog mensajeValidacion;
    private List<Entidad> listaEntidades;
    private List<Switch> listaSwitch;
    private List<Salida> listaSalida;
    private List<Compuerta> listaCompuertas;
    private List<Linea> listaLineas;

    public Logica() {
        listaSwitch = new ArrayList<>();
        listaSalida = new ArrayList<>();
        listaCompuertas = new ArrayList<>();
        listaLineas = new ArrayList<>();
        if (mensajeValidacion == null) {
            mensajeValidacion = new MensajeLog();
        }
    }

    public void guardaComponentesSeparados() {
        //valida si se a agregado por lo menos un componente
        if (listaEntidades != null && !listaEntidades.isEmpty()) {
            for (Entidad item : listaEntidades) {
                if (item instanceof Switch) {
                    listaSwitch.add((Switch) item);
                }
                if (item instanceof Salida) {
                    listaSalida.add((Salida) item);
                }
                if (item instanceof Compuerta) {
                    listaCompuertas.add((Compuerta) item);
                }
                if (item instanceof Linea) {
                    listaLineas.add((Linea) item);
                }
            }
        }
    }

    public Entidad validaConexionCompuertas() {
        for (Compuerta item : listaCompuertas) {
            if ("not".equalsIgnoreCase(item.getTipo())) {
                if (item.getEntrada1() == null || item.getSalida() == null) {
                    return item;
                }
            } else if ("on".equalsIgnoreCase(item.getTipo()) || "off".equalsIgnoreCase(item.getTipo())) {
                if (item.getSalida() == null) {
                    return item;
                }
            } else if ("lampOFF".equalsIgnoreCase(item.getTipo())) {
                if (item.getEntrada1() == null) {
                    return item;
                }
            } else if (item instanceof Compuerta) {
                if (item.getEntrada1() == null || item.getEntrada2() == null || item.getSalida() == null) {
                    return item;
                }
            }
        }
        return null;
    }

    public void verificaEntradas() {
        for (Switch sw : listaSwitch) {
            verificaLineaSwitch(sw.getNombre(), sw.getSalida());
        }
    }

    public void verificaLineaSwitch(String nombreSwitch, int valor) {
        String nombreObjeto = "";
        Linea linea = null;
        for (Linea ln : listaLineas) {
            if (ln.getNombreObjeto1().equalsIgnoreCase(nombreSwitch)) {
                nombreObjeto = ln.getNombreObjeto2();
                linea = ln;
            } else if (ln.getNombreObjeto2().equalsIgnoreCase(nombreSwitch)) {
                nombreObjeto = ln.getNombreObjeto1();
                linea = ln;

            }
        }
        if (!"".equalsIgnoreCase(nombreObjeto)) {
            for (Entidad entidad : listaEntidades) {
                if (entidad instanceof Linea == false && entidad instanceof Switch == false) {
                    if (entidad.getNombre().equalsIgnoreCase(nombreObjeto)) {
                        if (entidad.getEntrada1() == null) {
                            entidad.setEntrada1(valor);
                            System.out.println("switch" + nombreSwitch + " salida " + valor);
                            System.out.println("objeto " + entidad.getTipo() + "::" + entidad.getNombre() + " entrada1 " + valor);
                        } else {
                            entidad.setEntrada2(valor);
                            System.out.println("switch" + nombreSwitch + " salida " + valor);
                            System.out.println("objeto " + entidad.getTipo() + "::" + entidad.getNombre() + " entrada2 " + valor);
                        }
                    }
                }
            }
        }
        if (linea != null) {
            eliminaLinea(linea);
        }
    }

    public void eliminaLinea(Linea ln) {
        listaLineas.remove(ln);
    }

    public MensajeLog getMensajeValidacion() {
        return mensajeValidacion;
    }

    public List<Entidad> getListaEntidades() {
        return listaEntidades;
    }

    public void setListaEntidades(List<Entidad> listaEntidades) {
        this.listaEntidades = listaEntidades;
    }

    public List<Switch> getListaSwitch() {
        return listaSwitch;
    }

    public List<Salida> getListaSalida() {
        return listaSalida;
    }

    public List<Compuerta> getListaCompuertas() {
        return listaCompuertas;
    }

    public List<Linea> getListaLineas() {
        return listaLineas;
    }

}
