package com.acmebank.accountmanager.service

import com.acmebank.accountmanager.model.Balance
import com.acmebank.accountmanager.model.exception.BalanceNotFoundException
import com.acmebank.accountmanager.repository.BalanceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors
import java.util.stream.StreamSupport

@Service
class BalanceService(@Autowired val balanceRepository: BalanceRepository) {

    fun getAllBalance(): List<Balance> {
        return StreamSupport
            .stream(balanceRepository.findAll().spliterator(), false)
            .collect(Collectors.toList())
    }

    fun getBalance(id: Int): Balance {
        return balanceRepository.findById(id).orElseThrow { BalanceNotFoundException("${id} was not found") }
    }

    @Transactional
    fun transferMoney(from: Int, to: Int, amount: Double): Balance? {
        val fromUser = getBalance(from)
        val toUser = getBalance(to)
        if (fromUser.balance - amount > 0) {
            fromUser.balance -= amount
            toUser.balance += amount
            balanceRepository.save(fromUser)
            balanceRepository.save(toUser)
            return fromUser
        } else {
            return null
        }
    }
}