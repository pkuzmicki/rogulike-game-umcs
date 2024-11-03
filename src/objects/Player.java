package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player{
    BufferedImage playerImage;
    public int playerX;
    public int playerY;

    int row = 0, col = 0;

    GamePanel gamePanel;
    public Player(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        //loadImage();
//        playerX = gamePanel.map.objectsMap[row][col].x;
//        playerY = gamePanel.map.objectsMap[row][col].y;
//        playerX = gamePanel.screenWidth / 2 - playerImage.getWidth() / 2;
//        playerY = gamePanel.screenHeight / 2 - playerImage.getHeight() / 2;

    }

    public void loadImage(){
        try {
            playerImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/keeper.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void move(int dx, int dy){
        gamePanel.map.mapStartX += dx;
        gamePanel.map.mapStartY += dy;
        gamePanel.repaint();
    }

    public void draw(Graphics2D g2){
//        playerX -= 10;
//        playerY -= 45;
//        playerX -= sprite.getWidth(null)/4;
//        playerY -= sprite.getHeight(null)/2;
        //g2.drawImage(playerImage, playerX, playerY, null);

    }
}
