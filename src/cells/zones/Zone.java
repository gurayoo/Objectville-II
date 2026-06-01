package cells.zones;

import cells.Cell;
public abstract class Zone implements Cell {
    //other zones classes will inherit from here.

    private int level = 0;
    private int utilityDemand=1;
    private boolean hasSecurity;
    private boolean hasHealth;
    private boolean hasEducation;
    private int electricity;
    private int water;
    private int internet;
    private int currentOutput=0;
    private int currentPopulation = 0;
    private int currentGoods = 0;
    private int currentLifestyle = 0;
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isHasSecurity() {
        return hasSecurity;
    }

    public void setHasSecurity(boolean hasSecurity) {
        this.hasSecurity = hasSecurity;
    }

    public boolean isHasHealth() {
        return hasHealth;
    }

    public void setHasHealth(boolean hasHealth) {
        this.hasHealth = hasHealth;
    }

    public boolean isHasEducation() {
        return hasEducation;
    }

    public void setHasEducation(boolean hasEducation) {
        this.hasEducation = hasEducation;
    }

    public int getElectricity() {
        return electricity;
    }

    public void setElectricity(int electricity) {
        this.electricity = electricity;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getInternet() {
        return internet;
    }

    public void setInternet(int internet) {
        this.internet = internet;
    }


    public void reset(){

        hasSecurity = false;
        hasHealth = false;
        hasEducation= false;
        electricity=0;
        water=0;
        internet= 0;
        currentGoods=0;
        currentPopulation=0;
        currentLifestyle=0;

    }
    public abstract int getM();
    public abstract void calculateLevel();
    public abstract void calculateOutput(int a);

    public int getUtilityDemand() {
        return utilityDemand;
    }

    public void setUtilityDemand(int utilityDemand) {
        this.utilityDemand = utilityDemand;
    }

    @Override
    public void tick() {  //update cells after tick
        int m=getM();
        if (m == 0) {
            level = 0;
            currentOutput = 0;
            utilityDemand = 1;
        }
        else {
            calculateLevel();
            calculateOutput(m);
            utilityDemand=Math.max(1,currentOutput);

        }
    }
    public abstract String getName();
    public int getCurrentOutput() {
        return currentOutput;
    }

    public void setCurrentOutput(int currentOutput) {
        this.currentOutput = currentOutput;
    }

    public int getCurrentGoods() {
        return currentGoods;
    }

    public void setCurrentGoods(int currentGoods) {
        this.currentGoods = currentGoods;
    }

    public int getCurrentLifestyle() {
        return currentLifestyle;
    }

    public void setCurrentLifestyle(int currentLifestyle) {
        this.currentLifestyle = currentLifestyle;
    }

    public int getCurrentPopulation() {
        return currentPopulation;
    }

    public void setCurrentPopulation(int currentPopulation) {
        this.currentPopulation = currentPopulation;
    }
}