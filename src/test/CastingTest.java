package test;

import combat.Spell;
import main.GamePanel;
import objects.Monster;
import objects.Player;
import objects.items.Rune;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.*;


import combat.CastingManager;
import main.GamePanel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CastingTest {
    CastingManager cM;
    Spell spell;
    GamePanel gamePanel = new GamePanel();

    @BeforeEach
    void setUp() {
        spell = new Spell(gamePanel, "1");
    }

    @RepeatedTest(10)
    void testCriticalChance() {
        Player player = new Player(gamePanel, 1, 0, 1);
        Monster monster = new Monster(gamePanel, 0, 0, 11);
        gamePanel.objectManager.entities.add(monster);
        gamePanel.player.isInHideout = false;
        spell.col = 0;
        spell.row = 0;
        spell.setSpellValues(player);
        for (int i = 0; i < 15; i++) {

            if (!gamePanel.objectManager.entities.isEmpty()) {
                spell.effect();
                //System.out.println(spell.totalDmg);
            } else {
                assertTrue(monster.currentHP <= 0);
            }
        }

    }

    @RepeatedTest(5)
    void testCriticalValues() {
        Queue<Double> values = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            double totalDmg = 5 * spell.criticalHit();
            System.out.println(totalDmg);
            if (totalDmg == 10) {
                values.add(totalDmg);
            }
        }
        System.out.println(values.size());
        assertTrue(values.size() >= 5);
    }
}
