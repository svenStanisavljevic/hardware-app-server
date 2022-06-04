package hr.tvz.stanisavljevic.hardwareapp.review;

import hr.tvz.stanisavljevic.hardwareapp.hardware.Hardware;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="review")
@Data
@NoArgsConstructor
public class Review implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name="text")
    private String text;

    @Column(name="rating")
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "hardware_id")
    private Hardware hardware;

    public Review(Long id, String title, String text, Integer rating, Hardware hardware) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.rating = rating;
        this.hardware = hardware;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(id, review.id) && Objects.equals(title, review.title) && Objects.equals(text, review.text) && Objects.equals(rating, review.rating) && Objects.equals(hardware, review.hardware);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, text, rating, hardware);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", rating=" + rating +
                ", hardware=" + hardware +
                '}';
    }
}
