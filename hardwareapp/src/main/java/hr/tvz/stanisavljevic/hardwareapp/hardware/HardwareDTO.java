package hr.tvz.stanisavljevic.hardwareapp.hardware;

import java.io.Serializable;

public class HardwareDTO implements Serializable {

    private String name;
    private Long price;
    private String code;

    private Integer stock;

    public HardwareDTO(String name, Long price, String code, Integer stock) {
        this.name = name;
        this.price = price;
        this.code = code;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
