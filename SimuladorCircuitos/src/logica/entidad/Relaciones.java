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
public class Relaciones {

    private String tipoRelacion;
    private Integer valorRelacion;
    private String nombreRelacion;

    public Relaciones(String tipoRelacion, Integer valorRelacion, String nombreRelacion) {
        this.tipoRelacion = tipoRelacion;
        this.valorRelacion = valorRelacion;
        this.nombreRelacion = nombreRelacion;
    }

    public Relaciones() {
    }

    public String getTipoRelacion() {
        return tipoRelacion;
    }

    public void setTipoRelacion(String tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
    }

    public Integer getValorRelacion() {
        return valorRelacion;
    }

    public void setValorRelacion(Integer valorRelacion) {
        this.valorRelacion = valorRelacion;
    }

    public String getNombreRelacion() {
        return nombreRelacion;
    }

    public void setNombreRelacion(String nombreRelacion) {
        this.nombreRelacion = nombreRelacion;
    }

}
