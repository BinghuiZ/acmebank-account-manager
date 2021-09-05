package com.acmebank.accountmanager.model.exception

class InsufficientAccontException: RuntimeException {

    constructor(message: String?): super(message)
    constructor(message: String?, throwable: Throwable?): super(message, throwable)

}