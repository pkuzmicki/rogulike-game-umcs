package combat;

import main.GamePanel;

import java.util.ArrayList;

public class CastingManager {
    GamePanel gamePanel;

    public CastingManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        testicularTorsion = new SingleAttackSpell(gamePanel, 5, gamePanel.player.col + 1, gamePanel.player.row);
    }

    public SingleAttackSpell testicularTorsion;

    //ArrayList<SingleAttackSpell> testicularTorsion = new ArrayList<>();

    //czar ma miec swoja logike w swojej klasie czyli obszar dzialani dmg itp, a wywolywany ma byc tutaj
}
