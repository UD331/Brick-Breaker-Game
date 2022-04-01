import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import javax.swing.*;

public class Gameplay extends JPanel implements ActionListener, KeyListener {

    boolean gameStart = false;
    Timer timer;
    int delay = 6;
    int score = 0;

    int playerXPosition = 310;
    int ballStartXPosition = 120;
    int ballStartYPosition = 350;
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
        g.fillRect(playerXPosition, 550, 100, 8);

        g.setColor(Color.yellow);
        g.fillOval(ballStartXPosition, ballYDirection, 20, 20);

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (gameStart) {
            if (new Rectangle(ballStartXPosition, ballStartYPosition, 20, 20).intersects(new Rectangle(playerXPosition, 550, 100, 8))) {
                ballYDirection = -ballYDirection;
            }
            ballStartXPosition += ballXDirection;
            ballStartYPosition += ballStartYPosition;
            if (ballStartXPosition < 0) {
                ballXDirection = -ballXDirection;
            }
            if (ballStartYPosition < 0) {
                ballYDirection = -ballYDirection;
            }
            if (ballStartXPosition > 670) {
                ballXDirection = -ballXDirection;
            }
        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerXPosition >= 600) {
                playerXPosition = 600;
            } else {
                moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerXPosition < 10) {
                playerXPosition = 10;
            } else {
                moveLeft();
            }
        }
    }

    public void moveRight() {
        gameStart = true;
        playerXPosition += 20;
    }

    public void moveLeft() {
        gameStart = true;
        playerXPosition -= 20;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
