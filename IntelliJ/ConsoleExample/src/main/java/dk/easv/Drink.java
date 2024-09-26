package dk.easv;

public class Drink {
    private String name;
    private double price;
    private double alcPercentage;

    public Drink(String name, double price, double alcPercentage){
        this.name = name;
        this.price = price;
        this.alcPercentage = alcPercentage;
    }

    public double getAlcPercentage(){
        return alcPercentage;
    }

    @Override
    public String toString(){
        return name + " - " + alcPercentage +
                "% - " + price + " kr";
    }

}
