import java.util.Scanner;
public class Main {
    static Scanner sc = new Scanner(System.in);
    static char[][] gato;
    static String jugador1,jugador2;
    public static void main(String[] args) {
        System.out.println("JUEGO DEL GATO🐱‍🏍🐱‍🐉");
        System.out.print("tamño del tablero: ");
        int n = sc.nextInt();
        gato = new char[n][n];
        System.out.print("nombre del jugador 1: ");
        jugador1 = sc.next();
        System.out.print("nombre del jugador 2: ");
        jugador2 = sc.next();
        tablero();
        mostrarTablero();
        iniciarGato();
    }
    public static void tablero(){
        for(int i = 0; i < gato.length; i++) {
            for (int j = 0; j < gato[i].length; j++) {
                gato[i][j] = '*';
            }
        }
    }
    public static void mostrarTablero(){
        for(int i = 0; i < gato.length; i++){
            for(int j = 0; j < gato[i].length; j++){
                System.out.print(gato[i][j]+" ");
            }
            System.out.println(" ");
        }
    }
    public static boolean tiroValido(int fil, int col){
        return fil < gato.length && col < gato.length && gato[fil][col] == '*';
    }
    public static void iniciarGato(){
        boolean turno = false;
        int count = 0;
        do {
            if(!turno) System.out.println("turno de " + jugador1);
            else System.out.println("turno de " + jugador2);
            System.out.print("digite la fila: ");
            int fil = sc.nextInt();
            System.out.print("digite la columna: ");
            int col = sc.nextInt();
            if(!tiroValido(fil-1,col-1)){
                System.out.println("tira de nuevo");
                continue;
            }
            if(!turno) gato[fil-1][col-1] = 'x';
            else gato[fil-1][col-1] = 'o';
            turno = !turno;
            count++;
            if(count == gato.length*gato.length){
                System.out.println("Empate");
                return;
            }
            mostrarTablero();
        }while(!hayGanador());
        if(!turno) System.out.println("el ganador es " + jugador2);
        else System.out.println("el ganador es " + jugador1);
    }
    public static boolean hayGanador(){
        //verificar si hay una fila ganadora
        int n = gato.length;
        for(int i = 0; i < n; i++){
            boolean filaGanodora = true;
            for(int j = 1; j < n; j++){
                if(gato[i][j] != gato[i][0] || gato[i][j] == '*'){
                    filaGanodora = false;
                    break;
                }
            }
            if(filaGanodora) return filaGanodora;
        }
        //verificar si hay columana ganadora
        for(int j = 0; j < n; j++){
            boolean colGanadora = true;
            for(int i = 1; i < n; i++){
                if(gato[i][j] != gato[0][j] || gato[i][j] == '*'){
                    colGanadora = false;
                    break;
                }
            }
            if(colGanadora) return colGanadora;
        }
        //verificar si hay diagonal ganadora
        boolean diagonalPrincipalGanadora = true;
        boolean diagonalSecuandariaGanadora = true;
        for(int i = 1; i < n; i++){
            if (gato[i][i] != gato[0][0] || gato[i][i] == '*') diagonalPrincipalGanadora = false;
            if(gato[i][n-i-1] != gato[0][n-1] || gato[i][n-i-1] == '*') diagonalSecuandariaGanadora = false;
        }
        if(diagonalPrincipalGanadora || diagonalSecuandariaGanadora) return true;
        return false;
    }
}

