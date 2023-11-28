package nl.novi.techiteasy1121.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CIModuleInputDto {

    @Size(max = 30, message = "Name must be between 0-30 characters")
    public String name;
    @NotNull(message = "Type is required")
    public String type;
    @Positive(message = "Price must be higher than zero")
    public Double price;
}
