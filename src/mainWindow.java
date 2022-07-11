import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainWindow extends JFrame {
    public static mainWindow gf;

    public mainWindow() {
        setTitle("Snake");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(550, 570);

        setLocation(500, 500);
        GameField g = new GameField();
        add(g);

        setVisible(true);


    }

    public static void main(String[] args) {
        gf = new mainWindow();

    }
}

