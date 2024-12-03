package main;

import objects.Entity;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter {
    GamePanel gp;

    public boolean wPressed, aPressed, sPressed, dPressed;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean onePressed, twoPressed, threePressed, fourPressed;

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
        if (!gp.ui.isGameOver) {
            //MOVEMENT
            if (key == KeyEvent.VK_W) {
                wPressed = true;
//                if (gp.player.col > 0 && gp.map.tileMap[gp.player.col - 1][gp.player.row].tileType != -1) {
//                    gp.player.move(-1, 0);
//                    gp.turnManager.globalTurn++;
//
//                    //gp.map.createMap(gp.map.dx -= gp.map.tileWidth/2, gp.map.dy += gp.map.tileHeight/2);
//                }
//                for (Entity entity : gp.objectManager.entities) {
//                    entity.row -= 1;
//                    entity.setPositions();
//                    entity.setSize();
//                }
                gp.player.row += 1;
                gp.map.dx -= gp.map.tileWidth/2;
                gp.map.dy += gp.map.tileHeight/2;
            }
            if (key == KeyEvent.VK_S) {
                sPressed = true;
//                if (gp.player.col < gp.map.maxWorldCol - 1 && gp.map.tileMap[gp.player.col + 1][gp.player.row].tileType != -1) {
//                    gp.player.move(1, 0);
//                    gp.turnManager.globalTurn++;
//
//                    //gp.map.createMap(gp.map.dx += gp.map.tileWidth/2, gp.map.dy -= gp.map.tileHeight/2);
//                }

//                for (Entity entity : gp.objectManager.entities) {
//                    entity.row += 1;
//                    entity.setPositions();
//                    entity.setSize();
//                }
                gp.player.row -= 1;
                gp.map.dx += gp.map.tileWidth/2;
                gp.map.dy -= gp.map.tileHeight/2;
            }
            if (key == KeyEvent.VK_A) {
                aPressed = true;
//                if (gp.player.row > 0 && gp.map.tileMap[gp.player.col][gp.player.row - 1].tileType != -1) {
//                    gp.player.move(0, -1);
//                    gp.turnManager.globalTurn++;
//
//                    //gp.map.createMap(gp.map.dx += gp.map.tileWidth/2, gp.map.dy += gp.map.tileHeight/2);
//                }

//                for (Entity entity : gp.objectManager.entities) {
//                    entity.col += 1;
//                    entity.setPositions();
//                    entity.setSize();
//                }
                gp.player.col -= 1;
                gp.map.dx += gp.map.tileWidth/2;
                gp.map.dy += gp.map.tileHeight/2;
            }
            if (key == KeyEvent.VK_D) {
                dPressed = true;
                if (gp.player.row < gp.map.maxWorldRow - 1 && gp.map.tileMap[gp.player.col][gp.player.row + 1].tileType != -1) {
                    gp.player.move(0, 1);
                    gp.turnManager.globalTurn++;

                    //gp.map.createMap(gp.map.dx -= gp.map.tileWidth/2, gp.map.dy -= gp.map.tileHeight/2);
                }
//                for (Entity entity : gp.objectManager.entities) {
//                    entity.col -= 1;
//                    entity.setPositions();
//                    entity.setSize();
//                }
                gp.player.col += 1;
                gp.map.dx -= gp.map.tileWidth/2;
                gp.map.dy -= gp.map.tileHeight/2;
            }
            //ATTACK
            if (key == KeyEvent.VK_UP) {
                upPressed = true;
            }
            if (key == KeyEvent.VK_DOWN) {
                downPressed = true;
            }
            if (key == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }
            if (key == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }
            //SPELL SELECTION
            if (key == KeyEvent.VK_1) {
                onePressed = true;
                System.out.println("Testicular Torsion!");
                twoPressed = false;
                threePressed = false;
            }
            if (key == KeyEvent.VK_2) {
                twoPressed = true;
                System.out.println("Lifebloom");
                onePressed = false;
                threePressed = false;
            }
            if (key == KeyEvent.VK_3) {
                threePressed = true;
                System.out.println("Rolling Magma");
                onePressed = false;
                twoPressed = false;
            }

            gp.update();
            gp.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
//        if (key == KeyEvent.VK_W) {
//            wPressed = false;
//        }
//        if (key == KeyEvent.VK_S) {
//            sPressed = false;
//        }
//        if (key == KeyEvent.VK_A) {
//            aPressed = false;
//        }
//        if (key == KeyEvent.VK_D) {
//            dPressed = false;
//        }
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
