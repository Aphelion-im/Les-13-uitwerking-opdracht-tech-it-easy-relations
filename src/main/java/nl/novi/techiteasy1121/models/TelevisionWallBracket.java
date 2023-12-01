package nl.novi.techiteasy1121.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "TelevisionWallBracket")
@Table(name = "televisions_wall_brackets")
@Data
public class TelevisionWallBracket {

    // EmbeddedId zorgt dat er geen nieuwe Id wordt aangemaakt,
    // maar dat de variabelen met de @MapsId annotatie tot key worden gecombineerd.
    @EmbeddedId
    private TelevisionWallBracketKey id;

    // Dit is de owner kant van de relatie. Er staat een foreign key in de database
    @ManyToOne(fetch = FetchType.LAZY) // Eager Loading is a design pattern in which data initialization occurs on the spot. Lazy Loading is a design pattern that we use to defer initialization of an object as long as it’s possible.
    @MapsId("televisionId") // https://www.geeksforgeeks.org/hibernate-mapsid-annotation/
    @JoinColumn(name = "television_id")
    private Television television;

    // Dit is de owner kan van de relatie. Er staat een foreign key in de database
    @ManyToOne
    @MapsId("wallBracketId")
    @JoinColumn(name = "wall_bracket_id") //
    private WallBracket wallBracket;
}
