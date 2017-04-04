/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import logica.entidad.Entidad;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author john
 */
public class TestLogica {
    
    private Logica logica;
    private ArrayList<Entidad> lista;
    
    @Before
    public void instanciaLogica(){
        logica = new Logica();
        lista = new ArrayList<>();
    }
    @Test
    public void verificaAlmacenamientoLinea(){
        logica.guardaObjetos();
        String resultado = logica.getMensajeLog().getMensaje();
        if(resultado.startsWith("OK")){
            System.out.println("bien");
        }
        else{
            fail("Error::"+resultado);
        }
    }
    
}
