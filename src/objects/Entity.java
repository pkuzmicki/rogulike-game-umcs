package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Entity extends OBJ{
    GamePanel gamePanel;
    public BufferedImage entityImage;
    public String entityName;
    public int entityId;
    public int x, y;
    public int col, row;


    public Entity(GamePanel gamePanel, int col, int row, int entityId){
        this.gamePanel = gamePanel;
        this.entityId = entityId;
        this.col = col;
        this.row = row;
        manageEntities();
        setPosition(col, row);
        loadImage();
        setSize();
    }

    public void setPosition(int col, int row){
        this.x = gamePanel.map.tileMap[col][row].x;
        this.y = gamePanel.map.tileMap[col][row].y;
        //System.out.println(gamePanel.map.tileMap[col][row].x);
    }

    public void setSize(){
        if (entityId != 0) {
            x += entityImage.getWidth();
        }

        y -= switch (entityId) {
            case 1, 2 -> 65;
            case 11, 12 -> entityImage.getHeight() / 2;
            default -> 0;
        };
    }

    public void manageEntities() {
        entityName = switch (entityId) {
            case 1 -> "keeper_green";
            case 2 -> "keeper";
            case 11 -> "sigill_of_despair";
            case 12 -> "skull_pot";
            default -> null;
        };
    }

    public void loadImage(){
//        String entityPrefix = "/sprites/entities/";
//        String obstaclePrefix = "/sprites/obstacles/";
//        String suffix = ".png";
//        String path = entityName + suffix;
        String path = switch (entityId){
            case 1 -> "/sprites/entities/keeper_green.png";
            case 2 -> "/sprites/entities/keeper.png";
            case 11 -> "/sprites/obstacles/sigill_of_despair.png";
            case 12 -> "/sprites/obstacles/skull_pot.png";
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

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(entityImage, x, y, null);
    }
}
