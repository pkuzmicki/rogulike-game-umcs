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

        //generatorMansion();
        //generatorBridges();
        //generatorForest();
        generatorCaves();
        //generatorCavesSinglePath();

        generateEntities();

        gp.map.printMap();
    }

    public void generatorMansion() {
        for (int col = 0; col < gp.map.maxWorldCol; col++) {
            for (int row = 0; row < gp.map.maxWorldRow; row++) {
                gp.map.tileMap[col][row].setTileType(1);
            }
        }

        splitArea(0, 0, gp.map.maxWorldCol, gp.map.maxWorldRow, 3, 0, 2, 2, 0.4, 0.6);
    }

    public void generatorBridges() {
        for (int col = 0; col < gp.map.maxWorldCol; col++) {
            for (int row = 0; row < gp.map.maxWorldRow; row++) {
                gp.map.tileMap[col][row].setTileType(0);
            }
        }

        splitArea1(0, 0, gp.map.maxWorldCol, gp.map.maxWorldRow, 5, 1, 1, 1, 0.3, 0.7);

        gp.map.createRoom(0, 0 , "/maps/rooms/room01.txt");
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

            //passage = startCol + random.nextInt(width);
            //gp.map.tileMap[passage][splitLine].setTileType(1);

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

            //passage = startRow + random.nextInt(height);
            //gp.map.tileMap[splitLine][passage].setTileType(0);

            splitArea(startCol, startRow, splitLine - startCol, height, maxSplits - 1, tileType, minWidth, minHeight, minRatio, maxRatio);
            splitArea(splitLine + 1, startRow, startCol + width - splitLine - 1, height, maxSplits - 1, tileType, minWidth, minHeight, minRatio, maxRatio);
        }
    }


    private void splitArea1(int startCol, int startRow, int width, int height, int maxSplits, int tileType, int minHeight, int minWidth, double minRatio, double maxRatio) {
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

            splitArea1(startCol, startRow, width, splitLine - startRow, maxSplits - 1, tileType, minWidth, minHeight, minRatio, maxRatio);
            splitArea1(startCol, splitLine + 1, width, startRow + height - splitLine - 1, maxSplits - 1, tileType, minWidth, minHeight, minRatio, maxRatio);
        } else {
            splitLine = startCol + random.nextInt((int) (width * minRatio), (int) (width * maxRatio));
            for (int row = startRow; row < startRow + height; row++) {
                gp.map.tileMap[splitLine][row].setTileType(tileType);
            }

            splitArea1(startCol, startRow, splitLine - startCol, height, maxSplits - 1, tileType, minWidth, minHeight, minRatio, maxRatio);
            splitArea1(splitLine + 1, startRow, startCol + width - splitLine - 1, height, maxSplits - 1, tileType, minWidth, minHeight, minRatio, maxRatio);
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
        gp.map.createRoom(0,0, "/maps/rooms/room01.txt");

//        if (floodFill(0, 0, gp.map.maxWorldCol, gp.map.maxWorldRow)) {
//            generatorForest();
//        }
    }

    public void generatorCavesSinglePath() {
        int startCol = 1;
        int startRow = 1;
        int endCol = gp.map.maxWorldCol - 1;
        int endRow = gp.map.maxWorldRow - 1;

        int nextCol = startCol;
        int nextRow = startRow;
        int nextSide;

        //for (int i = 0; i < 100; i++) {
        while (gp.map.tileMap[endCol][endRow].tileType != 1) {
            nextSide = random.nextInt(0, 4);
            //if (nextCol > 0 && nextCol < gp.map.maxWorldCol && nextRow > 0 && nextRow < gp.map.maxWorldRow) {
                switch (nextSide) {
                    case 0:
                        nextRow--;
                        if (nextRow >= 0) {
                            gp.map.tileMap[nextCol][nextRow].setTileType(1);
                        } else {
                            nextRow++;
                        }
                        break;
                    case 1:
                        nextRow++;
                        if (nextRow < gp.map.maxWorldRow) {
                            gp.map.tileMap[nextCol][nextRow].setTileType(1);
                        } else {
                            nextRow--;
                        }
                        break;
                    case 2:
                        nextCol--;
                        if (nextCol >= 0) {
                            gp.map.tileMap[nextCol][nextRow].setTileType(1);
                        } else {
                            nextCol++;
                        }
                        break;
                    case 3:
                        nextCol++;
                        if (nextCol < gp.map.maxWorldCol) {
                            gp.map.tileMap[nextCol][nextRow].setTileType(1);
                        } else {
                            nextCol--;
                        }
                        break;
                }
            //}
        }
    }

    public void generatorCaves() {
        int startCol = 1;
        int startRow = 1;
        int endCol = gp.map.maxWorldCol - 1;
        int endRow = gp.map.maxWorldRow - 1;

        int lowCol = startCol;
        int lowRow = startRow;
        int highCol = endCol;
        int highRow = endRow;

        int nextSideForLow;
        int nextSideForHigh;

        for (int i = 0; i < 300; i++) {
            nextSideForLow = random.nextInt(0, 4);
            switch (nextSideForLow) {
                case 0:
                    lowRow--;
                    if (lowRow >= 0) {
                        gp.map.tileMap[lowCol][lowRow].setTileType(1);
                    } else {
                        lowRow++;
                    }
                    break;
                case 1:
                    lowRow++;
                    if (lowRow < gp.map.maxWorldRow) {
                        gp.map.tileMap[lowCol][lowRow].setTileType(1);
                    } else {
                        lowRow--;
                    }
                    break;
                case 2:
                    lowCol--;
                    if (lowCol >= 0) {
                        gp.map.tileMap[lowCol][lowRow].setTileType(1);
                    } else {
                        lowCol++;
                    }
                    break;
                case 3:
                    lowCol++;
                    if (lowCol < gp.map.maxWorldCol) {
                        gp.map.tileMap[lowCol][lowRow].setTileType(1);
                    } else {
                        lowCol--;
                    }
                    break;
            }
            gp.map.tileMap[0][0].setTileType(2);

            nextSideForHigh = random.nextInt(0, 4);
            switch (nextSideForHigh) {
                case 0:
                    highRow--;
                    if (highRow >= 0) {
                        gp.map.tileMap[highCol][highRow].setTileType(1);
                    } else {
                        highRow++;
                    }
                    break;
                case 1:
                    highRow++;
                    if (highRow < gp.map.maxWorldRow) {
                        gp.map.tileMap[highCol][highRow].setTileType(1);
                    } else {
                        highRow--;
                    }
                    break;
                case 2:
                    highCol--;
                    if (highCol >= 0) {
                        gp.map.tileMap[highCol][highRow].setTileType(1);
                    } else {
                        highCol++;
                    }
                    break;
                case 3:
                    highCol++;
                    if (highCol < gp.map.maxWorldCol) {
                        gp.map.tileMap[highCol][highRow].setTileType(1);
                    } else {
                        highCol--;
                    }
                    break;
            }
            gp.map.tileMap[gp.map.maxWorldCol-1][gp.map.maxWorldRow-1].setTileType(2);
        }

        if (floodFill(0, 0, gp.map.maxWorldCol, gp.map.maxWorldRow)) {
            generatorCaves();
        }
    }

    public void generateEntities() {
        int weakEntities = random.nextInt(7, 13);
        int mediumEntities = random.nextInt(3, 7);
        int roughEntities = random.nextInt(1, 3);
        int strongEntities = random.nextInt(0, 3);

        spawnEntities(weakEntities, 13, 0, 0);
        spawnEntities(mediumEntities, 11, 5, 5);
        spawnEntities(roughEntities, 14, 8, 8);
        spawnEntities(strongEntities, 12, 11, 11);
    }

    private void spawnEntities(int entityType, int entityId, int colsOffset, int rowsOffset) {
        int col, row;
        while (entityType > 0) {
            col = random.nextInt(0, gp.map.maxWorldCol);
            row = random.nextInt(0, gp.map.maxWorldRow);

            if (gp.map.tileMap[col][row].tileType != 0 && col > colsOffset && row > rowsOffset) {
                gp.objectManager.createEntity(col, row, entityId);
                entityType--;
            }
        }
    }

    public boolean floodFill(int startCol, int startRow, int endCol, int endRow) {
        if (!isTileWalkable(startCol, startRow) || !isTileWalkable(endCol, endRow)) {
            return false;
        }

        boolean[][] visited = new boolean[gp.map.maxWorldCol][gp.map.maxWorldRow];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startCol, startRow});
        visited[startCol][startRow] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int col = current[0];
            int row = current[1];

            if (col == endCol && row == endRow) {
                return true;
            }

            int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            for (int[] dir : directions) {
                int nextCol = col + dir[0];
                int nextRow = row + dir[1];

                if (isValidTile(nextCol, nextRow, visited)) {
                    queue.add(new int[]{nextCol, nextRow});
                    visited[nextCol][nextRow] = true;
                }
            }
        }

        return false;
    }

    private boolean isValidTile(int col, int row, boolean[][] visited) {
        return col >= 0 && col < gp.map.maxWorldCol &&
                row >= 0 && row < gp.map.maxWorldRow &&
                !visited[col][row] && isTileWalkable(col, row);
    }

    private boolean isTileWalkable(int col, int row) {
        return gp.map.tileMap[col][row].tileType == 1;
    }


}
