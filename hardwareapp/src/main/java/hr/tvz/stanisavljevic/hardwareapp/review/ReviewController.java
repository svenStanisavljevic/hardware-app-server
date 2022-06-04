package hr.tvz.stanisavljevic.hardwareapp.review;

import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("review")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController implements Serializable {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping(params = "hardwareCode")
    public List<ReviewDTO> getReviews(@RequestParam String hardwareCode) {
        return reviewService.findAll(hardwareCode);
    }
    @GetMapping
    public List<ReviewDTO> getAllRevies() {
        return reviewService.findEvery();
    }


}
