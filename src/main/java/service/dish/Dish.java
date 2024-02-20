package service.dish;

public class Dish {
    private String name;
    private double price;
    private int count;

    public Dish(String name, double price) {
        this.name = name;
        this.price = price;
        count = 1;
    }

    public String getName() { return name; }

    public double getPrice() { return price; }

    public int getCount() { return count; }

    public boolean getAvailability() { return count > 0; }

    public void setPrice(double price) { this.price = price; }

    public void setCount(int count) { this.count = count; }
}
