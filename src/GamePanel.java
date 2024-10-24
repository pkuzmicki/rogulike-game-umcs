import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    int screenWidth = 1000;
    int screenHeight = 600;

    public GamePanel(){
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    }



    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.red);
//        int tileXPosition = 0;
//        int tileYPosition = 0;
//
//
//        for (int j = 0; j < 6; j++){
//            for(int i = 0; i < 4; i++){
//                int[] x = {tileXPosition, tileXPosition + 150, tileXPosition + 300, tileXPosition + 150};
//                int[] y = {tileYPosition + 50, tileYPosition,tileYPosition + 50,tileYPosition + 100};
//
//                g2.fillPolygon(x, y, 4);
//                tileXPosition += 300;
//            }
//            tileXPosition = 0;
//            tileYPosition += 100;
//        }
//
//        tileXPosition = 150;
//        tileYPosition = 50;
//
//        g2.setColor(Color.CYAN);
//        for (int j = 0; j < 6; j++){
//            for(int i = 0; i < 4; i++){
//                int[] x = {tileXPosition, tileXPosition + 150, tileXPosition + 300, tileXPosition + 150};
//                int[] y = {tileYPosition + 50, tileYPosition,tileYPosition + 50,tileYPosition + 100};
//
//                g2.fillPolygon(x, y, 4);
//                tileXPosition += 300;
//            }
//            tileXPosition = 150;
//            tileYPosition += 100;
//        }


        int tileXPosition = 0;
        int tileYPosition = screenHeight / 2;

        for (int col = 0; col < 2; col++){
            for (int row = 0; row < 3; row++){
                int[] x = {tileXPosition, tileXPosition + 150, tileXPosition + 300, tileXPosition + 150};
                int[] y = {tileYPosition + 50, tileYPosition,tileYPosition + 50,tileYPosition + 100};
                g2.fillPolygon(x, y, 4);
                tileXPosition += 150;
                tileYPosition -= 50;
            }
            g2.setColor(Color.CYAN);
            tileXPosition = 150;
            tileYPosition += 200;
        }

    }

}
