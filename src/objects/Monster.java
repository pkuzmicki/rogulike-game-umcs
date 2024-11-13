package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Monster{
    GamePanel gamePanel;
    public BufferedImage monsterImage;
    public String monsterName;
    public int monsterId;
    public int x, y;
    public int col, row;
    public Monster(GamePanel gamePanel, int col, int row){
        this.gamePanel = gamePanel;
        this.col = col;
        this.row = row;
        setPosition(col, row);
    }

    public void setPosition(int col, int row){
        this.x = gamePanel.map.tileMap[col][row].x;
        this.y = gamePanel.map.tileMap[col][row].y;
    }

    public void loadImage(){
        String path = switch (monsterId){
            case 1 -> "/sprites/entities/keeper_green.png";
            case 2 -> "/sprites/entities/keeper.png";
            default -> null;
        };

        if (path == null){
            return;
        }

        try {
            monsterImage = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream(path))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
