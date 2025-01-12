package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class UI {
    GamePanel gp;
    public boolean isGameOver = false;

    public UI(GamePanel gamePanel) {
        this.gp = gamePanel;
        loadImage();
        cutSheet(10, hpBar, hp);
        cutSheet(10, manaBar, mana);
    }

    BufferedImage deathScreen;
    BufferedImage hp;
    BufferedImage mana;

    public void loadImage() {
        try {
            hp = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/sprites/ui/hp_bar.png"))));
            mana = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/sprites/ui/mana_bar.png"))));
            deathScreen = ImageIO.read((Objects.requireNonNull(getClass().getResourceAsStream("/sprites/ui/YOU_DIED.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    BufferedImage[] hpBar = new BufferedImage[10];
    BufferedImage[] manaBar = new BufferedImage[10];

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
