package service.food;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import service.FileHandler;

import java.util.ArrayList;
import java.util.List;

public class Dish {
    private String name;
    private double price;
    private int count;
    private int prepareTime;
    private List<FeedBack> feedBacks;
    @JsonIgnore
    private double averageFeedBackMark;

    public Dish() {
        name = "default";
        price = 0;
        prepareTime = 0;
        feedBacks = new ArrayList<FeedBack>();
        count = 1;
    }

    public Dish(@JsonProperty("name") String name, @JsonProperty("price") double price, @JsonProperty("preparetime") int prepareTime, @JsonProperty("feedbacks") List<FeedBack> feedBacks, @JsonProperty("count") int count) {
        this.name = name;
        this.price = price;
        this.prepareTime = prepareTime;
        this.feedBacks = feedBacks;
        this.count = count;
    }

    public Dish(String name,double price, int prepareTime) {
        this.name = name;
        this.price = price;
        this.prepareTime = prepareTime;
        this.feedBacks = new ArrayList<FeedBack>();
        count = 1;
    }

    public String getName() { return name; }

    public double getPrice() { return price; }

    public int getCount() { return count; }
    public int getPrepareTime() { return prepareTime; }
    public List<FeedBack> getFeedBacks() { return feedBacks; }

    public double getAverageFeedBackMark() {
        averageFeedBackMark = 0;
        for (FeedBack fb : feedBacks) {
            averageFeedBackMark += fb.getMark();
        }
        averageFeedBackMark /= feedBacks.size();

        return averageFeedBackMark;
    }

    @JsonIgnore
    public boolean getAvailability() { return count > 0; }

    public void addFeedBack(FeedBack feedBack) {
        feedBacks.add(feedBack);

        FileHandler.save(FoodMenu.getAll(), "dishes.json");
    }

    public void setPrice(double price) { this.price = price; }

    public void setCount(int count) { this.count = count; }
    public void setPrepareTime(int prepareTime) { this.prepareTime = prepareTime; }

    public void display() {
        System.out.println("- Блюдо: " + getName());
        System.out.println("Цена: " + getPrice() + " $");
        System.out.println("Время приготовления: " + getPrepareTime() + " мин.");
        System.out.println("Количество: " + getCount());
        System.out.println("Доступность: " + (getAvailability() ? "\uD83D\uDFE2" : "\uD83D\uDD34"));

        if (!feedBacks.isEmpty()) {
            System.out.println("Отзывы клиентов: ");
            for (FeedBack feedback : feedBacks) {
                feedback.display();
            }
        }
        System.out.println();
    }

    public void displayAdmin() {
        System.out.println("- Блюдо: " + getName());
        System.out.println("Цена: " + getPrice() + " $");
        System.out.println("Средняя оценка: " + getAverageFeedBackMark());
        System.out.println();
    }
}
