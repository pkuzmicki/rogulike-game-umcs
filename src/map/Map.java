package map;

import main.GamePanel;
import objects.Entity;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class Map {
    GamePanel gamePanel;
    public final int tileWidth = 151;
    public final int tileHeight = 51;

    public int maxWorldCol = 10;
    public int maxWorldRow = 10;

    public Tile[][] tileMap;

    public final int mapStartX, mapStartY;

    public int dx, dy;

    public Map(GamePanel gamePanel){
        this.gamePanel = gamePanel;

        tileMap = new Tile[maxWorldCol][maxWorldRow];

        mapStartX = gamePanel.screenWidth / 2 - tileWidth / 2;
        mapStartY = gamePanel.screenHeight / 2 - tileHeight / 2;

        createMap(mapStartX - dx, mapStartY - dy);

        createRoom(1, 1, "/maps/room01.txt");
        createRoom(1, 5, "/maps/room02.txt");
        createRoom(5, 2, "/maps/room03.txt");


        //loadMap(getMap("/maps/map01.txt", maxWorldCol, maxWorldRow));
    }

    public void createMap(int beginX, int beginY){
        int tileXPosition = beginX;
        int tileYPosition = beginY;
        int startX = tileXPosition;
        int startY = tileYPosition;

        for (int col = 0; col < maxWorldCol; col++) {
            for (int rows = 0; rows < maxWorldRow; rows++) {

                tileMap[rows][col] = new Tile(tileXPosition, tileYPosition);
                tileMap[rows][col].row = rows;
                tileMap[rows][col].col = col;
                tileMap[rows][col].setTileType(-1);

                tileXPosition += tileWidth/2;
                tileYPosition += tileHeight/2;
            }
            startX -= tileWidth/2;
            startY += tileHeight/2;

            tileXPosition = startX;
            tileYPosition = startY;
        }
    }

    public void createRoom(int startCol, int startRow, String roomPath){
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

//    public void loadMap(int[][] map){
//        for (int col = 0; col < maxWorldCol; col++){
//            for (int row = 0; row < maxWorldRow; row++){
//                tileMap[col][row].setTileType(map[col][row]);
//            }
//        }
//    }

    public void draw(Graphics2D g2){
        for (int col = 0; col < maxWorldCol; col++) {
            for (int row = 0; row < maxWorldRow; row++) {
                //g2.drawImage(tileMap[col][row].tileImage, tileMap[col][row].x + mapStartX, tileMap[col][row].y + mapStartY, null);
                g2.drawImage(tileMap[col][row].tileImage, tileMap[col][row].x, tileMap[col][row].y, null);
            }
        }
    }

}
