import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

class Planet {
    String ime;
    int radij;

    double povrsina(){
        return 4*Math.PI*Math.pow(radij,2);
    }
}

public class DN09 {
    public static void main(String[] args) throws FileNotFoundException {
        Planet[] planeti = new Planet[8];
        Scanner sc = new Scanner(new File(args[0]));
        String[] planet;
        for (int i = 0; i < 8; i++) {
            planet = sc.nextLine().split(":");
            planeti[i] = new Planet();
            planeti[i].ime = planet[0].toLowerCase(Locale.ROOT);
            planeti[i].radij = Integer.parseInt(planet[1]);
        }
        String[] sestetiPlaneti = args[1].split("\\+");
        double povrsinaVseh = 0;
        for (int i = 0; i < sestetiPlaneti.length; i++) {
            for (int j = 0; j < planeti.length; j++) {
                if (planeti[j].ime.equals(sestetiPlaneti[i].toLowerCase(Locale.ROOT))){
                    povrsinaVseh+= planeti[j].povrsina();
                }

            }

        }
        System.out.printf("Povrsina planetov \""+args[1]+"\" je %.0f milijonov km2", povrsinaVseh/1000000);
        }
    }

