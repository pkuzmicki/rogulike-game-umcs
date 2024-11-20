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


    public Entity(GamePanel gamePanel, int col, int row, int entityId){
        this.gamePanel = gamePanel;
        this.entityId = entityId;
        this.col = col;
        this.row = row;
        setPosition(col, row);
        loadImage();
    }

    public void setPosition(int col, int row){
        this.x = gamePanel.map.tileMap[col][row].x;
        this.y = gamePanel.map.tileMap[col][row].y;
        //System.out.println(gamePanel.map.tileMap[col][row].x);
    }

    public void loadImage(){
        String path = switch (entityId){
            case 1 -> {
                yield  "/sprites/entities/keeper_green.png";
            }
            case 2 -> {
                yield "/sprites/entities/keeper.png";
            }
            case 11 -> {
                yield  "/sprites/obstacles/sigill_of_despair.png";
            }
            case 12 -> {
                yield  "/sprites/obstacles/skull_pot.png";
            }
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
