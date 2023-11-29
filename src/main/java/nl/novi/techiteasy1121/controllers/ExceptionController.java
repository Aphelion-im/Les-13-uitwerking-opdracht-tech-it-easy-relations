package nl.novi.techiteasy1121.controllers;

import nl.novi.techiteasy1121.exceptions.BadRequestException;
import nl.novi.techiteasy1121.exceptions.RecordNotFoundException;
import nl.novi.techiteasy1121.exceptions.TelevisionNameTooLongException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    // Custom exceptions
    // Deze exception handler vangt elke RecordNotFoundException op die naar de gebruiker gegooid wordt en returned daar voor in de plaats een ResponseEntity met de Message en de NOT_FOUND-status (404)
    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<Object> exception(RecordNotFoundException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Deze exception handler vangt elke TelevisionNameTooLongException op die naar de gebruiker gegooid wordt en returned daar voor in de plaats een ResponseEntity met de Message en de NOT_FOUND-status (404)
    @ExceptionHandler(value = TelevisionNameTooLongException.class)
    public ResponseEntity<String> exception(TelevisionNameTooLongException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<String> exception(BadRequestException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Default Java exceptions
    // Deze exception handler vangt elke IndexOutOfBoundsException (deze exception komt uit de java.utils package) op die naar de gebruiker gegooid wordt en returned daar voor in de plaats een ResponseEntity met de Message en de NOT_FOUND-status (404)
    @ExceptionHandler(value = IndexOutOfBoundsException.class)
    public ResponseEntity<Object> exception(IndexOutOfBoundsException exception) {

        return new ResponseEntity<>("This id is not in the database", HttpStatus.NOT_FOUND);
    }
}