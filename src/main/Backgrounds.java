package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Backgrounds {
    GamePanel gp;

    BufferedImage background_level;
    BufferedImage background_hideout;

    BufferedImage[] background_tiles = new BufferedImage[4];
    BufferedImage background_level1;

    Random random = new Random();

    public Backgrounds(GamePanel gamePanel) {
        this.gp = gamePanel;
        background_level = gp.getImage("/sprites/ui/background_galaxy.png");
        background_hideout = gp.getImage("/sprites/ui/background2.png");
        background_level1 = gp.getImage("/sprites/ui/background_tiles.png");

        cutSheet(4, background_tiles, background_level1);
    }

    public void cutSheet(int sprites, BufferedImage[] cutImages, BufferedImage spriteSheet) {
        int w = 32;
        int h = 32;
        int x = 0, y = 0;

        for (int i = 0; i < sprites; i++) {
            cutImages[i] = spriteSheet.getSubimage(x, y, w, h);
            x += w;
        }
    }

    public void setBackground(Graphics2D g2) {
        int w = 0;
        int h = 0;
        int tileType;
        for (int i = 0; i < (gp.screenHeight / 32) + 1; i++) {
            for (int j = 0; j < gp.screenWidth / 32; j++) {
                tileType = random.nextInt(0, 100);
                if (tileType < 40) {
                    g2.drawImage(background_tiles[3], w, h, null);
                }
                if (tileType >= 40 && tileType < 80) {
                    g2.drawImage(background_tiles[2], w, h, null);
                }
                if (tileType >= 80 && tileType < 90) {
                    g2.drawImage(background_tiles[1], w, h, null);
                }
                if (tileType >= 90 && tileType < 100) {
                    g2.drawImage(background_tiles[0], w, h, null);
                }

                w += 32;
            }
            h += 32;
            w = 0;
        }
    }

    public void draw(Graphics2D g2) {
        if (gp.player.isInHideout) {
            g2.drawImage(background_hideout, -200, -100,  null);
        } else {
            //g2.drawImage(background_level, 0, 0, null);
            setBackground(g2);
        }

    }
}
