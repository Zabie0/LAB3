package com.staryy.droidbattle.Interface;

import com.staryy.droidbattle.Battle.Battle;
import com.staryy.droidbattle.Team.Team;
import com.staryy.droidbattle.Writer.WriteToFile;

import java.util.Scanner;

import static com.staryy.droidbattle.Colors.ConsoleColors.*;

public class InterfaceOfGame {
    private static final Scanner scanner = new Scanner(System.in);

    public static void startGame() {
        String value;
        System.out.println(YELLOW+"---------------Welcome to Droid Battle!---------------\n"+RESET);
        do {
            System.out.println(YELLOW+"1.Battle 1 on 1");
            System.out.println("2.Team-on-team combat");
            System.out.println("3.Read battle from file");
            System.out.println("4.Exit\n");
            System.out.print("Your choice: ");
            value = scanner.nextLine();
            switch (value) {
                case "1" -> startGame(1, 1);
                case "2" -> {
                    System.out.println("Enter team size:"+RESET);
                    startGame(scanner.nextInt(), 2);
                }
                case "3" -> WriteToFile.readFromFile();
                case "4" -> System.out.println(WHITE_BOLD+"Completion of the program!"+RESET);
            }
        } while (!value.equals("4"));
    }

    private static void startGame(int size, int value) {
        WriteToFile writer = null;
        System.out.println("Record the fight to a file? Yes/No: ");
        if (value==2){scanner.nextLine();}
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            writer = new WriteToFile();
            WriteToFile.writeToFile = true;
        } else {
            WriteToFile.writeToFile = false;
        }
        start(size);
        if (WriteToFile.writeToFile&& writer != null) {
            writer.closeWriter();
        }
    }

    private static void start(int size){
        WriteToFile.writeAndShow(BLUE+"\nFormation of the Blue team");
        Team blueTeam = new Team(size);
        WriteToFile.writeAndShow(RED+"\nFormation of the Red team");
        Team redTeam = new Team(size);

        Battle battle = new Battle(blueTeam, redTeam);
        battle.start();
    }
}
