package pg222.fitness.model;

public class Product {
    private String id;
    private String name;
    private double price;
    private boolean available;
    private String imageUrl;

    public Product(String id, String name, double price, boolean available, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.available = available;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    @Override
    public String toString() {
        return id + "," + name + "," + price + "," + available + "," + imageUrl;
    }
}