package nl.novi.techiteasy1121.services;

import lombok.AllArgsConstructor;
import nl.novi.techiteasy1121.dtos.WallBracketDto;
import nl.novi.techiteasy1121.dtos.WallBracketInputDto;
import nl.novi.techiteasy1121.exceptions.RecordNotFoundException;
import nl.novi.techiteasy1121.models.WallBracket;
import nl.novi.techiteasy1121.repositories.WallBracketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WallBracketService {

    private final WallBracketRepository wallBracketRepository;

    public List<WallBracketDto> getAllWallBrackets() {
        List<WallBracket> wallBracketList = wallBracketRepository.findAll();
        List<WallBracketDto> wallBracketDtoList = new ArrayList<>();
        for (WallBracket wb : wallBracketList) {
            wallBracketDtoList.add(transferToDto(wb));
        }
        return wallBracketDtoList;
    }

    public WallBracketDto getWallBracket(long id) {
        Optional<WallBracket> wallBracket = wallBracketRepository.findById(id);
        if (wallBracket.isPresent()) {
            WallBracketDto dto = transferToDto(wallBracket.get());
            return dto;
        } else {
            throw new RecordNotFoundException("No wallbracket found with this id");
        }
    }

    public WallBracketDto addWallbracket(WallBracketInputDto wallBracketInputDto) {
        WallBracket wallBracket = transferToWallBracket(wallBracketInputDto);
        wallBracketRepository.save(wallBracket);
        return transferToDto(wallBracket);
    }

    public void deleteWallBracket(Long id) {
        wallBracketRepository.deleteById(id);
    }

    public WallBracketDto updateWallBracket(Long id, WallBracketInputDto wallBracketInputDto) {

        if (wallBracketRepository.findById(id).isPresent()) {
            WallBracket wallBracket = wallBracketRepository.findById(id).get();
            WallBracket wallBracket1 = transferToWallBracket(wallBracketInputDto);
            wallBracket1.setId(wallBracket.getId());
            wallBracketRepository.save(wallBracket1);
            return transferToDto(wallBracket1);
        } else {
            throw new RecordNotFoundException("No wallbracket found with this id");
        }
    }

    public WallBracketDto transferToDto(WallBracket wallBracket) {
        var dto = new WallBracketDto();

        dto.setId(wallBracket.getId());
        dto.setName(wallBracket.getName());
        dto.setSize(wallBracket.getSize());
        dto.setAdjustable(wallBracket.getAdjustable());
        dto.setPrice(wallBracket.getPrice());

        return dto;
    }

    public WallBracket transferToWallBracket(WallBracketInputDto wallBracketInputDto) {
        var wallBracket = new WallBracket();

        wallBracket.setName(wallBracketInputDto.getName());
        wallBracket.setSize(wallBracketInputDto.getSize());
        wallBracket.setAdjustable(wallBracketInputDto.getAdjustable());
        wallBracket.setPrice(wallBracketInputDto.getPrice());

        return wallBracket;
    }

}