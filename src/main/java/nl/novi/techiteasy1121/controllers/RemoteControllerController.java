package nl.novi.techiteasy1121.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.novi.techiteasy1121.dtos.RemoteControllerDto;
import nl.novi.techiteasy1121.dtos.RemoteControllerInputDto;
import nl.novi.techiteasy1121.services.RemoteControllerService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static nl.novi.techiteasy1121.utilities.Utilities.getErrorString;

@RestController
@AllArgsConstructor
public class RemoteControllerController {

    private final RemoteControllerService remoteControllerService;

    @GetMapping("/remotecontrollers")
    public ResponseEntity<List<RemoteControllerDto>> getAllRemotecontrollers() {
        List<RemoteControllerDto> dtoList = remoteControllerService.getAllRemoteControllers();
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/remotecontrollers/{id}")
    public ResponseEntity<RemoteControllerDto> getRemotecontroller(@PathVariable("id") Long id) {
        RemoteControllerDto remoteControllerDto = remoteControllerService.getRemoteController(id);
        return ResponseEntity.ok(remoteControllerDto);
    }

    @PostMapping("/remotecontrollers")
    public ResponseEntity<Object> addRemoteController(@Valid @RequestBody RemoteControllerInputDto remoteControllerInputDto, BindingResult br) {
        if (br.hasFieldErrors()) {
            String errorString = getErrorString(br);
            return ResponseEntity.badRequest().body(errorString);
        } else {
            Long id = remoteControllerService.addRemoteController(remoteControllerInputDto).getId();
            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/" + id).toUriString());
            return ResponseEntity.created(uri).body("Remote controller added with id: " + id); // Of: body(remoteControllerInputDto)
        }
    }

    @DeleteMapping("/remotecontrollers/{id}")
    public ResponseEntity<Object> deleteRemoteController(@PathVariable Long id) {
        remoteControllerService.deleteRemoteController(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/remotecontrollers/{id}")
    public ResponseEntity<Object> updateRemoteController(@PathVariable("id") Long id, @Valid @RequestBody RemoteControllerInputDto remoteControllerInputDto) {
        RemoteControllerDto remoteControllerDto = remoteControllerService.updateRemoteController(id, remoteControllerInputDto);
        return ResponseEntity.ok().body(remoteControllerDto);
    }
}