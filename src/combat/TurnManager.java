package combat;

import main.GamePanel;

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
}