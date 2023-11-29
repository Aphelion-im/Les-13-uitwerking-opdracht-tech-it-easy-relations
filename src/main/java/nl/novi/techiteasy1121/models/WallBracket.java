package nl.novi.techiteasy1121.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "WallBracket")
@Table(name = "wall_brackets")
@Data
public class WallBracket {

    @Id
    @GeneratedValue
    private Long id;
    private String size;
    private Boolean adjustable;
    private String name;
    private Double price;

    // Dit is de target kant van de relatie. Er staat niks in de database
    @OneToMany(mappedBy = "wallBracket")
    @JsonIgnore // @JsonIgnore - Bij stack overflow foutmeldingen door een oneindige loop/recursie. Les 13 (2023/02): Video@1h18m
    List<TelevisionWallBracket> televisionWallBrackets;
}
