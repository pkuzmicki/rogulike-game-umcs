package map;

import main.GamePanel;
import objects.Item;
import objects.Monster;
import objects.Obstacle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class ObjectManager {
    public Obstacle[] listOfObstacles;
    public Monster[] listOfMonsters;
    public Item[] listOfItems;

    GamePanel gamePanel;
    public ObjectManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;

        listOfObstacles = new Obstacle[10];

        listOfObstacles[0].x = gamePanel.map.objectsMap[0][2].x;
        listOfObstacles[0].y = gamePanel.map.objectsMap[0][2].y;

        loadImage();
    }

    public void loadObjects(){
        getImage(0, "sigill_of_despair");
    }

    void getImage(int index, String obstacleName){
        try {
            listOfObstacles[index] = new Obstacle();
            listOfObstacles[index].sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/obstacles/" + obstacleName + ".png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadImage(){
        try {
            listOfObstacles[0].sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/obstacles/sigilofdespair.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2){
        int x = listOfObstacles[0].x;
        int y = listOfObstacles[0].y;
        x -= listOfObstacles[0].sprite.getWidth()/2;
        y -= (int) (listOfObstacles[0].sprite.getHeight()/1.5);
        g2.drawImage(listOfObstacles[0].sprite, x, y, null);
    }
}
