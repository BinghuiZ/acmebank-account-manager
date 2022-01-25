package com.acmebank.accountmanager.exception

class GeneralResponseException: RuntimeException {

    constructor(message: String?): super(message)
    constructor(message: String?, throwable: Throwable?): super(message, throwable)
}