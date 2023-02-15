
import java.util.Arrays;
import java.util.List;

public class kviz2 {

    static int[] unija(int[] tabela1, int[] tabela2){
        int[] tabelaSkupno = new int[tabela1.length+tabela2.length];
        System.arraycopy(tabela1, 0, tabelaSkupno, 0, tabela1.length);
        System.arraycopy(tabela2, 0, tabelaSkupno, tabela1.length, tabela2.length);
        return tabelaSkupno;
    }

    static int[] presek(int[] tabela1, int[] tabela2){
        java.util.List<Integer> novaTabela = new java.util.ArrayList<>();
        for (int i = 0; i < tabela1.length; i++) {
            for (int j = 0; j < tabela2.length; j++) {
                if (tabela1[i] == tabela2[j]){
                    if (!novaTabela.contains(tabela1[i])){
                        novaTabela.add(tabela1[i]);
                        break;
                    }

                }
            }
        }

        int[] tabelaPresek = new int[novaTabela.size()];
        for (int i = 0; i < novaTabela.size(); i++) {
            tabelaPresek[i] = novaTabela.get(i);
        }

        return tabelaPresek;
    }

    static int[] duplikati(int[] tabela) {
        java.util.List<Integer> novaTabela = new java.util.ArrayList<>();
        for (int value : tabela) {
            if (!novaTabela.contains(value)) {
                novaTabela.add(value);
            }

        }

        int[] tabelaPresek = new int[novaTabela.size()];
        for (int i = 0; i < novaTabela.size(); i++) {
            tabelaPresek[i] = novaTabela.get(i);
        }

        return tabelaPresek;
    }


    static int[] range(int a, int b, int c){
        int d= 0;
        if ((b-a)%c!=0){
            d++;
        }
            int[] veljavneStevilke = new int[(b-a)/c+d];
        for (int i = 0; i < (b-a)/c+d; i++) {
            veljavneStevilke[i] = a + i*c;
        }
        return veljavneStevilke;
    }

    static int vsotaSkupnihCifer(int a, int b){
        String tabela1 = Integer.toString(a);
        String tabela2 = Integer.toString(b);
        java.util.List<Character> novaTabela = new java.util.ArrayList<>();
        for (int i = 0; i < tabela1.length(); i++) {
            for (int j = 0; j < tabela2.length(); j++) {
                if (tabela1.charAt(i) == tabela2.charAt(j)){
                    if (!novaTabela.contains(tabela1.charAt(i))){
                        novaTabela.add(tabela1.charAt(i));
                        break;
                    }

                }
            }
        }
        int sestevek = 0;
        for (int i = 0; i < novaTabela.size(); i++) {
            sestevek += Integer.parseInt(String.valueOf(novaTabela.get(i)));

        }
        return sestevek;
    }



    static void rotiraj(int [] tabela, int k){
        int[] novaTabela = new int[tabela.length];
        System.arraycopy(tabela, 0, novaTabela, 0, tabela.length);
        for (int i = k% tabela.length; i < tabela.length; i++) {
            tabela[i-k% tabela.length] = novaTabela[i];
        }
        for (int j = 0; j < k% tabela.length; j++) {
            tabela[tabela.length-k% tabela.length+j] = novaTabela[j];

        }

    }

    static void izpisKoledarja(int leto, int mesec){
        java.time.YearMonth yearMonth = java.time.YearMonth.of(leto, mesec);
        int steviloDni = yearMonth.lengthOfMonth();
        int prviDan    = java.time.LocalDate.of(leto, mesec, 01).getDayOfWeek().getValue();
        System.out.println("PO  TO  SR  ČE  PE  SO  NE");
        for (int i = 1; i < prviDan; i++) {
            System.out.print("    ");
        }
        int stevec = prviDan;
        for (int i = 1; i <= steviloDni; i++) {
            if (stevec!=7){
                System.out.printf("%2d  ", i);
                stevec++;
            }
            else {
                System.out.printf("%2d\n", i);
                stevec = 1;
            }
        }
    }
    public static void main(String[] args) {
        izpisKoledarja(2022,5);
    }
}
