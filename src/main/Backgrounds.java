package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Backgrounds {
    GamePanel gp;

    BufferedImage background;
    BufferedImage background2;

    public Backgrounds(GamePanel gamePanel) {
        this.gp = gamePanel;
        background = gp.getImage("/sprites/ui/background.png");
        background2 = gp.getImage("/sprites/ui/background2.png");
    }


    public void draw(Graphics2D g2) {
        if (gp.player.isInHideout) {
            g2.drawImage(background2, -200, -100,  null);
        } else {
            g2.drawImage(background, -200, -100,2000, 1000, null);
        }

    }
}
