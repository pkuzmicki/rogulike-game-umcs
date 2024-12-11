package main;

import java.util.Scanner;

public class Commands {
    GamePanel gp;
    Scanner scanner;
    String nextCommand;
    public Commands(GamePanel gamePanel){
        this.gp = gamePanel;
        scanner = new Scanner(System.in);
    }

    public void getCommand(){
        if (scanner.hasNextLine()){
            nextCommand = scanner.nextLine();
            String[] commandParts = nextCommand.split(" ");
            switch (commandParts[0]) {
                case "add" -> gp.objectManager.createEntity(Integer.getInteger(commandParts[1]), Integer.getInteger(commandParts[2]), Integer.getInteger(commandParts[3]));
            }
        }
    }
}
