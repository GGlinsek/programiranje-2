import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DN12 {
    public static void main(String[] args) throws IOException {
        String[] poDelih = args[0].split("\\.");
        String tipDatoteke = poDelih[poDelih.length-1];
        if (tipDatoteke.equals("png")) {
            BufferedImage bimg = ImageIO.read(new File(args[0]));
            int width = bimg.getWidth();
            int height = bimg.getHeight();
            System.out.println(width+" x "+ height);
        }
        else{
            System.out.println("?");
        }


    }
}
