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
    public int[][] entityMap;

    public ObjectManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;

        entityMap = new int[gamePanel.map.maxWorldCol][gamePanel.map.maxWorldRow];

        setPlayerOnMap();

        //setEntitiesFromFile("/maps/room01E.txt", 1, 1);


        createEntity(2, 2, 12);
        createEntity(3, 0, 11);
        createEntity(0, 3, 21);

        //printEntityMap();
    }

    void setPlayerOnMap() {
        entityMap[gamePanel.player.col][gamePanel.player.row] = 1;
    }

    void createEntity(int col, int row, int entityId){
        if (entityId > 10 && entityId <= 20) {
            Monster monster = new Monster(gamePanel, col, row, entityId);
            entities.add(monster);
            entityMap[col][row] = entityId;
        }
        if (entityId > 20 && entityId <= 30) {
            Obstacle obstacle = new Obstacle(gamePanel, col, row, entityId);
            entities.add(obstacle);
            entityMap[col][row] = entityId;
        }
        if (entityId > 30 && entityId <= 40) {
            Item item = new Item(gamePanel, col, row, entityId);
            entities.add(item);
            entityMap[col][row] = entityId;
        }
    }

    void setEntitiesFromFile(String filePath, int startCol, int startRow){
        int[][] tempEntitiesMap = gamePanel.map.getMap(filePath);
        for (int col = 0; col < tempEntitiesMap.length; col++){
            for (int row = 0; row < tempEntitiesMap[0].length; row++){
                createEntity(col + startCol, row + startRow, tempEntitiesMap[col][row]);
                entityMap[col + startCol][row + startRow] = tempEntitiesMap[col][row];
            }
        }
    }

    public void destroyEntity(int col, int row) {
        entities.removeIf(entity -> entity.col == col && entity.row == row);
    }

    public void printEntityMap() {
        for (int col = 0; col < entityMap.length; col++) {
            for (int row = 0; row < entityMap[0].length; row++) {
                System.out.print(entityMap[col][row] + " ");
            }
            System.out.println();
        }
    }

}
