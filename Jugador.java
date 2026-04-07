// Clase que representa a un jugador del juego
// Autor: [Bryan Alejandro Osoy Monterroso] - Carnet: [7690 25 15671]

public class Jugador {
    
    private String nombre;
    private char simbolo;
    private int puntaje;
    
    // Constructor del jugador
    public Jugador(String nombre, char simbolo) {
        this.nombre = nombre;
        this.simbolo = simbolo;
        this.puntaje = 0;
    }
    
    // metodos get y set
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public char getSimbolo() {
        return simbolo;
    }
    
    public void setSimbolo(char simbolo) {
        this.simbolo = simbolo;
    }
    
    public int getPuntaje() {
        return puntaje;
    }
    
    public void sumarPunto() {
        this.puntaje++;
    }
    
    // para mostrar info del jugador
    public String toString() {
        return nombre + " (" + simbolo + ") - Puntos: " + puntaje;
    }
}
