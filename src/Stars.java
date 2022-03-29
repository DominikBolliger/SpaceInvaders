import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Stars extends Sprite{

    private Random rnd;

    public Stars(){
        initStars();
    }

    private void initStars() {

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


        setDx(rnd.nextInt(4 + 2) -2 );
        setDy(rnd.nextInt(4 + 1 -2) + 2);

    }

    public void moveStars(int newX, int newY){
        x += dx;
        y += dy;

        System.out.println(x + " " + y);

        if (getY() >= Commons.BOARD_HEIGHT){
            setY(newY);
            setX(newX);
        } else if (getX() < 0 || getX() > Commons.BOARD_WIDTH){
            setY(newY);
            setX(newX);
        }

    }

}
