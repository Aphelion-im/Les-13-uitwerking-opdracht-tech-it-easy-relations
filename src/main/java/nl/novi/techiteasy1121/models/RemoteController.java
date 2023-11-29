package nl.novi.techiteasy1121.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "RemoteController")
@Table(name = "remote_controllers")
@Data
public class RemoteController {

    @Id
    @GeneratedValue
    private Long id;

    private String compatibleWith;
    private String batteryType;
    private String name;
    private String brand;
    private Double price;
    private Integer originalStock;

    // Dit is de target kant van de relatie. Er staat niks in de database
    @OneToOne(mappedBy = "remoteController") // The mappedBy attribute is used to define the referencing side (non-owning side) of the relationship.
    Television television;
}
