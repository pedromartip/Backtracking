
package knighttour;

/**
 *
 * @author Tomeu
 */

import java.awt.Graphics;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;

public class Piece {

    public static final String CAVALL = "peces/cavallNegre.png";

    private BufferedImage img;

    public Piece(String s) { 
        try {
            img = ImageIO.read(new File(s)); //llegeix el fitxer amb la imatge
        } catch (IOException e) {}
    }

    public void paintComponent(Graphics g, float x, float y) { //pinta la imatge a la posició indicada
        g.drawImage(img,(int) x + 10, (int) y + 10, null);
    }
}
