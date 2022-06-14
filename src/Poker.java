import java.util.Random;

public class Poker {
    public static void ordenarArray(Carta[] arreglo, int [] arrayAux) {
        for (int i = 0; i< arreglo.length; i++){
            if (arreglo[i].valor.substring(0).equals("2")){
                arrayAux[i]=2;
            }else if (arreglo[i].valor.substring(0).equals("3")){
                arrayAux[i]=3;
            }else if (arreglo[i].valor.substring(0).equals("4")) {
                arrayAux[i]=4;
            }else if (arreglo[i].valor.substring(0).equals("5")){
                arrayAux[i]=5;
            }else if (arreglo[i].valor.substring(0).equals("6")){
                arrayAux[i]=6;
            }else if (arreglo[i].valor.substring(0).equals("7")){
                arrayAux[i]=7;
            }else if (arreglo[i].valor.substring(0).equals("8")){
                arrayAux[i]=8;
            }else if (arreglo[i].valor.substring(0).equals("9")){
                arrayAux[i]=9;
            }else if (arreglo[i].valor.substring(0).equals("A")){
                arrayAux[i]=1;
            }else if (arreglo[i].valor.substring(0).equals("T")){
                arrayAux[i]=10;
            }else if (arreglo[i].valor.substring(0).equals("J")){
                arrayAux[i]=11;
            }else if (arreglo[i].valor.substring(0).equals("Q")){
                arrayAux[i]=12;
            }else if (arreglo[i].valor.substring(0).equals("K")){
                arrayAux[i]=13;
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
    public static void imprimirArray(Carta[] arreglo){
        for (int i = 0; i<arreglo.length; i++){
            System.out.println(arreglo[i].valorPalo());
        }
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
        if(bandera==4){
            return true;
        }else {
            return false;
        }
    }
    //Método para ver si es color
    public static boolean color(Carta[] mano){
        int bandera = 0;
        String C = mano[0].valorPalo().substring(1);
        for(int i=0; i<mano.length; i++){
            if(mano[i].valorPalo().substring(1).equals(C)){
                bandera++;
            }
        }
        if(bandera==5){
            return true;
        }else {
            return false;
        }
    }
    //Método inicializar
    public static void inicializarArray(int [] array){
        for(int i=0; i<array.length; i++){
            array[i]=0;
        }
    }
    //Método para ver si es poker
    public static boolean poker(int [] array){
        int [] arrayAux = new int[array.length];
        inicializarArray(arrayAux);
        for(int i=0; i<array.length; i++){
            for(int j=0; j<array.length; j++){
                if (array[i]==array[j]){
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
            for(int j=0; j<array.length; j++){
                if (array[i]==array[j]){
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
        if(bandera1==true && bandera2==true){
            return true;
        }else{
            return false;
        }
    }
    //Cartas Orden
    public static String cartasOrden(int [] array){
        int [] arrayAux = new int[array.length];
        inicializarArray(arrayAux);
        boolean bandera1=false;
        boolean bandera2=false;
        for(int i=0; i<array.length; i++){
            for(int j=0; j<array.length; j++){
                if (array[i]==array[j]){
                    arrayAux[i]++;
                }
            }
        }
        for(int i=0; i<array.length; i++){
            if(arrayAux[i]==3){
                return "Trio";
            }
            if(arrayAux[i]==2){
                for (int j = 0; j<array.length; j++){
                   if (array[j]==2){
                       return "Par Doble";
                   }
                }
                return "Par";
            }
        }
        if (array[0]==1){
            return "Carta Alta: A";
        }else if(array[4]==10){
            return "Carta Alta: T";
        }else if(array[4]==11){
            return "Carta Alta: J";
        } else if (array[4]==12) {
            return "Carta Alta: Q";
        }else if(array[4]==13){
            return "Carta Alta: K";
        }else {
            return "Carta Alta:"+array[4];
        }
    }
    String jugadas(Carta[] mano){
        int [] arrayAux = new int[5];
        ordenarArray(mano, arrayAux);
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
            return (cartasOrden(arrayAux));
        }
    }
    public static String cartaAleatorio(){
        int min = 1;
        int max = 13;
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
        boolean bandera = true;
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
            }else if (valor==4) {
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
        System.out.println(mano.jugadas(mano1));
        System.out.println(mano.jugadas(mano2));

        imprimirArray(mano1);
        imprimirArray(mano2);
    }
}
