package nl.novi.techiteasy1121.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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

     /*
     Uitleg:
     Een OneToOne relatie heeft een eigenaar nodig. Maak de Television eigenaar door in RemoteController achter de
     @OneToOne mappedBy toe te voegen op deze manier _@OneToOne(mappedBy = "remotecontroller").
     Dit zorgt ervoor dat in de Television tabel een kolom wordt toegevoegd met de naam remote_controller_id.
     Vergeet niet de getter en setter toe te voegen na het leggen van de relatie in de modellen.

     Relatie velden @OneToOne:
     Television.java:  @OneToOne RemoteController remoteController;
     RemoteController.java:  @OneToOne(mappedBy = "remoteController") Television television;
     RemoteControllerDto.java: Geen verwijzingen
     RemoteControllerInputDto.java: Geen verwijzingen
     TelevisionDto: RemoteControllerDto remoteControllerDto;
     TelevisionInputDto: Geen verwijzingen
    */


    // Een OneToOne relatie tussen Television en RemoteController, zoals staat aangegeven in het klassendiagram
    @OneToOne
    RemoteController remoteController;

    // Dit is de owner kan van de relatie. Er staat een foreign key in de database
    // Een OneToMany relatie tussen Television en CI-Module
    @ManyToOne(fetch = FetchType.EAGER) // fetch = FetchType.EAGER?
    @JoinColumn(name = "ci_module_id") // Verwijst naar FK. https://www.geeksforgeeks.org/when-to-use-joincolumn-annotation-in-hibernate/?ref=ml_lbp
    private CIModule ciModule;

    // Dit is de target kant van de relatie. Er staat niks in de database
    // @OneToMany(mappedBy = "television") // Waarom staat er OneToMany als het een ManyToMany is? -- Origineel
    @OneToMany(mappedBy = "television", cascade = CascadeType.REMOVE, orphanRemoval = true)
    // Waarom staat er OneToMany als het een ManyToMany is?
    @LazyCollection(LazyCollectionOption.FALSE)
    // When we set this option to FALSE, we enable the eager fetching approach. . The main idea of using the @LazyCollection is to control whether the fetching of data should be using the lazy approach or the eager one. https://www.baeldung.com/hibernate-lazycollection
    @JsonIgnore // @JsonIgnore - Bij stack overflow foutmeldingen door een oneindige loop/recursie. Les 13 (2023/02): Video@1h18m. Staat waarschijnlijk alleen boven List, Collections, etc.
            Collection<TelevisionWallBracket> televisionWallBrackets;

}