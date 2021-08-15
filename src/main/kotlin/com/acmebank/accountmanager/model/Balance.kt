package com.acmebank.accountmanager.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="balance")
class Balance {
    @Id var id: Int = 0
    var first_name: String = ""
    var last_name: String = ""
    var balance: Double = 0.0
}