import javax.swing.*;

public class MainGame {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        jFrame.setTitle("Brick Breaker");
        jFrame.setBounds(10, 10, 700, 600);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Gameplay gameplay = new Gameplay();
        jFrame.add(gameplay);
    }
}
