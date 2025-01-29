package map;

import main.GamePanel;

import java.util.*;

public class DungeonGeneration {
    GamePanel gp;
    Random random;
    boolean isMapValid;
    public DungeonGeneration(GamePanel gamePanel) {
        this.gp = gamePanel;
        random = new Random();

        gp.map.resizeMap(10,10);

        //generatorMansion();
        //generatorBridges();

        gp.map.createRoom(0, 0, "/maps/rooms/room_level_end.txt", gp.map.tileMap);
        //gp.map.printMap();
    }

    public void generatorMansion() {
        for (int col = 0; col < gp.map.maxWorldCol; col++) {
            for (int row = 0; row < gp.map.maxWorldRow; row++) {
                gp.map.tileMap[col][row].setTileType(1);
            }
        }

        splitArea(0, 0, gp.map.maxWorldCol, gp.map.maxWorldRow, 4, 0, 2, 2, 0.4, 0.6);
    }

    public void generatorBridges() {
        for (int col = 0; col < gp.map.maxWorldCol; col++) {
            for (int row = 0; row < gp.map.maxWorldRow; row++) {
                gp.map.tileMap[col][row].setTileType(0);
            }
        }

        splitForBridges(0, 0, gp.map.maxWorldCol, gp.map.maxWorldRow, 5, 1, 1, 1, 0.3, 0.7);
    }

    private void splitArea(int startCol, int startRow, int width, int height, int maxSplits, int tileType, int minHeight, int minWidth, double minRatio, double maxRatio) {
        if (maxSplits == 0 || width <= minWidth || height <= minHeight) {
            return;
        }

        boolean divideHorizontally = random.nextBoolean();
        int splitLine;
        int passage;

        if (divideHorizontally) {
            int minLine = (int) (height * minRatio);
            int maxLine = (int) (height * maxRatio);

            if (maxLine <= minLine || minLine <= 0 || maxLine >= height) {
                return;
            }

            splitLine = startRow + random.nextInt(minLine, maxLine);

            for (int col = startCol; col < startCol + width; col++) {
                gp.map.tileMap[col][splitLine].setTileType(tileType);
            }

            passage = startCol + random.nextInt(width);
            gp.map.tileMap[passage][splitLine].setTileType(1);

            splitArea(startCol, startRow, width, splitLine - startRow, maxSplits - 1, tileType, minWidth, minHeight, minRatio, maxRatio);
            splitArea(startCol, splitLine + 1, width, startRow + height - splitLine - 1, maxSplits - 1, tileType, minWidth, minHeight, minRatio, maxRatio);
        } else {

            int minLine = (int) (width * minRatio);
            int maxLine = (int) (width * maxRatio);

            if (maxLine <= minLine || minLine <= 0 || maxLine >= width) {
                return;
            }

            splitLine = startCol + random.nextInt(minLine, maxLine);

            for (int row = startRow; row < startRow + height; row++) {
                gp.map.tileMap[splitLine][row].setTileType(tileType);
            }

            passage = startRow + random.nextInt(height);
            gp.map.tileMap[splitLine][passage].setTileType(0);

            splitArea(startCol, startRow, splitLine - startCol, height, maxSplits - 1, tileType, minWidth, minHeight, minRatio, maxRatio);
            splitArea(splitLine + 1, startRow, startCol + width - splitLine - 1, height, maxSplits - 1, tileType, minWidth, minHeight, minRatio, maxRatio);
        }
    }


