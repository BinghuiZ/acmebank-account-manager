package com.acmebank.accountmanager.model.exception

class InsufficientBalanceException: RuntimeException {

    constructor(message: String?): super(message)
    constructor(message: String?, throwable: Throwable?): super(message, throwable)

}