package service.dish;

public class Dish {
    private String name;
    private double price;
    private int count;
    private int prepareTime;

    public Dish(String name, double price, int prepareTime) {
        this.name = name;
        this.price = price;
        this.prepareTime = prepareTime;
        count = 1;
    }

    public String getName() { return name; }

    public double getPrice() { return price; }

    public int getCount() { return count; }
    public int getPrepareTime() { return prepareTime; }

    public boolean getAvailability() { return count > 0; }

    public void setPrice(double price) { this.price = price; }

    public void setCount(int count) { this.count = count; }
    public void setPrepareTime(int prepareTime) { this.prepareTime = prepareTime; }

}
