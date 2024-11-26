package map;

import main.GamePanel;
import objects.Entity;
import objects.Item;
import objects.Monster;
import objects.Obstacle;

import java.util.ArrayList;

public class ObjectManager {
    GamePanel gamePanel;

    public ArrayList<Entity> entities = new ArrayList<>();

    public ObjectManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;

        setEntitiesFromMap("/maps/room01E.txt", 1, 1);

        createEntity(3, 0, 1);
        createEntity(0, 3, 11);
    }

    void setEntitiesFromMap(String filePath, int startCol, int startRow){
        int[][] entitiesMap = gamePanel.map.getMap(filePath);
        for (int col = 0; col < entitiesMap.length; col++){
            for (int row = 0; row < entitiesMap[0].length; row++){
                createEntity(col + startCol, row + startRow, entitiesMap[col][row]);
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


    public void damageEntity(int col, int row) {

    }

    public void destroyEntity(int col, int row) {
        entities.removeIf(entity -> entity.col == col && entity.row == row);
    }

}
