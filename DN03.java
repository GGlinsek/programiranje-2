import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class DN03 {
    public static void main(String[] args) throws FileNotFoundException {
        int i = 0;
        String geslo = "";
        Random rnd = new Random(Integer.parseInt(args[2]));
        Scanner sc = new Scanner(new File(args[0]));
        int steviloVrst = Integer.parseInt(sc.nextLine());
        String [] Tabela = new String[steviloVrst];
        while (sc.hasNextLine()){
            Tabela[i] = sc.nextLine();
            i++;
        }
        for (int j = 0; j < Integer.parseInt(args[1]) ; j++) {
            String vrstica = Tabela[rnd.nextInt(steviloVrst)];
            char znak = vrstica.charAt(rnd.nextInt(vrstica.length()));
            geslo += znak;

        }
        sc.close();
        System.out.println(geslo);
    }
}
