package objects;

import main.GamePanel;

import java.awt.image.BufferedImage;

public class Monster{
    GamePanel gamePanel;
    public BufferedImage monsterImage;
    public String monsterName;
    public int x, y;
    public int col, row;
    public Monster(GamePanel gamePanel, int col, int row) {
        this.gamePanel = gamePanel;
        this.col = col;
        this.row = row;
        setPosition(col, row);
    }

    public void setPosition(int col, int row){
        this.x = gamePanel.map.tileMap[col][row].x;
        this.y = gamePanel.map.tileMap[col][row].y;
    }
}
