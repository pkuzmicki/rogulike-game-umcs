package combat;

import main.GamePanel;

public class CastingManager {
    GamePanel gp;

    public Spell testicularTorsion;
    public RangeSpell lifebloom;
    //public RangeSpell rollingMagma;
    //public Spell testSpell;

    public CastingManager(GamePanel gamePanel) {
        this.gp = gamePanel;
        testicularTorsion = new Spell(gamePanel, 5);
        lifebloom = new RangeSpell(gamePanel, 3, 3);

        //rollingMagma = new RangeSpell(gamePanel, 5, 3 );
        //testSpell = new Spell(gamePanel, 5);
    }

    public void playerCast(Spell spell) {
        int colOffset = 0, rowOffset = 0;
        if (gp.keyHandler.directionOfAction() != null) {
            switch (gp.keyHandler.directionOfAction()) {
                case "up" -> colOffset = -1;
                case "down" -> colOffset = 1;
                case "left" -> rowOffset = -1;
                case "right" -> rowOffset = 1;
                default -> {}
            };
        }

        if (colOffset != 0 || rowOffset != 0 && gp.keyHandler.onePressed){
            spell.cast(gp.player.col + colOffset, gp.player.row + rowOffset);
        }

        if (colOffset != 0 || rowOffset != 0 && gp.keyHandler.twoPressed){
            if (spell instanceof RangeSpell rangeSpell) {
                rangeSpell.cast(gp.player.col + colOffset, gp.player.row + rowOffset, colOffset, rowOffset);
            }
        }
    }

}
