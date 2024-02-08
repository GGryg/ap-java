package pl.ug.edu.ap.project.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.ug.edu.ap.project.domain.Review;
import pl.ug.edu.ap.project.domain.request.review.CreateReviewRequest;
import pl.ug.edu.ap.project.domain.request.review.EditReviewRequest;
import pl.ug.edu.ap.project.domain.response.ReviewDTO;
import pl.ug.edu.ap.project.mapper.ReviewMapper;
import pl.ug.edu.ap.project.service.review.ReviewService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewController {
    @Autowired
    private final ReviewService reviewService;

    @GetMapping("/reviews")
    public ResponseEntity<List<ReviewDTO>> getAllReviews() {
        return ResponseEntity.ok().body(ReviewMapper.INSTANCE.reviewsToReviewDTOs(reviewService.getAllReviews()));
    }

    @GetMapping("/manga/{mangaId}/review/{id}")
    public ResponseEntity<ReviewDTO> getReview(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(ReviewMapper.INSTANCE.reviewToReviewDTO(reviewService.getReview(id)));
    }

    @PostMapping("/manga/{mangaId}/review")
    public ResponseEntity<ReviewDTO> getAllReviewsForManga(@PathVariable("mangaId") Long mangaId, @Valid @RequestBody CreateReviewRequest request) {
        Review createdReview = reviewService.addReview(mangaId, request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdReview.getId())
                .toUri();
        return ResponseEntity.created(location).body(ReviewMapper.INSTANCE.reviewToReviewDTO(createdReview));
    }

    @PatchMapping("/manga/{mangaId}/review/{id}")
    public ResponseEntity<ReviewDTO> editReview(@PathVariable("id") Long id, @Valid @RequestBody EditReviewRequest request) {
        return ResponseEntity.ok().body(ReviewMapper.INSTANCE.reviewToReviewDTO(reviewService.editReview(id, request)));
    }

    @DeleteMapping("/manga/{mangaId}/review/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable("id") Long id) {
        reviewService.deleteReview(id);

        return ResponseEntity.noContent().build();
    }
}
