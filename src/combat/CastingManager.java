package combat;

import main.GamePanel;

public class CastingManager {
    GamePanel gamePanel;

    public SingleSpell testicularTorsion;
    public SingleSpell lifebloom;
    public RangeSpell rollingMagma;

    public CastingManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        testicularTorsion = new SingleSpell(gamePanel, gamePanel.player.dmg);
        lifebloom = new SingleSpell(gamePanel, -3);
        rollingMagma = new RangeSpell(gamePanel,5, 3 );
    }



}
