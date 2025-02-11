package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter {
    GamePanel gp;

    public boolean wPressed, aPressed, sPressed, dPressed;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean onePressed, twoPressed, threePressed;
    public boolean tabPressed = false;
    public boolean enterPressed;
    public boolean xPressed;

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

            //CAST ON SELF
            if (key == KeyEvent.VK_X) {
                xPressed = true;
            }
            //}

            //SPELL SELECTION
            if (key == KeyEvent.VK_1) {
                onePressed = true;
            }
            if (key == KeyEvent.VK_2) {
                twoPressed = true;
            }
            if (key == KeyEvent.VK_3) {
                threePressed = true;
            }

            //OPEN EQ
            if (key == KeyEvent.VK_TAB) {
                if (!gp.player.playerRunes.isEmpty()){
                    tabPressed = !tabPressed;
                    gp.eq.itemSelected = 0;
                    gp.player.isEquipmentOpen = !gp.player.isEquipmentOpen;
                }
            }

            //DROP ITEM
            if (key == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }

            if (key == KeyEvent.VK_F) {
                gp.player.currentMana += 10;
            }



//            if (key == KeyEvent.VK_Z) {
//                System.out.println("list of entities: ");
//                for (int i = 0; i < gp.objectManager.entities.size(); i++) {
//                    System.out.println(gp.objectManager.entities.get(i).name);
//
//                }
//            }

            gp.update();
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

        if (key == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }

        if (key == KeyEvent.VK_X) {
            xPressed = false;
        }
    }
}
