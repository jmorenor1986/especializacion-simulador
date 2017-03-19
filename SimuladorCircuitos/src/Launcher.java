
import presentacion.Modelo;

public class Launcher {
     private final Modelo aplicacion;

    public Launcher() {
        aplicacion = new Modelo();
        aplicacion.iniciar();
    }
   
    
    public static void main(String[] args) {
        new Launcher();
    }
    
}
