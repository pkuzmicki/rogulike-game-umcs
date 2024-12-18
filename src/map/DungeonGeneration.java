package map;

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
        generator1();
        //generator2();
        //generator3();
        //generator4();
    }

    public void generator1() {
        int nextRoomID = random.nextInt(1, 4);
        gp.map.createRoom(0, 0, "/maps/rooms/room0" + nextRoomID + ".txt");

        int nextCol = 0;
        int nextRow = 0;

        switch(nextRoomID) {
            case 1:
                nextCol++;
                nextRow++;
                break;
            case 2:
                nextCol+=2;
                nextRow++;
                break;
            case 3:
                nextCol+=2;
                nextRow+=2;
                break;
        }

        int direction = random.nextInt(0, 4);
        int nextCorridorLength = random.nextInt(3, 11);
        for (int i = 0; i < nextCorridorLength; i++) {
            gp.map.tileMap[nextCol][nextRow].setTileType(1);
            switch (direction){
                case 0 -> nextRow++;
                case 1 -> nextRow--;
                case 2 -> nextCol++;
                case 3 -> nextCol--;
            }
        }


        gp.map.printMap();
    }

    //POKOJE POLACZONE ZE SOBA JAK W ISAACU TYLKO BEZ DRZWI
    //MOZNA ZMODIFIKOWAC TAK ZE SIE Z ODSTEPEM POMIEDZY KAZDY POKOJ RESPI I DODAC DRZWI POMIEDZT NIMI
    public void generator2(){
        int mapCenterCol = gp.map.maxWorldCol/2 - 1;
        int mapCenterRow = gp.map.maxWorldRow/2 - 1;

        int roomCol;
        int roomRow;

        int howManyNeighbouringRooms = random.nextInt(2, 5);

        gp.map.createRoom(mapCenterCol, mapCenterRow, "/maps/rooms/room01.txt");

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
                            gp.map.createRoom(roomCol, roomRow, "/maps/rooms/room01.txt");
                            break;
                        case 2:
                            roomRow += 3;
                            gp.map.createRoom(roomCol, roomRow, "/maps/rooms/room01.txt");
                            break;
                        case 3:
                            roomCol -= 3;
                            gp.map.createRoom(roomCol, roomRow, "/maps/rooms/room01.txt");
                            break;
                        case 4:
                            roomCol += 3;
                            gp.map.createRoom(roomCol, roomRow, "/maps/rooms/room01.txt");
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

    void floodFill(int[][] tileMap, int x, int y, int newType){

    }


}
