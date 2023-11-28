package nl.novi.techiteasy1121.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import jakarta.persistence.*;

import java.util.Collection;

@Entity(name = "Television")
@Table(name = "televisions")
@Data
public class Television {

    @Id
    @GeneratedValue
    Long id;

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

    // Dit is de owner kan van de relatie. Er staat een foreign key in de database (kolom remote_controller_id waarschijnlijk)
    // Kolom remote_controller_id staat niet hier tussen de velden van deze klasse en ook niet in de TelevisionDto
    // Idem dito met ci_module_id. Waarschijnlijk maakt SB deze 2 foreign key kolommen automatisch aan met deze annotaties.
    // Uitleg: Een OneToOne relatie heeft een eigenaar nodig. Maak de Television eigenaar door in RemoteController achter de
    // @OneToOne mappedBy toe te voegen op deze manier _@OneToOne(mappedBy = "remotecontroller").
    // Dit zorgt ervoor dat in de Television tabel een kolom wordt toegevoegd met de naam remote_controller_id.
    // Vergeet niet de getter en setter toe te voegen na het leggen van de relatie in de modellen.

    // Een OneToOne relatie tussen Television en RemoteController
    @OneToOne
    RemoteController remoteController;

    // Dit is de owner kan van de relatie. Er staat een foreign key in de database
    // Een OneToMany relatie tussen Television en CI-Module
    @ManyToOne(fetch = FetchType.EAGER) // fetch = FetchType.EAGER?
    @JoinColumn(name = "ci_module_id") //  @JoinColumn(name = "ci_module_id")?
    private CIModule ciModule;

    // Dit is de target kant van de relatie. Er staat niks in de database
    @OneToMany(mappedBy = "television") // Waarom staat er OneToMany als het een ManyToMany is?
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    Collection<TelevisionWallBracket> televisionWallBrackets;
}