package objects;

import main.GamePanel;

import java.awt.image.BufferedImage;

public class Entity{
    GamePanel gamePanel;
    public BufferedImage sprite;
    public int x, y;
    public int col, row;


    public Entity(int col, int row){
        this.gamePanel = new GamePanel();
        this.col = col;
        this.row = row;
        this.x = calcX(col, row);
        this.y = calcY(row, col);
    }

    public int calcX(int col, int row){
        return (col - row) * (gamePanel.map.tileWidth/2);
    }

    public int calcY(int row, int col){
        return (col + row) * (gamePanel.map.tileHeight/2);
    }
}
