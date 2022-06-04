package hr.tvz.stanisavljevic.hardwareapp.review;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MockReviewService implements ReviewService {

    private ReviewRepository reviewRepository;

    public MockReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<ReviewDTO> findAll(String code) {

        return reviewRepository.findByHardware_Code(code).stream()
                .map(this::mapReviewToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> findEvery() {
        return reviewRepository.findAll().stream()
                .map(this::mapReviewToDTO).collect(Collectors.toList());
    }

    private ReviewDTO mapReviewToDTO(Review review) {
        return new ReviewDTO(review.getTitle(), review.getText(), review.getRating());
    }
}
