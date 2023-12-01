package nl.novi.techiteasy1121.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.novi.techiteasy1121.dtos.CIModuleDto;
import nl.novi.techiteasy1121.dtos.CIModuleInputDto;
import nl.novi.techiteasy1121.services.CIModuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static nl.novi.techiteasy1121.utilities.Utilities.getErrorString;


@RestController
@AllArgsConstructor
public class CIModuleController {

    // Alternative to @Autowired and Lombok @AllArgsConstructor dependency/constructor injection:
    private final CIModuleService ciModuleService;

    @GetMapping("/cimodules")
    public ResponseEntity<List<CIModuleDto>> getAllCIModules() {
        List<CIModuleDto> dtoList = ciModuleService.getAllCIModules();
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/cimodules/{id}")
    public ResponseEntity<CIModuleDto> getCIModule(@PathVariable("id") Long id) {
        CIModuleDto ciModuleDto = ciModuleService.getCIModule(id);
        return ResponseEntity.ok(ciModuleDto);
    }

    // Nog niet duidelijk hoe je een outputDto retourneert als je een inputDto hebt.
    @PostMapping("/cimodules")
    public ResponseEntity<Object> addCIModule(@Valid @RequestBody CIModuleInputDto ciModuleInputDto, BindingResult br) {
        if (br.hasFieldErrors()) {
            String errorString = getErrorString(br);
            return ResponseEntity.badRequest().body(errorString);
        } else {
            Long id = ciModuleService.addCIModule(ciModuleInputDto).getId();
            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/" + id).toUriString());
            return ResponseEntity.created(uri).body(ciModuleInputDto);
        }
    }

    @DeleteMapping("/cimodules/{id}")
    public ResponseEntity<Object> deleteCIModule(@PathVariable("id") Long id) {
        ciModuleService.deleteCIModule(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/cimodules/{id}")
    public ResponseEntity<Object> updateCIModule(@PathVariable("id") Long id, @Valid @RequestBody CIModuleInputDto ciModuleInputDto) {
        CIModuleDto ciModuleDto = ciModuleService.updateCIModule(id, ciModuleInputDto);
        return ResponseEntity.ok().body(ciModuleDto);
    }
}