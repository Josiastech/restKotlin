package com.josiastech.kotlinrest.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException: RuntimeException {

    constructor(codigo: String?): super("No encontrados registro "+codigo);
}