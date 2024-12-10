package hundirlaflota;

import java.util.Scanner;

public class Juego {
    private Scanner datos = new Scanner(System.in);
    private boolean valido = false;
    private String jugarOtraVez;
    private Jugadores jugador1;
    private Maquina maquina1;

    public Juego() {
        this.jugarOtraVez = "";
        this.jugador1 = new Jugadores();
        this.maquina1 = new Maquina();
    }
    
    //Con este metodo se inicia el juego
    public void jugar(){
        this.jugador1.setHaGanado(false);
        this.maquina1.setHaGanado(false);
        Tablero t1 = new Tablero();
        Tablero t2 = new Tablero();
        t1.crearTableroJugador();
        System.out.println("");
        t2.crearTableroDisparoDelJugador();
        System.out.println("");
        t1.colocarBarcosJugador();
        t2.colocarBarcosMaquina();
        System.out.println("Disparos, empieza el jugador");
        do {
            this.jugador1.setTurno("S");
            while(this.jugador1.getTurno().equalsIgnoreCase("S")){
                t2.recibirDisparosMaquina();
            }
            this.maquina1.setTurno("S");
            while(this.maquina1.getTurno().equalsIgnoreCase("S")){
                t1.recibirDisparosJugador();
            }
            if(t1.isJ1HaGanado() == true){
                this.jugador1.setHaGanado(true);
                this.jugador1.actualizarPuntuacion(1);
                System.out.println("Felicidades, has ganado");
            }else if (t2.isM1HaGanado()== true){
                this.maquina1.setHaGanado(true);
                this.maquina1.actualizarPuntuacion(1);
                System.out.println("Has perdido");
            }
        } while (this.jugador1.isHaGanado() == false && this.maquina1.isHaGanado() == false);
    }
    
    //Con este metodo jugamos otra vez
    public boolean jugarOtraVez(){
        do {
            try {
                System.out.println("Desea jugar de nuevo?");
                this.jugarOtraVez = this.datos.nextLine();            
                while (jugarOtraVez.equalsIgnoreCase("s")) {            
                    jugar();
                    jugarOtraVez();
                }
                if(this.jugarOtraVez.equalsIgnoreCase("n")){
                    valido = true;
                }else{
                    System.out.println("Introduce S o N");
                }
            } catch (Exception e) {
                System.out.println("Introduce S o N");
            }
        } while (valido==false);
        return this.valido;
    }
}
