package com.core.reflection;

/**
 * Created by Administrator on 2017/2/22.
 */
public class Chinese extends Person {
    private int intelligence=108;
    private int strength=80;
    private int agility=110;

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public Chinese(){
        this.name="小明";
    }
}
