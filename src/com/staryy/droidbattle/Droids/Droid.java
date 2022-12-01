package com.staryy.droidbattle.Droids;

import com.staryy.droidbattle.Writer.WriteToFile;

import java.util.Random;

import static com.staryy.droidbattle.Colors.ConsoleColors.*;

public class Droid {
    protected final String name;
    protected final int maximalDamage;
    protected int health;
    protected final int maximalEnergy;
    protected int currentEnergy;

    public Droid(int health, int maximalDamage,String name,int maximalEnergy) {
        this.health = health;
        this.maximalDamage = maximalDamage;
        this.name = name;
        this.maximalEnergy = maximalEnergy;
        this.currentEnergy = maximalEnergy;
    }

    public int getDamage() {
        return maximalDamage;
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int attack(){
        if(currentEnergy<=5){
            chargeEnergy();
            return 0;
        }
        Random random = new Random();
        currentEnergy -= 5;
        if(currentEnergy<=0){currentEnergy=0;}
        return random.nextInt(maximalDamage-1)+1;
    }

    public int getHealth() {
        return health;
    }

    public void chargeEnergy(){
        Random random = new Random();
        int chargedEnergy = random.nextInt(maximalEnergy-1)+1;
        currentEnergy += chargedEnergy;
        WriteToFile.writeAndShow(YELLOW+"The "+name+" recharged " + chargedEnergy + " Energy points!"+RESET);
    }
    public boolean getHit(int damage) {
        this.health -= damage;
        if (health < 0) {
            health = 0;
            WriteToFile.writeAndShow(BLACK+"The "+name+" was killed!"+RESET);
        }
        return true;
    }

    @Override
    public String toString() {
        return name + '\n'+
                "Health: " + health + '\n'+
                "Maximal damage: " + maximalDamage+ '\n' +
                "Energy: " + currentEnergy + '\n';
    }
}
