package pl.ug.edu.ap.project.service.review;

import pl.ug.edu.ap.project.domain.Review;
import pl.ug.edu.ap.project.domain.request.review.CreateReviewRequest;
import pl.ug.edu.ap.project.domain.request.review.EditReviewRequest;

import java.util.List;

public interface ReviewService {
    Review addReview(Long mangaId, CreateReviewRequest request);

    List<Review> getAllReviews();

    Review getReview(Long id);

    Review editReview(Long id, EditReviewRequest request);

    void deleteReview(Long id);

}
