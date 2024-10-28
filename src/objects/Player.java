package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    BufferedImage sprite;
    int playerX;
    int playerY;

    GamePanel gamePanel;
    public Player(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        loadImage();
    }

    public void setPlayerPosition(){
        playerX = gamePanel.screenWidth/2 - 20;
        playerY = gamePanel.screenHeight/2;
    }

    public void loadImage(){
        try {
            sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/keeper.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2){
        g2.drawImage(sprite, playerX, playerY, null);
    }
}
