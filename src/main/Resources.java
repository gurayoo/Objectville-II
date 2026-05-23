package main;

public class Resources {
    //resources will be taken from this class
    private int population;
    private int goods;
    private int lifestyle;

    public Resources(int goods, int lifestyle, int population) {
        this.goods = goods;
        this.lifestyle = lifestyle;
        this.population = population;
    }

    public int getGoods() {
        return goods;
    }

    public void setGoods(int goods) {
        this.goods = goods;
    }

    public int getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(int lifestyle) {
        this.lifestyle = lifestyle;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
