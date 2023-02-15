import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DN08 {

    private static final int K_POINT = 200;            // K-točka letalnice
    private static final int FLYING_HILL_POINTS = 120; // število točk za doseženo K-točko
    private static final double METER_VALUE = 1.2;     // faktor za dodatne točke za vsak meter pod/nad K-točko

    private static String[][] tekmovalci; // podatki o posameznem tekmovalcu; prva dimenzija so tekmovalci, druga različni podatki enega tekmovalca
    private static double[][] daljave;    // daljava prve (0) in druge (1) serije; prva dimenzija so tekmovalci, druga serija
    private static double[][][] ocene;    // ocene prve (0) in druge (1) serije od vseh 5 sodnikov; prva dimenzija so tekmovalci, druga serija, tretja sodniki
    private static double[][] komp_nalet; // kompenzacijske točke za nalet v prvi (0) in drugi (1) seriji; prva dimenzija so tekmovalci, druga serija
    private static double[][] komp_veter; // kompenzacijske točke za veter v prvi (0) in drugi (1) seriji; prva dimenzija so tekmovalci, druga serija
    private static double[][] tocke;      // seštevek doseženih točk v seriji; prva dimenzija so tekmovalci, druga serija: prva (0), druga (1), obe skupaj (2)

    static void definiranjeArrajev () {
        daljave = new double[tekmovalci.length][2];
        komp_nalet = new double[tekmovalci.length][2];
        komp_veter = new double[tekmovalci.length][2];
        tocke = new double[tekmovalci.length][3];
        ocene = new double[tekmovalci.length][2][5];

    }


    static void podatkiVTabelo(String a) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(a));
        int stevec = -1;
        tekmovalci = new String[sc.nextInt()+1][5];
        int stevilo = 1;
        sc.nextLine();
        while (sc.hasNextLine()){
            switch (stevec){
                case -1:
                    sc.nextLine();
                    break;
                case 0:
                    tekmovalci[stevilo][0] = sc.nextLine();
                    break;
                case 1:
                    tekmovalci[stevilo][1] = sc.nextLine();
                    break;
                case 2:
                    tekmovalci[stevilo][2] = sc.nextLine();
                    break;
                case 3:
                    tekmovalci[stevilo][3] = sc.nextLine();
                    break;
                case 4:
                    tekmovalci[stevilo][4] = sc.nextLine();
                    stevec = -2;
                    stevilo++;
                    break;

            }
            stevec++;
        }
        sc.close();
    }

    static void normalniIzpis(String x) {

        switch (x) {
            case "1":
                System.out.println("Start List 1st Round:");
                System.out.printf("BIB %-" + 25 + "s (NSA)  [DATE OF BIRTH, CLUB]\n", "NAME");
                for (int i = 1; i < tekmovalci.length; i++) {
                    System.out.printf("%3s %-" + 25 + "s (%s)  [%11s, %s]\n", tekmovalci[i][0], tekmovalci[i][1], tekmovalci[i][4], tekmovalci[i][3], tekmovalci[i][2]);
                }
                break;
            case "2":
                System.out.println("Results first round:");
                System.out.printf("BIB %-" + 25 + "s (NSA)  [DATE OF BIRTH, CLUB]%" + 26 + "s %s\n", "NAME", "", "DISTANCE [m]");
                for (int i = 1; i < tekmovalci.length; i++) {
                    System.out.printf("%3s %-" + 25 + "s (%s)  [%11s, %-" + 32 + "s ", tekmovalci[i][0], tekmovalci[i][1], tekmovalci[i][4], tekmovalci[i][3],tekmovalci[i][2] + "]");
                    if (daljave[i][0] > 0) {
                        System.out.printf("%.1f\n", daljave[i][0]);
                    } else {
                        System.out.println("Disqualified");
                    }
                }
                break;
            case "3":
                System.out.println("Results final round:");
                System.out.printf("BIB %-" + 25 + "s (NSA)  [DATE OF BIRTH, CLUB]%" + 25 + "s %-13s (DISTANCE [m])\n", "NAME", "", "DISTANCE [m]");
                for (int i = 1; i < tekmovalci.length; i++) {
                    System.out.printf("%3s %-" + 25 + "s (%s)  [%11s, %-" + 32 + "s ", tekmovalci[i][0], tekmovalci[i][1], tekmovalci[i][4], tekmovalci[i][3],tekmovalci[i][2] + "]");                    if (daljave[i][1]>0){
                        System.out.printf("%-13.1f ", daljave[i][1]);
                    }
                    else if (daljave[i][0]<0 || daljave[i][1]<0){
                        System.out.print("Disqualified  ");
                    }
                    else if (daljave[i][1]==0){
                        System.out.print("Not qualified ");
                    }
                    if (daljave[i][0] > 0) {
                        System.out.printf("(%.1f)\n", daljave[i][0]);
                    } else {
                        System.out.println(" ");
                    }

                }
                break;
            case "4":
                System.out.println("Points first round:");
                System.out.printf("BIB %-"+25+"s (NSA)  POINTS\n","NAME");
                for (int i = 1; i < tekmovalci.length; i++) {
                    System.out.printf("%3s %-" + 25 + "s (%s)  ",tekmovalci[i][0], tekmovalci[i][1], tekmovalci[i][4]);
                    if (tocke[i][0]<0){
                        System.out.println("DSQ");
                    }
                    else {
                        System.out.printf("%.1f\n", tocke[i][0]);
                    }
                }
                break;
            case "5":
                System.out.println("Points final round:");
                System.out.printf("BIB %-"+25+"s (NSA)  POINTS (1st)  POINTS (final)  TOTAL\n","NAME");
                for (int i = 1; i < tekmovalci.length; i++) {
                    System.out.printf("%3s %-" + 25 + "s (%s)  ",tekmovalci[i][0], tekmovalci[i][1], tekmovalci[i][4]);
                    if (tocke[i][0]<0){
                        System.out.printf("%-13s ","DSQ");
                    }
                    else {
                        System.out.printf("%-13.1f ", tocke[i][0]);
                    }
                    if (tocke[i][1]<0){
                        if (daljave[i][1]==0) {
                            System.out.printf("%-15s ", "  -");
                        }
                        else {
                            System.out.printf("%-15s ","DSQ");
                        }
                    }
                    else {
                        System.out.printf("%-15.1f ", tocke[i][1]);
                    }
                    if (tocke[i][2]<0){
                        System.out.print("  -\n");
                    }
                    else {
                        System.out.printf("%.1f\n", tocke[i][2]);
                    }
                }
            case "6":
            case "7":
        }
    }

    static void branjeRezultatov (String a, int b) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(a));
        sc.nextLine();
        while (sc.hasNextLine()){
            String popravljeno = "";
            String[] popravek = sc.nextLine().split(";;");//v primeru, da je ima GATE prazen
            if (popravek.length == 2){
                popravljeno = popravek[0] + ";0;" + popravek[1];
            }
            else{
                popravljeno = popravek[0];
            }
            String[] vrstica = popravljeno.split(";");

            if (vrstica.length>2){ //dodajanje rezultatov v tabelo
                daljave[Integer.parseInt(vrstica[0])][b] = Double.parseDouble(vrstica[2]);
                komp_nalet[Integer.parseInt(vrstica[0])][b] = Double.parseDouble(vrstica[4]);
                komp_veter[Integer.parseInt(vrstica[0])][b] = Double.parseDouble(vrstica[6]);
            }
            else { //tekmovalec je bil diskvalificiran
                daljave[Integer.parseInt(vrstica[0])][b] = -1;
                komp_nalet[Integer.parseInt(vrstica[0])][b] = 0;
                komp_veter[Integer.parseInt(vrstica[0])][b] = 0;
            }
            }
        sc.close();
    }

    static void oceneSodnikov(String a, int x) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(a));
        sc.nextLine();
        while (sc.hasNextLine()){
            String[] vrstica = sc.nextLine().split(";"); //razdeli vsako vrstico na posamezne ocene in jih priredi tabeli
            if (vrstica.length>2){
                ocene[Integer.parseInt(vrstica[0])][x][0] = Double.parseDouble(vrstica[1]);
                ocene[Integer.parseInt(vrstica[0])][x][1] = Double.parseDouble(vrstica[2]);
                ocene[Integer.parseInt(vrstica[0])][x][2] = Double.parseDouble(vrstica[3]);
                ocene[Integer.parseInt(vrstica[0])][x][3] = Double.parseDouble(vrstica[4]);
                ocene[Integer.parseInt(vrstica[0])][x][4] = Double.parseDouble(vrstica[5]);
            }
            else {
                ocene[Integer.parseInt(vrstica[0])][x][0] = -1;
                ocene[Integer.parseInt(vrstica[0])][x][1] = -1;
                ocene[Integer.parseInt(vrstica[0])][x][2] = -1;
                ocene[Integer.parseInt(vrstica[0])][x][3] = -1;
                ocene[Integer.parseInt(vrstica[0])][x][4] = -1;
            }
        }
        sc.close();
    }

    static void tockovanje(int x){
        for (int i = 1; i < tekmovalci.length; i++) {
            Double[] sodniki = new Double[5];
            for (int j = 0; j < 5; j++) {
                sodniki[j] = ocene[i][x][j];
            }
            Arrays.sort(sodniki);
            tocke[i][x] = FLYING_HILL_POINTS + (daljave[i][x]-K_POINT)*1.2+(sodniki[1]+sodniki[2]+sodniki[3]+ komp_nalet[i][x]+komp_veter[i][x]);
        }
    }

    static void skupneTocke(){
        for (int i = 1; i < tekmovalci.length; i++) {
            if (daljave[i][0]<0){///ce je tekmovalec bil diskvalificiran v prvi rundi
                tocke[i][2] = -1;
            }
            else if (daljave[i][1]<=0){///ce je tekmovalec bil diskvalifiran v drugi rundi ali je izpadel
                tocke[i][2] = tocke[i][0];
            }
            else{///ce je tekmovalec opravil oba skoka
                tocke[i][2] = tocke[i][0] + tocke[i][1];
            }
        }
    }

    static void zapisRezultatov(String a,String b) throws FileNotFoundException {
        int[] razvrstitev = new int[tekmovalci.length];
        double[] razvrstitevTock = new double[tekmovalci.length];
        for (int i = 1; i < tekmovalci.length; i++) {
            razvrstitevTock[i] = tocke[i][0];
        }
        Arrays.sort(razvrstitevTock);
        for (int i = 1; i < tekmovalci.length; i++) {
            if (razvrstitev[Arrays.binarySearch(razvrstitevTock, tocke[i][0])]!=0){
                if (daljave[i][0]>daljave[razvrstitev[Arrays.binarySearch(razvrstitevTock, tocke[i][0])]][0]){
                    razvrstitev[Arrays.binarySearch(razvrstitevTock, tocke[i][0])+1] = i;
                }
                else{
                    razvrstitev[Arrays.binarySearch(razvrstitevTock, tocke[i][0])+1] = razvrstitev[Arrays.binarySearch(razvrstitevTock, tocke[i][0])];
                    razvrstitev[Arrays.binarySearch(razvrstitevTock, tocke[i][0])] = i;
                }
            }
            else{
                razvrstitev[Arrays.binarySearch(razvrstitevTock, tocke[i][0])] = i;
            }
        }

        int mejaDiskvalificiranih = Arrays.binarySearch(razvrstitevTock, 0);

        PrintWriter pw = new PrintWriter(a);
        pw.println("START ORD.;BIB;NAME;NSA;POINTS;RANK;BEHIND POINTS");
        for (int i = mejaDiskvalificiranih+11; i < tekmovalci.length; i++) {

            if (i!=tekmovalci.length-1 &&razvrstitevTock[i]==razvrstitevTock[i+1]){
                pw.printf("%d;%d;%s;%s;%.1f;%d;%.1f\n",i-(10+mejaDiskvalificiranih),razvrstitev[i],tekmovalci[razvrstitev[i]][1],tekmovalci[razvrstitev[i]][4],tocke[razvrstitev[i]][0],tekmovalci.length-i-1,razvrstitevTock[tekmovalci.length-1]-razvrstitevTock[i]);
            }
            else{
                pw.printf("%d;%d;%s;%s;%.1f;%d;%.1f\n",i-(10+mejaDiskvalificiranih),razvrstitev[i],tekmovalci[razvrstitev[i]][1],tekmovalci[razvrstitev[i]][4],tocke[razvrstitev[i]][0],tekmovalci.length-i,razvrstitevTock[tekmovalci.length-1]-razvrstitevTock[i]);
            }

        }
        pw.close();

        PrintWriter pwb = new PrintWriter(b);
        pwb.println("RANK;BIB;NAME;NSA;DIST-1;DIST-2;POINTS-1;POINTS-2;TOTAL");
        int[] razvrstitevKoncno = new int[tekmovalci.length];
        double[] razvrstitevTockKoncno = new double[tekmovalci.length];
        for (int i = 1; i < tekmovalci.length; i++) {
             razvrstitevTockKoncno[i] =tocke[razvrstitev[i]][2];
        }
        Arrays.sort(razvrstitevTockKoncno);
        for (int i = 1; i < tekmovalci.length; i++) {
            razvrstitevKoncno[Arrays.binarySearch(razvrstitevTockKoncno, tocke[i][2])] = i;
        }
        for (int i = tekmovalci.length-1; i > 0; i--) {
            pwb.printf("%d;%d;%s;%s;%.1f;%.1f;%.1f;%.1f;%.1f\n", tekmovalci.length- i,razvrstitevKoncno[i],tekmovalci[razvrstitevKoncno[i]][1],tekmovalci[razvrstitevKoncno[i]][4],daljave[razvrstitevKoncno[i]][0],daljave[razvrstitevKoncno[i]][1],tocke[razvrstitevKoncno[i]][0],tocke[razvrstitevKoncno[i]][1],tocke[razvrstitevKoncno[i]][2]);
        }

        pwb.close();
    }


    static void najbolseNajslabseOcene(){
        double[] oceneSkupaj = new double[5];
        int stevec = 0;
        for (int i = 1; i < tekmovalci.length; i++) {
            if (ocene[i][0][0]>0){
                stevec++;
            }
            if (ocene[i][1][0]>0){
                stevec++;
            }
            oceneSkupaj[0] += ocene[i][0][0] + ocene[i][1][0];
            oceneSkupaj[1] += ocene[i][0][1] + ocene[i][1][1];
            oceneSkupaj[2] += ocene[i][0][2] + ocene[i][1][2];
            oceneSkupaj[3] += ocene[i][0][3] + ocene[i][1][3];
            oceneSkupaj[4] += ocene[i][0][4] + ocene[i][1][4];
        }
        double najnizja = oceneSkupaj[0];
        int sodnikNajnizja = 0;
        double najvisja = oceneSkupaj[0];
        int sodnikNajvisja = 0;
        for (int i = 1; i < oceneSkupaj.length; i++) {
            if (najnizja>oceneSkupaj[i]){
                najnizja = oceneSkupaj[i];
                sodnikNajnizja = i;
            }
            if (najvisja<oceneSkupaj[i]){
                najvisja = oceneSkupaj[i];
                sodnikNajvisja = i;
            }

        }
        System.out.printf("Best marks:  %s (%.1f)\nWorst marks: %s (%.1f)",Character.toString(65+sodnikNajvisja),najvisja/stevec,Character.toString(65+sodnikNajnizja),najnizja/stevec);
    }

    static void najmlajsiKSkakalec() throws ParseException {
        String najmlajsi = "All flights are below K-point.";
        Date najmlajsiRojstvo = null;
        SimpleDateFormat formatiranje=new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
        for (int i = 1; i < tekmovalci.length; i++) {
            Date datumRojstva = formatiranje.parse(tekmovalci[i][3]);
            if(daljave[i][0]>K_POINT || daljave[i][1]>K_POINT){
                if (najmlajsiRojstvo == null || datumRojstva.compareTo(najmlajsiRojstvo)>0){
                    najmlajsiRojstvo = datumRojstva;
                    najmlajsi = tekmovalci[i][1];
                }
                else if (datumRojstva.compareTo(najmlajsiRojstvo)==0){
                    najmlajsi += "\n"+tekmovalci[i][1];
                }
            }
        }
        System.out.println(najmlajsi);
    }



    public static void main(String[] args) throws FileNotFoundException, ParseException {

        switch (args[0]) {
            case "1":
                podatkiVTabelo(args[1]);
                normalniIzpis(args[0]);
                break;
            case "2":
                podatkiVTabelo(args[1]);
                definiranjeArrajev();
                branjeRezultatov(args[2],0);
                normalniIzpis(args[0]);
                break;
            case "3":
                podatkiVTabelo(args[1]);
                definiranjeArrajev();
                branjeRezultatov(args[2],0);
                branjeRezultatov(args[3],1);
                normalniIzpis(args[0]);

                break;
            case "4":
                podatkiVTabelo(args[1]);
                definiranjeArrajev();
                branjeRezultatov(args[2],0);
                oceneSodnikov(args[3], 0);
                tockovanje(0);
                normalniIzpis(args[0]);
                break;
            case "5":
                podatkiVTabelo(args[1]);
                definiranjeArrajev();
                branjeRezultatov(args[2],0);
                branjeRezultatov(args[4],1 );
                oceneSodnikov(args[3], 0);
                oceneSodnikov(args[5], 1);
                tockovanje(0);
                tockovanje(1);
                skupneTocke();
                normalniIzpis(args[0]);
                break;
            case "6":
                podatkiVTabelo(args[1]);
                definiranjeArrajev();
                branjeRezultatov(args[2],0);
                branjeRezultatov(args[4],1 );
                oceneSodnikov(args[3], 0);
                oceneSodnikov(args[5], 1);
                tockovanje(0);
                tockovanje(1);
                skupneTocke();
                zapisRezultatov(args[6],args[7]);
                break;
            case "7":
                podatkiVTabelo(args[1]);
                definiranjeArrajev();
                branjeRezultatov(args[2],0);
                branjeRezultatov(args[3],1 );
                najmlajsiKSkakalec();
                break;
            case "8":
                podatkiVTabelo(args[1]);
                definiranjeArrajev();
                oceneSodnikov(args[2], 0);
                oceneSodnikov(args[3], 1);
                najbolseNajslabseOcene();
                break;
        }

    }
}
