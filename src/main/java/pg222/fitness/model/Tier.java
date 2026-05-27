
package pg222.fitness.model;

import java.util.Objects;

public class Tier {
    private final int tierId;
    private final String name;
    private final double price;
    private final String features;

    public Tier(int tierId, String name, double price, String features) {
        this.tierId = tierId;
        this.name = name;
        this.price = price;
        this.features = features;
    }

    public int getTierId() {
        return tierId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getFeatures() {
        return features;
    }

    @Override
    public String toString() {
        return tierId + "," + name + "," + price + "," + features;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tier tier = (Tier) o;
        return tierId == tier.tierId &&
                Double.compare(tier.price, price) == 0 &&
                Objects.equals(name, tier.name) &&
                Objects.equals(features, tier.features);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tierId, name, price, features);
    }
}
