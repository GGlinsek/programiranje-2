import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Vozlisce {
    private int id;
    private double x;
    private double y;
    static List<Cesta> ceste = new ArrayList<>();

    public double GetX(){
        return x;
    }
    public double GetY(){ return y; }
    public int GetId(){
        return id;
    }

    Vozlisce(int id, double x, double y){
        this.id = id;
        this.x = x;
        this.y = y;
    }
    void dodajCesto(Cesta cesta){
        ceste.add(cesta);
    }

    static List<Integer> GetSosedi(int id){
        List<Integer> sosedi = new ArrayList<>();
        for (int i = 0; i < ceste.size(); i++) {
            Cesta cesta = ceste.get(i);
            if (cesta.GetVozlisceA().GetId() == id && !sosedi.contains(cesta.GetVozlisceB().GetId()) ){
                sosedi.add(cesta.GetVozlisceB().GetId());
            }
            else if (cesta.GetVozlisceB().GetId() == id && !sosedi.contains(cesta.GetVozlisceA().GetId()) ){
                sosedi.add(cesta.GetVozlisceA().GetId());
            }
        }
        return sosedi;
    }



    static Cesta NajdiCesto(int id1,int id2){
        for (int i = 0; i < ceste.size(); i++) {
            Cesta cesta = ceste.get(i);
            if ((cesta.GetVozlisceA().GetId() == id1 && cesta.GetVozlisceB().GetId() == id2) || (cesta.GetVozlisceB().GetId() == id1 && cesta.GetVozlisceA().GetId() == id2)){
                return cesta;
            }

        }
        return null;
    }



}

class Kraj extends Vozlisce{
    private String ime;
    Kraj(int id, double x, double y,String ime) {
        super(id, x, y);
        this.ime = ime;
    }
}

class Crpalka extends Vozlisce{
    private double cena95;
    private double cenadizel;

    public double Get95(){
        return cena95;
    }
    public double GetDizel(){
        return cenadizel;
    }

    Crpalka(int id, double x, double y, double cena95,double cenadizel) {
        super(id, x, y);
        this.cena95 = cena95;
        this.cenadizel = cenadizel;
    }
}


class Cesta{
    private int hitrost;
    private Vozlisce a;
    private Vozlisce b;
    public Vozlisce GetVozlisceA(){
        return a;
    }
    public Vozlisce GetVozlisceB(){
        return b;
    }
    public int GetHitrost() { return hitrost; }

    Cesta(int hitrost, Vozlisce a, Vozlisce b){
        this.hitrost = hitrost;
        this.a = a;
        this.b = b;
    }

    public double getDolzina(){
        double x = Math.abs(a.GetX()-b.GetX())*111.12;
        double y = Math.abs(a.GetY()-b.GetY())*77.4;
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public String toString(){
        return String.format("Cesta (%d,%d): dolzina=%.2f km, omejitev=%d km/h",a.GetId(),b.GetId(),getDolzina(), hitrost);
    }



}



class CestnoOmrezje{
    private Vozlisce[] vozlisca;
    private Cesta[] ceste;



    public Cesta[] GetCesteArray(){
        return ceste;
    }

    CestnoOmrezje(Vozlisce[] vozlisca, Cesta[] ceste){
        this.vozlisca = vozlisca;
        this.ceste = ceste;
    }

    static CestnoOmrezje izDatoteke(String imeDatoteke) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(imeDatoteke));
        String[] razdelitev = sc.nextLine().split(" ");
        Vozlisce[] vozlisceArray = new Vozlisce[Integer.parseInt(razdelitev[0])];
        Cesta[] cesteArray = new Cesta[Integer.parseInt(razdelitev[1])];
        for (int i = 0; i < Integer.parseInt(razdelitev[0]); i++) {
            String[] razdeljenaVrsta = sc.nextLine().split(" ");
            switch (razdeljenaVrsta[0]) {
                case "vozlisce": {
                    Vozlisce v1 = new Vozlisce(i, Double.parseDouble(razdeljenaVrsta[1]), Double.parseDouble(razdeljenaVrsta[2]));
                    vozlisceArray[i] = v1;
                    break;
                }
                case "kraj": {
                    StringBuilder ime_kraja = new StringBuilder();
                    for (int j = 3; j < razdelitev.length; j++) {
                        ime_kraja.append(razdelitev[j]);
                    }
                    Kraj v1 = new Kraj(i, Double.parseDouble(razdeljenaVrsta[1]), Double.parseDouble(razdeljenaVrsta[2]), ime_kraja.toString());
                    vozlisceArray[i] = v1;
                    break;
                }
                case "crpalka": {
                    Crpalka v1 = new Crpalka(i, Double.parseDouble(razdeljenaVrsta[1]), Double.parseDouble(razdeljenaVrsta[2]), Double.parseDouble(razdeljenaVrsta[3]), Double.parseDouble(razdeljenaVrsta[4]));
                    vozlisceArray[i] = v1;

                    break;
                }
            }
        }

