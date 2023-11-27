package nl.novi.techiteasy1121.services;

import nl.novi.techiteasy1121.dtos.TelevisionDto;
import nl.novi.techiteasy1121.dtos.WallBracketDto;
import nl.novi.techiteasy1121.exceptions.RecordNotFoundException;
import nl.novi.techiteasy1121.models.Television;
import nl.novi.techiteasy1121.models.TelevisionWallBracket;
import nl.novi.techiteasy1121.models.TelevisionWallBracketKey;
import nl.novi.techiteasy1121.models.WallBracket;
import nl.novi.techiteasy1121.repositories.TelevisionRepository;
import nl.novi.techiteasy1121.repositories.TelevisionWallBracketRepository;
import nl.novi.techiteasy1121.repositories.WallBracketRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Deze klasse bevat de service methodes van TelevisionWallBracketController.
// Deze klasse wijkt af van de andere service-klassen, omdat deze in 3 verschillende controllers wordt ge-autowired.
// Deze class heeft geen Dto
@Service
public class TelevisionWallBracketService{

    private final TelevisionRepository televisionRepository;

    private final WallBracketRepository wallBracketRepository;

    private final TelevisionWallBracketRepository televisionWallBracketRepository;

    public TelevisionWallBracketService(TelevisionRepository televisionRepository, WallBracketRepository wallBracketRepository, TelevisionWallBracketRepository televisionWallBracketRepository) {
        this.televisionRepository = televisionRepository;
        this.wallBracketRepository = wallBracketRepository;
        this.televisionWallBracketRepository = televisionWallBracketRepository;
    }

    public Collection<TelevisionDto> getTelevisionsByWallBracketId(Long wallBracketId) {
        Collection<TelevisionDto> televisionDtoCollection = new HashSet<>();

        Collection<TelevisionWallBracket> televisionWallbrackets = televisionWallBracketRepository.findAllByWallBracketId(wallBracketId);
        for (TelevisionWallBracket televisionWallbracket : televisionWallbrackets) {
            Television television = televisionWallbracket.getTelevision();
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

            televisionDtoCollection.add(televisionDto);
        }
        return televisionDtoCollection;
    }

    // Collection is de super klasse van zowel List als Set.
    public Collection<WallBracketDto> getWallBracketsByTelevisionId(Long televisionId) {
        //We gebruiken hier Set om te voorkomen dat er dubbele entries in staan.
        Set<WallBracketDto> wallBracketDtoSet = new HashSet<>();
        List<TelevisionWallBracket> televisionWallbrackets = televisionWallBracketRepository.findAllByTelevisionId(televisionId);
        for (TelevisionWallBracket televisionWallbracket : televisionWallbrackets) {
            WallBracket wallBracket = televisionWallbracket.getWallBracket();
            var dto = new WallBracketDto();

            dto.setId(wallBracket.getId());
            dto.setName(wallBracket.getName());
            dto.setSize(wallBracket.getSize());
            dto.setAdjustable(wallBracket.getAdjustable());
            dto.setPrice(wallBracket.getPrice());

            wallBracketDtoSet.add(dto);
        }
        return wallBracketDtoSet;
    }

    public TelevisionWallBracketKey addTelevisionWallBracket(Long televisionId, Long wallBracketId) {
        var televisionWallBracket = new TelevisionWallBracket();
        if (!televisionRepository.existsById(televisionId)) {throw new RecordNotFoundException();}
        Television television = televisionRepository.findById(televisionId).orElse(null);
        if (!wallBracketRepository.existsById(wallBracketId)) {throw new RecordNotFoundException();}
        WallBracket wallBracket = wallBracketRepository.findById(wallBracketId).orElse(null);
        televisionWallBracket.setTelevision(television);
        televisionWallBracket.setWallBracket(wallBracket);
        TelevisionWallBracketKey id = new TelevisionWallBracketKey(televisionId, wallBracketId);
        televisionWallBracket.setId(id);
        televisionWallBracketRepository.save(televisionWallBracket);
        return id;
    }
}
