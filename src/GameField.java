import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class GameField extends JPanel implements ActionListener {
    private final int SIZE = 500;
    private final int DOT_SIZE = 16;
    private final int ALL_DOTS = 400;
    private Image body;
    private Image dick;
    private Image headClose;
    private Image headOpen;
    private Image cage;
    private int dickX;
    private int dickY;
    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];
    private int dots;
    private Timer timer;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;
    private boolean mouthClose = true;
    private int framesCount = 0;
    ArrayList<String> endGamePic = new ArrayList<>();
    public String s ="";
    private int score = 0;

    public GameField() {
        setBackground(Color.BLACK);
        loadImage();
        initGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);


    }

    public void initGame() {
        dots = 3;
        for (int i = 0; i < dots; i++) {
            x[i] = 96 - i * DOT_SIZE;
            y[i] = 96;
        }
        timer = new Timer(250, this);
        timer.start();
        createDick();
    }

    private void createDick() {
        dickX = new Random().nextInt(2, 15) * DOT_SIZE;
        dickY = new Random().nextInt(2, 15) * DOT_SIZE;
    }

    public void loadImage() {
        ImageIcon iia = new ImageIcon("dick.png");
        dick = iia.getImage();
        ImageIcon iih = new ImageIcon("pupin.png");
        headClose = iih.getImage();
        ImageIcon iib = new ImageIcon("body.png");
        body = iib.getImage();
        ImageIcon iibc = new ImageIcon("pupinOpen.png");
        headOpen = iibc.getImage();
        ImageIcon c = new ImageIcon("cage.png");
        cage = c.getImage();
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        if (inGame) {
            g.drawImage(dick, dickX, dickY, this);

            for (int i = 0; i < 1; i++) {
                if (mouthClose) {
                    g.drawImage(headOpen, x[i], y[i], this);
                    mouthClose = false;
                } else {
                    g.drawImage(headClose, x[i], y[i], this);
                    mouthClose = true;
                }
            }
            for (int i = 2; i < dots; i++) {
                g.drawImage(body, x[i], y[i], this);
            }
            String str = String.valueOf(score);
            g.setColor(Color.WHITE);
            g.drawString(str, 450, 50);

            for (int i = 0; i < 500; i += 32) {
                g.drawImage(cage, i, 0, this);
                g.drawImage(cage, i, 500, this);
            }
            for (int i = 32; i < 500; i += 32) {
                g.drawImage(cage, 500, i, this);
                g.drawImage(cage, 0, i, this);
            }
        } else {
            EndGame();
            g.drawImage(new ImageIcon(s).getImage(), 150, 150, null);
        }
    }


    public void move() {
        for (int i = dots; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        if (left) {
            x[0] -= DOT_SIZE;
        }
        if (right) {
            x[0] += DOT_SIZE;
        }
        if (up) {
            y[0] -= DOT_SIZE;
        }
        if (down) {
            y[0] += DOT_SIZE;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkApple();
            checkCollisions();
            move();
        }
        repaint();

    }

    private void checkCollisions() {
        for (int i = dots; i > 0; i--) {
            if (i > 4 && x[0] == x[i] && y[0] == y[i])
                inGame = false;
        }
        if (x[0] > SIZE) {
            inGame = false;
        }
        if (x[0] < 0) {
            inGame = false;
        }
        if (y[0] > SIZE) {
            inGame = false;
        }
        if (y[0] < 0) {
            inGame = false;
        }
    }

    private void checkApple() {
        if (x[0] == dickX && y[0] == dickY) {
            dots++;
            createDick();
            score = score + 100;
        }
    }

    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT && !right) {
                left = true;
                up = false;
                down = false;

            }
            if (key == KeyEvent.VK_RIGHT && !left) {
                right = true;
                up = false;
                down = false;

            }
            if (key == KeyEvent.VK_UP && !down) {
                right = false;
                up = true;
                left = false;

            }
            if (key == KeyEvent.VK_DOWN && !up) {
                right = false;
                down = true;
                left = false;

            }

        }
    }

    public void EndGame() {
        endGamePic.add("pupinEndGame1.png");
        endGamePic.add("pupinEndGame2.png");
        endGamePic.add("pupinEndGame3.png");
        timer2.start();

        Button restart = new Button("RESTART");
        add(restart);
        restart.setVisible(true);
        restart.setBounds(137, 450, 250, 40);
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == restart) {
                    s = "restart";
                    inGame = true;

                }
            }
        });
    }

    Timer timer2 = new Timer(200, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            framesCount++;
            s = endGamePic.get(framesCount);
            if (framesCount >= endGamePic.size() - 1) {
                framesCount = 0;
            }
        }
    });

}



