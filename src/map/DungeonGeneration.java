package map;

import combat.RangeSpell;
import main.GamePanel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DungeonGeneration {
    GamePanel gp;
    Random random;
    public DungeonGeneration(GamePanel gamePanel) {
        this.gp = gamePanel;
        random = new Random();
        //generator1();
        //generator2();
        //generator3();
        generator4();
    }

    //POKOJE Z DLUGIMI KORYTARZAMI
    public void generator1() {
        int[] roomX = new int[20];
        int[] roomY = new int[20];

        for (int i = 0; i < 10; i++) {
            roomX[i] = random.nextInt(0, gp.map.maxWorldCol - 3);
            roomY[i] = random.nextInt(0, gp.map.maxWorldRow - 3);
            gp.map.createRoom(roomX[i], roomY[i], "/maps/room01.txt");


            int pathX = Math.abs(roomX[i+1] - roomX[i]);
            int pathY = Math.abs(roomY[i+1] - roomY[i]);
            System.out.println(pathX +" "+pathY);

            for(int x = roomX[i]; x < pathX; x++){
                gp.map.tileMap[x][roomY[i]].setTileType(1);
            }
            for(int y = roomX[i]; y < pathY; y++){
                gp.map.tileMap[roomX[i]][y].setTileType(1);
            }


        }
        gp.map.printMap();
    }

    //POKOJE POLACZONE ZE SOBA JAK W ISAACU TYLKO BEZ DRZWI
    //MOZNA ZMODIFIKOWAC TAK ZE SIE Z ODSTEPEM POMIEDZY KAZDY POKOJ RESPI I DODAC DRZWI POMIEDZT NIMI
    public void generator2(){
        int maxRooms = 20;
        int minRooms;
        int mapCenterCol = gp.map.maxWorldCol/2 - 1;
        int mapCenterRow = gp.map.maxWorldRow/2 - 1;

        int roomCol;
        int roomRow;

        int howManyNeighbouringRooms = random.nextInt(2, 5);

        gp.map.createRoom(mapCenterCol, mapCenterRow, "/maps/room01.txt");

        int nextSide;
        List<Integer> possibleNeighbours = new ArrayList<>();
        Collections.addAll(possibleNeighbours, 1, 2, 3, 4);
        //List<Integer> chosenNeighbours = new ArrayList<>();

        roomCol = mapCenterCol;
        roomRow = mapCenterRow;

        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < howManyNeighbouringRooms; i++) {
                nextSide = random.nextInt(possibleNeighbours.size());
                possibleNeighbours.remove(nextSide);
                //chosenNeighbours.add(nextSide);
                if (roomCol > 0 && roomCol < gp.map.maxWorldCol && roomRow > 0 && roomCol < gp.map.maxWorldRow) {
                    switch (nextSide) {
                        case 1:
                            roomRow -= 3;
                            gp.map.createRoom(roomCol, roomRow, "/maps/room01.txt");
                            break;
                        case 2:
                            roomRow += 3;
                            gp.map.createRoom(roomCol, roomRow, "/maps/room01.txt");
                            break;
                        case 3:
                            roomCol -= 3;
                            gp.map.createRoom(roomCol, roomRow, "/maps/room01.txt");
                            break;
                        case 4:
                            roomCol += 3;
                            gp.map.createRoom(roomCol, roomRow, "/maps/room01.txt");
                            break;
                    }
                }

            }
            possibleNeighbours.clear();
            Collections.addAll(possibleNeighbours,1, 2, 3, 4);
        }

        gp.map.printMap();
    }

    //JASKINIE / SLEPE BLADZENIE
    public void generator3() {
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

    public void generator4() {
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

        }
        gp.map.printMap();
    }

    void floodFill(int[][] tileMap, int x, int y, int newType){

    }


}
