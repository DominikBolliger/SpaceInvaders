import javax.swing.*;

public class Shot extends Sprite{

    public Shot(int x, int y) {
        initShot();
    }

    private void initShot() {

        String shotImage = "images/bullet.png";
        ImageIcon ii = new ImageIcon(shotImage);

        setImage(ii.getImage());

        int H_SPACE = 6;
        setX(x + H_SPACE);

        int V_SPACE = 1;
        setY(y - V_SPACE);

        dy = 2;
    }

    public void moveShot() {
        y -= dy;

        if (y <= 0) {
            this.die();
            this.setDying(true);
        }
    }
}
