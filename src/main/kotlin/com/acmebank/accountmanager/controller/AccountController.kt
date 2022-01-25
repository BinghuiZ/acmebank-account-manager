package com.acmebank.accountmanager.controller

import com.acmebank.accountmanager.exception.GeneralResponseException
import com.acmebank.accountmanager.model.Account
import com.acmebank.accountmanager.service.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

@RestController
@RequestMapping("/")
class AccountController(@Autowired val accountService: AccountService) {

    @GetMapping("/{id}")
    fun getAccountById(@PathVariable("id") id: Int): ResponseEntity<Account> {
        try {
            val account = accountService.getAccount(id)
            return ResponseEntity<Account>(account, HttpStatus.OK)
        } catch (e: Exception) {
            throw GeneralResponseException(e.message)
        }
    }

    @GetMapping("/list")
    fun getAllAccount(): ResponseEntity<List<Account>> {
        val list = accountService.getAllAccount()
        if (list.isEmpty()) {
            throw GeneralResponseException("No Records Found.")
        }
        return ResponseEntity<List<Account>>(list, HttpStatus.OK)
    }

    @PostMapping("/transfer")
    @ResponseBody
    fun transferMoney(@RequestParam("from") from: Int,
                      @RequestParam("to") to: Int,
                      @RequestParam("amount") amount: BigDecimal): ResponseEntity<Account> {
        try {
            return ResponseEntity<Account>(accountService.transferMoney(from, to, amount), HttpStatus.OK)
        } catch (e: Exception) {
            throw GeneralResponseException(e.message)
        }

    }
}
