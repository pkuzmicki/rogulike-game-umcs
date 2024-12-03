//passphrase: kamyk
package objects;

import main.GamePanel;

public class Player extends Monster {
    public int screenX = gp.screenWidth/2 - entityImage.getWidth()/2;
    public int screenY = gp.screenHeight/2 - entityImage.getHeight()/2;
    public int col;
    public int row;

    public Player(GamePanel gamePanel, int col, int row, int entityID) {
        super(gamePanel, col, row, entityID);
        this.col = col;
        this.row = row;
    }

    public void setPositions() {
        x = screenX;
        y = screenY;
    }
}
