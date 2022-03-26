import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import javax.swing.*;
import java.awt.*;

public class Gameplay extends JPanel implements ActionListener, KeyListener {

    boolean gameStart = false;
    Timer timer;
    int delay = 6;
    int score = 0;

    int playerStartPosition = 310;
    int ballStartXPosition = 120;
    int getBallStartYPosition = 350;
    int ballXDirection = -1;
    int ballYDirection = -2;

    public Gameplay() {
        addKeyListener(this);
        setFocusTraversalKeysEnabled(false);
        setFocusable(true);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint (Graphics g) {
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);

        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3  , 592);

        g.setColor(Color.green);
        g.fillRect(playerStartPosition, 550, 100, 8);

        g.setColor(Color.yellow);
        g.fillRect(ballStartXPosition, ballYDirection, 20, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
