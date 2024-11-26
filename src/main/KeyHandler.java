package main;

import objects.Entity;

import javax.sound.sampled.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;

public class KeyHandler extends KeyAdapter {
    GamePanel gamePanel;

    boolean wPressed, aPressed, sPressed, dPressed;
    boolean upPressed, downPressed, leftPressed, rightPressed;
    boolean onePressed, twoPressed, threePressed, fourPressed;

    public KeyHandler(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        //gamePanel.turnManager.globalTurn++;
        System.out.println(gamePanel.turnManager.globalTurn);
        if (key == KeyEvent.VK_W) {
            if (gamePanel.player.playerRow > 0) {
                gamePanel.player.move(0, -1);
                gamePanel.turnManager.globalTurn++;
            }
        }
        //MOVEMENT
        if (key == KeyEvent.VK_S) {
            if (gamePanel.player.playerRow < gamePanel.map.maxWorldRow - 1) {
                gamePanel.player.move(0 ,1);
                gamePanel.turnManager.globalTurn++;
            }

        }
        if (key == KeyEvent.VK_A) {
            if (gamePanel.player.playerCol > 0) {
                gamePanel.player.move(-1, 0);
                gamePanel.turnManager.globalTurn++;
            }
        }
        if (key == KeyEvent.VK_D) {
            if (gamePanel.player.playerCol < gamePanel.map.maxWorldCol - 1) {
                gamePanel.player.move(1, 0);
                gamePanel.turnManager.globalTurn++;
            }
        }
        //ATTACK
        if (key == KeyEvent.VK_UP) {
            upPressed = true;
            if (gamePanel.player.playerRow > 0) {
                gamePanel.map.tileMap[gamePanel.player.playerCol][gamePanel.player.playerRow - 1].setTileType(2);
                gamePanel.turnManager.globalTurn++;
            }
        }
        if (key == KeyEvent.VK_DOWN) {
            downPressed = true;
            if (gamePanel.player.playerRow < gamePanel.map.maxWorldRow - 1) {
                gamePanel.map.tileMap[gamePanel.player.playerCol][gamePanel.player.playerRow + 1].setTileType(2);
                gamePanel.turnManager.globalTurn++;
            }

        }
        if (key == KeyEvent.VK_LEFT) {
            leftPressed = true;
            if (gamePanel.player.playerCol > 0) {
                gamePanel.map.tileMap[gamePanel.player.playerCol - 1][gamePanel.player.playerRow].setTileType(2);
                gamePanel.turnManager.globalTurn++;
            }
        }
        if (key == KeyEvent.VK_RIGHT) {
            rightPressed = true;
            if (gamePanel.player.playerCol < gamePanel.map.maxWorldCol - 1) {
                int tempTileType = gamePanel.map.tileMap[gamePanel.player.playerCol + 1][gamePanel.player.playerRow].tileType;
                gamePanel.map.tileMap[gamePanel.player.playerCol + 1][gamePanel.player.playerRow].setTileType(2);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                gamePanel.map.tileMap[gamePanel.player.playerCol + 1][gamePanel.player.playerRow].setTileType(tempTileType);

                for (Entity entity : gamePanel.objectManager.entities) {
                    if (entity.col == gamePanel.player.playerCol + 1 && entity.row == gamePanel.player.playerRow && entity.entityId < 10 ) {

                        gamePanel.objectManager.destroyEntity(gamePanel.player.playerCol + 1, gamePanel.player.playerRow);
                        gamePanel.repaint();
                    }
                }
                gamePanel.turnManager.globalTurn++;
            }
        }

        if (key == KeyEvent.VK_1) {
            gamePanel.map.tileMap[gamePanel.player.playerCol + 1][gamePanel.player.playerRow].setTileType(2);
            gamePanel.map.tileMap[gamePanel.player.playerCol - 1][gamePanel.player.playerRow].setTileType(2);
            gamePanel.map.tileMap[gamePanel.player.playerCol][gamePanel.player.playerRow + 1].setTileType(2);
            gamePanel.map.tileMap[gamePanel.player.playerCol][gamePanel.player.playerRow - 1].setTileType(2);
        }
        gamePanel.repaint();
    }
}
