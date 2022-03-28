import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;

public class Player extends Sprite{

    private int width;

    public Player(){
        initPlayer();
    }

    private void initPlayer() {

        String playerImg = "images/player.png";
        ImageIcon ii = new ImageIcon(playerImg);

        width = ii.getImage().getWidth(null);
        setImage(ii.getImage());

        int START_X = 270;
        setX(START_X);

        int START_Y = 280;
        setY(START_Y);
    }

    public void act() {

        x += dx;
        y += dy;

        if (x <= 2) {
            x = 2;
        }

        if (x >= Commons.BOARD_WIDTH - 2 * width) {
            x = Commons.BOARD_WIDTH - 2 * width;
        }
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -Commons.PLAYER_SPEED;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = Commons.PLAYER_SPEED;
        }

        if (key == KeyEvent.VK_UP) {
            dx = -Commons.PLAYER_SPEED;
        }

        if (key == KeyEvent.VK_DOWN) {
            dx = Commons.PLAYER_SPEED;
        }

    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }
}