        for (int i = 0; i < Integer.parseInt(razdelitev[1]); i++) {
            String[] razdeljenaVrsta = sc.nextLine().split(" ");
            Cesta c1 = new Cesta(Integer.parseInt(razdeljenaVrsta[2]),vozlisceArray[Integer.parseInt(razdeljenaVrsta[0])],vozlisceArray[Integer.parseInt(razdeljenaVrsta[1])]);
            cesteArray[i] = c1;
            vozlisceArray[Integer.parseInt(razdeljenaVrsta[0])].dodajCesto(c1);
            vozlisceArray[Integer.parseInt(razdeljenaVrsta[1])].dodajCesto(c1);
        }
        CestnoOmrezje omrezjeCest = new CestnoOmrezje(vozlisceArray,cesteArray);
        sc.close();
        return omrezjeCest;
    }

    public double[] dolzinaPoti(int[] pot){
        double skupnaDolzina = 0;
        double cas = 0;
        for (int i = 1; i < pot.length; i++) {
            Cesta cesta = Vozlisce.NajdiCesto(pot[i-1],pot[i]);
            assert cesta != null;
            skupnaDolzina += cesta.getDolzina();
            cas += cesta.getDolzina()/ cesta.GetHitrost()*60;
        }
        return new double[]{skupnaDolzina,Math.round(cas)};
    }

    public void crpalkeObPoti(int[] vozlisca){
        List<Integer> crpalke = new ArrayList<>();
        List<Integer> listVozlisca = new ArrayList<>();
        for (int i = 0; i < vozlisca.length; i++) {
            listVozlisca.add(vozlisca[i]);
        }
        for (int i = 1; i < vozlisca.length; i++) {
            Cesta cesta = Vozlisce.NajdiCesto(vozlisca[i-1], vozlisca[i]);
            if (cesta.GetVozlisceA() instanceof Crpalka){
                System.out.printf("Crpalka(%d) [95: %.3f EUR, dizel: %.3f EUR]: na poti\n", cesta.GetVozlisceA().GetId(), ((Crpalka) cesta.GetVozlisceA()).Get95(),((Crpalka) cesta.GetVozlisceA()).GetDizel());
                crpalke.add(cesta.GetVozlisceA().GetId());
            }
            else {
                List<Integer> sosedi = Vozlisce.GetSosedi(cesta.GetVozlisceA().GetId());
                for (int j = 0; j < sosedi.size(); j++) {
                    Cesta sosednjaCesta = Vozlisce.NajdiCesto(cesta.GetVozlisceA().GetId(), sosedi.get(j));
                    if (!crpalke.contains(sosednjaCesta.GetVozlisceA().GetId()) && sosednjaCesta.GetVozlisceA() instanceof Crpalka && !listVozlisca.contains(sosednjaCesta.GetVozlisceA().GetId()))
                    {
                        System.out.printf("Crpalka(%d) [95: %.3f EUR, dizel: %.3f EUR]: %.2f km s poti\n", sosednjaCesta.GetVozlisceA().GetId(), ((Crpalka) sosednjaCesta.GetVozlisceA()).Get95(),((Crpalka) sosednjaCesta.GetVozlisceA()).GetDizel(), sosednjaCesta.getDolzina());
                        crpalke.add(sosednjaCesta.GetVozlisceA().GetId());
                    }
                    else if (!crpalke.contains(sosednjaCesta.GetVozlisceB().GetId()) && sosednjaCesta.GetVozlisceB() instanceof Crpalka && !listVozlisca.contains(sosednjaCesta.GetVozlisceB().GetId())){
                        System.out.printf("Crpalka(%d) [95: %.3f EUR, dizel: %.3f EUR]: %.2f km s poti\n", sosednjaCesta.GetVozlisceB().GetId(), ((Crpalka) sosednjaCesta.GetVozlisceB()).Get95(),((Crpalka) sosednjaCesta.GetVozlisceB()).GetDizel(), sosednjaCesta.getDolzina());
                        crpalke.add(sosednjaCesta.GetVozlisceB().GetId());
                    }
                }
            }
        }
    }


}

public class DN11 {
    public static void main(String[] args) throws FileNotFoundException {
        CestnoOmrezje omrezje_cest = CestnoOmrezje.izDatoteke(args[1]);
        switch (args[0]) {
            case "ceste":
                System.out.println("Omrezje vsebuje naslednje ceste:");
                for (int i = 0; i < omrezje_cest.GetCesteArray().length; i++) {
                    Cesta izpisCeste = new Cesta(omrezje_cest.GetCesteArray()[i].GetHitrost(), omrezje_cest.GetCesteArray()[i].GetVozlisceA(), omrezje_cest.GetCesteArray()[i].GetVozlisceB());
                    System.out.println(izpisCeste);
                }
                break;
            case "dolzinaPoti": {
                System.out.print("Pot: ");
                int[] pot = new int[args.length - 2];
                for (int i = 2; i < args.length; i++) {
                    System.out.print(args[i]);
                    if (i != args.length - 1) {
                        System.out.print(" - ");
                    }
                    pot[i - 2] = Integer.parseInt(args[i]);
                }
                System.out.println();
                double[] potInCas = omrezje_cest.dolzinaPoti(pot);
                System.out.printf("Skupna dolzina: %.2f km\nPredviden cas voznje: %.0fh %.0fmin", potInCas[0], Math.floor(potInCas[1] / 60), potInCas[1] % 60);


                break;
            }
            case "crpalkeObPoti": {
                int[] pot = new int[args.length - 2];
                for (int i = 2; i < args.length; i++) {
                    pot[i - 2] = Integer.parseInt(args[i]);
                }
                omrezje_cest.crpalkeObPoti(pot);
                break;
            }
            case "obremenjeneCeste":

                break;
        }
    }
}
