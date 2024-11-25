//passphrase: kamyk
package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends OBJ {
    GamePanel gamePanel;
    BufferedImage playerImage;
    final int playerScreenX;
    final int playerScreenY;
    public int playerX;
    public int playerY;
    public int playerCol;
    public int playerRow;

    public int playerTurn;
    public int playerHitpoints;


    public Player(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        playerCol = 0;
        playerRow = 0;
        loadImage();
        setPositions();
        playerScreenX = playerX;
        playerScreenY = playerY;
    }

    void setPositions() {
        playerX = gamePanel.map.tileMap[playerCol][playerRow].x + playerImage.getWidth() / 2;
        playerY = gamePanel.map.tileMap[playerCol][playerRow].y - 65;
        //System.out.println(gamePanel.map.tileMap[playerCol][playerRow].x + " " + gamePanel.map.tileMap[playerCol][playerRow].y);
        //System.out.println(playerX  + " " + playerY);
    }

    public void loadImage(){
        try {
            playerImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/crazy_wizard.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void move(int colMove, int rowMove){
        int x1 = gamePanel.map.tileMap[playerCol][playerRow].x;
        int y1 = gamePanel.map.tileMap[playerCol][playerRow].y;

        playerCol += colMove;
        playerRow += rowMove;
        setPositions();

        int x2 = gamePanel.map.tileMap[playerCol][playerRow].x;
        int y2 = gamePanel.map.tileMap[playerCol][playerRow].y;

        gamePanel.map.dx = x1 - x2;
        gamePanel.map.dy = y1 - y2;
        //System.out.println(gamePanel.map.dx + " " + gamePanel.map.dy);


        gamePanel.repaint();
    }


    @Override
    public int getY() {
        return this.playerY;
    }

    public void draw(Graphics2D g2){
        g2.drawImage(playerImage, playerX, playerY, null);
        //g2.drawImage(playerImage, playerX, playerY, (int)(playerImage.getWidth()*1.3), (int)(playerImage.getHeight()*1.3), null);

    }
}
