package objects;

import main.GamePanel;
import map.Map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player{
    BufferedImage sprite;
    public int playerX;
    public int playerY;

    int row = 0, col = 0;

    GamePanel gamePanel;
    public Player(GamePanel gamePanel){
        this.gamePanel = gamePanel;

        playerX = gamePanel.map.objectsMap[row][col].x;
        playerY = gamePanel.map.objectsMap[row][col].y;
        loadImage();
    }



    public void loadImage(){
        try {
            sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/keeper.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void draw(Graphics2D g2){
        playerX -= 10;
        playerY -= 45;
//        playerX -= sprite.getWidth(null)/4;
//        playerY -= sprite.getHeight(null)/2;
        g2.drawImage(sprite, playerX, playerY, null);

    }
}
