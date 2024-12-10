package hundirlaflota;

public class Maquina extends Participante{
    private int[] posicion;
    private int[] posDisparoMaquina;
    
    public Maquina() {
        super("IA");
        this.posicion = new int [2];
        this.posDisparoMaquina = new int[2];
    }
    
    //Con este método coloca los barcos la IA, es decir, pide una posicion
    public int[] colocarBarcos(){
        pedirPosicion(this.posicion);
        return posicion;
    }
    
    //Con este método la maquina dispara, es decir, pide una posicion
    public int[] dispararMaquina(){
        boolean dioBarco = false;
        disparoInteligente(dioBarco, this.posicion);
        return posDisparoMaquina;
    }
    
    public int[] disparoInteligente(boolean dioBarco, int[] posicion) {
        Tablero t1 = new Tablero();
        boolean hayBarco = false;
        int x = 0; 
        int y = 0;
        for (int i = 0; i < t1.getMiTablero().length; i++) {
            for (int j = 0; j < t1.getMiTablero().length; j++) {
                if (dioBarco == true) {
                    int[][] adyacentes = obtenerCasillasDeAlLado(i, j);
                    for (int[] casilla : adyacentes) {
                        int fila = casilla[0];
                        int columna = casilla[1];
                    }
                }
            }
        }
        if (dioBarco == false) {
            posicion[0] = (int)(Math.random()*t1.getMiTablero().length);
            posicion[1] = (int)(Math.random()*t1.getMiTablero().length);
            return posicion;
        }
        this.posDisparoMaquina[0] = x;
        this.posDisparoMaquina[1] = y;
        return this.posDisparoMaquina;
    }

    // Método para obtener las casillas adyacentes a una posición dada
    private int[][] obtenerCasillasDeAlLado(int fila, int columna) {
        int[][] casillasDeAlLado = new int[3][3];
        casillasDeAlLado[0][0] = fila - 1;
        casillasDeAlLado[0][1] = columna;
        casillasDeAlLado[0][2] = fila + 1;
        casillasDeAlLado[1][0] = columna;
        casillasDeAlLado[1][1] = fila;
        casillasDeAlLado[1][2] = columna - 1;
        casillasDeAlLado[2][0] = fila;
        casillasDeAlLado[2][1] = columna + 1;
        return casillasDeAlLado;
    }
    
    //Con este método pedimos una posicion aleatoria válida para los 2 anteriores
    //métodos
    public int[] pedirPosicion(int[] posicion){
        Tablero t1 = new Tablero();
        posicion[0] = (int)(Math.random()*t1.getMiTablero().length);
        posicion[1] = (int)(Math.random()*t1.getMiTablero().length);
        return posicion;
    }
    
    @Override
    public void setHaGanado(boolean haGanado) {
        super.setHaGanado(haGanado);
    }

    @Override
    public void actualizarPuntuacion(int puntos) {
        super.actualizarPuntuacion(puntos);
    }

    @Override
    public boolean isHaGanado() {
        return super.isHaGanado();
    }
    
    
}
