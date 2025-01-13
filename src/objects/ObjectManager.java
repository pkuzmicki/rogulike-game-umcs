package objects;

import main.GamePanel;
import objects.items.Item;
import objects.items.Rune;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ObjectManager {
    GamePanel gp;

    public ArrayList<Entity> entities = new ArrayList<>();
    public int[][] entityMap;

    public ObjectManager(GamePanel gamePanel){
        this.gp = gamePanel;

        initializeMap();

        createEntity(2, 2, 31);

        //setEntitiesFromFile("/maps/room01E.txt", 1, 1);

//        createEntity(4, 4, 12);
    }

    public void initializeMap() {
        entityMap = new int[gp.map.maxWorldCol][gp.map.maxWorldRow];

        setPlayerOnMap();
        entities.add(gp.player);
    }

    void setPlayerOnMap() {
        entityMap[gp.player.col][gp.player.row] = 1;
    }

    public void createEntity(int col, int row, int entityId){
        if (entityId > 10 && entityId <= 20) {
            Monster monster = new Monster(gp, col, row, entityId);
            entities.add(monster);
            entityMap[col][row] = entityId;
        }
        if (entityId > 20 && entityId <= 30) {
            Obstacle obstacle = new Obstacle(gp, col, row, entityId);
            entities.add(obstacle);
            entityMap[col][row] = entityId;
        }
        if (entityId > 30 && entityId <= 40) {
            Item item = new Item(gp, col, row, entityId);
            entities.add(item);
            entityMap[col][row] = entityId;
        }
    }

    void setEntitiesFromFile(String filePath, int startCol, int startRow){
        int[][] tempEntitiesMap = gp.map.getMap(filePath);
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

    public void checkForItems() {
        //List<Entity> entitiesToRemove = new ArrayList<>();

        for (Entity entity : gp.objectManager.entities) {
            if (entity.id > 30) {
                if (gp.player.col == entity.col && gp.player.row == entity.row) {
                    if (entity instanceof Item) {
                        Item item = (Item) entity;
                        if (item instanceof Rune) {
                            Rune rune = (Rune) item;

                            //entitiesToRemove.add(entity);
                            gp.player.playerRunes.add(rune);
                        }

                        gp.objectManager.entityMap[entity.col][entity.row] = 0;
                        gp.objectManager.entities.remove(entity);
                        gp.repaint();

                        System.out.println(gp.player.playerRunes);
                    }
                }
            }
        }
        //gp.objectManager.entities.removeAll(entitiesToRemove);
    }

}
