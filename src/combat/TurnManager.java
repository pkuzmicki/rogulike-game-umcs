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

    public void draw(Graphics2D g2) {
        g2.setFont(new Font("COMIC SANS", Font.PLAIN, 24));
        if (globalTurn%2 == 0) {
            g2.setColor(Color.CYAN);
        } else {
            g2.setColor(Color.RED);
        }
        g2.drawString(String.valueOf(globalTurn), 10, 25);
    }
}