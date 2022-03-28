import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel {

    private Timer timer;
    private Player player;
    private boolean inGame = true;
    private Shot shot;

    public Board(){
        initBoard();
        gameInit();
    }

    private void gameInit() {
        player = new Player();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.black);
        timer = new Timer(Commons.DELAY, new GameCycle());
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        drawPlayer(g);
    }

    private void drawPlayer(Graphics g) {

        if (player.isVisible()) {
            System.out.println(player.getX() + " " + player.getY());
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
        player.act();
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

//                    if (!shot.isVisible()) {
//
//                        shot = new Shot(x, y);
//                    }
                }
            }
        }
    }

}
