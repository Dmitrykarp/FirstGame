import javax.swing.*;

/**
 * Created by DmitryKarp on 20.03.2016.
 */
public class Game {
    public static void main(String[] args) {
        JFrame window = new JFrame("Platformer");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(new GamePanel());
        window.pack();
        window.setVisible(true);
    }
}
