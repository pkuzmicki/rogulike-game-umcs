package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gamePanel;

    public KeyHandler(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            gamePanel.player.move(0, -1);
        }
        if (key == KeyEvent.VK_DOWN) {
            gamePanel.player.move(0 ,1);
        }
        if (key == KeyEvent.VK_LEFT) {
            gamePanel.player.move(-1, 0);
        }
        if (key == KeyEvent.VK_RIGHT) {
            gamePanel.player.move(1, 0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
