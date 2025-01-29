package objects.items;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

public class Rune extends Item {
    Random random;
    DecimalFormat df;

    public int level;
    public int numberOfMods;

    public double dmg;
    public double critical;
    public int range;
    public double hp;
    public int mana;
    public int runeSpace;

    public List<String> listOfMods;
    public Set<String> selectedMods = new HashSet<>();

    public BufferedImage colorsSheet;
    public BufferedImage shapesSheet;
    public BufferedImage[] colors = new BufferedImage[4];
    public BufferedImage[] shapes = new BufferedImage[4];

    public Rune(GamePanel gamePanel, int col, int row, int level) {
        super(gamePanel, col, row, 31);
        this.level = level;
        random = new Random();
        df = new DecimalFormat("#.##");
        listOfMods = new ArrayList<>();

        loadImage();
        cutSheet(4, colors, colorsSheet);
        cutSheet(4, shapes, shapesSheet);

        image = mergeImages();

        drawMods();
    }

    public void drawMods() {
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
                case "critical" -> critical = drawCrit();
                case "range" -> range = (int) drawWeight(1, 3);
                case "hp" -> hp = drawHp();
                case "mana" -> mana = (int) drawMana();
                case "runeSpace" -> runeSpace = 1;
            }
        }

    }

    private double drawCrit() {
        return switch (level) {
            case 2 -> drawWeight(0.5, 4);
            case 3 -> drawWeight(3, 7);
            case 4 -> drawWeight(5, 15);
            default -> throw new IllegalStateException("Unexpected value: " + level);
        };
    }

    private double drawHp() {
        boolean isNegative = random.nextInt(100) > 90;
        if (isNegative) {
            return Double.parseDouble(df.format(random.nextInt(-5, -1)));
        } else {
            return switch (level) {
                case 0 -> drawWeight(5, 10);
                case 1 -> drawWeight(10, 20);
                case 2 -> drawWeight(15, 30);
                case 3 -> drawWeight(25, 40);
                case 4 -> drawWeight(40, 50);
                default -> throw new IllegalStateException("Unexpected value: " + level);
            };
        }
    }

    private double drawMana() {
        boolean isNegative = random.nextInt(100) > 90;
        if (isNegative) {
            return random.nextInt(-10, -1);
        } else {
            return switch (level) {
                case 0 -> drawWeight(5, 10);
                case 1 -> drawWeight(10, 20);
                case 2 -> drawWeight(20, 40);
                case 3 -> drawWeight(30, 60);
                case 4 -> drawWeight(50, 100);
                default -> throw new IllegalStateException("Unexpected value: " + level);
            };
        }
    }

    public double drawDmg() {
        boolean isNegative = random.nextInt(100) > 90;
        if (isNegative) {
            return Double.parseDouble(df.format(random.nextDouble(-1.0, 0.0)));
        } else {
            return switch (level) {
                case 0 -> drawWeight(0.1, 0.4);
                case 1 -> drawWeight(0.2, 0.7);
                case 2 -> drawWeight(0.5, 1.5);
                case 3 -> drawWeight(0.9, 2.0);
                case 4 -> drawWeight(1.5, 5.0);
                default -> throw new IllegalStateException("Unexpected value: " + level);
            };
        }
    }

    public double drawWeight(double min, double max) {
        double lambda = 1.0;

        double randVal = random.nextDouble();
        double range = max - min;
        double value = min + range * (1 - Math.pow(randVal, lambda));

        return Double.parseDouble(df.format(value));
    }

    @Override
    public void loadImage() {
        try {
            colorsSheet = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/items/colors.png")));
            shapesSheet = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprites/items/shapes.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void cutSheet(int sprites, BufferedImage[] cutImages, BufferedImage spriteSheet) {
        int w = 34;
        int h = 34;
        int x = 0, y = 0;

        for (int i = 0; i < sprites; i++) {
            cutImages[i] = spriteSheet.getSubimage(x, y, w, h);
            x += 34;
        }
    }

    public BufferedImage mergeImages() {
        BufferedImage image = new BufferedImage(34, 34, BufferedImage.TYPE_INT_ARGB);

        int getColor = random.nextInt(0, 4);
        for (int i = 0; i < colors[getColor].getHeight(); i++) {
            for (int j = 0; j < colors[getColor].getWidth(); j++) {
                int pixel = colors[getColor].getRGB(j, i);
                image.setRGB(j, i, pixel);
            }
        }

        int getShape = random.nextInt(0, 4);
        for (int i = 0; i < shapes[getShape].getHeight(); i++) {
            for (int j = 0; j < shapes[getShape].getWidth(); j++) {
                int pixel = shapes[getShape].getRGB(j, i);
                if ((pixel & 0xFF000000) != 0) {
                    image.setRGB(j, i, pixel);
                }
            }
        }

        return image;
    }

}

