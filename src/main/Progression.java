package main;

import java.util.Random;

public class Progression {
    GamePanel gp;
    Random random;
    public int startPoint;
    public int levelCounter;

    private int forestSize = 10;
    private int cavesSize = 10;

    public Progression(GamePanel gamePanel) {
        this.gp = gamePanel;
        random = new Random();
        gameInit();
    }

    public void gameInit() {
        gp.player.teleportToHideout();
    }

    public int getStart() {
        return random.nextInt(1, 4);
    }

    public void setStart(int startPoint) {
        int[][] locations = {{0, 0},    {0, gp.map.maxWorldRow - 3}, {gp.map.maxWorldCol - 3, 0}, {gp.map.maxWorldCol - 3, gp.map.maxWorldRow - 3}};
        int[] endPoints = {3, 2, 1, 0};
        int endPoint = endPoints[startPoint];

        gp.map.createRoom(locations[startPoint][0], locations[startPoint][1], "/maps/rooms/room_level_start.txt", gp.map.tileMap);
        gp.map.createRoom(locations[endPoint][0], locations[endPoint][1], "/maps/rooms/room_level_end.txt", gp.map.tileMap);
    }

    public void chooseLevel() {
        int levelID = random.nextInt(0, 2);
        switch (levelID) {
            case 0 -> levelForest();
            case 1 -> levelCaves();
        }
        increaseDifficulty();
        levelCounter++;
    }

    public void levelCaves() {
        startPoint = getStart();
        gp.map.resizeMap(cavesSize, cavesSize);
        if (startPoint == 0 || startPoint == 3) {
            gp.dgen.generatorCaves(0, 0, gp.map.maxWorldCol - 1, gp.map.maxWorldRow - 1);
        } else {
            gp.dgen.generatorCaves(gp.map.maxWorldCol - 1, 0, 0, gp.map.maxWorldRow - 1);
        }
        gp.egen.generateCaves();
        setStart(startPoint);
        gp.player.teleportToLevel();
    }

    public void levelForest() {
        startPoint = getStart();
        gp.map.resizeMap(forestSize, forestSize);
        gp.dgen.generatorForest();
        gp.egen.generateForest();
        setStart(startPoint);
        gp.player.teleportToLevel();
    }

    public void increaseDifficulty() {
        forestSize += 5;
        cavesSize += 5;
    }
}
