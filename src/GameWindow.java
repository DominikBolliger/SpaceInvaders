import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {

    public GameWindow(){
        initUI();
    }

    private void initUI() {
        setTitle("GameWindow");
        setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        add(new Board());
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            GameWindow game = new GameWindow();
            game.setVisible(true);
        });
    }

}


