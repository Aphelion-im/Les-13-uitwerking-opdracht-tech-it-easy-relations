package nl.novi.techiteasy1121.services;

import lombok.AllArgsConstructor;
import nl.novi.techiteasy1121.dtos.CIModuleDto;
import nl.novi.techiteasy1121.dtos.CIModuleInputDto;
import nl.novi.techiteasy1121.exceptions.RecordNotFoundException;
import nl.novi.techiteasy1121.models.CIModule;
import nl.novi.techiteasy1121.models.Television;
import nl.novi.techiteasy1121.repositories.CIModuleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Deze klasse bevat de service methodes van de CIModuleController

@Service
@AllArgsConstructor
public class CIModuleService {

    private final CIModuleRepository ciModuleRepository;

    public List<CIModuleDto> getAllCIModules() {
        List<CIModule> ciModules = ciModuleRepository.findAll();
        List<CIModuleDto> dtoList = new ArrayList<>();
        for (CIModule ci : ciModules) {
            dtoList.add(transferToDto(ci));
        }
        return dtoList;
    }

    public CIModuleDto getCIModule(long id) {
        Optional<CIModule> ciModule = ciModuleRepository.findById(id);
        if (ciModule.isPresent()) {
            CIModuleDto ci = transferToDto(ciModule.get());
            return ci;
        } else {
            throw new RecordNotFoundException("No CI-Module found with this id");
        }
    }

    public CIModuleDto addCIModule(CIModuleInputDto ciModuleInputDto) {
        CIModule ciModule = transferToCIModule(ciModuleInputDto);
        ciModuleRepository.save(ciModule);
        return transferToDto(ciModule);
    }

    public void deleteCIModule(Long id) {
        ciModuleRepository.deleteById(id);
    }

    public CIModuleDto updateCIModule(Long id, CIModuleInputDto ciModuleInputDto) {
        if (ciModuleRepository.findById(id).isPresent()) {
            CIModule ciModule = ciModuleRepository.findById(id).get();
            CIModule ciModule1 = transferToCIModule(ciModuleInputDto);
            ciModule1.setId(ciModule.getId());
            ciModuleRepository.save(ciModule1);
            return transferToDto(ciModule1);
        } else {
            throw new RecordNotFoundException("No CI-Module found with this id");
        }
    }

    public CIModule transferToCIModule(CIModuleInputDto ciModuleInputDto) {
        var ciModule = new CIModule();
        ciModule.setName(ciModuleInputDto.getName());
        ciModule.setType(ciModuleInputDto.getType());
        ciModule.setPrice(ciModuleInputDto.getPrice());
        return ciModule;
    }

    public static CIModuleDto transferToDto(CIModule ciModule) {
        CIModuleDto ciModuleDto = new CIModuleDto();

        ciModuleDto.setId(ciModule.getId());
        ciModuleDto.setName(ciModule.getName());
        ciModuleDto.setType(ciModule.getType());
        ciModuleDto.setPrice(ciModule.getPrice());
        return ciModuleDto;
    }
}
