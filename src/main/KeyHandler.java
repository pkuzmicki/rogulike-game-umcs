package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter {
    GamePanel gp;

    public boolean wPressed, aPressed, sPressed, dPressed;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean onePressed, twoPressed, threePressed;
    public boolean xPressed;
    public boolean fPressed;

    public KeyHandler(GamePanel gamePanel){
        this.gp = gamePanel;
    }

    public String directionOfAction() {
        String direction = "";
        if (upPressed) direction = "up";
        if (downPressed) direction = "down";
        if (leftPressed) direction = "left";
        if (rightPressed) direction = "right";
        if (xPressed) direction = "self";
        return direction;
    }

    public String directionOfMove() {
        String direction = "";
        if (wPressed) direction = "up";
        if (sPressed) direction = "down";
        if (aPressed) direction = "left";
        if (dPressed) direction = "right";
        return direction;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (!gp.ui.isGameOver) {
            //MOVEMENT
            if (key == KeyEvent.VK_W) {
                wPressed = true;
            }
            if (key == KeyEvent.VK_S) {
                sPressed = true;
            }
            if (key == KeyEvent.VK_A) {
                aPressed = true;
            }
            if (key == KeyEvent.VK_D) {
                dPressed = true;
            }

            //ATTACK
            //if (gp.turnManager.globalTurn%2 == 0) {
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
            //}

            //SPELL SELECTION
            if (key == KeyEvent.VK_1) {
                onePressed = true;
                //twoPressed = false;
                //threePressed = false;
            }
            if (key == KeyEvent.VK_2) {
                twoPressed = true;
                //onePressed = false;
                //threePressed = false;
            }
            if (key == KeyEvent.VK_3) {
                threePressed = true;
                //onePressed = false;
                //twoPressed = false;
            }

            //SELF CAST
            if (key == KeyEvent.VK_X) {
                gp.turnManager.globalTurn++;
            }

            if (key == KeyEvent.VK_F) {
                fPressed = true;
                gp.player.currentMana += 10;
            }

            //DEBUG
            if (key == KeyEvent.VK_M) {
                gp.map.maxWorldCol = 20;
                gp.map.maxWorldRow = 20;
                gp.map.createMap(gp.map.mapStartX, gp.map.mapStartY, gp.map.maxWorldCol, gp.map.maxWorldRow, gp.map.tileMap);
                //gp.objectManager.initializeMap();

                if (!gp.objectManager.entities.isEmpty()) {
                    gp.objectManager.entities.subList(1, gp.objectManager.entities.size()).clear();
                }

                gp.generation.generatorForest();
                gp.generation.generateEntities();
            }

            if (key == KeyEvent.VK_Q) {
                gp.player.teleportToHideout();
            }

            if (key == KeyEvent.VK_E) {
                gp.player.teleportToLevel();
            }

            gp.update();
            //gp.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            wPressed = false;
        }
        if (key == KeyEvent.VK_S) {
            sPressed = false;
        }
        if (key == KeyEvent.VK_A) {
            aPressed = false;
        }
        if (key == KeyEvent.VK_D) {
            dPressed = false;
        }

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

        if (key == KeyEvent.VK_1) {
            onePressed = false;
        }
        if (key == KeyEvent.VK_2) {
            twoPressed = false;
        }
        if (key == KeyEvent.VK_3) {
            threePressed = false;
        }


        if (key == KeyEvent.VK_X) {
            xPressed = false;
        }



        if (key == KeyEvent.VK_F) {
            fPressed = false;
        }
    }
}
