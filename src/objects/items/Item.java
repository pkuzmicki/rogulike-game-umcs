package objects.items;

import main.GamePanel;
import objects.Entity;

public class Item extends Entity {
    public Item(GamePanel gamePanel, int col, int row, int itemId) {
        super(gamePanel, row, col, itemId);
    }
}
