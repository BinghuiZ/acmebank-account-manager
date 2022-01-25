package com.acmebank.accountmanager.model

import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="bankaccount")
class Account {
    @Id var id: Int = 0
    var first_name: String = ""
    var last_name: String = ""
    var balance: BigDecimal = BigDecimal.ZERO
}