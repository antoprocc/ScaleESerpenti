package org.example.exceptions;

public class SerpenteNonValidoException extends RuntimeException{
    public SerpenteNonValidoException() {
        super("Il serpente non è valido");
    }
}
