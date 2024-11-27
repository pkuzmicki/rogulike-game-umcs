package combat;

import main.GamePanel;

import java.util.ArrayList;

public class CastingManager {
    GamePanel gamePanel;

    public SingleAttackSpell testicularTorsion;
    public SingleAttackSpell lifebloom;

    public CastingManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        testicularTorsion = new SingleAttackSpell(gamePanel, gamePanel.player.dmg);
        lifebloom = new SingleAttackSpell(gamePanel, -3);
    }



}
