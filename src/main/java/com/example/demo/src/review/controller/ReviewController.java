package com.example.demo.src.review.controller;

import com.example.demo.src.review.model.dto.Review;
import com.example.demo.src.review.model.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ModelAndView getAllReviews(ModelAndView modelAndView) {
        List<Review> reviews = reviewService.getAllReviews();
        modelAndView.addObject("reviews", reviews);
        modelAndView.setViewName("reviewList"); // view name
        return modelAndView;
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ModelAndView getReviewsByRestaurantId(ModelAndView modelAndView, @PathVariable int restaurantId) {
        List<Review> reviews = reviewService.getReviewsByRestaurantId(restaurantId);
        modelAndView.addObject("reviews", reviews);
        modelAndView.setViewName("reviewListByRestaurant"); // view name
        return modelAndView;
    }

    @GetMapping("/edit/{reviewId}")
    public ModelAndView editReview(ModelAndView modelAndView, @PathVariable int reviewId) {
        Review review = reviewService.getReviewById(reviewId);
        modelAndView.addObject("review", review);
        modelAndView.setViewName("editReview"); // view name
        return modelAndView;
    }

    @PostMapping("/update")
    public String updateReview(@ModelAttribute Review review) {
        reviewService.updateReview(review);
        return "redirect:/reviews";
    }

    @GetMapping("/delete/{reviewId}")
    public String deleteReview(@PathVariable int reviewId) {
        reviewService.deleteReview(reviewId);
        return "redirect:/reviews";
    }

    @GetMapping("/new")
    public ModelAndView newReview(ModelAndView modelAndView) {
        modelAndView.addObject("review", new Review());
        modelAndView.setViewName("newReview"); // view name
        return modelAndView;
    }

    @PostMapping("/create")
    public String createReview(@ModelAttribute Review review) {
        reviewService.insertReview(review);
        return "redirect:/reviews";
    }
}