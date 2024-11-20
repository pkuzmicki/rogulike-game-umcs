package objects;

import main.GamePanel;

public class Monster extends Entity{
    public int hitPoints;

    public Monster(GamePanel gamePanel, int col, int row, int monsterId){
        super(gamePanel, col, row, monsterId);
    }
}
