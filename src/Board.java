import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class Board extends JPanel {

    private Timer timer;
    private Player player;
    private SkyBack sky;
    private boolean inGame = true;
    private ArrayList<Shot> shots;
    private ArrayList<Stars> stars;
    private ArrayList<Enemy> enemies;
    private ArrayList<SkyBack> skybacks;
    private Random rnd;

    public Board(){
        initBoard();
        gameInit();
    }

    private void gameInit() {

        //Create Player
        player = new Player();

        //Ceate stars List
        stars = new ArrayList<>();
        for (int i = 0; i < Commons.AMOUNT_OF_STARS; i++) {
            stars.add(new Stars());
        }

        //Create shots List
        shots = new ArrayList<>();
        for (int i = 0; i < Commons.AMOUNT_OF_BULLETS; i++) {
            shots.add(new Shot(0, 0));
        }
        
        //Create Enemies
        enemies = new ArrayList<>();
        for (int i = 0; i < Commons.AMMOUNT_OF_ENEMIES; i++) {
            enemies.add(new Enemy());
        }

        //Create Sky
        skybacks = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            skybacks.add(new SkyBack(0, i * Commons.BOARD_HEIGHT));
        }
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Commons.BOARD_BACKGROUND);
        timer = new Timer(Commons.DELAY, new GameCycle());
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        drawSkyBack(g);
        //drawSky(g);
        drawShot(g);
        drawPlayer(g);
        drawEnemie(g);
    }

    private void drawShot(Graphics g) {
        for(Shot bullet:shots){
            if (bullet.isVisible()){
                g.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), this);
            }
        }
    }

    private void drawSky(Graphics g){
        for (Stars star:stars) {
            g.drawImage(star.getImage(), star.getX(), star.getY(), this);
        }
    }

    private void drawPlayer(Graphics g) {

        if (player.isVisible()) {
            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }

        if (player.isDying()) {
            player.die();
            inGame = false;
        }
    }

    private void drawSkyBack(Graphics g){
        for(SkyBack sky:skybacks){
            if (sky.isVisible()) {
                g.drawImage(sky.getImage(), sky.getX(), sky.getY(), this);
            }
        }
    }
    
    private void drawEnemie(Graphics g){
        for (Enemy enemy:enemies){
            if (enemy.isVisible()) {
                g.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), this);
            }   
        }
    }

    public class GameCycle implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            doGameCycle();
        }
    }

    private void update(){

        //Player
        player.act();

        //Sky
        for(SkyBack sky:skybacks){
            sky.moveSky(player.getDy());
        }

        //Stars
        rnd = new Random();
        for (Stars star:stars) {
            star.moveStars(player.getX() + 24, player.getY() + 30);
        }

        //Shot
        for (Shot bullet:shots){
            bullet.moveShot();
        }
    }

    private void doGameCycle() {
        update();
        repaint();
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {

            player.keyPressed(e);

            int x = player.getX();
            int y = player.getY();

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {
                if (inGame) {
                    for(Shot bullet:shots){
                        if (!bullet.isVisible() ) {
                            bullet.setX(x + 24);
                            bullet.setY(y - 15);
                            bullet.setVisible(true);
                            break;
                        }
                    }
                }
            }
        }
    }
}
