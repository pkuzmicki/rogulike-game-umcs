package map;

import main.GamePanel;
import objects.Entity;
import objects.Item;
import objects.Monster;
import objects.Obstacle;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ObjectManager {
    GamePanel gamePanel;

    public ArrayList<Entity> entities = new ArrayList<>();

    public ObjectManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;

        //setEntitiesFromMap("/maps/room01E.txt", 1, 1);

        createEntity(0, 0, 1);
    }

    void setEntitiesFromMap(String filePath, int startCol, int startRow){
        int[][] entitiesMap = gamePanel.map.getMap(filePath);
        for (int col = startCol; col < entitiesMap.length; col++){
            for (int row = startRow; row < entitiesMap[0].length; row++){
                createEntity(col, row, entitiesMap[col][row]);
            }
        }
    }

    void createEntity(int col, int row, int entityId){
        if (entityId <= 10) {
            Monster monster = new Monster(gamePanel, col, row, entityId);
            entities.add(monster);
        }
        if (entityId > 10 && entityId <= 20) {
            Obstacle obstacle = new Obstacle(gamePanel, col, row, entityId);
            entities.add(obstacle);
        }
        if (entityId > 20 && entityId <= 30) {
            Item item = new Item(gamePanel, col, row, entityId);
            entities.add(item);
        }
    }

    void destroyEntity(int col, int row) {
        entities.removeIf(entity -> entity.col == col && entity.row == row);
    }

    public void draw(Graphics2D g2){
        for (Entity entity : entities) {
            g2.drawImage(entity.entityImage, entity.x, entity.y, null);
        }
    }
}
