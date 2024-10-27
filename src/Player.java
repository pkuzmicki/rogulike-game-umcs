import java.awt.*;

public class Player {
    GamePanel gamePanel;
    int playerX;
    int playerY;

    public Player(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void setPlayerPosition(){
        playerX = gamePanel.screenWidth/2;
        playerY = gamePanel.screenHeight/2;
    }

    public void draw(Graphics2D g2){
        g2.fillOval(playerX, playerY, 20, 20);
    }
}
