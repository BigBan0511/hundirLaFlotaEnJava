package hundirlaflota;

import java.util.Scanner;

public class Barco {
    private boolean hundido;
    private String longitud;
    private int toques;
    private int[] posicion;
    private String orientacion;
    private String nombre;
    private int posicionEnArray;

    public Barco(String longitud, String nombre) {
        this.hundido = false;
        //la longitud de los barcos
        this.longitud = longitud;
        this.toques = 0;
        this.orientacion = "";
        //Con el nombre y cogiendo el primer caracter le pongo simbolo al barco
        this.nombre = nombre;
    }
    
    //Comprueba si el barco está hundido
    public boolean estaHundido(){
        if (this.longitud.length()==this.toques){
            this.hundido = true;
        }
        return this.hundido;
    }
    
    //Le suma los disparos recibidos
    public void recibirToques(){
        this.toques++;
    }
    
    //Con este método pedimos la orientación del barco al jugador
    public void orientacionDelBarcoJugadores(){
        Scanner datos = new Scanner(System.in);
        boolean validoOrientacion = false;
        do {                    
            try {
                System.out.println("Desea colocarlo Horizontal o Verticalmente?");
                System.out.println("Escriba H o V");
                this.orientacion = datos.next();
                if(this.orientacion.equalsIgnoreCase("H") || this.orientacion.equalsIgnoreCase("V") && this.orientacion.length() == 1){
                    validoOrientacion = true;
                }else{
                    System.out.println("Elija orientarlo vertical u horizontal");
                }
            } catch (Exception e) {
                System.out.println("Esa orientacion no existe");
            }
        } while (validoOrientacion == false);
    }
    
    //Con este método pedimos la orientación del barco a la IA
    public void orientacionBarcoMaquina(){
        int numAleatorio = (int)(Math.random()*2)+1;
        switch (numAleatorio) {
            case 1:
                this.orientacion = "H";
                break;
            case 2:
                this.orientacion = "V";
                break;
        }
    }

    public boolean isHundido() {
        return hundido;
    }

    public String getLongitud() {
        return longitud;
    }

    public int getToques() {
        return toques;
    }

    public int[] getPosicion() {
        return posicion;
    }

    public String getOrientacion() {
        return orientacion;
    }

    public void setPosicion(int[] posicion) {
        this.posicion = posicion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPosicionEnArray() {
        return posicionEnArray;
    }

    public void setPosicionEnArray(int posicionEnArray) {
        this.posicionEnArray = posicionEnArray;
    }
    
}
