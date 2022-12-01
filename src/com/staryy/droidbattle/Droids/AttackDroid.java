package com.staryy.droidbattle.Droids;

import com.staryy.droidbattle.Writer.WriteToFile;

import java.util.Random;

import static com.staryy.droidbattle.Colors.ConsoleColors.*;

public class AttackDroid extends Droid{
    private final int criticalHit;
    private final int chanceOfCriticalHit;

    public AttackDroid() {
        super(70, 20, "Attack Droid", 30);
        this.chanceOfCriticalHit = 25;
        this.criticalHit = 30;
    }

    @Override
    public int attack() {
        Random random = new Random();
        int i = random.nextInt(100);
        if(currentEnergy < 5){
            chargeEnergy();
            return 0;
        }
        else {
            if ( i < chanceOfCriticalHit&&currentEnergy>13) {
                currentEnergy -= 13;
                WriteToFile.writeAndShow(YELLOW+"Critical hit!"+RESET);
                return criticalHit;
            }
           else{
                currentEnergy -= 5;
                return random.nextInt(maximalDamage-1)+1;
            }
        }
    }
}
