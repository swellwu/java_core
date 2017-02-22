package com.core.reflection;

/**
 * Created by Administrator on 2017/2/22.
 */
public class American extends Person {
        private int intelligence=95;
        private int strength=113;
        private int agility=101;

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

        public American(){
            this.name="Jack";
        }
}
