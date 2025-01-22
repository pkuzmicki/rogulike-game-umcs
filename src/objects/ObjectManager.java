package objects;

import main.GamePanel;
import objects.items.Rune;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ObjectManager {
    GamePanel gp;

    public ArrayList<Entity> entities = new ArrayList<>();
    public int[][] entityMap;

    public ObjectManager(GamePanel gamePanel){
        this.gp = gamePanel;

        initializeMap();

        createEntity(2, 2, 31);
        createEntity(3, 2, 32);
        createEntity(4, 2, 33);
        createEntity(5, 2, 34);

        createEntity(4, 4, 12);

        //setEntitiesFromFile("/maps/room01E.txt", 1, 1);
    }

    public void initializeMap() {
        entityMap = new int[gp.map.maxWorldCol][gp.map.maxWorldRow];

        //setPlayerOnMap();
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
            Rune rune = new Rune(gp, col, row, entityId);
            if (gp.player.isInHideout) {
                gp.hideout.entities.add(rune);
            } else {
                entities.add(rune);
                //entityMap[col][row] = entityId;
            }
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

    public List<Entity> entitiesToKill = new ArrayList<>();

    public void destroyEntity(int col, int row, int id) {
        //.removeIf(entity -> entity.col == col && entity.row == row);
        if (!gp.player.isInHideout) {
            for (Entity entity : entities) {
                if (entity.col == col && entity.row == row && entity.id == id) {
                    entitiesToKill.add(entity);
                }
            }
        } else {
            for (Entity entity : gp.hideout.entities) {
                if (entity.col == col && entity.row == row) {
                    entitiesToKill.add(entity);
                }
            }
        }
    }

    public void killAll() {
        entities.removeAll(entitiesToKill);
        gp.hideout.entities.removeAll(entitiesToKill);
        gp.repaint();
    }

    public void printEntityMap() {

        for (int row = 0; row < entityMap[0].length; row++) {

            for (int col = 0; col < entityMap.length; col++) {
                System.out.print(entityMap[col][row] + " ");
            }
            System.out.println();
        }
    }

    public void draw(Graphics2D g2) {
        if (!gp.player.isInHideout) {
            List<Entity> objectsToDraw = new ArrayList<>(entities);
            objectsToDraw.sort(Comparator.comparing(Entity::getHeight));

            for (Entity entity : objectsToDraw) {
                entity.draw(g2);
            }
        } else {
            List<Entity> objectsToDraw = new ArrayList<>(gp.hideout.entities);
            objectsToDraw.add(gp.player);
            //objectsToDraw.add(gp.hideout.dummy);

            objectsToDraw.sort(Comparator.comparing(Entity::getHeight));

            for (Entity entity : objectsToDraw) {
                entity.draw(g2);
            }
        }

    }

}
