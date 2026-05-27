package pg222.fitness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pg222.fitness.model.Review;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ReviewService {
    private static final String REVIEWS_FILE = "reviews.txt";

    @Autowired
    private FileService fileService;

    public void addReview(String username, String reviewText) throws IOException {
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Review review = new Review(username, reviewText, dateTime);
        fileService.appendToFile(REVIEWS_FILE, review.toString());
    }

    public List<Review> getAllReviews() throws IOException {
        List<Review> reviews = new ArrayList<>();
        List<String> lines = fileService.readFile(REVIEWS_FILE);

        for (String line : lines) {
            Review review = Review.fromString(line);
            if (review != null) {
                reviews.add(review);
            }
        }

        // Sort reviews by date (newest first)
        Collections.reverse(reviews);
        return reviews;
    }
}