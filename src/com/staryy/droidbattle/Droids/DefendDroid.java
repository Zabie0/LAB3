package com.staryy.droidbattle.Droids;

import com.staryy.droidbattle.Writer.WriteToFile;

import java.util.Random;

import static com.staryy.droidbattle.Colors.ConsoleColors.*;

public class DefendDroid extends Droid {
    protected final int chanceToAbsorbDamage;
    public DefendDroid() {
        super(100, 10,"Defend Droid",20);
        this.chanceToAbsorbDamage = 15;
    }

    @Override
    public boolean getHit(int damage) {
        Random random = new Random();
        if(random.nextInt(100) > chanceToAbsorbDamage && currentEnergy < 10) {
            this.health -= damage;
            if (health<0) {
                health = 0;
                WriteToFile.writeAndShow(BLACK+"The "+name+" was killed!"+RESET);
            }
            return true;
        }
        else{
            currentEnergy -= 10;
            WriteToFile.writeAndShow(PURPLE+"The attack was blocked!"+RESET);
            return false;
        }
    }
}
