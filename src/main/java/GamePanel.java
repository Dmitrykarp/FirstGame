import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

/**
 * Created by DmitryKarp on 20.03.2016.
 */
public class GamePanel extends Container implements  Runnable, KeyListener{

    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;

    private Thread thread;
    private boolean running;

    private BufferedImage image;
    private Graphics2D g;

    private int FPS=30;
    private int targetTime = 1000 / FPS;

    private  TileMap tileMap;
    private Player player;

    public GamePanel(){
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify(){
        super.addNotify();
        if(thread == null){
            thread = new Thread(this);
            thread.start();
        }
        addKeyListener(this);
    }

    public void run(){
        init();

        long startTime;
        long urdTime;
        long waitTime;


        while (running){
            startTime = System.nanoTime();

            update();
            render();
            draw();

            urdTime = (System.nanoTime() - startTime)/ 1000000;
            waitTime = targetTime - urdTime;

            try{
                Thread.sleep(waitTime);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void init(){
        running = true;

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();

        tileMap = new TileMap("src/main/resources/map2.txt", 32);
        tileMap.loadTiles("src/main/resources/Graphics/tileset.gif");
        player = new Player(tileMap);
        player.setx(50);
        player.sety(50);
    }

    /////////////////////////////////////////////////////////////////////////////////////

    private void update(){
        tileMap.update();
        player.update();
    }

    private void render(){

        g.setColor(Color.BLACK);
        g.fillRect(0,0,WIDTH,HEIGHT);

        tileMap.draw(g);
        player.draw(g);

    }

    private void draw(){
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
    }

    public void keyTyped(KeyEvent key){}
    public void keyPressed(KeyEvent key){

        int code = key.getKeyCode();
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A){
            player.setLeft(true);
        }
        if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D){
            player.setRight(true);
        }
        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_SPACE){
            player.setJumping(true);
        }

        if (code == KeyEvent.VK_1){
            player.setPerson(1);
        }
        if (code == KeyEvent.VK_2){
            player.setPerson(2);
        }


    }
    public void keyReleased(KeyEvent key){

        int code = key.getKeyCode();
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A){
            player.setLeft(false);
        }
        if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D){
            player.setRight(false);
        }
        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_SPACE){
            player.setJumping(false);
        }
    }
}
