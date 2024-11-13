package map;

import main.GamePanel;
import objects.Entity;
import objects.Monster;

import java.awt.*;
import java.util.ArrayList;

public class ObjectManager {
    GamePanel gamePanel;

    public ArrayList<Entity> entities = new ArrayList<>();
    public ArrayList<Monster> monsters = new ArrayList<>();
    //public ArrayList<Obstacle> obstacles = new ArrayList<>();

    public ObjectManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;

        //setObjectsFromMap("/maps/map01E.txt");

        //setEntitiesFromMap("/maps/room01E.txt", 1, 1);
        
    }

//    public void loadObjects(){
//        getObjects(0, "sigill_of_despair", 1, 2);
//        getObjects(1, "sigill_of_despair", 0, 3);
//        getObjects(2, "sigill_of_despair", 4, 3);
//        getObjects(2, "skull_pot", 4, 3);
//    }

//    void getObjects(int index, String objectName, int row , int col){
//        try {
//            listOfObstacles[index] = new Obstacle();
//            listOfObstacles[index].sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/obstacles/" + objectName + ".png")));
//            listOfObstacles[index].x = gamePanel.map.objectsMap[row][col].x;
//            listOfObstacles[index].y = gamePanel.map.objectsMap[row][col].y;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    void setEntitiesFromMap(String filePath, int startCol, int startRow){
        int[][] entitiesMap = gamePanel.map.getMap(filePath);
        for (int col = startCol; col < entitiesMap.length; col++){
            for (int row = startRow; row < entitiesMap[0].length; row++){
                Entity entity = new Entity(col, row);
                entity.entityId = entitiesMap[col][row];
                entity.loadImage();
                entity.col = col;
                entity.row = row;
                entities.add(entity);
            }
        }
    }

        void setObjectsFromMap(String filePath){
        int[][] monsterMap = gamePanel.map.getMap(filePath);
        for (int col = 0; col < monsterMap.length; col++){
            for (int row = 0; row < monsterMap[0].length; row++){

                Monster monster = new Monster(gamePanel, col, row);
                monster.monsterId = monsterMap[col][row];
                monster.loadImage();
                monster.col = col;
                monster.row = row;
                monsters.add(monster);

            }
        }
    }

    void createObject(int col, int row, int monsterId){
        Monster monster = new Monster(gamePanel, col, row);
        monster.monsterId = monsterId;
        monster.loadImage();
    }

    void deleteObject(int col, int row , String entityName){

    }

    public void draw(Graphics2D g2){
        for (Entity entity : entities) {
            g2.drawImage(entity.entityImage, entity.x, entity.y, null);
        }

//        for (Monster monster : monsters){
//            g2.drawImage(monster.monsterImage, monster.x + gamePanel.map.entityWidth + gamePanel.map.mapStartX, monster.y - (int)(gamePanel.map.entityHeight * 0.6)  + gamePanel.map.mapStartY, null);
//        }
    }

//    public void draw(Graphics2D g2){
//        int x;
//        int y;
//        for (int i = 0; i < listOfObstacles.length; i++){
//            if (listOfObstacles[i] != null){
//                x = listOfObstacles[i].x;
//                y = listOfObstacles[i].y;
//                x -= listOfObstacles[i].sprite.getWidth()/2;
//                y -= (int) (listOfObstacles[i].sprite.getHeight()/1.5);
//                g2.drawImage(listOfObstacles[i].sprite, x, y, null);
//            }
//
//        }
//    }
}
