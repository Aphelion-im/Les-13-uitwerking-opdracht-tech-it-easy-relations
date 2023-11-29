package nl.novi.techiteasy1121.services;

import lombok.AllArgsConstructor;
import nl.novi.techiteasy1121.dtos.RemoteControllerDto;
import nl.novi.techiteasy1121.dtos.RemoteControllerInputDto;
import nl.novi.techiteasy1121.exceptions.RecordNotFoundException;
import nl.novi.techiteasy1121.models.RemoteController;
import nl.novi.techiteasy1121.repositories.RemoteControllerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RemoteControllerService {

    private final RemoteControllerRepository remoteControllerRepository;

    public List<RemoteControllerDto> getAllRemoteControllers() {
        List<RemoteControllerDto> dtos = new ArrayList<>();
        List<RemoteController> remoteControllers = remoteControllerRepository.findAll();
        for (RemoteController rc : remoteControllers) {
            dtos.add(transferToDto(rc));
        }
        return dtos;
    }

    public RemoteControllerDto getRemoteController(long id) {
        Optional<RemoteController> remoteController = remoteControllerRepository.findById(id);
        if (remoteController.isPresent()) {
            return transferToDto(remoteController.get());
        } else {
            throw new RecordNotFoundException("No remotecontroller found with id: " + id);
        }
    }

    public RemoteControllerDto addRemoteController(RemoteControllerInputDto remoteControllerInputDto) {
        RemoteController remoteController = transferToRemoteController(remoteControllerInputDto);
        remoteControllerRepository.save(remoteController);
        return transferToDto(remoteController);
    }

    public void deleteRemoteController(Long id) {
        remoteControllerRepository.deleteById(id);
    }

    public RemoteControllerDto updateRemoteController(Long id, RemoteControllerInputDto remoteControllerInputDto) {
        if (remoteControllerRepository.findById(id).isPresent()) {
            RemoteController remoteController = remoteControllerRepository.findById(id).get();
            RemoteController remoteController1 = transferToRemoteController(remoteControllerInputDto);
            remoteController1.setId(remoteController.getId());
            remoteControllerRepository.save(remoteController1);
            return transferToDto(remoteController1);
        } else {
            throw new RecordNotFoundException("No remotecontroller found with id: " + id);
        }
    }

    // Ontdubbelen?
    public RemoteControllerDto transferToDto(RemoteController remoteController) {
        RemoteControllerDto remoteControllerDto = new RemoteControllerDto();

        remoteControllerDto.setId(remoteController.getId());
        remoteControllerDto.setCompatibleWith(remoteController.getCompatibleWith());
        remoteControllerDto.setBatteryType(remoteController.getBatteryType());
        remoteControllerDto.setName(remoteController.getName());
        remoteControllerDto.setBrand(remoteController.getBrand());
        remoteControllerDto.setPrice(remoteController.getPrice());
        remoteControllerDto.setOriginalStock(remoteController.getOriginalStock());

        return remoteControllerDto;
    }

    // Ontdubbelen?
    public RemoteController transferToRemoteController(RemoteControllerInputDto remoteControllerInputDto) {
        var remoteController = new RemoteController();

        remoteController.setCompatibleWith(remoteControllerInputDto.getCompatibleWith());
        remoteController.setBatteryType(remoteControllerInputDto.getBatteryType());
        remoteController.setName(remoteControllerInputDto.getName());
        remoteController.setBrand(remoteControllerInputDto.getBrand());
        remoteController.setPrice(remoteControllerInputDto.getPrice());
        remoteController.setOriginalStock(remoteControllerInputDto.getOriginalStock());

        return remoteController;
    }

}
