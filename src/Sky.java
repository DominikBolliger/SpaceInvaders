import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Sky extends Sprite{

    private Random rnd;

    public Sky(){
        initSky();
    }

    private void initSky() {

        String shotImage = "";
        Random rnd = new Random();
        int color = rnd.nextInt(7);

        switch (color){
            case 1:
                shotImage = "images/Sky/starB.png";
                break;
            case 2:
                shotImage = "images/Sky/starG.png";
                break;
            case 3:
                shotImage = "images/Sky/starO.png";
                break;
            case 4:
                shotImage = "images/Sky/starP.png";
                break;
            case 5:
                shotImage = "images/Sky/starR.png";
                break;
            case 6:
                shotImage = "images/Sky/starY.png";
                break;
        }



        rnd = new Random();
        int scale = rnd.nextInt(20 + 1 - 10) + 10;
        ImageIcon ii = new ImageIcon(shotImage);
        setImage(ii.getImage().getScaledInstance(scale, scale, Image.SCALE_SMOOTH));

        int START_X = rnd.nextInt(Commons.BOARD_WIDTH);
        setX(START_X);

        int START_Y = rnd.nextInt(-10 + 1 + 140) -140;
        setY(START_Y);

        setDx(rnd.nextInt(4 + 2) -2 );
        setDy(rnd.nextInt(4 + 1 -2) + 2);

    }

    public void moveSky(){
        x += dx;
        y += dy;
    }

}
