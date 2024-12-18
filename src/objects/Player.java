//passphrase: kamyk
package objects;

import main.GamePanel;
import objects.items.Rune;

import java.awt.*;
import java.util.ArrayList;

public class Player extends Monster {
    public int screenX = gp.screenWidth/2 - image.getWidth()/2;
    public int screenY = gp.screenHeight/2 - image.getHeight()/2;

    ArrayList<Rune> playerRunes = new ArrayList<>(4);

    public Player(GamePanel gamePanel, int col, int row, int entityID) {
        super(gamePanel, col, row, entityID);
    }

    public void setPositions() {
        x = screenX;
        y = screenY;
    }

    public void move(int colMove, int rowMove){
        if (gp.objectManager.entityMap[col + colMove][row + rowMove] == 0) {
            int prevCol = col;
            int prevRow = row;
            col += colMove;
            row += rowMove;
            gp.objectManager.entityMap[col][row] = id;
            gp.objectManager.entityMap[prevCol][prevRow] = 0;
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, screenX, screenY, null);
    }
}
