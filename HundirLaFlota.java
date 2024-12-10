package hundirlaflota;

public class HundirLaFlota {

    public static void main(String[] args) {
        System.out.println("Bienvenido a Hundir la Flota");
        Juego nuevoJuego = new Juego();
        nuevoJuego.jugar();
        nuevoJuego.jugarOtraVez();
    }
    
}
