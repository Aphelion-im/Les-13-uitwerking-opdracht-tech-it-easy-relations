package nl.novi.techiteasy1121.dtos;

import lombok.Data;

@Data
public class WallBracketDto {

    private Long id;
    private String size;
    private Boolean adjustable;
    private String name;
    private Double price;
}
