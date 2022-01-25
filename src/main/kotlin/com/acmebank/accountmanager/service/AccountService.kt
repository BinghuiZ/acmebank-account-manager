package com.acmebank.accountmanager.service

import com.acmebank.accountmanager.model.Account
import com.acmebank.accountmanager.repository.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.util.stream.Collectors
import java.util.stream.StreamSupport

@Service
class AccountService(@Autowired val accountRepository: AccountRepository) {

    fun getAllAccount(): List<Account> {
        return StreamSupport
            .stream(accountRepository.findAll().spliterator(), false)
            .collect(Collectors.toList())
    }

    fun getAccount(id: Int): Account {
        return accountRepository.findById(id).orElseThrow { Exception("$id was not found") }
    }

    @Transactional
    fun transferMoney(from: Int, to: Int, amount: BigDecimal): Account {
        val fromUser = getAccount(from)
        val toUser = getAccount(to)
        if (fromUser.balance - amount > BigDecimal.ZERO) {
            fromUser.balance -= amount
            toUser.balance += amount
            accountRepository.save(fromUser)
            accountRepository.save(toUser)
            return fromUser
        } else {
            throw Exception("Your balance is not enough to transfer money.")
        }
    }
}