
public class Kviz1 {
    static void java(){
        System.out.println("   J    a   v     v  a      ");
        System.out.println("   J   a a   v   v  a a     ");
        System.out.println("J  J  aaaaa   V V  aaaaa    ");
        System.out.println(" JJ  a     a   V  a     a   ");
    }

    static void kalkulator(int a, int b){
        if (b==0){
            System.out.print("Napaka: deljenje z 0");
        }
        else {
            System.out.printf("%d + %d = %d\n", a, b, a + b);
            System.out.printf("%d - %d = %d\n", a, b, a - b);
            System.out.printf("%d x %d = %d\n", a, b, a * b);
            System.out.printf("%d / %d = %d\n", a, b, a / b);
            System.out.printf("%d ", a);
            System.out.print("% ");
            System.out.printf("%d = %d", b, a % b);
        }
    }

    static void nicli(int a, int b, int c){
        if ((Math.pow(b,2)-4*a*c)<0){
            System.out.print("Napaka: nicli enacbe ne obstajata.");
        }
        else {
            System.out.printf("x1=%.2f, x2=%.2f", (1.0 * (-b + Math.sqrt(Math.pow(b, 2) - 4 * a * c))) / 2, (1.0 * (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c))) / 2);
        }
    }

    static void krog(double r, int d){
        if (r>0&&d>0){
            System.out.printf("Obseg kroga s polmerom r=%.2f je %."+d+"f\n",r, 2*Math.PI*r);
            System.out.printf("Ploscina kroga s polmerom r=%.2f je %."+d+"f",r, Math.PI*Math.pow(r,2));
        }
        else if (r<0){
            System.out.print("Napaka: negativen polmer");
        }
        else{
            System.out.print("Napaka: negativen d");
        }
    }

    static String pretvoriSekunde(int sekunde){
        if(sekunde<0){
            return "Število sekund ne more biti negativno";
        }
        else {
            String cas = "";
            return String.format("%02d:%02d:%02d",sekunde / 3600,(sekunde / 60) % 60,sekunde  % 60);
        }
    }

    static void javaJavaJava(int n){
        if (n<0){
            System.out.println("Napaka: negativen n");
        }
        else {
            String prva = "     J    a   v     v  a   ";
            String druga = "     J   a a   v   v  a a  ";
            String tretja = "  J  J  aaaaa   V V  aaaaa ";
            String cetrta = "   JJ  a     a   V  a     a";
            for (int i = 0; i < n; i++) {
                System.out.print(prva);
            }
            System.out.println();
            for (int i = 0; i < n; i++) {
                System.out.print(druga);
            }
            System.out.println();
            for (int i = 0; i < n; i++) {
                System.out.print(tretja);
            }
            System.out.println();
            for (int i = 0; i < n; i++) {
                System.out.print(cetrta);
            }
            System.out.println();
        }
    }

    static boolean jeFibonaccijevo(int n){
        int prejsno = 0;
        int trenutno = 1;
        int fibonacci = 0;
        while (fibonacci<n){
            fibonacci = prejsno + trenutno;
            prejsno = trenutno;
            trenutno = fibonacci;
        }
        return fibonacci == n;
    }

    static boolean jePrastevilo(int n){
        if (n<=0){
            return false;
        }
        boolean flag = true;
        for (int i = 2; i <= n / 2; ++i) {
            if (n % i == 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }
    static void izrisiZastavo(int n){
        for (int i = 0; i < n*3; i++) {
            if (i%2==0){
                System.out.print("* ".repeat(n*2));
                System.out.println("=".repeat(11*n+1));
            }
            else{
                System.out.print(" *".repeat(n*2-1)+"  ");
                System.out.println("=".repeat(11*n+1));
            }
        }
        for (int j = 0; j < n*2; j++) {
            System.out.println("=".repeat(15*n+1));
        }
    }
    static void vDesetisko(int n){
        int k = 0;
        for (int i = 0; i < Integer.toString(n).length(); i++) {
            if (Integer.parseInt(String.valueOf(Integer.toString(n).charAt(i)))>7){
                System.out.printf("Število %d ni število v osmiškem sistemu (števka %c)",n,Integer.toString(n).charAt(i));
                k = 1;
                break;
            }

        }

        if (k==0){
            System.out.printf("%d(8) = %d(10)", n, Integer.parseInt(Integer.toString(n), 8));
        }

    }

    static String pretvoriVDesetisko(String n,int b){
        for (int i = 0; i < n.length(); i++) {
            if (b!=16 && Integer.parseInt(String.valueOf(n.charAt(i)))>b-1){
                return String.format("Napaka pri pretvorbi sistema - števka %c" ,n.charAt(i));

            }
            else if (b==16 && ((int) n.charAt(i)>70)) {
                return String.format("Napaka pri pretvorbi sistema - števka %c", n.charAt(i));
            }
        }

        return String.format("%s(%d)=%d(10)", n,b , Integer.parseInt(n,b));
    }

    static int vsotaPrvih(int n){
        int stPrastevil=0;
        int vsotaPrastevil= 0;
        int stevilo = 2;
        while (stPrastevil<n) {
            boolean prastevilo = true;
            for (int i = 2; i <= stevilo / 2; ++i) {
                if (stevilo % i == 0) {
                    prastevilo = false;
                    break;
                }
            }
            if(prastevilo){
                vsotaPrastevil += stevilo;
                stPrastevil++;
            }
            stevilo++;
        }
        return vsotaPrastevil;
    }

    static void pitagoroviTrojcki(int x){
        for (int a = 1; a <= x; a++) {
            for (int b = a; b <= x; b++) {
                if (Math.pow(a,2)+Math.pow(b,2)<=Math.pow(x,2)){
                    if (Math.sqrt(Math.pow(a,2)+Math.pow(b,2))%1==0){
                        System.out.printf("%d %d %d\n",a,b,Math.round(Math.sqrt(Math.pow(a,2)+Math.pow(b,2))));
                    }
                }
                else{
                    break;
                }
            }

        }
    }

    static void narisiDrevo(int n){
        if (n==0){
            System.out.println(" . ");
        }
        else if (n<3){
            System.out.print(" | \n".repeat(n));
        }
        else {
            if (n%2==1){
                System.out.println(" * ");
            }
            System.out.print("* *\n".repeat((n-2)/2));
            System.out.print(" | \n".repeat(2));
        }
    }

    static String izracunajRazliko(String prviCas, String drugiCas){
        int prvi = 0;
        int drugi = 0;
        int vsota = 0;
        String[] casPrvega = prviCas.split(":");
        String[] casDrugega = drugiCas.split(":");

        prvi = Integer.parseInt(casPrvega[0])*3600 + Integer.parseInt(casPrvega[1])*60 + Integer.parseInt(casPrvega[2]);
        drugi = Integer.parseInt(casDrugega[0])*3600 + Integer.parseInt(casDrugega[1])*60 + Integer.parseInt(casDrugega[2]);

        vsota = Math.abs(prvi-drugi);
        return String.format("%02d:%02d:%02d",vsota / 3600,(vsota/ 60) % 60,vsota  % 60);
    }

    static String pretvoriVMorse(String sporocilo){
        String[] code
                = { ".-", "-...", "-.-.", "-..",  ".",
                "..-.", "--.",  "....", "..",   ".---",
                "-.-",  ".-..", "--",   "-.",   "---",
                ".--.", "--.-", ".-.",  "...",  "-",
                "..-",  "...-", ".--",  "-..-", "-.--",
                "--..",".----","..---","...--","....-",".....","-....","--...","---..","----."};
        String[] morseStevilke = {};
        String vMorse = "";
        for (int i = 0; i < sporocilo.length(); i++) {

            int ascii = (int) sporocilo.charAt(i);
            if(ascii>96){
                ascii-= 97;
            }
            else{
                ascii-=65;
            }
            if(Character.isDigit(sporocilo.charAt(i))){
                ascii = 25 + Integer.parseInt(String.valueOf(sporocilo.charAt(i)));
            }
            if (i==sporocilo.length()-1){
                vMorse += code[ascii];
            }
            else if (ascii<35 && ascii>=0) {
                vMorse += code[ascii] + " ";
            }
            else if (sporocilo.charAt(i)==' '){
                vMorse += "  ";
            }
            else{
                vMorse += "? ";
            }
        }
        return vMorse;
    }

    static void praDvojcek(int n){
        int prejsnoPrastevilo=2;
        for (int j = 3; j <= n; j++) {
            boolean prastevilo = true;
            for (int i = 2; i <= j / 2; ++i) {
                if (j % i == 0) {
                    prastevilo = false;
                    break;
                }
            }
            if (prastevilo){
                if (j-prejsnoPrastevilo==2){
                    System.out.printf("(%d, %d)\n",prejsnoPrastevilo,j);
                }
                prejsnoPrastevilo = j;
            }
        }
    }

    static void trikotnik(int n, int tip){
        switch (tip) {
            case 1:
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= i; j++) {
                        System.out.printf("%d ", j % 10);
                    }
                    System.out.println();
                }
                break;
            case 2:
                for (int i = n; i > 0; i--) {
                    System.out.print("  ".repeat(n - i));
                    for (int j = 1; j <= i; j++) {
                        System.out.printf("%d ", j % 10);
                    }
                    System.out.println();
                }
                break;
            case 3:
                for (int i = 1; i <= n; i++) {
                    System.out.print("  ".repeat(n - i));
                    for (int j = i; j > 0; j--) {
                        System.out.printf("%d ", j % 10);
                    }
                    System.out.println();
                }
                break;
            case 4:
                for (int i = n; i > 0; i--) {
                    for (int j = i; j > 0; j--) {
                        System.out.printf("%d ", j % 10);
                    }

                    System.out.println();
                }

                break;
            case 5:
                for (int i = 1; i <= n; i++) {
                    System.out.print("  ".repeat(n - i));
                    for (int j = 1; j <= i; j++) {
                        System.out.printf("%d ", j % 10);
                    }
                    for (int j = i - 1; j > 0; j--) {
                        System.out.printf("%d ", j % 10);

                    }
                    System.out.println();
                }
                break;
            case 6:
                for (int i = n; i > 0; i--) {
                    System.out.print("  ".repeat(n - i));
                    for (int j = 1; j <= i; j++) {
                        System.out.printf("%d ", j % 10);
                    }
                    for (int j = i - 1; j > 0; j--) {
                        System.out.printf("%d ", j % 10);

                    }
                    System.out.println();
                }
                break;
            case 7:
                for (int i = 1; i <= n; i++) {
                    System.out.print("  ".repeat(n - i));
                    for (int j = 0; j < i; j++) {
                        System.out.printf("%d ", (j + i) % 10);
                    }
                    for (int j = i - 1; j > 0; j--) {
                        System.out.printf("%d ", (j + i - 1) % 10);
                    }

                    System.out.println();
                }
                break;
        }
    }

    static void metulj(int n, int tip){
        switch (tip) {
            case 1:
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= i; j++) {
                        if (j != n) {
                            System.out.printf("%d ", j % 10);
                        }
                    }
                    if (((n - i) * 2 - 1) > 0) {
                        System.out.print("  ".repeat((n - i) * 2 - 1));
                    }
                    for (int j = i; j > 0; j--) {
                        System.out.printf("%d ", j % 10);
                    }
                    System.out.println();
                }
                break;
            case 2:
                for (int i = n; i > 0; i--) {
                    for (int j = 1; j <= i; j++) {
                        if (j != n) {
                            System.out.printf("%d ", j % 10);
                        }
                    }
                    if (((n - i) * 2 - 1) > 0) {
                        System.out.print("  ".repeat((n - i) * 2 - 1));
                    }
                    for (int j = i; j > 0; j--) {
                        System.out.printf("%d ", j % 10);
                    }
                    System.out.println();
                }
                break;
            case 3:
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= i; j++) {
                        if (j != n) {
                            System.out.printf("%d ", j % 10);
                        }
                    }
                    if (((n - i) * 2 - 1) > 0) {
                        System.out.print("  ".repeat((n - i) * 2 - 1));
                    }
                    for (int j = i; j > 0; j--) {
                        System.out.printf("%d ", j % 10);
                    }
                    System.out.println();
                }
                for (int i = n - 1; i > 0; i--) {
                    for (int j = 1; j <= i; j++) {
                        if (j != n) {
                            System.out.printf("%d ", j % 10);
                        }
                    }
                    if (((n - i) * 2 - 1) > 0) {
                        System.out.print("  ".repeat((n - i) * 2 - 1));
                    }
                    for (int j = i; j > 0; j--) {
                        System.out.printf("%d ", j % 10);
                    }
                    System.out.println();
                }
                break;
        }
    }

    static void veckratnikDelitelj(int a, int b){
        if (a==0 || b==0){
            System.out.println("Napaka: obe števili morata biti različni od nič.");
        }
        else{
            int manjsa = 0;
            if (Math.abs(a)<=Math.abs(b)){
                manjsa = Math.abs(a);
            }
            else{
                manjsa = Math.abs(b);
            }
            for (int i = manjsa; i >=1 ; i--) {
                if (a%i==0 && b%i==0){
                    System.out.printf("Največji skupni delitelj je %d.\n",i);
                    break;
                }
            }
            int veckratnik = 1;
            while (true){

                if ((manjsa*veckratnik)%a==0 && (manjsa*veckratnik)%b==0){
                    System.out.printf("Najmanjši skupni večkratnik je %d.",manjsa*veckratnik);
                    break;
                }
                veckratnik++;
            }
        }
    }


    public static void main(String[] args) {

        veckratnikDelitelj(3,6);
        ///System.out.println(pretvoriVMorse("Na FRIju nam je res lepo 113"));
    }
}
