package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Entity {
    public GamePanel gp;
    public BufferedImage image;
    public String name;
    public int id;
    public int x, y;
    public int col, row;
    public boolean setInHideOut = false;

    public Entity(GamePanel gamePanel, int col, int row, int entityID){
        this.gp = gamePanel;
        this.id = entityID;
        this.col = col;
        this.row = row;
        loadImage();
        setEntityName();
        setPositions();
        //setSize();
    }

    public void setPositions(){
        if (!gp.player.isInHideout) {
            this.x = gp.map.tileMap[col][row].x;
            this.y = gp.map.tileMap[col][row].y;
        } else {
            this.x = gp.hideout.hideoutMap[col][row].x;
            this.y = gp.hideout.hideoutMap[col][row].y;
        }
        setSize();
    }

    public void setSize(){
        this.x += (int) switch (id) {
            case 1 -> 0;
            case 31, 32, 33, 34 -> image.getWidth() * 1.5;
            default -> image.getWidth();
        };

        this.y -= switch (id) {
            case 11, 12, 13, 14, 15 -> 65;
            case 21, 22 -> image.getHeight() / 2;
            default -> 0;
        };
    }

    public void setEntityName() {
        name = switch (id) {
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
        String path = switch (id){
            case 1 -> "/sprites/entities/silly_wizard.png";
            case 11 -> "/sprites/entities/keeper_green.png";
            case 12 -> "/sprites/entities/keeper_red.png";
            case 13 -> "/sprites/entities/keeper_blue.png";
            case 14 -> "/sprites/entities/keeper_yellow.png";
            case 15 -> "/sprites/entities/crazy_wizard.png";

            case 21 -> "/sprites/obstacles/sigill_of_despair.png";
            case 22 -> "/sprites/obstacles/skull_pot.png";

            case 31 -> "/sprites/items/rune01.png";
            case 32 -> "/sprites/items/rune02.png";
            case 33 -> "/sprites/items/rune03.png";
            case 34 -> "/sprites/items/rune04.png";

            default -> null;
        };

        try {
            image = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream(path))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getHeight() {
        return col + row;
    }

    public void draw(Graphics2D g2) {
        if (x + gp.map.dx > -gp.map.tileWidth && x + gp.map.dx < gp.screenWidth & y + gp.map.dy > -gp.map.tileHeight && y + gp.map.dy < gp.screenHeight) {
            g2.drawImage(image, x + gp.map.dx, y + gp.map.dy, null);
        }
    }
}
