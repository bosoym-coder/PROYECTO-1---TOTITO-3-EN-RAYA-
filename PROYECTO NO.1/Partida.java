// Clase para guardar y restaurar partidas en archivo de texto
// Autor: [Bryan Alejandro Osoy Monterroso] - Carnet: [7690 25 15671]

import java.io.*;

public class Partida {
    
    private String nombreArchivo = "partida_guardada.txt";
    
    // guarda la partida en un archivo de texto
    public void guardarPartida(Jugador j1, Jugador j2, char[][] matriz, int turnoActual) {
        try {
            FileWriter fw = new FileWriter(nombreArchivo);
            BufferedWriter bw = new BufferedWriter(fw);


            
            
            // guardamos los datos de los jugadores
            bw.write(j1.getNombre() + "\n");
            bw.write(j1.getSimbolo() + "\n");
            bw.write(j1.getPuntaje() + "\n");
            bw.write(j2.getNombre() + "\n");
            bw.write(j2.getSimbolo() + "\n");
            bw.write(j2.getPuntaje() + "\n");
            bw.write(turnoActual + "\n");
            
            // guardamos el tablero fila por fila
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    bw.write(matriz[i][j] + "");
                }
                bw.write("\n");
            }
            
            bw.close();
            fw.close();
            System.out.println("Partida guardada correctamente!");
            
        } catch (IOException e) {
            System.out.println("Error al guardar la partida: " + e.getMessage());
        }
    }
    
    // carga la partida del archivo
    // devuelve null si no hay partida guardada
    public String[] cargarPartida() {
        File archivo = new File(nombreArchivo);
        
        if (!archivo.exists()) {
            System.out.println("No hay ninguna partida guardada.");
            return null;
        }
        
        try {
            FileReader fr = new FileReader(nombreArchivo);
            BufferedReader br = new BufferedReader(fr);
            
            // 7 datos de jugadores + turno, mas 10 filas del tablero = 17 lineas
            String[] datos = new String[17];
            for (int i = 0; i < 17; i++) {
                datos[i] = br.readLine();
            }
            
            br.close();
            fr.close();
            return datos;
            
        } catch (IOException e) {
            System.out.println("Error al cargar la partida: " + e.getMessage());
            return null;
        }
    }
    
    // borra el archivo de la partida guardada
    public void borrarPartida() {
        File archivo = new File(nombreArchivo);
        if (archivo.exists()) {
            archivo.delete();
        }
    }
    
    // revisa si hay una partida guardada
    public boolean existePartida() {
        File archivo = new File(nombreArchivo);
        return archivo.exists();
    }
}
