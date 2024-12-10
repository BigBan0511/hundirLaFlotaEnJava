package hundirlaflota;

public class Tablero extends Barco{
    private char[][] miTablero;
    private char iconoAgua;
    private char iconoTocado;
    private char iconoFallo;
    private Barco[] arrayBarcosMaquina;
    private Barco[] arrayBarcosJugadores;
    private Maquina m1;
    private Jugadores j1;
    private boolean j1HaGanado;
    private boolean m1HaGanado;

    public Tablero() {
        super("", "");
        this.miTablero = new char[10][10];
        this.iconoAgua = '~';
        this.iconoTocado = 'o';
        this.iconoFallo = 'x';
        this.m1HaGanado = false;
        this.j1HaGanado = false;
        this.m1 = new Maquina();
        this.j1 = new Jugadores();
        this.arrayBarcosMaquina = new Barco[10];
        this.arrayBarcosJugadores = new Barco[10];
        crearBarcos();
    }
    
    //Metodos del jugador
    
    //Con este método dibujamos el tablero de los barcos del jugador y los disparos recibidos
    public void crearTableroJugador(){
        System.out.println("");
        System.out.println("  A  B  C  D  E  F  G  H  I  J");
        for (int x = 0; x < this.miTablero.length; x++) {
            System.out.print((x + 1) + " ");
            for (int y = 0; y < this.miTablero[x].length; y++) {
                if(this.miTablero[x][y] == 0){
                    this.miTablero[x][y] = this.iconoAgua;
                }
                System.out.print(this.miTablero[x][y] + "  ");
            }System.out.println("");
        }
    }
    
    //Con este método dibujamos el tablero de disparo del jugador y donde ha dado
    public void crearTableroDisparoDelJugador(){
        System.out.println("  A  B  C  D  E  F  G  H  I  J");
        for (int x = 0; x < this.miTablero.length; x++) {
            System.out.print((x+1) + " ");
            for (int y = 0; y < this.miTablero[x].length; y++) {
                if(this.miTablero[x][y] == 0){
                    this.miTablero[x][y] = this.iconoAgua;
                }
                System.out.print(this.miTablero[x][y] + "  ");
            }System.out.println("");
        }
    }
    
    //Con este método se colocan los barcos en el tablero con la posición pedida en
    //la clase Jugadores
    public void colocarBarcosJugador(){
        int num = 0;
        while(num < this.arrayBarcosJugadores.length){
            System.out.println("Vamos a colocar el siguiente barco: " + this.arrayBarcosJugadores[num].getNombre());
            System.out.println("");
            this.arrayBarcosJugadores[num].orientacionDelBarcoJugadores();
            int[] posicion = this.j1.colocarBarcos();
            this.arrayBarcosJugadores[num].setPosicion(posicion);
            while(sePuedeColocarBarco(posicion,this.arrayBarcosJugadores, num)==false){
                System.out.println("Posicion incorrecta");
                posicion = this.j1.colocarBarcos();
            }
            colocarConOrientacionDelBarco(posicion[1], posicion[0], this.arrayBarcosJugadores[num]);
            crearTableroJugador();
            num++;
        }
    }
    
    //Con este método el tablero de barcos del jugador recibe los disparos de la máquina
    public void recibirDisparosJugador() {
        boolean haDado = false;
        int[] posicionDelDisparo = new int[2];
        posicionDelDisparo = this.m1.disparoInteligente(haDado, posicionDelDisparo);
        while (sePuedeDisparar(posicionDelDisparo) == false) {
            System.out.println("Disparo invalido.");
            posicionDelDisparo = this.m1.disparoInteligente(haDado, posicionDelDisparo);
        }
        int num = 0;
        String resultadoDisparo = "";
        while (num < this.arrayBarcosJugadores.length) {
            resultadoDisparo = enQueHaDado(posicionDelDisparo[1],posicionDelDisparo[0], num, this.arrayBarcosJugadores,
                    this.arrayBarcosJugadores[num].getPosicionEnArray());
            System.out.print(resultadoDisparo);
            if (resultadoDisparo.equalsIgnoreCase("Tocado") || resultadoDisparo.equalsIgnoreCase("Tocado y hundido")) {
                if(this.arrayBarcosJugadores[num].estaHundido() == true){
                    this.m1.setTurno("S");
                }else{
                    haDado = true;
                    this.m1.setTurno("S");
                    posicionDelDisparo = this.m1.disparoInteligente(haDado, posicionDelDisparo);
                }
            }else if(resultadoDisparo.equalsIgnoreCase("Agua")){
                this.m1.setTurno("N");
                this.j1.setTurno("S");
                num = this.arrayBarcosJugadores.length - 1;
            }
            num++;
        }
        if(resultadoDisparo.equalsIgnoreCase("Agua")){
            this.miTablero[posicionDelDisparo[1]][posicionDelDisparo[0]] = this.iconoFallo;
        }
        System.out.println("");
        crearTableroJugador();
        boolean haGanado = false;
        if(comprobarHundidosJug() == true){
            this.m1HaGanado = true;
        }
    }
    
