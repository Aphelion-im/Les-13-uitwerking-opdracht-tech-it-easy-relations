package nl.novi.techiteasy1121.controllers;

import jakarta.validation.Valid;
import nl.novi.techiteasy1121.dtos.CIModuleDto;
import nl.novi.techiteasy1121.dtos.CIModuleInputDto;
import nl.novi.techiteasy1121.dtos.RemoteControllerDto;
import nl.novi.techiteasy1121.dtos.RemoteControllerInputDto;
import nl.novi.techiteasy1121.services.RemoteControllerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RemoteControllerController {
    private final RemoteControllerService remoteControllerService;

    public RemoteControllerController(RemoteControllerService remoteControllerService) {
        this.remoteControllerService = remoteControllerService;
    }

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
    public ResponseEntity<RemoteControllerDto> addRemoteController(@Valid @RequestBody RemoteControllerInputDto remoteControllerInputDto) {
        RemoteControllerDto remoteControllerDto1 = remoteControllerService.addRemoteController(remoteControllerInputDto);
        return ResponseEntity.created(null).body(remoteControllerDto1);
    }

    @DeleteMapping("/remotecontrollers/{id}")
    public ResponseEntity<Object> deleteRemoteController(@PathVariable Long id) {
        remoteControllerService.deleteRemoteController(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/remotecontrollers/{id}")
    public ResponseEntity<Object> updateRemoteController2(@PathVariable("id") Long id, @Valid @RequestBody RemoteControllerInputDto remoteControllerInputDto) {
        RemoteControllerDto remoteControllerDto = remoteControllerService.updateRemoteController(id, remoteControllerInputDto);
        return ResponseEntity.ok().body(remoteControllerDto);
    }


}