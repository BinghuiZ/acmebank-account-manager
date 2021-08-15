package com.acmebank.accountmanager.repository

import com.acmebank.accountmanager.model.Balance
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BalanceRepository: CrudRepository<Balance, Int> {
}