package com.staryy.droidbattle.Droids;

import com.staryy.droidbattle.Writer.WriteToFile;

import java.util.Random;

import static com.staryy.droidbattle.Colors.ConsoleColors.*;

public class HealDroid extends Droid {
    private final int chanceToHeal;
    private final int maximalHeal;

    public HealDroid() {
        super(100, 15, "Heal Droid", 30);
        this.chanceToHeal = 25;
        this.maximalHeal = 15;
    }

    @Override
    public boolean getHit(int damage) {
        Random random = new Random();
        this.health -= damage;
        if(random.nextInt(100) < chanceToHeal && currentEnergy > 10 && health > 0) {
            heal();
            if(currentEnergy < 10){
                chargeEnergy();
            }
        }
        if(health<0)  {
            health = 0;
            WriteToFile.writeAndShow(BLACK+"The "+name+" droid was killed!"+RESET);
        }
        return true;
    }

    public void heal(){
        Random random = new Random();
        int recover = random.nextInt(maximalHeal-1)+1;
        this.health += recover;
        currentEnergy -= 10;
        WriteToFile.writeAndShow(GREEN+"The heal droid recovered "+recover+" Health Points"+RESET);
    }

}
