// Clase principal
// Proyecto 01 - Totito (3 en raya) en matriz 10x10
// Autor: [BRYAN ALEJANDRO OSOY MONTESORRO] - Carnet: [7690 25 15671]

import java.util.Scanner;

public class Main {
    
    static Scanner sc = new Scanner(System.in);
    static Tablero tablero = new Tablero();
    static Partida partida = new Partida();
    static Jugador jugador1 = null;
    static Jugador jugador2 = null;
    // 1 para jugador1, 2 para jugador2
    static int turnoActual = 1;
   
    static String nombreEstudiante = "[Bryan Alejandro Osoy Montesorro]";
    static String carnetEstudiante = "[7690 25 15671]";
    
    public static void main(String[] args) {
        System.out.println("=== BIENVENIDO AL TOTITO (3 EN RAYA) ===");
        System.out.println("Tablero de 10x10");
        
        menuPrincipal();
    }
    
    // menu principal del programa
    public static void menuPrincipal() {
        int opcion = 0;
        
        while (opcion != 4) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Nuevo juego");
            System.out.println("2. Restaurar partida guardada");
            System.out.println("3. Ver puntajes");
            System.out.println("4. Salir");
            System.out.print("Elige una opcion: ");
            
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingresa un numero valido.");
                continue;
            }
            
