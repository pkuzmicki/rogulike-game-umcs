package map;

import main.GamePanel;
import objects.Entity;
import objects.Item;
import objects.OBJ;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Map {
    final int tileWidth = 151;
    final int tileHeight = 51;

    final int entityWidth =  51;
    final int entityHeight = 101;
    int maxWorldCol = 6;
    int maxWorldRow = 6;

    public Tile[][] tileMap;
    public OBJ[][] objectsMap;

    public int x, y;

    GamePanel gamePanel;
    public Map(GamePanel gamePanel){
        this.gamePanel = gamePanel;

        tileMap = new Tile[maxWorldRow][maxWorldCol];
        objectsMap = new OBJ[maxWorldRow][maxWorldCol];

        x = gamePanel.screenWidth / 2 - tileWidth/2;
        y = 75;

        loadImage();
        createRoom(x, y, maxWorldRow, maxWorldCol);
    }


    public BufferedImage tile;
    public BufferedImage tileHigh;




    public void loadImage() {
        try {
            tile = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/tiles/tile01.png")));
            tileHigh = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/tiles/tile01H.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void createRoom(int minX, int minY, int maxX, int maxY){
        int tileXPosition = minX;
        int tileYPosition = minY;
        int startX = tileXPosition;
        int startY = tileYPosition;

        for (int cols = 0; cols < maxY; cols++) {
            for (int rows = 0; rows < maxX; rows++) {

                tileXPosition += tileWidth/2;
                tileYPosition += tileHeight/2;

                tileMap[rows][cols] = new Tile(tileXPosition, tileYPosition);
                objectsMap[rows][cols] = new OBJ(tileXPosition, tileYPosition);

                //System.out.println("Tile: " + rows + cols + ' ' + tileMap[rows][cols].row + ' ' + tileMap[rows][cols].col);
            }
            startX -= tileWidth/2;
            startY += tileHeight/2;

            tileXPosition = startX;
            tileYPosition = startY;

        }
    }


    public void draw(Graphics2D g2){
        for (int cols = 0; cols < maxWorldCol; cols++) {
            for (int rows = 0; rows < maxWorldRow; rows++) {
                g2.drawImage(tile, tileMap[rows][cols].row - tileWidth / 2, tileMap[rows][cols].col - tileHeight / 2, null);
            }
        }

        drawCoordinates(g2);
        g2.drawImage(tileHigh, 470, 150, null);
    }

    public void drawCoordinates(Graphics2D g2){
        int tileXPosition = gamePanel.screenWidth / 2 - tileWidth/2;
        int tileYPosition = 75;
        int startX = tileXPosition;
        int startY = tileYPosition;

        char x = 'A';
        int y = 1;
        char startCordX = x;
        int startCordY = y;

        g2.setColor(Color.WHITE);

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
