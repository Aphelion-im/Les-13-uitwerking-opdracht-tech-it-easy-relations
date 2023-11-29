package nl.novi.techiteasy1121.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.novi.techiteasy1121.dtos.IdInputDto;
import nl.novi.techiteasy1121.dtos.TelevisionDto;
import nl.novi.techiteasy1121.dtos.TelevisionInputDto;
import nl.novi.techiteasy1121.dtos.WallBracketDto;
import nl.novi.techiteasy1121.services.TelevisionService;
import nl.novi.techiteasy1121.services.TelevisionWallBracketService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static nl.novi.techiteasy1121.utilities.Utilities.getErrorString;

@RestController
@AllArgsConstructor
public class TelevisionController {

    private final TelevisionService televisionService;

    private final TelevisionWallBracketService televisionWallBracketService;

    @GetMapping("/televisions")
    public ResponseEntity<List<TelevisionDto>> getAllTelevisions(@RequestParam(value = "brand", required = false) Optional<String> brand) {
        List<TelevisionDto> dtos;
        if (brand.isEmpty()) {
            dtos = televisionService.getAllTelevisions();
        } else {
            dtos = televisionService.getAllTelevisionsByBrand(brand.get());
        }
        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/televisions/{id}")
    public ResponseEntity<TelevisionDto> getTelevision(@PathVariable("id") Long id) {
        TelevisionDto television = televisionService.getTelevisionById(id);
        return ResponseEntity.ok().body(television);
    }

    @PostMapping("/televisions")
    public ResponseEntity<Object> addTelevision(@Valid @RequestBody TelevisionInputDto televisionInputDto, BindingResult br) {
        if (br.hasFieldErrors()) {
            String errorString = getErrorString(br);
            return ResponseEntity.badRequest().body(errorString);
        } else {
            Long id = televisionService.addTelevision(televisionInputDto).getId();
            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/" + id).toUriString());
            return ResponseEntity.created(uri).body(televisionInputDto);
        }
    }

    // Als een item niet verwijderd kan worden, in verband met relaties, geeft het alsnog een 204 No Content bericht
    @DeleteMapping("/televisions/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable Long id) {
        televisionService.deleteTelevision(id);
        return ResponseEntity.noContent().build(); // Beide ResponseEntity vormen geven geen feedback na verwijderen van een item
    }

    @PutMapping("/televisions/{id}")
    public ResponseEntity<Object> updateTelevision(@PathVariable Long id, @Valid @RequestBody TelevisionInputDto televisionInputDto) {
        TelevisionDto televisionDto = televisionService.updateTelevision(id, televisionInputDto);
        return ResponseEntity.ok().body(televisionDto);
    }

    // Onderstaande 2 methodes zijn endpoints om andere entiteiten toe te voegen aan de Television.
    // Dit is één manier om dit te doen, met één PathVariable en één RequestBody.
    // Remote_controller_id wel zichtbaar in de database onder Televisions
    @PutMapping("/televisions/{id}/remotecontroller")
    public ResponseEntity<Object> assignRemoteControllerToTelevision(@PathVariable("id") Long id, @Valid @RequestBody IdInputDto input) {
        televisionService.assignRemoteControllerToTelevision(id, input.id);
        return ResponseEntity.noContent().build();
    }

    // Dit is een andere manier om het te doen, met twee Pathvariables, maar het kan uiteraard ook anders.
    @PutMapping("/televisions/{id}/{ciModuleId}")
    public ResponseEntity<Object> assignCIModuleToTelevision(@PathVariable("id") Long id, @PathVariable("ciModuleId") Long ciModuleId) {
        televisionService.assignCIModuleToTelevision(id, ciModuleId);
        return ResponseEntity.noContent().build();
    }

    // Deze methode is om alle wallbrackets op te halen die aan een bepaalde television gekoppeld zijn.
    // Deze methode maakt gebruik van de televisionWallBracketService.
    @GetMapping("/televisions/wallBrackets/{televisionId}")
    public ResponseEntity<Collection<WallBracketDto>> getWallBracketsByTelevisionId(@PathVariable("televisionId") Long televisionId) {
        return ResponseEntity.ok(televisionWallBracketService.getWallBracketsByTelevisionId(televisionId));
    }
}
