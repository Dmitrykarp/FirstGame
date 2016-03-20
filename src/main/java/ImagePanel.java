import javax.swing.*;
import java.awt.*;

class ImagePanel extends JPanel {

    private Image img;

    public ImagePanel() {
        this.img = new ImageIcon("src/main/resources/Graphics/Background.png").getImage();
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
}