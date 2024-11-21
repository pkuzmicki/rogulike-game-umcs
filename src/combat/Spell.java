package combat;

public class Spell {
    int damage;
    boolean dealsDamageOvertime;
    boolean isRanged;
    boolean isAOE;
    int col, row;

    Spell(int dmg, int col, int row) {
        this.damage = dmg;
        this.col = col;
        this.row = row;
    }
}
