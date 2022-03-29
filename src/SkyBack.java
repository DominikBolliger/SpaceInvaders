import javax.swing.*;

public class SkyBack extends Sprite{

    public SkyBack(int x, int y){
        initSky();
        this.x = x;
        this.y = y;
    }

    private void initSky() {
        String playerImg = "images/SkyBack/skyback2.jpg";
        ImageIcon ii = new ImageIcon(playerImg);

        setImage(ii.getImage());
        setX(x);
        setY(y);
        System.out.println(x + " " + y);
    }

    public void moveSky(int pluSpeed){

        y += 2 - pluSpeed;

        if (y >= 600){
            y = -600;
        }
    }
}
