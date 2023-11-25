//fila 3, silla 2
package lab7p1_aaroncastillo;

import java.util.Random;
import java.util.Scanner;

public class Lab7P1_AaronCastillo {

    static Scanner leer = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        System.out.println("*****Menu*****");
        System.out.println("1. Tres en raya");
        System.out.println("2. Puntos de silla ");
        System.out.println("Ingrese cualquier otro numero para salir");
        System.out.print("Ingrse su opcion: ");
        int opcion = leer.nextInt();
        while (opcion > 0 && opcion < 3) {
            switch (opcion) {
                case 1:
                    int tam = 3;
                    int tam2 = 3;
                    char resp = 's';
                    while (resp == 's' || resp == 'S') {
                        System.out.println("");
                        System.out.println("---Bienvenido al tres en raya---");
                        char tablero[][] = new char[tam][tam2];
                        tablero = generarTablero(tam, tam2);
                        System.out.println("Tablero actual: ");
                        imprimir(tablero);
                        int cont = 0;
                        while (cont <= 9) {
                            System.out.println("Es el turno de : X");
                            System.out.print("Ingrese fila (0,1,2): ");
                            int fila = leer.nextInt();
                            System.out.print("Ingrese la columna (0,1,2): ");
                            int columna = leer.nextInt();
                            boolean repetir = true;
                            boolean ver = verificar(tablero, fila, columna);
                            while (ver == false) {
                                System.out.println("");
                                System.out.println("La posicion ya fue tomada o numero no valido.");
                                System.out.print("Ingrese fila (0,1,2): ");
                                fila = leer.nextInt();
                                System.out.print("Ingrese la columna (0,1,2): ");
                                columna = leer.nextInt();
                                ver = verificar(tablero, fila, columna);
                            }
                            ver = verificar(tablero, fila, columna);
                            while (repetir == true) {
                                if (ver == true) {
                                    System.out.println("");
                                    System.out.println("El usuario ha elegido la posicion: (" + fila + ", " + columna + ")");
                                    tablero = (X(tablero, fila, columna));
                                    System.out.println("Tablero actual: ");
                                    imprimir(tablero);
                                    repetir = false;
                                    boolean vic = verificarVictoria(tablero);
                                    if (vic == true) {
                                        System.out.println("¡X ha ganado!");
                                        cont = 9;
                                    }
                                } else {
                                    System.out.println("");
                                    System.out.println("La posicion ya fue tomada o numero no valido.");
                                    System.out.println("");
                                    repetir = true;
                                    System.out.print("Ingrese fila (0,1,2): ");
                                    fila = leer.nextInt();
                                    System.out.print("Ingrese la columna (0,1,2): ");
                                    columna = leer.nextInt();
                                    ver = verificar(tablero, fila, columna);
                                }
                            }
                            cont++;
                            if (cont < 9) {
                                System.out.println("");
                                System.out.println("Es el turno de: 0");
                                int fila2 = random.nextInt((2 - 0) + 1) + 0;
                                int columna2 = random.nextInt((2 - 0) + 1) + 0;
                                boolean repetir2 = true;
                                boolean ver2 = verificar2(tablero, fila2, columna2);
                                while (repetir2 == true) {
                                    if (ver2 == true) {
                                        System.out.println("La maquina ha elegido la posicion: (" + fila2 + ", " + columna2 + ")");
                                        tablero = (O(tablero, fila2, columna2));
                                        System.out.println("Tablero actual: ");
                                        imprimir(tablero);
                                        repetir2 = false;
                                        boolean vic = verificarVictoria(tablero);
                                        if (vic == true) {
                                            System.out.println("¡0 ha ganado!");
                                            cont = 9;
                                        }
                                    } else {
                                        repetir2 = true;
                                        fila2 = random.nextInt((2 - 0) + 1) + 0;
                                        columna2 = random.nextInt((2 - 0) + 1) + 0;
                                        ver2 = verificar2(tablero, fila2, columna2);
                                    }
                                }
                            }
                            cont++;
                        }
                        if (cont == 10) {
                            System.out.println("¡Es un empate!");
                        }
                        System.out.println("");
                        System.out.println("Desea continuar jugando? [s/n]");
                        resp = leer.next().charAt(0);
                    }

                    break;
                case 2:
                    System.out.print("Ingrese el tamaño para la fila: ");
                    int fila = leer.nextInt();
                    System.out.print("Ingrese el tamaño para la columna: ");
                    int columna = leer.nextInt();
                    int[][] matrizrandom = new int[fila][columna];
                    matrizrandom = llenar_random(fila, columna);
                    System.out.println("Matiz generada: ");
                    imprimir2(matrizrandom);
                    break;
            }
            System.out.println("");
            System.out.println("*****Menu*****");
            System.out.println("1. Tres en raya");
            System.out.println("2. Puntos de silla ");
            System.out.println("Ingrese cualquier otro numero para salir");
            System.out.print("Ingrse su opcion: ");
            opcion = leer.nextInt();
        }
    }

    public static char[][] generarTablero(int tam, int tam2) {
        char[][] temporal = new char[tam][tam2];
        char espacio = ' ';
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam2; j++) {
                temporal[i][j] = espacio;
            }
        }
        return temporal;
    }

    public static void imprimir(char[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            System.out.print("[");
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j]);
                if (j < 2) {
                    System.out.print(",");
                }
            }
            System.out.println("]");
        }
    }

    public static boolean verificar(char[][] tablero, int fila, int columna) {
        boolean vef = false;
        if (fila < 0 || fila >= 3 || columna < 0 || columna >= 3) {
            vef = false;
        } else {
            for (int i = 0; i < tablero.length; i++) {
                for (int j = 0; j < tablero[i].length; j++) {
                    if (i == fila && j == columna) {
                        if (tablero[i][j] == 'X' || tablero[i][j] == '0') {
                            vef = false;
                        } else {
                            vef = true;
                        }
                    }
                }
            }
        }
        return vef;
    }

    public static boolean verificar2(char[][] tablero, int fila2, int columna2) {
        boolean vef2 = false;
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (i == fila2 && j == columna2) {
                    if (tablero[i][j] == 'X' || tablero[i][j] == '0') {
                        vef2 = false;
                    } else {
                        vef2 = true;
                    }
                }
            }
        }
        return vef2;
    }

    public static char[][] X(char[][] tablero, int fila, int columna) {
        char pos = 'X';
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (i == fila && j == columna) {
                    tablero[i][j] = pos;
                }
            }
        }
        return tablero;
    }

    public static char[][] O(char[][] tablero, int fila2, int columna2) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (i == fila2 && j == columna2) {
                    tablero[i][j] = '0';
                }
            }
        }
        return tablero;
    }

    public static boolean verificarVictoria(char[][] tablero) {
        boolean win = false;
        for (int i = 0; i < tablero.length; i++) {
            if ((tablero[i][0] == 'X' && tablero[i][1] == 'X' && tablero[i][2] == 'X') || (tablero[0][i] == 'X' && tablero[1][i] == 'X' && tablero[2][i] == 'X')) {
                win = true;
            }
        }
        if ((tablero[0][0] == 'X' && tablero[1][1] == 'X' && tablero[2][2] == 'X') || (tablero[0][2] == 'X' && tablero[1][1] == 'X' && tablero[2][0] == 'X')) {
            win = true;
        }
        for (int i = 0; i < tablero.length; i++) {
            if ((tablero[i][0] == '0' && tablero[i][1] == '0' && tablero[i][2] == '0') || (tablero[0][i] == '0' && tablero[1][i] == '0' && tablero[2][i] == '0')) {
                win = true;
            }
        }
        if ((tablero[0][0] == '0' && tablero[1][1] == '0' && tablero[2][2] == '0') || (tablero[0][2] == '0' && tablero[1][1] == '0' && tablero[2][0] == '0')) {
            win = true;
        }

        return win;
    }

    public static int[][] llenar_random(int fila, int columna) {
        int[][] temporal = new int[fila][columna];
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                temporal[i][j] = random.nextInt((99 - 0) + 1) + 0;
            }
        }
        return temporal;
    }

    public static void imprimir2(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println("");
        }
    }

}
