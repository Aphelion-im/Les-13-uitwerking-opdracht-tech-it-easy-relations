package nl.novi.techiteasy1121.dtos;

import lombok.Data;

@Data
public class RemoteControllerDto {

    public Long id;
    public String compatibleWith;
    public String batteryType;
    public String name;
    public String brand;
    public Double price;
    public Integer originalStock;
}
