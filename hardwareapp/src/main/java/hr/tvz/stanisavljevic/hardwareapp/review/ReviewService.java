package hr.tvz.stanisavljevic.hardwareapp.review;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> findAll(String code);

    List<ReviewDTO> findEvery();
}
