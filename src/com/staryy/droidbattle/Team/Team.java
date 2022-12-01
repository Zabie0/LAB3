package com.staryy.droidbattle.Team;

import com.staryy.droidbattle.Droids.AttackDroid;
import com.staryy.droidbattle.Droids.DefendDroid;
import com.staryy.droidbattle.Droids.Droid;
import com.staryy.droidbattle.Droids.HealDroid;
import com.staryy.droidbattle.Writer.WriteToFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Team {
    private final List<Droid> droids = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public Team(int size)
    {
        fillTeam(size);
    }

    public void fillTeam(int size){
        for (int i = 0; i < size; i++) {
            WriteToFile.writeAndShow("Choose droid №" + (i + 1));
            droids.add(chooseDroid());
        }
    }

    public List<Droid> getDroids() {
        return droids;
    }

    public Droid chooseDroid(){
        WriteToFile.writeAndShow("1. Heal Droid\n2. Attack Droid\n3. Defend Droid");
        Droid droid;
        System.out.print("Your choice: ");
        String value = scanner.nextLine();

        while (!value.equals("1") && !value.equals("2")&& !value.equals("3")) {
            WriteToFile.writeAndShow("Invalid request");
            value = scanner.nextLine();
        }

        droid = switch (value) {
            case "1" -> new HealDroid();
            case "2" -> new AttackDroid();
            default -> new DefendDroid();
        };

        WriteToFile.writeInputValue("Your choice: "+value);
        return droid;
    }
}
