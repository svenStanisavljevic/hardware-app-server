package hr.tvz.stanisavljevic.hardwareapp.hardware;

import javax.validation.constraints.*;

public class HardwareCommand {

    @NotBlank(message = "Name must be entered.")
    @NotNull(message = "Name must not be null.")
    private String name;
    @NotBlank(message = "Code must be entered.")
    @NotNull(message = "Code must not be null.")
    private String code;

    @NotNull(message = "Price must be entered.")
    @Positive(message = "Price must be positive.")
    @Digits(integer = 7, fraction = 2, message = "Price must not have more than 7 integer and 2 fraction spaces.")
    private Long price;

    @NotNull(message = "Type must be entered.")
    private String type;

    @NotNull(message = "Stock must be entered.")
    @PositiveOrZero(message = "Stock must be positive or zero.")
    private Integer stock;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public HardwareCommand(String name, String code, Long price, String type, Integer stock) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.type = type;
        this.stock = stock;
    }
}
