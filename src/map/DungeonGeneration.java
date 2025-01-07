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

        //generatorFortress();
        generatorForest(15, 100);
        //generatorCaves();
        //generatorCavesSinglePath();

        //generateEntities(10);
    }

    public void generatorFortress() {
        int roomCount = 5;

        int nextRoom;
        int col, row;

        nextRoom = random.nextInt(1, 4);
        gp.map.createRoom(0, 0, "/maps/rooms/room0" + nextRoom + ".txt");

        while (roomCount > 0) {
            nextRoom = random.nextInt(1, 4);
            col = random.nextInt( 4, gp.map.maxWorldCol - 4);
            row = random.nextInt( 4, gp.map.maxWorldRow - 4);
            if (gp.map.tileMap[col][row].tileType == 0) {
                gp.map.createRoom(col, row, "/maps/rooms/room0" + nextRoom + ".txt");
                roomCount--;
            }
        }
        gp.map.printMap();
    }

    public void generatorForest(int cols, int rows){
        //gp.map.maxWorldCol = cols;
        //gp.map.maxWorldRow = rows;
        //gp.map.createMap(gp.map.mapStartX, gp.map.mapStartY);

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
        gp.map.printMap();
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
        gp.map.printMap();
    }

    public void generateEntities(int entityCount) {
        int col, row;

        while (entityCount > 0) {
            col = random.nextInt(0, gp.map.maxWorldCol);
            row = random.nextInt(0, gp.map.maxWorldRow);
            if (gp.map.tileMap[col][row].tileType != 0 && col > 5 && row > 5) {
                gp.objectManager.createEntity(col, row, 11);
                entityCount--;
            }
        }

    }

    boolean checkForStability(Tile[][] map, int startRow, int startCol, int endRow, int endCol) {
        Tile[][] tmpMap = map;

        if (startCol < 0 || startRow < 0 || startRow >= gp.map.maxWorldRow || startCol < gp.map.maxWorldCol) {
            return false;
        }

        if (startRow == endRow && startCol == endCol) {
            return true;
        }

        tmpMap[startRow][startCol].setTileType(0);

        return  checkForStability(tmpMap, startRow + 1, startCol, endRow, endCol) ||
                checkForStability(tmpMap, startRow - 1, startCol, endRow, endCol) ||
                checkForStability(tmpMap, startRow, startCol, endRow + 1, endCol) ||
                checkForStability(tmpMap, startRow, startCol - 1, endRow, endCol);
    }



}