            switch (opcion) {
                case 1:
                    configurarNuevoJuego();
                    break;
                case 2:
                    restaurarPartida();
                    break;
                case 3:
                    mostrarPuntajes();
                    break;
                case 4:
                    System.out.println("Gracias por jugar. Hasta luego!");
                    break;
                default:
                    System.out.println("Opcion no valida, intenta de nuevo.");
            }
        }
    }
    
    // configura los datos para empezar un juego nuevo
    public static void configurarNuevoJuego() {
        System.out.println("\n--- NUEVO JUEGO ---");
        
        // pedir nombre del jugador 1
        System.out.print("Nombre del Jugador 1: ");
        String nombre1 = sc.nextLine();
        
        // pedir simbolo del jugador 1
        char simbolo1 = pedirSimbolo(nombre1, ' ');
        
        // pedir nombre del jugador 2
        System.out.print("Nombre del Jugador 2: ");
        String nombre2 = sc.nextLine();
        
        // el jugador 2 no puede usar el mismo simbolo
        char simbolo2 = pedirSimbolo(nombre2, simbolo1);
        
        // si ya habia jugadores, preguntamos si reiniciar puntajes
        if (jugador1 != null && jugador2 != null) {
            System.out.print("Deseas reiniciar los puntajes? (s/n): ");
            String resp = sc.nextLine();
            if (resp.equalsIgnoreCase("s")) {
                jugador1 = new Jugador(nombre1, simbolo1);
                jugador2 = new Jugador(nombre2, simbolo2);
            } else {
                // actualizamos nombre y simbolo pero mantenemos puntaje
                jugador1.setNombre(nombre1);
                jugador1.setSimbolo(simbolo1);
                jugador2.setNombre(nombre2);
                jugador2.setSimbolo(simbolo2);
            }
        } else {
            jugador1 = new Jugador(nombre1, simbolo1);
            jugador2 = new Jugador(nombre2, simbolo2);
        }
        
        tablero.limpiarTablero();
        turnoActual = 1;
        iniciarJuego();
    }
    
    // pide el simbolo al jugador, evita que use el mismo que el otro
    public static char pedirSimbolo(String nombre, char simboloOtro) {
        char simbolo = ' ';
        boolean valido = false;
        
        while (!valido) {
            System.out.print("Simbolo para " + nombre + " (X, O u otro caracter): ");
            String entrada = sc.nextLine();
            
            if (entrada.length() == 0) {
                System.out.println("Debes ingresar al menos un caracter.");
                continue;
            }
            
            simbolo = entrada.charAt(0);
            
            // verificamos que no sea punto porque lo usamos para casillas vacias
            if (simbolo == '.') {
                System.out.println("El punto (.) esta reservado para casillas vacias. Elige otro.");
                continue;
            }
            
            // verificamos que no sea igual al otro jugador
            if (simbolo == simboloOtro) {
                System.out.println("Ese simbolo ya lo usa el otro jugador. Elige uno diferente.");
                continue;
            }
            
            valido = true;
        }
        
        return simbolo;
    }
    
    // aqui se juega la partida
    public static void iniciarJuego() {
        boolean juegoTerminado = false;
        
        while (!juegoTerminado) {
            Jugador jugadorActual;
            if (turnoActual == 1) {
                jugadorActual = jugador1;
            } else {
                jugadorActual = jugador2;
            }
            
            tablero.mostrarTablero(nombreEstudiante, carnetEstudiante);
            System.out.println("\nTurno de: " + jugadorActual.getNombre() + " [" + jugadorActual.getSimbolo() + "]");
            
            // menu durante el juego
            System.out.println("1. Hacer jugada");
            System.out.println("2. Guardar partida");
            System.out.println("3. Salir al menu principal");
            System.out.print("Opcion: ");
            
            int opcion;
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opcion no valida.");
                continue;
            }
            
            if (opcion == 2) {
                partida.guardarPartida(jugador1, jugador2, tablero.getMatriz(), turnoActual);
                continue;
            } else if (opcion == 3) {
                System.out.print("Deseas guardar antes de salir? (s/n): ");
                String resp = sc.nextLine();
                if (resp.equalsIgnoreCase("s")) {
                    partida.guardarPartida(jugador1, jugador2, tablero.getMatriz(), turnoActual);
                }
                return;
            } else if (opcion != 1) {
                System.out.println("Opcion no valida.");
                continue;
            }
            
            // pedir la jugada
            int fila = -1;
            int col = -1;
            
            try {
                System.out.print("Fila (1-10): ");
                fila = Integer.parseInt(sc.nextLine()) - 1;
                System.out.print("Columna (1-10): ");
                col = Integer.parseInt(sc.nextLine()) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Ingresa numeros validos.");
                continue;
            }
            
            boolean jugadaOk = tablero.colocarSimbolo(fila, col, jugadorActual.getSimbolo());
            
            if (!jugadaOk) {
                System.out.println("Casilla ocupada o fuera de rango, intenta de nuevo.");
                continue;
            }
            
            // revisar si gano
            if (tablero.hayGanador(jugadorActual.getSimbolo())) {
                tablero.mostrarTablero(nombreEstudiante, carnetEstudiante);
                System.out.println("\n*** " + jugadorActual.getNombre() + " ha ganado! Felicidades! ***");
                jugadorActual.sumarPunto();
                partida.borrarPartida();
                juegoTerminado = true;
                preguntarOtraPartida();
            } else if (tablero.tableroLleno()) {
                tablero.mostrarTablero(nombreEstudiante, carnetEstudiante);
                System.out.println("\nEmpate! El tablero esta lleno.");
                partida.borrarPartida();
                juegoTerminado = true;
                preguntarOtraPartida();
            } else {
                // cambiar turno
                if (turnoActual == 1) {
                    turnoActual = 2;
                } else {
                    turnoActual = 1;
                }
            }
        }
    }
    
    // pregunta si quieren jugar otra ronda
    public static void preguntarOtraPartida() {
        System.out.print("\nDesean jugar otra partida con los mismos jugadores? (s/n): ");
        String resp = sc.nextLine();
        if (resp.equalsIgnoreCase("s")) {
            tablero.limpiarTablero();
            turnoActual = 1;
            iniciarJuego();
        }
    }
    
    // restaura una partida guardada
    public static void restaurarPartida() {
        if (!partida.existePartida()) {
            System.out.println("No hay ninguna partida guardada.");
            return;
        }
        
        String[] datos = partida.cargarPartida();
        if (datos == null) return;
        
        // reconstruimos los jugadores con los datos guardados
        jugador1 = new Jugador(datos[0], datos[1].charAt(0));
        // no tenemos setter de puntaje, lo sumamos manualmente
        int pts1 = Integer.parseInt(datos[2]);
        for (int i = 0; i < pts1; i++) jugador1.sumarPunto();
        
        jugador2 = new Jugador(datos[3], datos[4].charAt(0));
        int pts2 = Integer.parseInt(datos[5]);
        for (int i = 0; i < pts2; i++) jugador2.sumarPunto();
        
        turnoActual = Integer.parseInt(datos[6]);
        
        // reconstruimos la matriz
        char[][] matrizCargada = new char[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                matrizCargada[i][j] = datos[7 + i].charAt(j);
            }
        }
        tablero.setMatriz(matrizCargada);
        
        System.out.println("Partida restaurada! Continua jugando...");
        iniciarJuego();
    }
    
    // muestra los puntajes actuales
    public static void mostrarPuntajes() {
        if (jugador1 == null) {
            System.out.println("Todavia no se ha jugado ninguna partida.");
            return;
        }
        System.out.println("\n--- PUNTAJES ---");
        System.out.println(jugador1.toString());
        System.out.println(jugador2.toString());
    }
}
