package objects;

import main.GamePanel;

public class Obstacle extends Entity{

    boolean isBreakable = false;

    public Obstacle(GamePanel gamePanel, int col, int row, int obstacleId) {
        super(gamePanel, col, row, obstacleId);
    }
}
