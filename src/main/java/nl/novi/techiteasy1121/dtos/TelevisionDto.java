package nl.novi.techiteasy1121.dtos;

import lombok.Data;
import nl.novi.techiteasy1121.models.TestEnum;

// Geen validatie in de outputDto
// Wel een id in de outputDto
// Velden exact gelijk aan Television.java
@Data
// Lombok: A shortcut for @ToString, @EqualsAndHashCode, @Getter on all fields, @Setter on all non-final fields, and @RequiredArgsConstructor.
public class TelevisionDto {
    private Long id;
    private TestEnum testEnum;
    private String type;
    private String brand;
    private String name;
    private Double price;
    private Double availableSize;
    private Double refreshRate;
    private String screenType;
    private String screenQuality;
    private Boolean smartTv;
    private Boolean wifi;
    private Boolean voiceControl;
    private Boolean hdr;
    private Boolean bluetooth;
    private Boolean ambiLight;
    private Integer originalStock;
    private Integer sold;

    private RemoteControllerDto remoteControllerDto; //@OneToOne relatie
    private CIModuleDto ciModuleDto;
}
