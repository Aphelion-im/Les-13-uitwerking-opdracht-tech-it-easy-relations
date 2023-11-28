package nl.novi.techiteasy1121.dtos;

import jakarta.validation.constraints.NotNull;

// Om een remote controller id in te voeren en te koppelen met een television
public class IdInputDto {

    @NotNull
    public Long id;
}

