package map;

import main.GamePanel;
import objects.Monster;
import objects.Obstacle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ObjectManager {
    GamePanel gamePanel;
    BufferedImage monsterImage;

    public ArrayList<Monster> monsters = new ArrayList<>();
    public ArrayList<Obstacle> obstacles = new ArrayList<>();

    public ObjectManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;

        createObjects("/maps/map01E.txt");
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

        void createObjects(String filePath){
        int[][] monsterMap = gamePanel.map.getMap(filePath);
        for (int col = 0; col < monsterMap.length; col++){
            for (int row = 0; row < monsterMap[0].length; row++){
                if (monsterMap[col][row] == 1){
                    Monster monster = new Monster(gamePanel, col, row);
                    monster.col = col;
                    monster.row = row;

                    try {
                        monster.monsterImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/entities/keeper_green.png")));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    monsters.add(monster);
                }
            }
        }
    }

    public void draw(Graphics2D g2){
        for (Monster monster : monsters){
            g2.drawImage(monster.monsterImage, monster.x + gamePanel.map.entityWidth/2 + gamePanel.map.mapStartX, monster.y - gamePanel.map.entityHeight/2 + gamePanel.map.mapStartY, null);
        }
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
