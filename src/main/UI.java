package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class UI {
    GamePanel gp;
    BufferedImage deathScreen;
    public boolean isGameOver = false;

    public UI(GamePanel gamePanel) {
        this.gp = gamePanel;
        loadImage();
    }

    public void loadImage() {
        try {
            deathScreen = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/sprites/ui/YOU_DIED.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(deathScreen, 0, 0 , null);
    }
}
