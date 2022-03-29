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
    private boolean inGame = true;
    private ArrayList<Shot> shots;
    private ArrayList<Sky> stars;
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
            Sky star = new Sky();
            stars.add(star);
        }

        //Create shots List
        shots = new ArrayList<>();
        for (int i = 0; i < Commons.AMOUNT_OF_BULLETS; i++) {
            Shot bullet = new Shot(0, 0);
            shots.add(bullet);
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
        //drawSky(g);
        drawShot(g);
        drawPlayer(g);
    }

    private void drawShot(Graphics g) {
        for(Shot bullet:shots){
            if (bullet.isVisible()){
                g.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), this);
            }
        }
    }

    private void drawSky(Graphics g){
        for (Sky star:stars) {
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
        rnd = new Random();
        for (Sky star:stars) {
            star.moveSky();
            if (star.getY() >= Commons.BOARD_HEIGHT){
                star.setY(-10);
                star.setDx(rnd.nextInt(4 + 2) -2 );
                star.setDy(rnd.nextInt(4 + 1 -2) + 2);
            } else if (star.getX() < 0 || star.getX() > Commons.BOARD_WIDTH){
                star.setY(-15);
                star.setX(rnd.nextInt(Commons.BOARD_WIDTH));
                star.setDx(rnd.nextInt(4 + 2) -2 );
                star.setDy(rnd.nextInt(4 + 1 -2) + 2);
            }
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
