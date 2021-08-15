package com.acmebank.accountmanager.model.exception

import kotlin.RuntimeException

class BalanceNotFoundException : RuntimeException {

    constructor(message: String?): super(message)
    constructor(message: String?, throwable: Throwable?): super(message, throwable)

}