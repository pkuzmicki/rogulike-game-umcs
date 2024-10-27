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

    int maxWorldCol = 4;
    int maxWorldRow = 3;

    Tile[][] tileMap;
    int[][] entityMap;

    public BufferedImage tile;

    final int tileWidth = 300;
    final int tileHeight = 100;




    public void loadImage() {
        try {
            tile = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tile01.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadMap(){
        for (int cols = 0; cols < maxWorldCol; cols++){
            for (int rows = 0; rows < maxWorldRow; rows++){
                tileMap[rows][cols] = new Tile(rows, cols);
            }
        }
    }

    public void draw(Graphics2D g2){
        int tileXPosition = gamePanel.screenWidth / 2 - tileWidth/2;
        int tileYPosition = 50;
        g2.drawImage(tile, 500 ,100, null);
        for (int cols = 0; cols < maxWorldCol; cols++){
            for (int rows = 0; rows < maxWorldRow; rows++){
                g2.drawImage(tile, tileXPosition ,tileYPosition, null);
                tileXPosition += 150;
                tileYPosition += 50;
            }
            tileXPosition -= tileWidth * 2;
            tileYPosition -= tileHeight;
        }

    }

}
