public class DN04 {
    public static void main(String[] args) {
        String geslo = "";
        for (int i = 0; i < args[0].length()/8; i++) {
            String stevilka="";
            for (int j = 0; j <=7 ; j++) {
                stevilka+=args[0].charAt(i*8+j);
            }
            geslo += (char)Integer.parseInt(stevilka,2);
        }
        System.out.println(geslo);
    }
}
