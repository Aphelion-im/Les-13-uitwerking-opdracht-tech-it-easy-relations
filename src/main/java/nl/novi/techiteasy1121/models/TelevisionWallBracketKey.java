package nl.novi.techiteasy1121.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

//Deze embeddable class wordt gebruikt als Embedded Id in de TelevisionWallBracket class
@Embeddable // ????
@AllArgsConstructor // @Data annotatie werkt hier niet i.v.m. TelevisionWallBracketService
@NoArgsConstructor
public class TelevisionWallBracketKey implements Serializable {

    @Column(name = "television_id")
    private Long televisionId;

    @Column(name = "wall_bracket_id")
    private Long wallBracketId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TelevisionWallBracketKey that = (TelevisionWallBracketKey) o;
        return televisionId.equals(that.televisionId) && wallBracketId.equals(that.wallBracketId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(televisionId, wallBracketId);
    }
}
