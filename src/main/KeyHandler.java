package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter {
    GamePanel gp;

    public boolean wPressed, aPressed, sPressed, dPressed;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    boolean onePressed, twoPressed, threePressed, fourPressed;

    public KeyHandler(GamePanel gamePanel){
        this.gp = gamePanel;
    }

    public String directionOfAction() {
        String direction = null;
        if (upPressed) direction = "up";
        if (downPressed) direction = "down";
        if (leftPressed) direction = "left";
        if (rightPressed) direction = "right";
        return direction;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            if (gp.player.row > 0 && gp.map.tileMap[gp.player.col][gp.player.row - 1].tileType != -1 && gp.objectManager.entityMap[gp.player.col][gp.player.row - 1] < 10) {
                gp.player.move(0, -1);
                gp.turnManager.globalTurn++;
            }
        }
        //MOVEMENT
        if (key == KeyEvent.VK_S) {
            if (gp.player.row < gp.map.maxWorldRow - 1 && gp.map.tileMap[gp.player.col][gp.player.row + 1].tileType != -1  && gp.objectManager.entityMap[gp.player.col][gp.player.row + 1] < 10) {
                gp.player.move(0 ,1);
                gp.turnManager.globalTurn++;
            }

        }
        if (key == KeyEvent.VK_A ) {
            if (gp.player.col > 0 && gp.map.tileMap[gp.player.col - 1][gp.player.row].tileType != -1  && gp.objectManager.entityMap[gp.player.col - 1][gp.player.row] < 10) {
                gp.player.move(-1, 0);
                gp.turnManager.globalTurn++;
            }
        }
        if (key == KeyEvent.VK_D) {
            if (gp.player.col < gp.map.maxWorldCol - 1 && gp.map.tileMap[gp.player.col + 1][gp.player.row].tileType != -1  && gp.objectManager.entityMap[gp.player.col + 1][gp.player.row] < 10) {
                gp.player.move(1, 0);
                gp.turnManager.globalTurn++;
            }
        }
        //ATTACK
        if (key == KeyEvent.VK_UP) {
            upPressed = true;
//            if (gp.player.row > 0) {
//                //gamePanel.map.tileMap[gamePanel.player.col][gamePanel.player.row - 1].setTileType(2);
//                gp.castingManager.testicularTorsion.cast(gp.player.dmg, directionOfAction());
//                System.out.println(directionOfAction());
//                gp.turnManager.globalTurn++;
//            }
//            upPressed = false;
        }
        if (key == KeyEvent.VK_DOWN) {
            downPressed = true;
//            if (gp.player.row < gp.map.maxWorldRow - 1) {
//                //gamePanel.map.tileMap[gamePanel.player.col][gamePanel.player.row + 1].setTileType(2);
//                gp.castingManager.testicularTorsion.cast(gp.player.dmg, directionOfAction());
//                gp.turnManager.globalTurn++;
//            }
//            downPressed = false;
        }
        if (key == KeyEvent.VK_LEFT) {
            leftPressed = true;
//            if (gp.player.col > 0) {
//                //gamePanel.map.tileMap[gamePanel.player.col - 1][gamePanel.player.row].setTileType(2);
//                gp.castingManager.testicularTorsion.cast(gp.player.dmg, directionOfAction());
//                gp.turnManager.globalTurn++;
//            }
//            leftPressed = false;
        }

        if (key == KeyEvent.VK_RIGHT) {
            rightPressed = true;
//            if (gp.player.col < gp.map.maxWorldCol + 1) {
//                gp.castingManager.testicularTorsion.cast(gp.player.dmg, directionOfAction());
//                gp.turnManager.globalTurn++;
//            }
//            rightPressed = false;
        }

        if (key == KeyEvent.VK_1) {
            gp.map.tileMap[gp.player.col + 1][gp.player.row].setTileType(2);
            gp.map.tileMap[gp.player.col - 1][gp.player.row].setTileType(2);
            gp.map.tileMap[gp.player.col][gp.player.row + 1].setTileType(2);
            gp.map.tileMap[gp.player.col][gp.player.row - 1].setTileType(2);
        }

        gp.update();
        gp.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (key == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (key == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (key == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }
}
