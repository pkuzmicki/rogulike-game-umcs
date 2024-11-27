package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {
    GamePanel gamePanel;
    public boolean pressed;
    public int x;
    public int y;

    MouseHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int button = e.getButton();
        if (button == MouseEvent.BUTTON1) {
            pressed = true;
            x = e.getX();
            y = e.getY();
            //System.out.println(x + " " + y);
        }
    }
}
