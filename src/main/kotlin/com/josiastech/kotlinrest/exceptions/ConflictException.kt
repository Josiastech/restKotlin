package com.josiastech.kotlinrest.exceptions
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus

@ResponseStatus(HttpStatus.CONFLICT)
class ConflictException: RuntimeException
{
    constructor(codigo: String?): super("Codigo Pais y codigo dentro de Objeto Pais son diferentes "+codigo);
}