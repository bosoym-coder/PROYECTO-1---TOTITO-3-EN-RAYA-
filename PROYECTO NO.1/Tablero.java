// Clase que maneja el tablero de 10x10 y la logica del juego
// Autor: [Bryan Alejandro Osoy Monterroso] - Carnet: [7690 25 15671]

public class Tablero {
    
    // la matriz del juego es de 10x10
    private char[][] matriz;
    private int filas = 10;
    private int columnas = 10;
    
    public Tablero() {
        matriz = new char[filas][columnas];
        limpiarTablero();
    }
    
    // limpia el tablero poniendo espacios vacios
    public void limpiarTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = '.';
            }
        }
    }
    
    // muestra el tablero en consola
    public void mostrarTablero(String nombreJ1, String carnetJ1) {
        System.out.println("\n   1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < filas; i++) {
            // para alinear bien los numeros de fila
            if (i < 9) {
                System.out.print((i + 1) + "  ");
            } else {
                System.out.print((i + 1) + " ");
            }
            for (int j = 0; j < columnas; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        // mostrar nombre y carnet debajo del tablero
        System.out.println("\n" + nombreJ1 + " - " + carnetJ1);
    }
    
    // coloca el simbolo en la posicion indicada
    public boolean colocarSimbolo(int fila, int columna, char simbolo) {
        // verificamos que la casilla este libre
        if (fila >= 0 && fila < filas && columna >= 0 && columna < columnas) {
            if (matriz[fila][columna] == '.') {
                matriz[fila][columna] = simbolo;
                return true;
            }
        }
        return false;
    }
    
    // revisa si alguien gano
    public boolean hayGanador(char simbolo) {
        // revisar filas
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j <= columnas - 3; j++) {
                if (matriz[i][j] == simbolo && matriz[i][j+1] == simbolo && matriz[i][j+2] == simbolo) {
                    return true;
                }
            }
        }
        
        // revisar columnas
        for (int i = 0; i <= filas - 3; i++) {
            for (int j = 0; j < columnas; j++) {
                if (matriz[i][j] == simbolo && matriz[i+1][j] == simbolo && matriz[i+2][j] == simbolo) {
                    return true;
                }
            }
        }
        
        // revisar diagonal hacia abajo-derecha
        for (int i = 0; i <= filas - 3; i++) {
            for (int j = 0; j <= columnas - 3; j++) {
                if (matriz[i][j] == simbolo && matriz[i+1][j+1] == simbolo && matriz[i+2][j+2] == simbolo) {
                    return true;
                }
            }
        }
        
        // revisar diagonal hacia abajo-izquierda
        for (int i = 0; i <= filas - 3; i++) {
            for (int j = 2; j < columnas; j++) {
                if (matriz[i][j] == simbolo && matriz[i+1][j-1] == simbolo && matriz[i+2][j-2] == simbolo) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    // revisa si el tablero esta lleno (empate)
    public boolean tableroLleno() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (matriz[i][j] == '.') {
                    return false;
                }
            }
        }
        return true;
    }
    
    // devuelve la matriz para poder guardarla
    public char[][] getMatriz() {
        return matriz;
    }
    
    // carga una matriz guardada
    public void setMatriz(char[][] nuevaMatriz) {
        this.matriz = nuevaMatriz;
    }
}
