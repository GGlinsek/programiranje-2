public class DN10 {
    static int Pravokotnik(int a, int b){
        return (2*a+2*b);
    }
    static int NKotnik(int a, int b){
        return (a*b);
    }
    static int Kvadrat(int a){
        return (a*4);
    }

    static int Primer(String a){
        int rezultat = 0;
        switch (a.split(":")[0]){

            case "pravokotnik":
                rezultat = Pravokotnik(Integer.parseInt(a.split(":")[1]),Integer.parseInt(a.split(":")[2]));
                break;
            case "kvadrat": {
                rezultat =  Kvadrat(Integer.parseInt(a.split(":")[1]));
                break;
            }
            case "nkotnik":{
                rezultat = NKotnik(Integer.parseInt(a.split(":")[1]),Integer.parseInt(a.split(":")[2]));
                break;
            }
        }
        return rezultat;
    }

    public static void main(String[] args) {

        int vsota = 0;
        for (int i = 0; i < args.length; i++) {
            vsota += Primer(args[i]);

        }
        System.out.println(vsota);
    }
}

