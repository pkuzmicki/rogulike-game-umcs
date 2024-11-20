package objects;

import main.GamePanel;

public class Item extends Entity {
    public Item(GamePanel gamePanel, int col, int row, int itemId) {
        super(gamePanel, row, col, itemId);
    }
}
