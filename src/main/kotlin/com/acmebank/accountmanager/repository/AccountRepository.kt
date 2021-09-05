package com.acmebank.accountmanager.repository

import com.acmebank.accountmanager.model.Account
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository: CrudRepository<Account, Int> {
}