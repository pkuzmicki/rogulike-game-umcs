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
        String path;
        String name = null;

         switch (tileType){
            case 0 -> tileImage = new BufferedImage(151,51, BufferedImage.TYPE_INT_ARGB) ;
            case 1 -> name = "stone_brick_tile";
            case 2 -> name = "teleport_tile";

            case 5 -> name = "brick_tile";
            case 9 -> name = "rune_place";
            case 3 -> name = "teleport_tile2";
            case 6 -> name = "blood_tile";

            case 7 -> name = "rainbow_bridge_tile";
            case 8 -> name = "rainbow_bridge_tile2";

            default -> name = "missing_tile";
        };

         path = "/sprites/tiles/" + name + ".png";

        try {
            if (tileType != 0) {
                tileImage = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream(path))));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
