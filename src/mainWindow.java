import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainWindow extends JFrame {
    public static mainWindow mw;
    public int i = 0;

    public mainWindow() {
        setTitle("Snake");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(550, 570);
        setLocation(500, 500);

        GameField g = new GameField();
        add(g);
        setVisible(true);
        setLocationRelativeTo(null);    }

    public static void main(String[] args) {
        mw = new mainWindow();


    }
}

