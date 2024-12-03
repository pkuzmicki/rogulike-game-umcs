package main;

public class Camera {
    GamePanel gp;
    int cameraX = 0;
    int cameraY = 0;
    int cameraWidth;
    int cameraHeight;

    public Camera(GamePanel gamePanel) {
        this.gp = gamePanel;
        cameraWidth = gp.screenWidth;
        cameraHeight = gp.screenHeight;
    }

    public void moveCamera(int dx, int dy) {
        cameraX += dx;
        cameraY += dy;
    }

    public void draw() {

    }
}
