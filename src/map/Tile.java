package map;

import java.awt.image.BufferedImage;

public class Tile {
    BufferedImage tileImage;
    boolean collision = false;
    int row;
    int col;
    public Tile(int row, int col){
        this.row = row;
        this.col = col;
    }

}
