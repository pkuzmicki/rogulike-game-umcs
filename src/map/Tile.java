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

    String pre = "/sprites/tiles/";
    String suf = ".png";

    public void loadImage(){
        String path = "";

         switch (tileType){
            //case 0 -> "/sprites/tiles/tile00.png";
            case 0 -> {
                tileImage = new BufferedImage(151,51, BufferedImage.TYPE_INT_ARGB) ;
            }
            case 1 -> path = pre + "stone_brick_tile2" + suf;
            case 2 -> path = pre + "tile02" + suf;
            case 3 -> path = pre + "tile03" + suf;
            case 4 -> path = pre + "tile00" + suf;
            case 5 -> path = pre + "brick_tile" + suf;
            case 6 -> path = pre + "blood_tile" + suf;
            case 7 -> path = pre + "rainbow_bridge_tile" + suf;
            case 8 -> path = pre + "rainbow_bridge_tile2" + suf;
            default -> path = pre + "missing_tile" + suf;
        };

        try {
            tileImage = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream(path))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
