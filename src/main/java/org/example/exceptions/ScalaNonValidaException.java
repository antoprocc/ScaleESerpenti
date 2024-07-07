package org.example.exceptions;

public class ScalaNonValidaException extends RuntimeException{
    public ScalaNonValidaException(){
        super("La scala non è valida");
    }
}
