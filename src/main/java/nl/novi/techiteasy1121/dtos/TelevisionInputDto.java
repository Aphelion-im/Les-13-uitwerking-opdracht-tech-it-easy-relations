package nl.novi.techiteasy1121.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;
import nl.novi.techiteasy1121.models.TestEnum;

@Data
public class TelevisionInputDto {

    private TestEnum testEnum;
    @NotNull(message = "Type is required")
    private String type;
    @NotNull(message = "Brand is required")
    private String brand;
    @Size(max = 30, message = "Name must be between 0-30 characters")
    private String name;
    @Positive(message = "Price must be higher than zero")
    private Double price;
    private Double availableSize;
    private Double refreshRate;
    private String screenType;
    private String screenQuality;
    private Boolean smartTv;
    private Boolean wifi;
    private Boolean voiceControl;
    @AssertTrue(message = "All televisions must be HDR minimum")
    private Boolean hdr;
    private Boolean bluetooth;
    private Boolean ambiLight;
    @PositiveOrZero(message = "Televisions cannot have negative stock")
    private Integer originalStock;
    private Integer sold;
}

