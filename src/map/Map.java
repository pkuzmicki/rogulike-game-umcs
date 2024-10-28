package map;

import main.GamePanel;
import objects.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Map {
    GamePanel gamePanel;
    public Map(GamePanel gamePanel){
        this.gamePanel = gamePanel;

        loadImage();
    }

    int maxWorldCol = 7;
    int maxWorldRow = 7;

    Tile[][] tileMap;
    Entity[][] entityMap;

    public BufferedImage tile;

    final int tileWidth = 225;
    final int tileHeight = 75;


    public void loadImage() {
        try {
            tile = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tile02.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void createPoints(){
        int tileXPosition = gamePanel.screenWidth / 2 - tileWidth/2;
        int tileYPosition = 50;
        int startX = tileXPosition;
        int startY = tileYPosition;

        for (int cols = 0; cols < maxWorldCol; cols++) {
            for (int rows = 0; rows < maxWorldRow; rows++) {
                tileXPosition += tileWidth/2;
                tileYPosition += tileHeight/2;
                tileMap[rows][cols] = new Tile(tileXPosition, tileYPosition);
                System.out.println(tileMap[rows][cols]);
            }
            startX -= tileWidth/2;
            startY += tileHeight/2;

            tileXPosition = startX;
            tileYPosition = startY;

        }
    }

    public void draw(Graphics2D g2){
        int tileXPosition = gamePanel.screenWidth / 2 - tileWidth/2;
        int tileYPosition = 50;
        int startX = tileXPosition;
        int startY = tileYPosition;

        for (int cols = 0; cols < maxWorldCol; cols++) {
            for (int rows = 0; rows < maxWorldRow; rows++) {
                g2.drawImage(tile, tileXPosition, tileYPosition, null);
                tileXPosition += tileWidth/2;
                tileYPosition += tileHeight/2;
            }
            startX -= tileWidth/2;
            startY += tileHeight/2;

            tileXPosition = startX;
            tileYPosition = startY;
        }
        //drawCoordinates(g2);
    }

    public void drawCoordinates(Graphics2D g2){
        int tileXPosition = gamePanel.screenWidth / 2 - tileWidth/2;
        int tileYPosition = 50;
        int startX = tileXPosition;
        int startY = tileYPosition;

        char x = 'A';
        int y = 1;
        char startCordX = x;
        int startCordY = y;

        g2.setColor(Color.BLACK);

        for (int cols = 0; cols < maxWorldCol; cols++) {
            for (int rows = 0; rows < maxWorldRow; rows++) {
                tileXPosition += tileWidth/2;
                tileYPosition += tileHeight/2;

                g2.drawString(String.valueOf(x) + y, tileXPosition, tileYPosition);
                x++;
                y++;
            }
            x = startCordX;
            startCordY += 1;
            y = startCordY;

            startX -= tileWidth/2;
            startY += tileHeight/2;

            tileXPosition = startX;
            tileYPosition = startY;
        }
    }



}
