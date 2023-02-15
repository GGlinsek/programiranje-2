public class Kviz3 {
    static int[] sestejPolinoma(int[] a, int[] b){
        int[] novPolinom;
        if (b.length>a.length){
            novPolinom = new int[b.length];
        }
        else{
            novPolinom = new int[a.length];
        }
        for (int i = 0; i < a.length; i++) {
            if (a.length <= i){
                novPolinom[i] = b[i];
            }
            else if (b.length<= i){
                novPolinom[i] = a[i];
            }
            else{
                novPolinom[i] = a[i] + b[i];
            }

        }
        return novPolinom;
    }

    static int[] zmnoziPolinoma(int[] a, int[] b){
        int[] novPolinom;
        if (b.length>a.length){
            novPolinom = new int[b.length];
        }
        else{
            novPolinom = new int[a.length];
        }
        for (int i = 0; i < a.length; i++) {
            if (a.length <= i){
                novPolinom[i] = b[i];
            }
            else if (b.length<= i){
                novPolinom[i] = a[i];
            }
            else{
                novPolinom[i] = a[i] * b[i];
            }

        }
        return novPolinom;
    }


    static int izracunajRPN(String racun){
        String[] razdeljeni = racun.split(" ");
        String[] stevilke = new String[razdeljeni.length/2 + 1] ;
        String[] znaki = new String[razdeljeni.length/2];
        for (int i = 0; i < razdeljeni.length; i++) {
            if (i < Math.ceil(razdeljeni.length/2.0)){
                stevilke[i] = razdeljeni[i];
            }
            else{
                znaki[i-(razdeljeni.length/2 + 1)] = razdeljeni[i];
            }
        }



        return 5;
    }


    public static void main(String[] args) {
        izracunajRPN("1 2 3 4 + * +");
    }
}