    //Con este método se comprueba si están todos los barcos hundidos del jugador
    public boolean comprobarHundidosJug(){
        boolean estanHundidosTodos = true;
        int num = 0;
        while(num < this.arrayBarcosJugadores.length){
            if(this.arrayBarcosJugadores[num].isHundido() == false){
                estanHundidosTodos = false;
            }
            num++;
        }
        return estanHundidosTodos;
    }
    
    //Metodos de la Maquina
    
    //Con este método se colocan los barcos en el tablero con la posición pedida en
    //la clase Máquina
    public void colocarBarcosMaquina(){
        int num = 0;
        for (int i = 0; i < this.arrayBarcosMaquina.length; i++) {
            this.arrayBarcosMaquina[i].orientacionBarcoMaquina();
        }
        while(num < this.arrayBarcosMaquina.length){
            int[] posicion = this.m1.colocarBarcos();
            this.arrayBarcosMaquina[num].setPosicion(posicion);
            while(sePuedeColocarBarco(posicion, this.arrayBarcosMaquina, num)==false){
                posicion = this.m1.colocarBarcos();
            }
            colocarConOrientacionDelBarco(posicion[1], posicion[0], this.arrayBarcosMaquina[num]);
            num++;
        }
        //System.out.println("  A  B  C  D  E  F  G  H  I  J");
        for (int x = 0; x < this.miTablero.length; x++) {
            //System.out.print((x + 1) + " ");
            for (int y = 0; y < this.miTablero[x].length; y++) {
                if(this.miTablero[x][y] == 0){
                    this.miTablero[x][y] = this.iconoAgua;
                }
                //System.out.print(this.miTablero[x][y] + "  ");
            }//System.out.println("");
        }
    }
    
    //Con este método el tablero de barcos de la IA recibe los disparos del Jugador
    public void recibirDisparosMaquina() {
        int[] posicionDelDisparo;
        posicionDelDisparo = this.j1.disparoJugador();
        while (sePuedeDisparar(posicionDelDisparo) == false) {
            System.out.println("Disparo invalido.");
            posicionDelDisparo = this.j1.disparoJugador();
        }
        int num = 0;
        String resultadoDisparo = "";
        while (num < this.arrayBarcosMaquina.length) {
            resultadoDisparo = enQueHaDado(posicionDelDisparo[1],posicionDelDisparo[0], num, this.arrayBarcosMaquina,
                    this.arrayBarcosMaquina[num].getPosicionEnArray());
            System.out.print(resultadoDisparo);
            if (resultadoDisparo.equalsIgnoreCase("Tocado") || resultadoDisparo.equalsIgnoreCase("Tocado y hundido")) {
                if(this.arrayBarcosMaquina[this.arrayBarcosMaquina[num].getPosicionEnArray()].estaHundido() == true){
                    this.j1.setTurno("S");
                }else{
                    this.j1.setTurno("S");
                }
            }else if(resultadoDisparo.equalsIgnoreCase("Agua")){
                this.j1.setTurno("N");
                this.m1.setTurno("S");
                num = this.arrayBarcosMaquina.length - 1;
            }
            num++;
        }
        if(resultadoDisparo.equalsIgnoreCase("Agua")){
            this.miTablero[posicionDelDisparo[1]][posicionDelDisparo[0]] = this.iconoFallo;
        }
        System.out.println("");
        crearTableroDisparoDelJugador();
        if(comprobarHundidosMaq() == true){
            this.j1HaGanado = true;
        }
    }
    
    //Con este método se comprueba si están todos los barcos hundidos de la IA
    public boolean comprobarHundidosMaq(){
        boolean estanHundidosTodos = true;
        int num = 0;
        while(num < this.arrayBarcosMaquina.length){
            if(this.arrayBarcosMaquina[num].isHundido() == false){
                estanHundidosTodos = false;
            }
            num++;
        }
        return estanHundidosTodos;
    }
    
    
    //Metodos comunes a la maquina y al jugador
    
    //Con este método se colocan los barcos según la orientación del baroo
    public void colocarConOrientacionDelBarco(int x, int y, Barco barco) {
        int longitud = barco.getLongitud().length();
        String orientacion = barco.getOrientacion();
        if (orientacion.equalsIgnoreCase("H")) {
            for (int i = 0; i < longitud; i++) {
                this.miTablero[x][y + i] = barco.getNombre().charAt(0);
            }
        } else {
            for (int i = 0; i < longitud; i++) {
                this.miTablero[x + i][y] = barco.getNombre().charAt(0);
            }
        }
    }
    
