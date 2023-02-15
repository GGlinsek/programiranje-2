import java.util.Random;

public class DN07 {
    static String[] prva   = {"Miha", "Micka", "Tone", "Lojze", "Mojca", "Pepca", "Franci", "Francka"};
    static String[] druga  = {"Vozi", "Seka", "Potrebuje", "Gleda", "Barva", "Voha", "Lomi", "Popravlja"};
    static String[] tretja = {"Kolo", "Avto", "Likalnik", "Sonce", "Vrtnico", "Drevo", "Lopato", "Sekiro"};

    public static void main(String[] args) {
        String[] razdeljenoGeslo = new String[4];
        String posameznaBeseda= "";
        int pozicija = 0;
        for (int i = 0; i < args[0].length(); i++) {
            if (args[0].charAt(i)<91) {
                razdeljenoGeslo[pozicija] = posameznaBeseda;
                posameznaBeseda = "";
                pozicija++;
            }
            posameznaBeseda += args[0].charAt(i);
        }
        razdeljenoGeslo[pozicija] = posameznaBeseda;

        boolean prvaBeseda = false;
        boolean drugaBeseda = false;
        boolean tretjaBeseda = false;
        for (int i = 0; i < prva.length; i++) {
            if (razdeljenoGeslo[1].equals(prva[i])){
                prvaBeseda = true;
            }
            if (razdeljenoGeslo[2].equals(druga[i])){
                drugaBeseda = true;
            }
            if (razdeljenoGeslo[3].equals(tretja[i])){
                tretjaBeseda = true;
            }

        }
        if (prvaBeseda && drugaBeseda && tretjaBeseda){
            System.out.println("true");
        }
        else{
            System.out.println("false");
        }
    }


}
