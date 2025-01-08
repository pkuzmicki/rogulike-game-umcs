package map;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Tile {
    public BufferedImage tileImage;
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
        String path = "";

         switch (tileType){
            //case 0 -> "/sprites/tiles/tile00.png";
            case 0 -> {
                tileImage = new BufferedImage(151,51, BufferedImage.TYPE_INT_ARGB) ;
            }
            case 1 -> path = "/sprites/tiles/tile01H.png";
            case 2 -> path = "/sprites/tiles/tile02.png";
            case 3 -> path = "/sprites/tiles/tile03.png";
            case 4 -> path = "/sprites/tiles/tile00.png";
            default -> path = "/sprites/tiles/missing_tile.png";
        };

        try {
            tileImage = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream(path))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
