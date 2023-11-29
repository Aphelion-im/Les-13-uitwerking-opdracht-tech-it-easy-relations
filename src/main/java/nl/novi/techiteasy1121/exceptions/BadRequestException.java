package nl.novi.techiteasy1121.exceptions;

public class BadRequestException extends RuntimeException {
    private static final long serialVersionUID = 1L; // Wat doet dit?

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException() {
        super();
    }
}
