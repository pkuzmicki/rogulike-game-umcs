package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class UI {
    GamePanel gp;
    public boolean isGameOver = false;

    BufferedImage deathScreen;
    BufferedImage hp;
    BufferedImage mana;
    BufferedImage[] hpBar = new BufferedImage[10];
    BufferedImage[] manaBar = new BufferedImage[10];

    Font wizardFont;

    public UI(GamePanel gamePanel) {
        this.gp = gamePanel;
        loadImage();
        wizardFont = loadFont();
        cutSheet(10, hpBar, hp);
        cutSheet(10, manaBar, mana);
    }

    public Font loadFont( ) {
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/WizardOfTheMoon-YG5y.ttf"));
            return font.deriveFont(30f);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadImage() {
        try {
            hp = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/sprites/ui/hp_bar.png"))));
            mana = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/sprites/ui/mana_bar.png"))));
            deathScreen = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/sprites/ui/YOU_DIED.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public void cutSheet(int sprites, BufferedImage[] cutImages, BufferedImage spriteSheet) {
        int w = 66;
        int h = 66;
        int x = 0, y = 0;

        for (int i = 0; i < sprites; i++) {
            cutImages[i] = spriteSheet.getSubimage(x, y, w, h);
            x += 66;
        }
    }

    public void draw(Graphics2D g2) {

//        g2.setFont(wizardFont);
//        if (gp.turnManager.globalTurn < 8) {
//            g2.drawString("W", gp.map.tileMap[0][1].x + gp.map.tileWidth/2 + gp.map.dx, gp.map.tileMap[0][1].y + gp.map.tileHeight/2 + gp.map.dy);
//            g2.drawString("A", gp.map.tileMap[1][0].x + gp.map.tileWidth/2 + gp.map.dx, gp.map.tileMap[1][0].y + gp.map.tileHeight/2 + gp.map.dy);
//            g2.drawString("S", gp.map.tileMap[2][1].x + gp.map.tileWidth/2 + gp.map.dx, gp.map.tileMap[2][1].y + gp.map.tileHeight/2 + gp.map.dy);
//            g2.drawString("D", gp.map.tileMap[1][2].x + gp.map.tileWidth/2 + gp.map.dx, gp.map.tileMap[1][2].y + gp.map.tileHeight/2 + gp.map.dy);
//        }
//        if (gp.turnManager.globalTurn > 8 && gp.turnManager.globalTurn < 16 ) {
//            g2.drawString("->", gp.map.tileMap[0][1].x + gp.map.tileWidth/2 + gp.map.dx, gp.map.tileMap[0][1].y + gp.map.tileHeight/2 + gp.map.dy);
//            g2.drawString("<-", gp.map.tileMap[1][0].x + gp.map.tileWidth/2 + gp.map.dx, gp.map.tileMap[1][0].y + gp.map.tileHeight/2 + gp.map.dy);
//            g2.drawString("<-", gp.map.tileMap[2][1].x + gp.map.tileWidth/2 + gp.map.dx, gp.map.tileMap[2][1].y + gp.map.tileHeight/2 + gp.map.dy);
//            g2.drawString("->", gp.map.tileMap[1][2].x + gp.map.tileWidth/2 + gp.map.dx, gp.map.tileMap[1][2].y + gp.map.tileHeight/2 + gp.map.dy);
//        }



        double scale = 2.2;
        int width = (int) (hpBar[0].getWidth() * scale);
        int height = (int) (hpBar[0].getHeight() * scale);

        int hp = (int) (gp.player.currentHP / gp.player.maxHP * 100);
        int hpX = 0;
        int hpY = (int) (gp.screenHeight - hpBar[0].getHeight() * scale);

        if (hp > 0 && hp <= 15) {
            g2.drawImage(hpBar[7], hpX, hpY, width, height, null);
        } else if (hp > 15 && hp <= 25) {
            g2.drawImage(hpBar[6], hpX, hpY, width, height, null);
        } else if (hp > 25 && hp <= 40) {
            g2.drawImage(hpBar[5], hpX, hpY, width, height, null);
        } else if (hp > 40 && hp <= 50) {
            g2.drawImage(hpBar[4], hpX, hpY, width, height, null);
        } else if (hp > 50 && hp <= 60) {
            g2.drawImage(hpBar[3], hpX, hpY, width, height, null);
        } else if (hp > 60 && hp <= 75) {
            g2.drawImage(hpBar[2], hpX, hpY, width, height, null);
        } else if (hp > 75 && hp <= 90) {
            g2.drawImage(hpBar[1], hpX, hpY, width, height, null);
        } else if (hp > 90 && hp <= 100){
            g2.drawImage(hpBar[0], hpX, hpY, width, height, null);
        } else {
            g2.drawImage(hpBar[8], hpX, hpY, width, height, null);
        }

        g2.drawImage(manaBar[8], hpX, hpY, width, height, null);
        g2.drawImage(hpBar[9], hpX, hpY, width, height, null);
        g2.drawString(String.valueOf(gp.player.currentHP), hpX + 80, hpY + 80);

        int mana = (int) ((double) gp.player.currentMana / gp.player.maxMana * 100);
        int manaX = (int) (gp.screenWidth - manaBar[0].getWidth() * scale);
        int manaY = (int) (gp.screenHeight - manaBar[0].getHeight() * scale);

        if (mana > 0 && mana <= 15) {
            g2.drawImage(manaBar[7], manaX, manaY, width, height, null);
        } else if (mana > 15 && mana <= 25) {
            g2.drawImage(manaBar[6], manaX, manaY, width, height, null);
        } else if (mana > 25 && mana <= 40) {
            g2.drawImage(manaBar[5], manaX, manaY, width, height, null);
        } else if (mana > 40 && mana <= 50) {
            g2.drawImage(manaBar[4], manaX, manaY, width, height, null);
        } else if (mana > 50 && mana <= 60) {
            g2.drawImage(manaBar[3], manaX, manaY, width, height, null);
        } else if (mana > 60 && mana <= 75) {
            g2.drawImage(manaBar[2], manaX, manaY, width, height, null);
        } else if (mana > 75 && mana <= 90) {
            g2.drawImage(manaBar[1], manaX, manaY, width, height, null);
        } else if (mana > 90 && mana <= 100) {
            g2.drawImage(manaBar[0], manaX, manaY, width, height, null);
        } else {
            g2.drawImage(manaBar[8], manaX, manaY, width, height, null);
        }

        g2.drawImage(manaBar[8], manaX, manaY, width, height, null);
        g2.drawImage(manaBar[9], manaX, manaY, width, height, null);
        g2.drawString(String.valueOf(gp.player.currentMana), manaX + 30, manaY + 80);


        if (isGameOver) {
            g2.drawImage(deathScreen,  0, 0, gp.screenWidth, gp.screenHeight, null);
        }
    }
}
