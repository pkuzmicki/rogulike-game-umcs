//passphrase: kamyk
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
    public int playerCol;
    public int playerRow;


    GamePanel gamePanel;
    public Player(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        playerCol = 0;
        playerRow = 0;
        loadImage();
        setPositions();
    }

    void setPositions() {
        playerX = gamePanel.map.tileMap[playerCol][playerRow].x;
        playerY = gamePanel.map.tileMap[playerCol][playerRow].y;
    }

    public void loadImage(){
        try {
            playerImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/keeper.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void move(int colMove, int rowMove){
        playerCol += colMove;
        playerRow += rowMove;
        setPositions();
        gamePanel.repaint();
    }

    public void draw(Graphics2D g2){
//        playerX += playerImage.getWidth() / 2 + (int)(playerImage.getWidth() * 0.6);
//        playerY -= playerImage.getHeight() / 2 + (int)(playerImage.getHeight() * 0.6);
        playerY -= playerImage.getHeight();

        g2.drawImage(playerImage, playerX, playerY, null);
    }
}
