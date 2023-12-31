package nl.novi.techiteasy1121.controllers;

import lombok.AllArgsConstructor;
import nl.novi.techiteasy1121.services.TelevisionWallBracketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

// Deze controller is voor de "tussenklasse" TelevisionWallBracket. Het bevat enkel een POST-method om een WallBracket
// aan een Television toe te voegen. We hoeven geen GET-Method te maken voor een TelevisionWallBracket, omdat deze
// klasse in principe alleen een backende implementatie van een many-to-many relatie is en niet interessant is voor de gebruiker.
@RestController
@RequestMapping("/tvwb")
@AllArgsConstructor
public class TelevisionWallBracketController {

    private TelevisionWallBracketService televisionWallBracketService;

    // Voeg een koppel toe: Television + Wallbracket
    @PostMapping("/{televisionId}/{wallBracketId}")
    public ResponseEntity<Object> addTelevisionWallBracket(@PathVariable("televisionId") Long televisionId, @PathVariable("wallBracketId") Long wallbracketId) {
        televisionWallBracketService.addTelevisionWallBracket(televisionId, wallbracketId);
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/").toUriString());
        return ResponseEntity.created(uri).body("Added pair: Television with id: " + televisionId + " and Wallbracket with id: " + wallbracketId);
    }

    // Verwijder dit koppel: Television + Wallbracket
    @DeleteMapping("/{televisionId}/{wallBracketId}")
    public ResponseEntity<Object> deleteTelevisionWallBracket(@PathVariable("televisionId") Long televisionId, @PathVariable("wallBracketId") Long wallbracketId) {
        televisionWallBracketService.deleteTelevisionWallBracket(televisionId, wallbracketId);
        return ResponseEntity.noContent().build();
    }
}