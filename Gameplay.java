import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import javax.swing.*;

public class Gameplay extends JPanel implements ActionListener, KeyListener {

    boolean gameStart = false;
    int totalBricks = 21;
    Timer timer;
    int delay = 6;
    int score = 0;

    int playerXPosition = 310;
    int ballStartXPosition = 120;
    int ballStartYPosition = 350;
    int ballXDirection = -1;
    int ballYDirection = -2;

    MapGenerator mapGenerator;

    public Gameplay() {
        mapGenerator = new MapGenerator(3, 7);
        addKeyListener(this);
        setFocusTraversalKeysEnabled(false);
        setFocusable(true);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint (Graphics g) {
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);


        mapGenerator.draw((Graphics2D)g);
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3  , 592);

        g.setColor(Color.white);
        g.setFont(new Font("serif",Font.BOLD, 25));
        g.drawString("" + score, 590, 30);

        g.setColor(Color.green);
        g.fillRect(playerXPosition, 550, 100, 8);

        g.setColor(Color.yellow);
        g.fillOval(ballStartXPosition, ballStartYPosition, 20, 20);

        if (totalBricks <= 0) {
            gameStart = false;
            ballXDirection = 0;
            ballYDirection = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif",Font.BOLD, 30));
            g.drawString("You Won:" + score, 260, 300);
            g.setFont(new Font("serif",Font.BOLD, 20));
            g.drawString("Press Enter to Restart:", 230, 350);
        }
        if (ballStartYPosition > 570) {
            gameStart = false;
            ballXDirection = 0;
            ballYDirection = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif",Font.BOLD, 30));
            g.drawString("Game Over, Scores:" + score, 190, 300);
            g.setFont(new Font("serif",Font.BOLD, 20));
            g.drawString("Press Enter to Restart:", 230, 350);
        }
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (gameStart) {
            if (new Rectangle(ballStartXPosition, ballStartYPosition, 20, 20).intersects(new Rectangle(playerXPosition, 550, 100, 8))) {
                ballYDirection = -ballYDirection;
            }
            A: for (int i = 0; i < mapGenerator.map.length; i++) {
                for (int j = 0; j < mapGenerator.map[0].length; j++) {
                    if(mapGenerator.map[i][j] > 0){
                        int brickX = j * mapGenerator.brickWidth + 80;
                        int brickY = j * mapGenerator.brickHeight + 50;
                        int brickWidth = mapGenerator.brickWidth;
                        int brickHeight = mapGenerator.brickHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballStartXPosition, ballStartYPosition,20,20);

                        if (ballRect.intersects(rect)) {
                            mapGenerator.setBrickValue(0, i, j);
                            totalBricks--;
                            score += 5;

                            if (ballStartXPosition + 19 <= rect.x || ballStartXPosition + 1 >= rect.x + rect.y) {
                                ballXDirection = -ballXDirection;
                            } else {
                                ballYDirection = -ballYDirection;
                            }
                            break A;
                        }
                    }
                }
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
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!gameStart) {
                gameStart = true;
                ballStartYPosition = 120;
                ballStartXPosition = 350;
                ballXDirection = -1;
                ballYDirection = -2;
                playerXPosition = 310;
                score = 0;
                totalBricks = 21;
                mapGenerator = new MapGenerator(3, 7);
                repaint();
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
