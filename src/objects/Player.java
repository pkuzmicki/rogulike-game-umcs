//passphrase: kamyk
package objects;

import main.GamePanel;

import java.awt.*;

public class Player extends Monster {
    public int screenX = gp.screenWidth/2 - entityImage.getWidth()/2;
    public int screenY = gp.screenHeight/2 - entityImage.getHeight()/2;
    //public int col;
    //public int row;

    public Player(GamePanel gamePanel, int col, int row, int entityID) {
        super(gamePanel, col, row, entityID);
//        this.col = col;
//        this.row = row;
    }

    public void setPositions() {
        x = screenX;
        y = screenY;
    }

    public int getY() {
        return this.screenY;
    }

    public void move(int colMove, int rowMove){
        if (gp.objectManager.entityMap[col + colMove][row + rowMove] == 0) {
            int prevCol = col;
            int prevRow = row;
            col += colMove;
            row += rowMove;
            //setPositions();
            //setSize();
            gp.objectManager.entityMap[col][row] = entityID;
            gp.objectManager.entityMap[prevCol][prevRow] = 0;
            gp.repaint();
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(entityImage, screenX, screenY, null);
    }
}
