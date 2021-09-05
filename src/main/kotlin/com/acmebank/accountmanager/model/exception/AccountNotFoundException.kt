package com.acmebank.accountmanager.model.exception

import kotlin.RuntimeException

class AccountNotFoundException : RuntimeException {

    constructor(message: String?): super(message)
    constructor(message: String?, throwable: Throwable?): super(message, throwable)

}