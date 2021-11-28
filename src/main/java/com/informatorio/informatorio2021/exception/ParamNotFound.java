package com.informatorio.informatorio2021.exception;


public class ParamNotFound extends RuntimeException{

    public ParamNotFound() {
        super();
    }

    public ParamNotFound(String id_inexistente) {
    }
}
