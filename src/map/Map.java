package map;

import main.GamePanel;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class Map {
    GamePanel gp;
    public final int tileWidth = 151;
    public final int tileHeight = 51;

    public int maxWorldCol = 30;
    public int maxWorldRow = 30;

    public Tile[][] tileMap;

    public final int mapStartX, mapStartY;

    public int dx = 0, dy = 0;

    public Map(GamePanel gamePanel){
        this.gp = gamePanel;

        mapStartX = gamePanel.screenWidth / 2 - tileWidth / 2;
        mapStartY = gamePanel.screenHeight / 2 - tileHeight / 2;

        tileMap = new Tile[maxWorldCol][maxWorldRow];

        createMap(mapStartX, mapStartY, maxWorldCol, maxWorldRow, tileMap);

        //createRoom(1, 1, "/maps/room01.txt");
        //createRoom(1, 5, "/maps/room02.txt");
        //createRoom(5, 2, "/maps/room03.txt");

        //printMap();
    }

    public void createMap(int beginX, int beginY, int maxCol, int maxRow, Tile[][] tileMap){
        int tileXPosition = beginX;
        int tileYPosition = beginY;
        int startX = tileXPosition;
        int startY = tileYPosition;

        for (int col = 0; col < maxCol; col++) {
            for (int row = 0; row < maxRow; row++) {

                tileMap[col][row] = new Tile(tileXPosition, tileYPosition);
                tileMap[col][row].row = row;
                tileMap[col][row].col = col;
                tileMap[col][row].setTileType(0);

                tileXPosition += tileWidth/2;
                tileYPosition += tileHeight/2;
            }
            startX -= tileWidth/2;
            startY += tileHeight/2;

            tileXPosition = startX;
            tileYPosition = startY;
        }
    }

    public void resizeMap(int maxCol, int maxRow) {
        gp.map.maxWorldCol = maxCol;
        gp.map.maxWorldRow = maxRow;
        tileMap = new Tile[maxWorldCol][maxWorldRow];
        gp.objectManager.initializeMap();
        createMap(mapStartX, mapStartY, maxWorldCol, maxWorldRow, tileMap);
    }

    public void createRoom(int startCol, int startRow, String roomPath, Tile[][] tileMap){
        int[][] roomMap = getMap(roomPath);

        for (int col = 0; col < roomMap.length; col++){
            for (int row = 0; row < roomMap[0].length; row++){
                tileMap[col + startCol][row + startRow].setTileType(roomMap[col][row]);
            }
        }
    }

    public int[][] getMap(String filePath){
        ArrayList<int[]> rows = new ArrayList<>();
        try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream(filePath))));
        String line;
        while ((line = reader.readLine()) != null){
            String[] characters = line.split(" ");
            int[] row = new int[characters.length];

            for (int col = 0; col < characters.length; col++){
                row[col] = Integer.parseInt(characters[col]);
            }
            rows.add(row);
        }
            return rows.toArray(new int[0][]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printMap(){
        for (int col = 0; col < maxWorldCol; col++){
            for (int row = 0; row < maxWorldRow; row++){
                System.out.print(tileMap[col][row].tileType + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void draw(Graphics2D g2){
        for (int col = 0; col < maxWorldCol; col++) {
            for (int row = 0; row < maxWorldRow; row++) {
                if (tileMap[col][row].x + dx > -tileWidth && tileMap[col][row].x + dx < gp.screenWidth & tileMap[col][row].y + dy > -tileHeight && tileMap[col][row].y + dy < gp.screenHeight) {
                    g2.drawImage(tileMap[col][row].tileImage, tileMap[col][row].x + dx, tileMap[col][row].y + dy, null);
                }
            }
        }
    }

}
