import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    public int screenWidth = 1000;
    public int screenHeight = 600;

    Player player = new Player(this);
    Map map = new Map(this);

    public GamePanel(){
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    }

    public void setupGame(){
        player.setPlayerPosition();
    }


    public int tileXPosition = screenWidth / 2;
    public int tileYPosition = 50;

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.red);

        map.draw(g2);

        int tileWidth = 300;
        int tileHeight = 100;

        int[] x1 = {tileXPosition, tileXPosition - tileWidth/2, tileXPosition, tileXPosition + tileWidth/2};
        int[] y1 = {tileYPosition - tileHeight/2, tileYPosition, tileYPosition + tileHeight/2, tileYPosition};


//        for (int cols = 0; cols < map.maxWorldCol; cols++){
//            for (int rows = 0; rows < map.maxWorldRow; rows++){
//                x1 = new int[]{tileXPosition, tileXPosition - 150, tileXPosition, tileXPosition + 150};
//                y1 = new int[]{tileYPosition - 50, tileYPosition, tileYPosition + 50, tileYPosition};
//                tileXPosition += 150;
//                tileYPosition += 50;
//                g2.drawPolygon(x1, y1, 4);
//            }
//            tileXPosition -= tileWidth * 2;
//            tileYPosition -= tileHeight;
//        }

        /*


        g2.setColor(Color.BLUE);
        int[] x1 = {tileXPosition, tileXPosition - tileWidth/2, tileXPosition, tileXPosition + tileWidth/2};
        int[] y1 = {tileYPosition - tileHeight/2, tileYPosition, tileYPosition + tileHeight/2, tileYPosition};
        g2.fillPolygon(x1, y1, 4);
        g2.setColor(Color.WHITE);
        g2.drawString("A1", tileXPosition, tileYPosition);

        g2.setColor(Color.RED);
        tileXPosition += 150;
        tileYPosition += 50;
        x1 = new int[]{tileXPosition, tileXPosition - 150, tileXPosition, tileXPosition + 150};
        y1 = new int[]{tileYPosition - 50, tileYPosition, tileYPosition + 50, tileYPosition};
        g2.fillPolygon(x1, y1, 4);
        g2.setColor(Color.WHITE);
        g2.drawString("B1", tileXPosition, tileYPosition);

        g2.setColor(Color.BLUE);
        tileXPosition += 150;
        tileYPosition += 50;
        x1 = new int[]{tileXPosition, tileXPosition - 150, tileXPosition, tileXPosition + 150};
        y1 = new int[]{tileYPosition - 50, tileYPosition, tileYPosition + 50, tileYPosition};
        g2.fillPolygon(x1, y1, 4);
        g2.setColor(Color.WHITE);
        g2.drawString("C1", tileXPosition, tileYPosition);

        g2.setColor(Color.RED);
        tileXPosition -= tileWidth * 1.5;
        tileYPosition -= tileHeight/2;
        x1 = new int[]{tileXPosition, tileXPosition - 150, tileXPosition, tileXPosition + 150};
        y1 = new int[]{tileYPosition - 50, tileYPosition, tileYPosition + 50, tileYPosition};
        g2.fillPolygon(x1, y1, 4);
        g2.setColor(Color.WHITE);
        g2.drawString("A2", tileXPosition, tileYPosition);

        g2.setColor(Color.BLUE);
        tileXPosition += 150;
        tileYPosition += 50;
        x1 = new int[]{tileXPosition, tileXPosition - 150, tileXPosition, tileXPosition + 150};
        y1 = new int[]{tileYPosition - 50, tileYPosition, tileYPosition + 50, tileYPosition};
        g2.fillPolygon(x1, y1, 4);
        g2.setColor(Color.WHITE);
        g2.drawString("B2", tileXPosition, tileYPosition);

        g2.setColor(Color.RED);
        tileXPosition += 150;
        tileYPosition += 50;
        x1 = new int[]{tileXPosition, tileXPosition - 150, tileXPosition, tileXPosition + 150};
        y1 = new int[]{tileYPosition - 50, tileYPosition, tileYPosition + 50, tileYPosition};
        g2.fillPolygon(x1, y1, 4);
        g2.setColor(Color.WHITE);
        g2.drawString("C2", tileXPosition, tileYPosition);

        g2.setColor(Color.BLUE);
        tileXPosition -= tileWidth * 1.5;
        tileYPosition -= tileHeight/2;
        x1 = new int[]{tileXPosition, tileXPosition - 150, tileXPosition, tileXPosition + 150};
        y1 = new int[]{tileYPosition - 50, tileYPosition, tileYPosition + 50, tileYPosition};
        g2.fillPolygon(x1, y1, 4);
        g2.setColor(Color.WHITE);
        g2.drawString("A3", tileXPosition, tileYPosition);

        g2.setColor(Color.RED);
        tileXPosition += 150;
        tileYPosition += 50;
        x1 = new int[]{tileXPosition, tileXPosition - 150, tileXPosition, tileXPosition + 150};
        y1 = new int[]{tileYPosition - 50, tileYPosition, tileYPosition + 50, tileYPosition};
        g2.fillPolygon(x1, y1, 4);
        g2.setColor(Color.WHITE);
        g2.drawString("B3", tileXPosition, tileYPosition);

        g2.setColor(Color.BLUE);
        tileXPosition += 150;
        tileYPosition += 50;
        x1 = new int[]{tileXPosition, tileXPosition - 150, tileXPosition, tileXPosition + 150};
        y1 = new int[]{tileYPosition - 50, tileYPosition, tileYPosition + 50, tileYPosition};
        g2.fillPolygon(x1, y1, 4);
        g2.setColor(Color.WHITE);
        g2.drawString("C3", tileXPosition, tileYPosition);

        g2.setColor(Color.YELLOW);
        player.draw(g2);
        */

    }


}
