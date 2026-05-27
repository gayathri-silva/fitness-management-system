package pg222.fitness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pg222.fitness.model.Review;
import pg222.fitness.service.ReviewService;
import jakarta.servlet.http.HttpSession;
import pg222.fitness.model.User;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public String showReviewForm(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/api/users/login";
        }
        return "submit-review";
    }

    @PostMapping("/submit")
    public String submitReview(@RequestParam String reviewText, HttpSession session) throws IOException {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/api/users/login";
        }

        reviewService.addReview(user.getUsername(), reviewText);
        return "redirect:/reviews/view";
    }

    @GetMapping("/view")
    public String viewReviews(Model model) throws IOException {
        List<Review> reviews = reviewService.getAllReviews();
        model.addAttribute("reviews", reviews);
        return "view-reviews";
    }
}