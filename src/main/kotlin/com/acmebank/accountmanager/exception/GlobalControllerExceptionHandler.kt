package com.acmebank.accountmanager.exception

import com.acmebank.accountmanager.model.BaseModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class GlobalControllerExceptionHandler {

    @ExceptionHandler(value = [(GeneralResponseException::class)])
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    fun handleGeneralResponseException(exception: GeneralResponseException): ResponseEntity<BaseModel> {
        return ResponseEntity(BaseModel(HttpStatus.BAD_REQUEST.name, exception.message.toString()), HttpStatus.BAD_REQUEST)
    }

}