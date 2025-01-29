package test;

import main.GamePanel;
import objects.items.Rune;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class RuneTest {
    private Rune rune;
    Random random = new Random();
    GamePanel gamePanel = new GamePanel();

    @BeforeEach
    void setUp() {
        rune = new Rune(gamePanel, 0, 0, 4);
    }

    @RepeatedTest(10)
    void testValues() {
        Queue<Double> queue = new LinkedList<>();
        for (int i = 0; i < 200; i++) {
            double value = rune.drawDmg();
            if(value > 2.5) {
                queue.add(value);
            }
        }
        assertTrue(queue.size() > 130);
    }
}