    //Con este método se comprueba que sea una posición válida para colocar el barco
    public boolean sePuedeColocarBarco(int[] posicion, Barco[] arrayBarcos, int num){
        if (this.miTablero[posicion[1]][posicion[0]] != this.iconoAgua) {
            return false;
        }
        if(arrayBarcos[num].getOrientacion().equalsIgnoreCase("H")){
            if (posicion[0] + arrayBarcos[num].getLongitud().length() > this.miTablero.length) {
                return false;
            }
        }else if(arrayBarcos[num].getOrientacion().equalsIgnoreCase("V")){
            if (posicion[1] + arrayBarcos[num].getLongitud().length() > this.miTablero[posicion[1]].length) {
                return false;
            }
        }
        for (int x = 0; x < arrayBarcos[num].getLongitud().length(); x++) {
            if (arrayBarcos[num].getOrientacion().equalsIgnoreCase("H")) {
                if (this.miTablero[posicion[1]][posicion[0] + x] != this.iconoAgua) {
                    return false;
                }
            } else if (arrayBarcos[num].getOrientacion().equalsIgnoreCase("V")) {
                if (this.miTablero[posicion[1] + x][posicion[0]] != this.iconoAgua) {
                    return false;
                }
            }
        }
        return true;
    }
    
    //Con este método se comprueba si la posición elegida 
    //para disparar ya se ha elegido anteriormente
    public boolean sePuedeDisparar(int[] posicion){
        if(this.miTablero[posicion[1]][posicion[0]] == this.iconoFallo 
                        || this.miTablero[posicion[1]][posicion[0]] == this.iconoTocado){
            return false;
        }
        return true;
    }
    
    //Con este método se comprueba si ha dado en agua o si ha dado en un barco y
    //ha sido tocado o tocado y hundido
    public String enQueHaDado(int x, int y, int num, Barco[] arrayBarcos, int indiceDelBarco){
        String enQueDio = "";
        if (this.miTablero[x][y] == this.iconoAgua) {
            enQueDio = "Agua";
        } else if (this.miTablero[x][y] == arrayBarcos[num].getNombre().charAt(0)) {
            if(arrayBarcos[num].isHundido() != true){
                arrayBarcos[num].recibirToques();
                this.miTablero[x][y] = this.iconoTocado;
            }
            if (arrayBarcos[num].estaHundido()) {
                enQueDio = "Tocado y hundido";
            } else {
                enQueDio = "Tocado";
            }
        }
        return enQueDio;
    }
    
    //Con este método se crean los barcos y sus atributos
    private void crearBarcos(){
        for (int x = 0; x < this.arrayBarcosMaquina.length; x++) {
            if (x<4){
                this.arrayBarcosMaquina[x] = new Barco("v", "velero");
                this.arrayBarcosMaquina[x].setPosicionEnArray(x);
            }else if(x>=4 && x<7){
                this.arrayBarcosMaquina[x] = new Barco("ff", "fragata");
                this.arrayBarcosMaquina[x].setPosicionEnArray(x);
            }else if(x>=7 && x<9){
                this.arrayBarcosMaquina[x] = new Barco("bbb", "buque");
                this.arrayBarcosMaquina[x].setPosicionEnArray(x);
            }else if(x == 9){
                this.arrayBarcosMaquina[x] = new Barco("pppp", "portaviones");
                this.arrayBarcosMaquina[x].setPosicionEnArray(x);
            }
        }
        for (int x = 0; x < this.arrayBarcosJugadores.length; x++) {
            if (x<4){
                this.arrayBarcosJugadores[x] = new Barco("v", "velero");
                this.arrayBarcosJugadores[x].setPosicionEnArray(x);
            }else if(x>=4 && x<7){
                this.arrayBarcosJugadores[x] = new Barco("ff", "fragata");
                this.arrayBarcosJugadores[x].setPosicionEnArray(x);
            }else if(x>=7 && x<9){
                this.arrayBarcosJugadores[x] = new Barco("bbb", "buque");
                this.arrayBarcosJugadores[x].setPosicionEnArray(x);
            }else if(x == 9){
                this.arrayBarcosJugadores[x] = new Barco("pppp", "portaviones");
                this.arrayBarcosJugadores[x].setPosicionEnArray(x);
            }
        }
    }

    public char[][] getMiTablero() {
        return miTablero;
    }

    public Barco[] getArrayBarcosMaquina() {
        return arrayBarcosMaquina;
    }

    public boolean isJ1HaGanado() {
        return j1HaGanado;
    }

    public boolean isM1HaGanado() {
        return m1HaGanado;
    }
    
    
}