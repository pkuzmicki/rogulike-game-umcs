package objects.items;

import main.GamePanel;

import java.util.Random;

public class Rune extends Item {
    Random random;

    public int level;
    public int numberOfMods;

    public double dmg;
    public double critical;
    public int range;
    public double hp;
    public int mana;
    public int runeSpace;

    public Rune(GamePanel gamePanel, int col, int row, int itemId) {
        super(gamePanel, col, row, itemId);
        random = new Random();

        dmg = random.nextInt(0, 20);
    }

    public void generateRune() {
        //dodac szanse krora sie zwieksza z progresja
        level = random.nextInt(0, 5);

        switch (level) {
            case 1 -> {
                numberOfMods = random.nextInt(1, 3);
            }
            case 2 -> {
                numberOfMods = random.nextInt(1, 4);
            }
            case 3 -> {
                numberOfMods = random.nextInt(2, 3);
            }
            case 4 -> {
                numberOfMods = random.nextInt(2, 4);
            }
        }
    }

    private void getStats() {
        int statId = random.nextInt(1, 7);
        switch (statId) {
            case 1 -> {
                if (random.nextBoolean()) {
                    dmg += random.nextInt(-5, 40);
                } else {
                    dmg += gp.player.dmg * random.nextDouble(0.01, 0.2);
                }
            }
            case 2 -> {
                critical += random.nextInt(1, 10);
            }
            case 3 -> {
                range += random.nextInt(1, 3);
            }
            case 4 -> {
                if (random.nextBoolean()) {
                    hp += random.nextInt(-5, 50);
                } else {
                    hp += gp.player.maxHP * random.nextDouble(0.01, 0.1);
                }
            }
            case 5 -> {
                if (random.nextBoolean()) {
                    mana += random.nextInt(-5, 50);
                } else {
                    mana += (int) (gp.player.maxMana * random.nextDouble(0.05, 0.3));
                }
            }
            case 6 -> {
                runeSpace++;
            }
        }
    }


}
