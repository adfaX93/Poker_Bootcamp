import java.util.Arrays;
import java.util.Random;

public class Poker {
    public static void ordenarMano(Carta[] arreglo, int [] arrayAux) {
        for (int i = 0; i< arreglo.length; i++){
            switch (arreglo[i].valor) {
                case "2":
                    arrayAux[i] = 2;
                    break;
                case "3":
                    arrayAux[i] = 3;
                    break;
                case "4":
                    arrayAux[i] = 4;
                    break;
                case "5":
                    arrayAux[i] = 5;
                    break;
                case "6":
                    arrayAux[i] = 6;
                    break;
                case "7":
                    arrayAux[i] = 7;
                    break;
                case "8":
                    arrayAux[i] = 8;
                    break;
                case "9":
                    arrayAux[i] = 9;
                    break;
                case "A":
                    arrayAux[i] = 1;
                    break;
                case "T":
                    arrayAux[i] = 10;
                    break;
                case "J":
                    arrayAux[i] = 11;
                    break;
                case "Q":
                    arrayAux[i] = 12;
                    break;
                case "K":
                    arrayAux[i] = 13;
                    break;
            }
        }
        int aux;
        Carta cartaAux;
        for (int i = 0; i < arrayAux.length - 1; i++) {
            for (int j = 0; j < arrayAux.length - i - 1; j++) {
                if (arrayAux[j + 1] < arrayAux[j]) {
                    aux = arrayAux[j + 1];
                    arrayAux[j + 1] = arrayAux[j];
                    arrayAux[j] = aux;
                    cartaAux = arreglo[j+1];
                    arreglo[j+1] =arreglo[j];
                    arreglo[j] = cartaAux;
                }
            }
        }
    }
    public static void imprimirMano(Carta[] arreglo){
        for (Carta carta : arreglo) {
            System.out.print("[" + carta.valorPalo() + "] ");
        }
        System.out.println();
    }
    //Método para ver si la mano es una escalera
    public static boolean escalera(int [] arrayAux){
        int bandera = 0;
        if(arrayAux[0]==1 && ((arrayAux[1]==2)||(arrayAux[1]==10))){//inicio la A como 1, luego verifico si tomo la A como 14
            bandera=1;
            for (int i=1; i<arrayAux.length-1; i++){
                if(((arrayAux[i])+1)==(arrayAux[i+1])){
                    bandera++;
                }
            }
        }else {
            for (int i=0; i<arrayAux.length-1; i++){
                if(((arrayAux[i])+1)==(arrayAux[i+1])){
                    bandera++;
                }
            }
        }
        return bandera == 4;
    }
    //Método para ver si es color
    public static boolean color(Carta[] mano){
        int bandera = 0;
        String C = mano[0].valorPalo().substring(1);
        for (Carta carta : mano) {
            if (carta.valorPalo().substring(1).equals(C)) {
                bandera++;
            }
        }
        return bandera == 5;
    }
    //Método inicializar
    public static void inicializarArray(int [] array){
        Arrays.fill(array, 0);
    }
    //Método para ver si es poker
    public static boolean poker(int [] array){
        int [] arrayAux = new int[array.length];
        inicializarArray(arrayAux);
        for(int i=0; i<array.length; i++){
            for (int k : array) {
                if (array[i] == k) {
                    arrayAux[i]++;
                }
            }
        }
        for (int i=0; i<array.length; i++){
            if(arrayAux[i]==4){
                return true;
            }
        }
        return false;
    }
    //Método Full
    public static boolean full(int [] array){
        int [] arrayAux = new int[array.length];
        inicializarArray(arrayAux);
        boolean bandera1=false;
        boolean bandera2=false;
        for(int i=0; i<array.length; i++){
            for (int k : array) {
                if (array[i] == k) {
                    arrayAux[i]++;
                }
            }
        }
        for (int i=0; i<array.length; i++){
            if(arrayAux[i]==3){
                bandera1=true;
            }
            if(arrayAux[i]==2){
                bandera2=true;
            }
        }
        return bandera1 && bandera2;
    }
    //Cartas Orden
    public static String cartasOrden(int [] array, Carta [] mano){
        int [] arrayAux = new int[array.length];
        inicializarArray(arrayAux);
        for(int i=0; i<array.length; i++){
            for (int k : array) {
                if (array[i] == k) {
                    arrayAux[i]++;
                }
            }
        }
        for (int i=0; i<array.length; i++){
            if(arrayAux[i]==3){
                return "Trio";
            }
        }
        int doble=0;
        for(int i=0; i<array.length; i++){
            if(arrayAux[i]==2){
                doble++;
            }
        }
        if(doble==4){
            return "Par doble";
        }
        for(int i=0; i<array.length; i++){
            if(arrayAux[i]==2){
                return "Par";
            }
        }
        return String.valueOf(cartaAlta(mano));
    }
    //Método carta alta
    public static int cartaAlta(Carta [] mano) {
        if (mano[0].valor.equals("A")) {
            return 14;
        } else if (mano[4].valor.equals("T")){
            return 10;
        }else if (mano[4].valor.equals("J")){
            return 11;
        }else if (mano[4].valor.equals("Q")){
            return 12;
        }else if (mano[4].valor.equals("K")){
            return 13;
        }else{
            return  Integer.parseInt(mano[4].valor);
        }
    }
    String jugadas(Carta[] mano){
        int [] arrayAux = new int[5];
        ordenarMano(mano, arrayAux);
        boolean escalera=escalera(arrayAux);
        boolean color=color(mano);
        boolean poker=poker(arrayAux);
        if (escalera && color){
            return "Escalera Color";
        }else if(escalera){
            return "Escalera";
        }else if(color){
            return "Color";
        }else if (poker){
            return "Poker";
        }else if (full(arrayAux)){
            return "Full";
        }else{
            return (cartasOrden(arrayAux,mano));
        }
    }
    public static String cartaAleatorio(){
        int min = 1;
        int max = 12;
        String carta="";
        Random random = new Random();
        int valor = random.nextInt(max + min) + min;
        if(valor==1){
            carta = carta.concat("A");
        } else if (valor==10) {
            carta = carta.concat("T");
        }else if (valor==11) {
            carta = carta.concat("J");
        }else if (valor==12) {
            carta = carta.concat("Q");
        }else if (valor==13) {
            carta = carta.concat("K");
        }else {
            carta=carta.concat(String.valueOf(valor));
        }
        max = 3;
        valor = random.nextInt(max + min) + min;
        boolean bandera;
        do {
            if(valor==1){
                carta = carta.concat("S");
                bandera = false;
            } else if (valor==2) {
                carta = carta.concat("C");
                bandera = false;
            }else if (valor==3) {
                carta = carta.concat("H");
                bandera = false;
            }else {
                carta = carta.concat("D");
                bandera = false;
            }
        }while (bandera);
        return carta;
    }
    public static void generarMano(Carta[] mano){
        for (int i=0; i<mano.length;i++){
            mano[i] =new Carta(cartaAleatorio());
        }
    }
    public static void main(String[] args) {
        Carta [] mano1 = new  Carta[5];
        Carta [] mano2 = new  Carta[5];
        Poker mano = new Poker();
        generarMano(mano1);
        generarMano(mano2);
        if (mano.jugadas(mano1).equals("Escalera Color") || mano.jugadas(mano2).equals("Escalera Color")){
            if (mano.jugadas(mano1).equals("Escalera Color") && mano.jugadas(mano2).equals("Escalera Color")){
                if (cartaAlta(mano1)>cartaAlta(mano2)){
                    System.out.println("Gana Mano 1, Escalera Color, carta alta: "+cartaAlta(mano1));
                }else {
                    System.out.println("Gana Mano 2, Escalera Color, carta alta: "+cartaAlta(mano2));
                }
            }else {
                if (mano.jugadas(mano1).equals("Escalera Color")){
                    System.out.println("Gana Mano 1, Escalera Color");
                }else {
                    System.out.println("Gana Mano 2, Escalera Color");
                }
            }
        }else if (mano.jugadas(mano1).equals("Color")||mano.jugadas(mano2).equals("Color")){
            if (mano.jugadas(mano1).equals("Color") && mano.jugadas(mano2).equals("Color")){
                if (cartaAlta(mano1)>cartaAlta(mano2)){
                    System.out.println("Gana Mano 1, Color, carta alta: "+cartaAlta(mano1));
                }else {
                    System.out.println("Gana Mano 2, Color, carta alta: "+cartaAlta(mano2));
                }
            }else {
                if (mano.jugadas(mano1).equals("Color")){
                    System.out.println("Gana Mano 1, Color");
                }else {
                    System.out.println("Gana Mano 2, Color");
                }
            }
        }else if (mano.jugadas(mano1).equals("Escalera")||mano.jugadas(mano2).equals("Escalera")){
            if (mano.jugadas(mano1).equals("Escalera") && mano.jugadas(mano2).equals("Escalera")){
                if (cartaAlta(mano1)>cartaAlta(mano2)){
                    System.out.println("Gana Mano 1, Escalera, carta alta: "+cartaAlta(mano1));
                }else {
                    System.out.println("Gana Mano 2, Escalera, carta alta: "+cartaAlta(mano2));
                }
            }else {
                if (mano.jugadas(mano1).equals("Escalera")){
                    System.out.println("Gana Mano 1, Escalera");
                }else {
                    System.out.println("Gana Mano 2, Escalera");
                }
            }
        }else if (mano.jugadas(mano1).equals("Poker")||mano.jugadas(mano2).equals("Poker")){
            if (mano.jugadas(mano1).equals("Poker") && mano.jugadas(mano2).equals("Poker")){
                if (cartaAlta(mano1)>cartaAlta(mano2)){
                    System.out.println("Gana Mano 1, Poker, carta alta: "+cartaAlta(mano1));
                }else {
                    System.out.println("Gana Mano 2, Poker, carta alta: "+cartaAlta(mano2));
                }
            }else {
                if (mano.jugadas(mano1).equals("Poker")){
                    System.out.println("Gana Mano 1, Poker");
                }else {
                    System.out.println("Gana Mano 2, Poker");
                }
            }
        }else if (mano.jugadas(mano1).equals("Full")||mano.jugadas(mano2).equals("Full")){
            if (mano.jugadas(mano1).equals("Full") && mano.jugadas(mano2).equals("Full")){
                if (cartaAlta(mano1)>cartaAlta(mano2)){
                    System.out.println("Gana Mano 1, Full, carta alta: "+cartaAlta(mano1));
                }else {
                    System.out.println("Gana Mano 2, Full, carta alta: "+cartaAlta(mano2));
                }
            }else {
                if (mano.jugadas(mano1).equals("Full")){
                    System.out.println("Gana Mano 1, Full");
                }else {
                    System.out.println("Gana Mano 2, Full");
                }
            }
        }else if (mano.jugadas(mano1).equals("Trio")||mano.jugadas(mano2).equals("Trio")){
            if (mano.jugadas(mano1).equals("Trio") && mano.jugadas(mano2).equals("Trio")){
                if (cartaAlta(mano1)>cartaAlta(mano2)){
                    System.out.println("Gana Mano 1, Trio, carta alta: "+cartaAlta(mano1));
                }else {
                    System.out.println("Gana Mano 2, Trio, carta alta: "+cartaAlta(mano2));
                }
            }else {
                if (mano.jugadas(mano1).equals("Trio")){
                    System.out.println("Gana Mano 1, Trio");
                }else {
                    System.out.println("Gana Mano 2, Trio");
                }
            }
        }else if (mano.jugadas(mano1).equals("Par doble")||mano.jugadas(mano2).equals("Par doble")){
            if (mano.jugadas(mano1).equals("Par doble") && mano.jugadas(mano2).equals("Par doble")){
                if (cartaAlta(mano1)>cartaAlta(mano2)){
                    System.out.println("Gana Mano 1, Par doble, carta alta: "+cartaAlta(mano1));
                }else {
                    System.out.println("Gana Mano 2, Par doble, carta alta: "+cartaAlta(mano2));
                }
            }else {
                if (mano.jugadas(mano1).equals("Par doble")){
                    System.out.println("Gana Mano 1, Par doble");
                }else {
                    System.out.println("Gana Mano 2, Par doble");
                }
            }
        }else if (mano.jugadas(mano1).equals("Par")||mano.jugadas(mano2).equals("Par")){
            if (mano.jugadas(mano1).equals("Par") && mano.jugadas(mano2).equals("Par")){
                if (cartaAlta(mano1)>cartaAlta(mano2)){
                    System.out.println("Gana Mano 1, Par, carta alta: "+cartaAlta(mano1));
                }else {
                    System.out.println("Gana Mano 2, Par, carta alta: "+cartaAlta(mano2));
                }
            }else {
                if (mano.jugadas(mano1).equals("Par")){
                    System.out.println("Gana Mano 1, Par");
                }else {
                    System.out.println("Gana Mano 2, Par");
                }
            }
        }else{
            if (cartaAlta(mano1)==cartaAlta(mano2)){
                System.out.println("Empate!");
            }else if (cartaAlta(mano1)>cartaAlta(mano2)){
                System.out.println("Gana Mano 1, Carta alta: "+cartaAlta(mano1));
            }else {
                System.out.println("Gana Mano 2, Carta alta: "+cartaAlta(mano2));
            }
        }
        System.out.print("Mano 1: ");
        imprimirMano(mano1);
        System.out.print("Mano 2: ");
        imprimirMano(mano2);
    }
}
