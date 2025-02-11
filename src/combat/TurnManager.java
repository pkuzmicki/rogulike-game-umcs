package combat;

import main.GamePanel;

import java.awt.*;

public class TurnManager {
    GamePanel gp;
    public int globalTurn = 0;

    public TurnManager(GamePanel gamePanel) {
        this.gp = gamePanel;
    }

    public void updateTurn() {
        if (gp.keyHandler.wPressed || gp.keyHandler.sPressed || gp.keyHandler.aPressed || gp.keyHandler.dPressed || gp.keyHandler.upPressed || gp.keyHandler.downPressed || gp.keyHandler.leftPressed || gp.keyHandler.rightPressed) {
                globalTurn++;
        }
    }

    public void draw(Graphics2D g2) {
        g2.setFont(new Font("COMIC SANS", Font.PLAIN, 24));
        g2.setColor(Color.WHITE);
        g2.setFont(gp.ui.wizardFont);
        FontMetrics metrics = g2.getFontMetrics(gp.ui.wizardFont);
        g2.drawString(String.valueOf(globalTurn), gp.screenWidth / 2 - metrics.stringWidth(String.valueOf(globalTurn)) / 2, gp.screenHeight - 90);
    }
}