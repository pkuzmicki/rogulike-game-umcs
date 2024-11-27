package combat;

import main.GamePanel;

public class Spell {
    GamePanel gamePanel;
    int damage;
    String name;
    boolean dealsDamageOvertime;
    boolean isRanged;
    boolean isAOE;

    Spell(GamePanel gamePanel, int dmg) {
        this.gamePanel = gamePanel;
        this.damage = dmg;
    }
}
