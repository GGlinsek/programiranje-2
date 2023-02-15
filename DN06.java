public class DN06 {
    static int bsdChecksum(String niz) {
        int checksum = 0;

        for (int i = 0; i<niz.length(); i++) {
            checksum = (checksum >> 1) + ((checksum & 1) << 15);
            checksum += niz.charAt(i);
            checksum &= 0xffff;
        }
        return checksum;
    }

    static Character povecajZadnjo(char a){
        int ascii = (int) a;
        ascii++;
        return (char) ascii;
    }
    static StringBuilder zamenjajZ(StringBuilder neki){
        for (int i = neki.length()-1; i >= 0; i--) {
            if(neki.charAt(i)=='z' && neki.charAt(i-1)=='z'){
                neki.setCharAt(i,'a');
            }
            else if (neki.charAt(i)=='z' && neki.charAt(i-1)!='z'){
                neki.setCharAt(i,'a');
                int ascii = (int) neki.charAt(i-1);
                ascii++;
                char crka = (char) ascii;
                neki.setCharAt(i-1, crka);
                return neki;
            }
        }
        return neki;
    }

    public static void main(String[] args) {
        int bsd = bsdChecksum(args[0]);
        String prviString = "a".repeat(args[0].length());
        StringBuilder string = new StringBuilder(prviString);
        while (bsd!=bsdChecksum(String.valueOf(string))){
            if(string.charAt(prviString.length()-1)=='z'){
                string = zamenjajZ(string);
            }
            else {
                string.setCharAt(prviString.length() - 1, povecajZadnjo(string.charAt(prviString.length() - 1)));

            }
        }
        System.out.println(string);

    }
}
