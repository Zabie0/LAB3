package com.staryy.droidbattle.Battle;

import com.staryy.droidbattle.Droids.Droid;
import com.staryy.droidbattle.Team.Team;
import com.staryy.droidbattle.Writer.WriteToFile;

import java.util.Random;

import static com.staryy.droidbattle.Colors.ConsoleColors.*;

public class Battle {
    protected Team blueTeam;
    protected Team redTeam;
    protected int currentRound = 1;

    public Battle(Team blueTeam, Team redTeam) {
        this.blueTeam = blueTeam;
        this.redTeam = redTeam;
    }

    public void start() {
        battle();
    }

    private void fight(Team first,Team second, boolean blueAttack)
    {
        int firstDroidIndex = 0;
        int secondDroidIndex = 0;
        if(first.getDroids().size() != 1 && second.getDroids().size() != 1){
            Random random = new Random();
            firstDroidIndex = random.nextInt(blueTeam.getDroids().size()-1);
            secondDroidIndex= random.nextInt(redTeam.getDroids().size()-1);
        }

        if(!first.getDroids().isEmpty())
        {
            Droid firstDroid=first.getDroids().get(firstDroidIndex);
            Droid secondDroid=second.getDroids().get(secondDroidIndex);
            int damage = firstDroid.attack();
            boolean isDamaged = secondDroid.getHit(damage);

            if(isDamaged)
                if(blueAttack) {
                    WriteToFile.writeAndShow(BLUE+first.getDroids().get(firstDroidIndex).getName() + " give " + damage + " damage to " + secondDroid.getName() + RESET);
                }
                else{
                    WriteToFile.writeAndShow(RED+first.getDroids().get(firstDroidIndex).getName()+" give "+damage+ " damage to "+secondDroid.getName()+RESET);
                }
            if (second.getDroids().get(secondDroidIndex).getHealth()<=0) {second.getDroids().remove(secondDroid);}
        }
        else WriteToFile.writeAndShow(WHITE+"Team is empty\n"+RESET);
    }

    private void battle()
    {
        while (!blueTeam.getDroids().isEmpty() && !redTeam.getDroids().isEmpty()) {
            WriteToFile.writeAndShow(RESET+"\n----------------------------Round №" + currentRound++ +"----------------------------");
            WriteToFile.writeAndShow(BLUE+"Blue team turn:");
            fight(blueTeam,redTeam, true);
            WriteToFile.writeAndShow(RED+"\nRed team turn:");
            fight(redTeam,blueTeam, false);
            showInfo();
        }
        if(blueTeam.getDroids().isEmpty()) WriteToFile.writeAndShow(RED_BOLD+"\nRed team win\n"+RESET);
        else WriteToFile.writeAndShow(BLUE_BOLD+"\nBlue team win\n"+RESET);
    }

    private void showInfo()
    {
        WriteToFile.writeAndShow(RED+"\nRed team:");
        printList(redTeam);

        WriteToFile.writeAndShow(BLUE+"\nBlue team:");
        printList(blueTeam);
    }

    private void printList(Team team)
    {
        if(team.getDroids().isEmpty()) WriteToFile.writeAndShow(CYAN+"Empty"+RESET);

        for (int i = 0;i<team.getDroids().size();i++)
            WriteToFile.writeAndShow(team.getDroids().get(i).toString());
    }
}
