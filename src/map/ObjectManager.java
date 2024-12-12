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
        entities.add(gamePanel.player);
        //setEntitiesFromFile("/maps/room01E.txt", 1, 1);


        createEntity(4, 4, 12);
        //createEntity(2, 1, 11);

        createEntity(0, 3, 11);
        //createEntity(0, 4, 11);
        //createEntity(0, 5, 11);
        createEntity(0, 6, 11);
        createEntity(1, 0, 14);
        createEntity(4, 1, 13);

        //createEntity(2, 0, 14);
        //createEntity(2, 1, 13);


        //createEntity(0, 3, 21);
    }

    void setPlayerOnMap() {
        entityMap[gamePanel.player.col][gamePanel.player.row] = 1;
    }

    public void createEntity(int col, int row, int entityId){
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
