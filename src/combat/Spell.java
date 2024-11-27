package combat;

import main.GamePanel;

public class Spell {
    GamePanel gamePanel;
    int damage;
    boolean dealsDamageOvertime;
    boolean isRanged;
    boolean isAOE;
    int col, row;

    Spell(int dmg, int col, int row, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.damage = dmg;
        this.col = col;
        this.row = row;
    }
}
