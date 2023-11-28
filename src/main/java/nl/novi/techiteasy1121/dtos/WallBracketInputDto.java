package nl.novi.techiteasy1121.dtos;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class WallBracketInputDto {

    private String size;
    private Boolean adjustable;
    @Size(max = 30, message = "Name must be between 0-30 characters")
    private String name;
    @Positive(message = "Price must be higher than zero")
    private Double price;
}
