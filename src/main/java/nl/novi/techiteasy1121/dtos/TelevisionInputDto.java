package nl.novi.techiteasy1121.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;

// InputDto heeft validatie
// InputDto heeft geen id
// Dto en InputDto hebben @Data
// Velden exact gelijk aan Television.java en TelevisionDto, behalve id
@Data
public class TelevisionInputDto {

    @NotNull(message = "Type is required")
    private String type;
    @NotNull(message = "Brand is required")
    private String brand;
    @Size(max = 100, message = "Name must be between 0-100 characters")
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

    // Geen relatie velden in InputDto
    // NOVI Constructors, getters en setters uitgeschakeld
}

