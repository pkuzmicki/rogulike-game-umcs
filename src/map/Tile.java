package map;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Tile {
    public BufferedImage tileImage;
    public boolean collision = false;
    public int tileType;
    public int x, y;
    public int col, row;

    public Tile(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setTileType(int tileType){
        this.tileType = tileType;
        loadImage();
    }

    public void loadImage(){
        String path = switch (tileType){
            case 0 -> "/sprites/tiles/tile01.png";
            case 1 -> "/sprites/tiles/tile02.png";
            case 2 -> "/sprites/tiles/tile03.png";
            default -> "/sprites/tiles/missing_tile.png";
        };

        try {
            tileImage = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream(path))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
