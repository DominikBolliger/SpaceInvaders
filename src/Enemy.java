import javax.swing.*;

public class Enemy extends Sprite{


    public Enemy(){
        initEnemy();
    }

    private void initEnemy() {

        String playerImg = "images/Enemy/enemy.png";
        ImageIcon ii = new ImageIcon(playerImg);

        setImage(ii.getImage());

        int START_X = 0;
        setX(START_X);

        int START_Y = 0;
        setY(START_Y);

    }
}
