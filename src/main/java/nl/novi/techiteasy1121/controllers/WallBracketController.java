package nl.novi.techiteasy1121.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.novi.techiteasy1121.dtos.TelevisionDto;
import nl.novi.techiteasy1121.dtos.WallBracketDto;
import nl.novi.techiteasy1121.dtos.WallBracketInputDto;
import nl.novi.techiteasy1121.services.TelevisionWallBracketService;
import nl.novi.techiteasy1121.services.WallBracketService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;

import static nl.novi.techiteasy1121.utilities.Utilities.getErrorString;

@RestController
@AllArgsConstructor
public class WallBracketController {

    private final WallBracketService wallBracketService;
    private final TelevisionWallBracketService televisionWallBracketService;

    @GetMapping("/wallbrackets")
    public ResponseEntity<List<WallBracketDto>> getAllWallBrackets() {

        List<WallBracketDto> wallBrackets = wallBracketService.getAllWallBrackets();

        return ResponseEntity.ok(wallBrackets);
    }

    @GetMapping("/wallbrackets/{id}")
    public ResponseEntity<WallBracketDto> getWallBracket(@PathVariable("id") Long id) {

        WallBracketDto wallBracketDto = wallBracketService.getWallBracket(id);

        return ResponseEntity.ok(wallBracketDto);
    }

    @PostMapping("/wallbrackets")
    public ResponseEntity<Object> addWallBracket(@Valid @RequestBody WallBracketInputDto wallBracketInputDto, BindingResult br) {
        if (br.hasFieldErrors()) {
            String errorString = getErrorString(br);
            return ResponseEntity.badRequest().body(errorString);
        } else {
            Long id = wallBracketService.addWallbracket(wallBracketInputDto).getId();
            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/" + id).toUriString());
            return ResponseEntity.created(uri).body(wallBracketInputDto);
        }
    }

    // Kan id 1001-1005 niet verwijderen i.v.m. constraints/relaties
    // Geen waarschuwing als je iets wilt verwijderen wanneer het id niet bestaat
    @DeleteMapping("/wallbrackets/{id}")
    public ResponseEntity<Object> deleteWallBracket(@PathVariable("id") Long id) {
        wallBracketService.deleteWallBracket(id);
        return ResponseEntity.noContent().build();
    }

    // Hier nog een BindingResult:
    @PutMapping("/wallbrackets/{id}")
    public ResponseEntity<WallBracketDto> updateWallBracket(@PathVariable("id") Long id, @Valid @RequestBody WallBracketInputDto wallBracketInputDto) {
        WallBracketDto wallBracketDto = wallBracketService.updateWallBracket(id, wallBracketInputDto);
        return ResponseEntity.ok().body(wallBracketDto);
    }

    // Deze methode haalt alle televisies op die aan een bepaalde wallbracket gekoppeld zijn.
    // Deze methode maakt gebruikt van de televisionWallBracketService.
    // Deze combinaties staan in de televisions_wall_brackets koppeltabel in het data.sql bestand
    @GetMapping("/wallbrackets/televisions/{wallBracketId}")
    public ResponseEntity<Collection<TelevisionDto>> getTelevisionsByWallBracketId(@PathVariable("wallBracketId") Long wallBracketId) {
        return ResponseEntity.ok(televisionWallBracketService.getTelevisionsByWallBracketId(wallBracketId));
    }
}