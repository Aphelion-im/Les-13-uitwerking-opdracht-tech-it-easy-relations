package nl.novi.techiteasy1121.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// Waarschijnlijk bestaat deze klasse om een koppeltabel aan te maken in deze @ManyToMany relatie
//Deze embeddable class wordt gebruikt als Embedded Id in de TelevisionWallBracket class
@Embeddable // To declare that a class will be embedded by other entities.
@AllArgsConstructor // @Data annotatie werkt hier niet i.v.m. TelevisionWallBracketService
@NoArgsConstructor
// @EqualsAndHashCode // Niet zeker of dit nodig is
public class TelevisionWallBracketKey implements Serializable {

    @Column(name = "television_id")
    private Long televisionId;

    @Column(name = "wall_bracket_id")
    private Long wallBracketId;
}
