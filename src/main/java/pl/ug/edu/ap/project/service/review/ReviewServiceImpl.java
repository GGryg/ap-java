package pl.ug.edu.ap.project.service.review;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ug.edu.ap.project.domain.Manga;
import pl.ug.edu.ap.project.domain.Review;
import pl.ug.edu.ap.project.domain.User;
import pl.ug.edu.ap.project.domain.request.review.CreateReviewRequest;
import pl.ug.edu.ap.project.domain.request.review.EditReviewRequest;
import pl.ug.edu.ap.project.exception.manga.MangaNotFoundException;
import pl.ug.edu.ap.project.exception.review.ReviewNotFoundException;
import pl.ug.edu.ap.project.exception.user.UserNotFoundException;
import pl.ug.edu.ap.project.repository.MangaRepository;
import pl.ug.edu.ap.project.repository.ReviewRepository;
import pl.ug.edu.ap.project.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private final ReviewRepository reviewRepository;
    @Autowired
    private final MangaRepository mangaRepository;
    @Autowired
    private final UserRepository userRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review getReview(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));
    }

    @Transactional
    public Review addReview(Long mangaId, CreateReviewRequest request) {
        Manga manga = mangaRepository.findById(mangaId)
                .orElseThrow(() -> new MangaNotFoundException(mangaId));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException(request.getUserId()));
        Review review = Review.builder()
                .review(request.getReview())
                .recommended(request.isRecommended())
                .manga(manga)
                .user(user)
                .build();

        return reviewRepository.save(review);
    }

    public Review editReview(Long id, EditReviewRequest request) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));
        request.getReview().ifPresent(reviewText -> {
            if (!reviewText.isBlank()) {
                review.setReview(reviewText);
            }
        });
        request.getRecommended().ifPresent(review::setRecommended);

        return reviewRepository.save(review);
    }

    public void deleteReview(Long id) {
        Review foundReview = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));
        reviewRepository.delete(foundReview);
    }
}
