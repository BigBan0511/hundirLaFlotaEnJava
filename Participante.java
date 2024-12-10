package hundirlaflota;

public class Participante {
    private String nombre;
    private int puntuacion;
    private boolean haGanado;
    private String turno;

    public Participante(String nombre) {
        this.nombre = nombre;
        this.puntuacion = 0;
        this.haGanado = false;
        this.turno = "";
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public void setHaGanado(boolean haGanado) {
        this.haGanado = haGanado;
    }

    public boolean isHaGanado() {
        return haGanado;
    }

    public void actualizarPuntuacion(int puntos) {
        this.puntuacion = this.puntuacion + puntos;
    }
}
