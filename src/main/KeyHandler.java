package main;

import objects.Entity;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter {
    GamePanel gp;

    public boolean wPressed, aPressed, sPressed, dPressed;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean onePressed = true, twoPressed, threePressed, fourPressed;
    public boolean xPressed;

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
                if (gp.player.col > 0 && gp.map.tileMap[gp.player.col - 1][gp.player.row].tileType >= 1 && gp.objectManager.entityMap[gp.player.col - 1][gp.player.row] == 0) {
                    gp.player.move(-1, 0);
                    //to sie powinno w move wykonac
                    gp.map.dx -= gp.map.tileWidth/2;
                    gp.map.dy += gp.map.tileHeight/2;

                    gp.y += 1;
                    gp.x += 1;

                    //gp.turnManager.globalTurn++;
                }
            }
            if (key == KeyEvent.VK_S) {
                sPressed = true;
                if (gp.player.col < gp.map.maxWorldCol - 1 && gp.map.tileMap[gp.player.col + 1][gp.player.row].tileType >= 1 && gp.objectManager.entityMap[gp.player.col + 1][gp.player.row] == 0) {
                    gp.player.move(1, 0);
                    gp.map.dx += gp.map.tileWidth/2;
                    gp.map.dy -= gp.map.tileHeight/2;

                    gp.y -= 1;
                    gp.x -= 1;

                    //gp.turnManager.globalTurn++;
                }
            }
            if (key == KeyEvent.VK_A) {
                aPressed = true;
                if (gp.player.row > 0 && gp.map.tileMap[gp.player.col][gp.player.row - 1].tileType >= 1 && gp.objectManager.entityMap[gp.player.col][gp.player.row - 1] == 0) {
                    gp.player.move(0, -1);
                    gp.map.dx += gp.map.tileWidth/2;
                    gp.map.dy += gp.map.tileHeight/2;

                    gp.x += 1;
                    gp.y += 1;

                    //gp.turnManager.globalTurn++;
                }
            }
            if (key == KeyEvent.VK_D) {
                dPressed = true;
                if (gp.player.row < gp.map.maxWorldRow - 1 && gp.map.tileMap[gp.player.col][gp.player.row + 1].tileType >= 1 && gp.objectManager.entityMap[gp.player.col][gp.player.row + 1] == 0) {
                    gp.player.move(0, 1);
                    gp.map.dx -= gp.map.tileWidth/2;
                    gp.map.dy -= gp.map.tileHeight/2;

                    gp.x -= 1;
                    gp.y -= 1;

                    //gp.turnManager.globalTurn++;
                }
            }
            //ATTACK
            if (gp.turnManager.globalTurn%2 == 0) {
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

                if (key == KeyEvent.VK_X) {
                    xPressed = true;
                }
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
            if (key == KeyEvent.VK_X) {
                gp.turnManager.globalTurn++;
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

        if (key == KeyEvent.VK_X) {
            xPressed = false;
        }
        if (key == KeyEvent.VK_M) {
            gp.map.maxWorldCol = 20;
            gp.map.maxWorldRow = 20;
            gp.map.createMap(gp.map.mapStartX, gp.map.mapStartY);
            gp.objectManager.initializeMap();
            //gp.generation.generatorForest();
            gp.generation.generateEntities(10);

            gp.repaint();
        }
    }
}
