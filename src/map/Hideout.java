package map;

import main.GamePanel;

import java.awt.*;

public class Hideout {
    GamePanel gp;
    public Tile[][] hideoutMap;
    public int maxCol = 20, maxRow = 20;
    public int hideoutX;
    public int hideoutY;

    public Hideout(GamePanel gamePanel){
        this.gp = gamePanel;
        hideoutMap = new Tile[maxCol][maxRow];
        hideoutX = 0;
        hideoutY = (gp.map.tileHeight/2) * 60;
        createHideout();
    }

    public void createHideout() {
        gp.map.createMap(gp.map.mapStartX, gp.map.mapStartY - hideoutY, maxCol, maxRow, hideoutMap);
        gp.map.createRoom(0, 0, "/maps/rooms/hideout", hideoutMap);
    }

    public void draw(Graphics2D g2) {
        for (int col = 0; col < maxCol; col++) {
            for (int row = 0; row < maxRow; row++) {
                g2.drawImage(hideoutMap[col][row].tileImage, hideoutMap[col][row].x + gp.map.dx, hideoutMap[col][row].y + gp.map.dy, null);
            }
        }
    }
}
