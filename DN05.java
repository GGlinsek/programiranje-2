public class DN05 {
    public static void main(String[] args) {
        int[] tabela = new int[10];
        String beseda = "";
        for (int i = 0; i < args.length; i++) {
            if (i==0){
                beseda= args[i];
            }
            else {
                beseda = beseda + " " + args[i];
            }
        }

        for (int j = 0; j < beseda.length(); j++) {
            if (Character.isDigit(beseda.charAt(j))){
                tabela[Character.getNumericValue(beseda.charAt(j))]++;
            }
        }
        int maxVrednost= 0;
        String najpogostejse = "";
        for (int k = 0; k < tabela.length; k++) {
            if(tabela[k]>maxVrednost){
                maxVrednost = tabela[k];
                najpogostejse = k + " ";
            }
            else if (tabela[k]==maxVrednost){
                najpogostejse = najpogostejse + k + " ";
            }


        }
        if (maxVrednost==0){
            System.out.println("V nizu '" + beseda + "' ni stevk");
        }
        else if (maxVrednost>0){
            System.out.println("'"+beseda+"' -> "+ najpogostejse + "("+maxVrednost+")");
        }
    }
}
