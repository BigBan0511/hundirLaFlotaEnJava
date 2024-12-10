package hundirlaflota;

import java.util.Scanner;

public class Jugadores extends Participante{
    private Scanner datos = new Scanner(System.in);
    private char posBarco;
    private int[] posicion;
    private int[] posDisparo;
    private char posicionDisparo;

    public Jugadores() {
        super("Jugador 1");
        this.posicion = new int [2];
        this.posDisparo = new int [2];
        this.posBarco = ' ';
        this.posicionDisparo = ' ';
    }
    
    
    //Con este metodo devolvemos la posicion para colocar los barcos del Jugador
    public int[] colocarBarcos(){
        pedirPosicion(this.posicion, this.posBarco);
        return this.posicion;
    }
    
    //Con este método se traduce la letra de la posición vertical que ha pedido
    //al jugador
    public int traducirPosicionTablero(char posTabY) {
        char letra = posTabY;
        int posicionY = 0;           
        switch (letra) {
            case 'A':
                posicionY = 0;
                break;
            case 'B':
                posicionY = 1;
                break;
            case 'C':
                posicionY = 2;
                break;
            case 'D':
                posicionY = 3;
                break;
            case 'E':
                posicionY = 4;
                break;
            case 'F':
                posicionY = 5;
                break;
            case 'G':
                posicionY = 6;
                break;
            case 'H':
                posicionY = 7;
                break;
            case 'I':
                posicionY = 8;
                break;
            case 'J':
                posicionY = 9;
                break;
        }
        
        return posicionY;
    }
    
    //Con este método se comprueba y pide una posición para los anteriores métodos
    public int[] pedirPosicion(int[] posicion, char posY){
        boolean valido = false;
        String teclado = "";
        do {            
            try{
                System.out.println("Introduce la posicionY");
                this.datos = new Scanner (System.in);
                teclado = this.datos.next().toUpperCase();
                posY = teclado.charAt(0);
                System.out.println("Introduce la posicionX");
                this.datos = new Scanner (System.in);
                posicion[1] = this.datos.nextInt();
                posicion[1] = posicion[1] - 1;
                posicion[0] = traducirPosicionTablero(posY);
                if(posicion[1]<10 && posicion[1]>=0){
                    valido = true;
                }
                if(teclado.length() < 1 || teclado.length() > 1){
                    valido = false;
                }
                if(posY <'A' || posY >'J'){
                    System.out.println("Posicion Y no valida");
                    valido = false;
                }
            }catch(Exception e){
                System.out.println("Posicion no valida");
            }
        }while (valido == false);
        return posicion;
    }

    //Con este metodo devolvemos la posicion para disparar a los barcos de la IA
    public int[] disparoJugador(){
        pedirPosicion(this.posDisparo, this.posicionDisparo);
        return this.posDisparo;
    }
    
    public int[] getPosicion() {
        return posicion;
    }
    
    @Override
    public void setHaGanado(boolean haGanado) {
        super.setHaGanado(haGanado);
    }
    
    @Override
    public boolean isHaGanado() {
        return super.isHaGanado();
    }

    @Override
    public void actualizarPuntuacion(int puntos) {
        super.actualizarPuntuacion(puntos);
    }
}
