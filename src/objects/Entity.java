package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Entity{
    GamePanel gamePanel;
    public BufferedImage entityImage;
    public int entityId;
    public int x, y;
    public int col, row;


    public Entity(int col, int row){
        this.gamePanel = new GamePanel();
        this.col = col;
        this.row = row;
        setPosition(col, row);
    }

    public void setPosition(int col, int row){
        this.x = gamePanel.map.tileMap[col][row].x;
        this.y = gamePanel.map.tileMap[col][row].y;
    }

    public void loadImage(){
        String path = switch (entityId){
            case 1 -> "/sprites/entities/keeper_green.png";
            case 2 -> "/sprites/entities/keeper.png";

            case 10 -> "/sprites/obstacles/sigill_of_despair.png";
            default -> null;
        };

        if (path == null){
            return;
        }

        try {
            entityImage = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream(path))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