    private void splitForBridges(int startCol, int startRow, int width, int height, int maxSplits, int tileType, int minHeight, int minWidth, double minRatio, double maxRatio) {
        if (maxSplits == 0 || width <= minWidth || height <= minHeight) {
            return;
        }

        boolean divideHorizontally = random.nextBoolean();
        int splitLine;
        int passage;

        if (divideHorizontally) {
            splitLine = startRow + random.nextInt((int) (height * minRatio), (int) (height * maxRatio));

            for (int col = startCol; col < startCol + width; col++) {
                gp.map.tileMap[col][splitLine].setTileType(tileType);
            }

            passage = startCol + random.nextInt(width);
            gp.map.tileMap[passage][splitLine].setTileType(1);

            splitForBridges(startCol, startRow, width, splitLine - startRow, maxSplits - 1, tileType, minWidth, minHeight, minRatio, maxRatio);
            splitForBridges(startCol, splitLine + 1, width, startRow + height - splitLine - 1, maxSplits - 1, tileType, minWidth, minHeight, minRatio, maxRatio);
        } else {
            splitLine = startCol + random.nextInt((int) (width * minRatio), (int) (width * maxRatio));
            for (int row = startRow; row < startRow + height; row++) {
                gp.map.tileMap[splitLine][row].setTileType(tileType);
            }

            splitForBridges(startCol, startRow, splitLine - startCol, height, maxSplits - 1, tileType, minWidth, minHeight, minRatio, maxRatio);
            splitForBridges(splitLine + 1, startRow, startCol + width - splitLine - 1, height, maxSplits - 1, tileType, minWidth, minHeight, minRatio, maxRatio);
        }
    }

    public void generatorForest(){
        int nextState;
        for (int i = 0; i < gp.map.maxWorldCol; i++) {
            for (int j = 0; j < gp.map.maxWorldRow; j++) {
                nextState = random.nextInt(0, 4);
                if (nextState == 1 || nextState == 2 || nextState == 3) {
                    gp.map.tileMap[i][j].setTileType(1);
                } else {
                    gp.map.tileMap[i][j].setTileType(0);
                }
            }
        }
    }

    private void move(Tile drunkard, int dCol, int dRow) {
        int newCol = drunkard.x + dCol;
        int newRow = drunkard.y + dRow;

        if (newCol >= 0 && newCol < gp.map.maxWorldCol && newRow >= 0 && newRow < gp.map.maxWorldRow) {
            drunkard.x = newCol;
            drunkard.y = newRow;
            gp.map.tileMap[newCol][newRow].setTileType(1);
        }
    }

    public void generatorCaves(int startCol, int startRow, int endCol, int endRow) {
        Tile firstDrunkard = new Tile(startCol, startRow);
        Tile secondDrunkard = new Tile(endCol, endRow);

        int[][] moves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int nextMove;

        while (!floodFill(startCol, startRow, endCol, endRow)) {
            nextMove = random.nextInt(moves.length);
            move(firstDrunkard, moves[nextMove][0], moves[nextMove][1]);
            nextMove = random.nextInt(moves.length);
            move(secondDrunkard, moves[nextMove][0], moves[nextMove][1]);
        }
    }

    boolean[][] visitedFields;

    public void initArray() {
        visitedFields = new boolean[gp.map.maxWorldCol][gp.map.maxWorldRow];
    }

    public boolean floodFill(int startCol, int startRow, int endCol, int endRow) {
        initArray();

        Queue<Tile> tilesToCheck = new LinkedList<>();

        tilesToCheck.add(new Tile(startCol, startRow));
        visitedFields[startCol][startRow] = true;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!tilesToCheck.isEmpty()) {
            Tile current = tilesToCheck.poll();

            if (current.x == endCol && current.y == endRow) {
                return true;
            }

            for (int[] dir : directions) {
                int newCol = current.x + dir[0];
                int newRow = current.y + dir[1];

                if (newCol >= 0 && newCol < gp.map.maxWorldCol && newRow >= 0 && newRow < gp.map.maxWorldRow) {
                    if (!visitedFields[newCol][newRow] && gp.map.tileMap[newCol][newRow].tileType != 0) {
                        tilesToCheck.add(new Tile(newCol, newRow));
                        visitedFields[newCol][newRow] = true;
                    }
                }
            }
        }

        return false;
    }

    public void clearMap() {
        for (int i = 0; i < gp.map.maxWorldCol; i++) {
            for (int j = 0; j < gp.map.maxWorldRow; j++) {
                gp.map.tileMap[j][i].setTileType(0);
            }
        }
    }
}
