package nl.novi.techiteasy1121.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RemoteControllerInputDto {

    public String compatibleWith;
    public String batteryType;
    @Size(max = 30, message = "Name must be between 0-30 characters")
    public String name;
    @NotNull(message = "Brand is required")
    public String brand;
    @Positive(message = "Price must be higher than zero")
    public Double price;
    @PositiveOrZero(message = "Televisions cannot have negative stock")
    public Integer originalStock;
}
