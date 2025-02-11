package objects;

import main.GamePanel;
import objects.items.Rune;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ObjectManager {
    GamePanel gp;

    public ArrayList<Entity> entities = new ArrayList<>();
    public int[][] entityMap;

    public ObjectManager(GamePanel gamePanel){
        this.gp = gamePanel;

        entityMap = new int[gp.map.maxWorldCol][gp.map.maxWorldRow];
        //initializeMap();
    }

    public void initializeMap() {
        entityMap = new int[gp.map.maxWorldCol][gp.map.maxWorldRow];

        entities.add(gp.player);
    }

//    void setPlayerOnMap() {
//        entityMap[gp.player.col][gp.player.row] = 1;
//    }

    public void createEntity(int col, int row, int entityId){
        if (entityId > 10 && entityId <= 20) {
            Monster monster = new Monster(gp, col, row, entityId);
            entities.add(monster);
            //entityMap[col][row] = entityId;
        }
        if (entityId > 20 && entityId <= 30) {
            Obstacle obstacle = new Obstacle(gp, col, row, entityId);
            entities.add(obstacle);
            entityMap[col][row] = entityId;
        }
    }

    public void createRune(int col, int row, int level) {
        Rune rune = new Rune(gp, col, row, level);
        if (gp.player.isInHideout) {
            gp.hideout.entities.add(rune);
        } else {
            entities.add(rune);
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
        if (!gp.player.isInHideout) {
            Iterator<Entity> iterator = entities.iterator();
            while (iterator.hasNext()) {
                Entity entity = iterator.next();
                if (entity.col == col && entity.row == row && entity.id == id) {
                    iterator.remove();
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
