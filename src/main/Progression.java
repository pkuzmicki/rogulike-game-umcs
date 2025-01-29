package main;

import objects.Entity;

import java.util.Random;

public class Progression {
    GamePanel gp;
    Random random;

    public Progression(GamePanel gamePanel) {
        this.gp = gamePanel;
        random = new Random();
        gameInit();
    }

    public void gameInit() {
        gp.player.teleportToHideout();
    }

    public void setStartAndFinish() {
//        int[][] locations = {{0, 0},    {0, gp.map.maxWorldRow}, {gp.map.maxWorldCol, 0}, {gp.map.maxWorldCol, gp.map.maxWorldRow}};
//        int startPoint = random.nextInt(0, 4);
//        int[] endPoints = {3, 2, 1, 0};
//        int endPoint = endPoints[startPoint];

        gp.map.createRoom(0, 0, "/maps/rooms/room_level_start.txt", gp.map.tileMap);
        gp.map.createRoom(gp.map.maxWorldCol - 3, gp.map.maxWorldRow - 3, "/maps/rooms/room_level_end.txt", gp.map.tileMap);
    }

    public void chooseLevel() {
        int levelID = random.nextInt(0, 2);
        switch (levelID) {
            case 0 -> levelForest();
            case 1 -> levelCaves();
        }
    }

    public void levelCaves() {
        gp.map.resizeMap(30, 30);
        gp.dgen.generatorCaves(0, 0, gp.map.maxWorldCol - 1, gp.map.maxWorldRow - 1);
        gp.egen.generateEntities();
        setStartAndFinish();
        gp.player.teleportToLevel(1, 1);
    }

    public void levelForest() {
        gp.map.resizeMap(20, 40);
        gp.dgen.generatorForest();
        gp.egen.generateForest();
        setStartAndFinish();
        gp.player.teleportToLevel(1, 1);
    }

//    public boolean checkQuestKillAll() {
//        return gp.objectManager.entities.get(1) == null;
//    }
//
//    public boolean checkQuestKillBoss(int bossID) {
//        for (Entity entity : gp.objectManager.entities) {
//            if (entity.id == bossID) {
//                return false;
//            }
//        }
//        return true;
//    }

}
