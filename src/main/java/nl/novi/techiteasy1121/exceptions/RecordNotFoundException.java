// https://howtodoinjava.com/java/serialization/serialversionuid/
package nl.novi.techiteasy1121.exceptions;

public class RecordNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L; // Wat doet dit?

    public RecordNotFoundException() {

        super();

    }

    public RecordNotFoundException(String message) {

        super(message);

    }

}
