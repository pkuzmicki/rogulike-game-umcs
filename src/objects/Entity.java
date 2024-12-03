package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Entity {
    GamePanel gp;
    public BufferedImage entityImage;
    public String entityName;
    public int entityID;
    public int x, y;
    public int col, row;

    public Entity(GamePanel gamePanel, int col, int row, int entityID){
        this.gp = gamePanel;
        this.entityID = entityID;
        this.col = col;
        this.row = row;
        loadImage();
        setEntityName();
        setPositions();
        setSize();
    }

    public void setPositions(){
        this.x = gp.map.tileMap[col][row].x;
        this.y = gp.map.tileMap[col][row].y;
    }

    public void setSize(){
        this.x += switch (entityID) {
            case 1 -> 0;
            default -> entityImage.getWidth();
        };

        this.y -= switch (entityID) {
            case 11, 12, 13, 14 -> 65;
            case 21, 22 -> entityImage.getHeight() / 2;
            default -> 0;
        };
    }

    public void setEntityName() {
        entityName = switch (entityID) {
            case 1 -> "player";
            case 11 -> "keeper_green";
            case 12 -> "keeper_red";
            case 13 -> "keeper_blue";
            case 14 -> "keeper_yellow";
            case 21 -> "sigill_of_despair";
            case 22 -> "skull_pot";
            default -> null;
        };
    }

    public void loadImage(){
        String path = switch (entityID){
            case 1 -> "/sprites/entities/crazy_wizard.png";
            case 11 -> "/sprites/entities/keeper_green.png";
            case 12 -> "/sprites/entities/keeper.png";
            case 13 -> "/sprites/entities/keeper_blue.png";
            case 14 -> "/sprites/entities/keeper_yellow.png";
            case 21 -> "/sprites/obstacles/sigill_of_despair.png";
            case 22 -> "/sprites/obstacles/skull_pot.png";
            default -> null;
        };

        try {
            entityImage = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream(path))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getY() {
        return this.y;
    }

    //przeniesc sprawdzanie kolizji i mape postaci tutaj albo gdzies do nowej klasy moze
    public void move(int colMove, int rowMove){
        if (gp.objectManager.entityMap[col + colMove][row + rowMove] == 0) {
            int prevCol = col;
            int prevRow = row;
            col += colMove;
            row += rowMove;
            setPositions();
            setSize();
            gp.objectManager.entityMap[col][row] = entityID;
            gp.objectManager.entityMap[prevCol][prevRow] = 0;
            gp.repaint();
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(entityImage, x + gp.map.dx, y + gp.map.dy, null);
    }
}
