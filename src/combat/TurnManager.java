package combat;

import main.GamePanel;

import java.awt.*;
import java.util.Timer;

public class TurnManager {
    GamePanel gamePanel;
    public int globalTurn = 0;

    public TurnManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void turnCounter() {
        if (gamePanel.keyHandler.wPressed || gamePanel.keyHandler.aPressed || gamePanel.keyHandler.sPressed || gamePanel.keyHandler.dPressed) {
            globalTurn++;
        }
    }

    public void pause() {
        Timer timer = new Timer();
        try {
            timer.wait(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2) {
        g2.setFont(new Font("COMIC SANS", Font.PLAIN, 24));
        g2.setColor(Color.white);
        g2.drawString(String.valueOf(globalTurn), 10, 25);
    }
}