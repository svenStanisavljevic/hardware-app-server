package hr.tvz.stanisavljevic.hardwareapp.hardware;

import hr.tvz.stanisavljevic.hardwareapp.review.Review;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="hardware")
@Data
@NoArgsConstructor
public class Hardware implements Serializable {

    enum HardwareType {
        CPU, GPU, MBO, RAM, STORAGE, OTHER
    }
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="code")
    private String code;

    @Column(name="price")
    private Long price;

    @Column(name ="type")
    private String type;

    @Column(name = "instock")
    private Integer stock;

    @OneToMany(mappedBy = "hardware", fetch = FetchType.LAZY)
    private List<Review> reviews;

    public Hardware(String name, String code, Long price, String type, Integer stock) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.type = type;
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hardware hardware = (Hardware) o;
        return name.equals(hardware.name) && code.equals(hardware.code) &&
                price.equals(hardware.price) && type.equals(hardware.type) && stock.equals(hardware.stock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, price, type, stock);
    }

    @Override
    public String toString() {
        return "Hardware{name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", stock=" + stock +
                ", reviews=" + reviews +
                '}';
    }
}
