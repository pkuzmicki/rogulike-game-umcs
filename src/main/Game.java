package main;

import javax.swing.*;
import java.awt.*;

public class Game {
    public static JFrame window;

    public static void main(String[] args) {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //window.setResizable(false);
        window.setTitle("DoD");

        GamePanel gp = new GamePanel();
        window.add(gp);
        window.pack();

        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //window.setSize(screenSize.width, screenSize.height);
        //gp.setFullScreen();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }
}
