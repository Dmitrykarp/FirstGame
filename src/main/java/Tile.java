import java.awt.image.BufferedImage;

/**
 * Created by Support on 20.03.2016.
 */
public class Tile {

    private BufferedImage image;
    private boolean blocked;

    public Tile(BufferedImage image, boolean blocked){
        this.image = image;
        this.blocked = blocked;
    }

    public BufferedImage getImage(){ return image;}
    public boolean isBlocked(){return blocked;}

}
