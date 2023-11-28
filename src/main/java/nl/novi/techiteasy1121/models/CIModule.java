package nl.novi.techiteasy1121.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "CIModule")
@Table(name = "cimodules")
@Data
public class CIModule {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String type;
    private Double price;

    // Dit is de target kant van de relatie. Er staat niks in de database
    @OneToMany(mappedBy = "ciModule")
    @JsonIgnore
    List<Television> televisions;
    }
