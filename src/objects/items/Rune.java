package objects.items;

import main.GamePanel;

import java.util.*;

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

    public List<String> listOfMods;

    public Rune(GamePanel gamePanel, int col, int row, int itemId) {
        super(gamePanel, col, row, itemId);
        random = new Random();
        listOfMods = new ArrayList<>();

        drawMods();
    }

    public Set<String> selectedMods = new HashSet<>();

    public void drawMods() {
        level = drawWeight(4);

        switch (level) {
            case 0 -> numberOfMods = 1;
            case 1 -> numberOfMods = random.nextInt(1, 3);
            case 2 -> numberOfMods = random.nextInt(1, 4);
            case 3 -> numberOfMods = random.nextInt(2, 4);
            case 4 -> numberOfMods = random.nextInt(2, 5);
        }

        List<String> availableMods = new ArrayList<>();

        availableMods.add("dmg");
        availableMods.add("hp");
        availableMods.add("mana");
        if (level >= 2) availableMods.add("critical");
        if (level >= 3) availableMods.add("runeSpace");
        if (level >= 4) availableMods.add("range");


        for (int i = 0; i < numberOfMods; i++) {
            selectedMods.add(availableMods.get(random.nextInt(availableMods.size())));
        }

        for (String mod : selectedMods) {
            listOfMods.add(mod);
            switch (mod) {
                case "dmg" -> dmg = drawDmg();
                case "critical" -> critical = drawWeight(10);
                case "range" -> range = drawWeight(3);
                case "hp" -> hp = drawHp();
                case "mana" -> mana = (int) drawMana();
                case "runeSpace" -> runeSpace = 1;
            }
        }

    }

    private double drawDmg() {
        boolean isNegative = random.nextInt(100) > 90;
        if (isNegative) {
            return random.nextInt(-5, -1);
        } else {
            return drawWeight(40);
        }
    }

    private double drawHp() {
        boolean isNegative = random.nextInt(100) > 90;
        if (isNegative) {
            return random.nextInt(-5, -1);
        } else {
            return drawWeight(40);
        }
    }

    private double drawMana() {
        boolean isNegative = random.nextInt(100) > 90;
        if (isNegative) {
            return random.nextInt(-10, -1);
        } else {
            return drawWeight(60);
        }
    }

    private int drawWeight(int max) {
        int sum = 0;

        for (int i = 1; i <= max; i++) {
            sum += i;
        }

        int num = random.nextInt(sum) + 1;

        int currSum = 0;
        for (int i = 1; i <= max; i++) {
            currSum += (max - i + 1);
            if (num <= currSum) {
                return i;
            }
        }
        return max;
    }


}

