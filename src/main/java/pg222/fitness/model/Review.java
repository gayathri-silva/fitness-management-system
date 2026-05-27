package pg222.fitness.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Review {
    private String username;
    private String reviewText;
    private String dateTime;

    public Review(String username, String reviewText, String dateTime) {
        this.username = username;
        this.reviewText = reviewText;
        this.dateTime = dateTime;
    }

    // Getters
    public String getUsername() { return username; }
    public String getReviewText() { return reviewText; }
    public String getDateTime() { return dateTime; }

    @Override
    public String toString() {
        return username + "," + reviewText.replace(",", ";;") + "," + dateTime;
    }

    public static Review fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length >= 3) {
            String reviewText = parts[1].replace(";;", ",");
            return new Review(parts[0], reviewText, parts[2]);
        }
        return null;
    }
}