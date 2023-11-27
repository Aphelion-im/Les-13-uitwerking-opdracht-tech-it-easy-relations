package nl.novi.techiteasy1121.controllers;

import jakarta.validation.Valid;
import nl.novi.techiteasy1121.dtos.CIModuleDto;
import nl.novi.techiteasy1121.dtos.CIModuleInputDto;
import nl.novi.techiteasy1121.services.CIModuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Dit is de Controller klasse van de CIModule entiteit en heeft vergelijkbare methodes als de TelevisionController
@RestController
public class CIModuleController {
    private final CIModuleService ciModuleService;

    public CIModuleController(CIModuleService ciModuleService) {
        this.ciModuleService = ciModuleService;
    }

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

    // Geen URI uri present/ Location van de resource URI.
    @PostMapping("/cimodules")
    public ResponseEntity<CIModuleDto> addCIModule(@Valid @RequestBody CIModuleInputDto ciModuleInputDto) {
        CIModuleDto ciModuleDto = ciModuleService.addCIModule(ciModuleInputDto);
        return ResponseEntity.created(null).body(ciModuleDto);
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