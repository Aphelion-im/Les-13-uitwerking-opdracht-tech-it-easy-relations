package nl.novi.techiteasy1121.services;

import nl.novi.techiteasy1121.dtos.TelevisionDto;
import nl.novi.techiteasy1121.dtos.TelevisionInputDto;
import nl.novi.techiteasy1121.exceptions.RecordNotFoundException;
import nl.novi.techiteasy1121.models.Television;
import nl.novi.techiteasy1121.repositories.CIModuleRepository;
import nl.novi.techiteasy1121.repositories.RemoteControllerRepository;
import nl.novi.techiteasy1121.repositories.TelevisionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class TelevisionService {

    private final TelevisionRepository televisionRepository;

    private final RemoteControllerRepository remoteControllerRepository;

    private final RemoteControllerService remoteControllerService;

    private final CIModuleRepository ciModuleRepository;

    private final CIModuleService ciModuleService;

    public TelevisionService(TelevisionRepository televisionRepository,
                             RemoteControllerRepository remoteControllerRepository,
                             RemoteControllerService remoteControllerService,
                             CIModuleRepository ciModuleRepository,
                             CIModuleService ciModuleService
    ) {
        this.televisionRepository = televisionRepository;
        this.remoteControllerRepository = remoteControllerRepository;
        this.remoteControllerService = remoteControllerService;
        this.ciModuleRepository = ciModuleRepository;
        this.ciModuleService = ciModuleService;
    }

    public List<TelevisionDto> getAllTelevisions() {
        List<Television> tvList = televisionRepository.findAll();
        return transferTvListToDtoList(tvList);
    }

    public List<TelevisionDto> getAllTelevisionsByBrand(String brand) {
        List<Television> tvList = televisionRepository.findAllTelevisionsByBrandEqualsIgnoreCase(brand);
        return transferTvListToDtoList(tvList);
    }

    public List<TelevisionDto> transferTvListToDtoList(List<Television> televisions) {
        List<TelevisionDto> tvDtoList = new ArrayList<>();
        for (Television tv : televisions) {
            TelevisionDto dto = transferToDto(tv);
            if (tv.getCiModule() != null) {
                dto.setCiModuleDto(ciModuleService.transferToDto(tv.getCiModule()));
            }
            if (tv.getRemoteController() != null) {
                dto.setRemoteControllerDto(remoteControllerService.transferToDto(tv.getRemoteController()));
            }
            tvDtoList.add(dto);
        }
        return tvDtoList;
    }

    public TelevisionDto getTelevisionById(Long id) {
        if (televisionRepository.findById(id).isPresent()) {
            Television tv = televisionRepository.findById(id).get();
            TelevisionDto dto = transferToDto(tv);
            if (tv.getCiModule() != null) {
                dto.setCiModuleDto(ciModuleService.transferToDto(tv.getCiModule()));
            }
            if (tv.getRemoteController() != null) {
                dto.setRemoteControllerDto(remoteControllerService.transferToDto(tv.getRemoteController()));
            }
            return transferToDto(tv);
        } else {
            throw new RecordNotFoundException("geen televisie gevonden");
        }
    }

    public TelevisionDto addTelevision(TelevisionInputDto televisionInputDto) {
        Television tv = transferToTelevision(televisionInputDto);
        televisionRepository.save(tv);
        return transferToDto(tv);
    }


    // Id 1001 - 1005 kun je niet verwijderen omdat ze een koppeling en een entry hebben met/in andere tabellen.
    public void deleteTelevision(@RequestBody Long id) {
        televisionRepository.deleteById(id);
    }


    public TelevisionDto updateTelevision(Long id, TelevisionInputDto inputDto) {
        if (televisionRepository.findById(id).isPresent()) {
            Television tv = televisionRepository.findById(id).get();
            Television tv1 = transferToTelevision(inputDto);
            tv1.setId(tv.getId());
            televisionRepository.save(tv1);
            return transferToDto(tv1);
        } else {
            throw new RecordNotFoundException("No television found with this id");
        }
    }

    public Television transferToTelevision(TelevisionInputDto televisionInputDto) {
        var television = new Television();
        television.setType(televisionInputDto.getType());
        television.setBrand(televisionInputDto.getBrand());
        television.setName(televisionInputDto.getName());
        television.setPrice(televisionInputDto.getPrice());
        television.setAvailableSize(televisionInputDto.getAvailableSize());
        television.setRefreshRate(televisionInputDto.getRefreshRate());
        television.setScreenType(televisionInputDto.getScreenType());
        television.setScreenQuality(televisionInputDto.getScreenQuality());
        television.setSmartTv(televisionInputDto.getSmartTv());
        television.setWifi(televisionInputDto.getWifi());
        television.setVoiceControl(televisionInputDto.getVoiceControl());
        television.setHdr(televisionInputDto.getHdr());
        television.setBluetooth(televisionInputDto.getBluetooth());
        television.setAmbiLight(televisionInputDto.getAmbiLight());
        television.setOriginalStock(televisionInputDto.getOriginalStock());
        television.setSold(televisionInputDto.getSold());

        return television;
    }

    public TelevisionDto transferToDto(Television television) {
        TelevisionDto televisionDto = new TelevisionDto();

        televisionDto.setId(television.getId());
        televisionDto.setType(television.getType());
        televisionDto.setBrand(television.getBrand());
        televisionDto.setName(television.getName());
        televisionDto.setPrice(television.getPrice());
        televisionDto.setAvailableSize(television.getAvailableSize());
        televisionDto.setRefreshRate(television.getRefreshRate());
        televisionDto.setScreenType(television.getScreenType());
        televisionDto.setScreenQuality(television.getScreenQuality());
        televisionDto.setSmartTv(television.getSmartTv());
        televisionDto.setWifi(television.getWifi());
        televisionDto.setVoiceControl(television.getVoiceControl());
        televisionDto.setHdr(television.getHdr());
        televisionDto.setBluetooth(television.getBluetooth());
        televisionDto.setAmbiLight(television.getAmbiLight());
        televisionDto.setOriginalStock(television.getOriginalStock());
        televisionDto.setSold(television.getSold());

        // Als extra op deze transfer methode, voegen we ook de relaties toe.
        // Hier moeten we eerst een null check voor doen,
        // omdat we anders in CIModule.transferToDto de get-methodes van "null" aanroepen en dat kan niet.
        if (television.getCiModule() != null) {
            televisionDto.setCiModuleDto(CIModuleService.transferToDto(television.getCiModule()));
        }
        return televisionDto;
    }

    public void assignRemoteControllerToTelevision(Long id, Long remoteControllerId) {
        var optionalTelevision = televisionRepository.findById(id);
        var optionalRemoteController = remoteControllerRepository.findById(remoteControllerId);
        if (optionalTelevision.isPresent() && optionalRemoteController.isPresent()) {
            var television = optionalTelevision.get();
            var remoteController = optionalRemoteController.get();
            television.setRemoteController(remoteController);
            televisionRepository.save(television);
        } else {
            throw new RecordNotFoundException();
        }
    }

    public void assignCIModuleToTelevision(Long id, Long ciModuleId) {
        var optionalTelevision = televisionRepository.findById(id);
        var optionalCIModule = ciModuleRepository.findById(ciModuleId);
        if (optionalTelevision.isPresent() && optionalCIModule.isPresent()) {
            var television = optionalTelevision.get();
            var ciModule = optionalCIModule.get();
            television.setCiModule(ciModule);
            televisionRepository.save(television);
        } else {
            throw new RecordNotFoundException();
        }
    }
}
