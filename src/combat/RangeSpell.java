package combat;

import main.GamePanel;

public class RangeSpell extends Spell{
    public int range;

    public RangeSpell(GamePanel gamePanel, int dmg, int range) {
        super(gamePanel, dmg);
        this.range = range;
    }

    public void cast(int col, int row, int colOffset, int rowOffset) {
        for (int i = 0; i < range; i++) {
            effect(col, row);
            gp.map.tileMap[col][row].setTileType(2);
            col += colOffset;
            row += rowOffset;
        }
        gp.turnManager.globalTurn++;
        gp.repaint();
    }

}
