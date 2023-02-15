public class DN02 {
    public static void main(String[] args) {
        for(int i=0; i< args.length; i++){
            if(i%2==0){
                System.out.print("*".repeat(args[i].length()+4)+ " ");
            }
            else{
                System.out.print("+".repeat(args[i].length()+4)+" ");
            }
        }
        System.out.println();
        for(int i=0; i< args.length; i++){
            if (i%2==0){
                System.out.print("* " + args[i] + " * ");
            }
            else{
                System.out.print("+ " + args[i] + " + ");
            }
        }
        System.out.println();
        for(int i=0; i< args.length; i++){
            if(i%2==0){
                System.out.print("*".repeat(args[i].length()+4)+ " ");
            }
            else{
                System.out.print("+".repeat(args[i].length()+4)+" ");
            }
        }

    }
}
