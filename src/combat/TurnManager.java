package combat;

import main.GamePanel;

public class TurnManager {
    GamePanel gamePanel;
    public int globalTurn = 0;

    public TurnManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    void pause() {
        int i = 0;
        while(i < 120) {
            i++;
        }
        i = 0;
        System.out.println("a");
    }
}
