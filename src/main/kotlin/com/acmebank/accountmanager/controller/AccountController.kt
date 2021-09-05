package com.acmebank.accountmanager.controller

import com.acmebank.accountmanager.model.Account
import com.acmebank.accountmanager.model.exception.AccountNotFoundException
import com.acmebank.accountmanager.model.exception.InsufficientAccontException
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
    fun getAccountById(@PathVariable("id") id: Int): ResponseEntity<CusResponseObject> {
        try {
            val account = accountService.getAccount(id)
            return ResponseEntity<Account>(account, HttpStatus.OK) as ResponseEntity<CusResponseObject>
        } catch (e: AccountNotFoundException) {
            return ResponseEntity<ErrorHandleObject>(ErrorHandleObject(e.message), HttpStatus.BAD_REQUEST) as ResponseEntity<CusResponseObject>
        }
    }

    @GetMapping("/list")
    fun getAllAccount(): ResponseEntity<CusResponseObject> {
        val list = accountService.getAllAccount()
        if (list.isEmpty()) {
            return ResponseEntity<ErrorHandleObject>(ErrorHandleObject("No Records Found."), HttpStatus.BAD_REQUEST) as ResponseEntity<CusResponseObject>
        }
        return ResponseEntity<List<Account>>(list, HttpStatus.OK) as ResponseEntity<CusResponseObject>
    }

    @PostMapping("/transfer")
    @ResponseBody
    fun transferMoney(@RequestParam("from") from: Int,
                      @RequestParam("to") to: Int,
                      @RequestParam("amount") amount: BigDecimal): ResponseEntity<CusResponseObject> {
        try {
            return ResponseEntity<Account>(accountService.transferMoney(from, to, amount), HttpStatus.OK) as ResponseEntity<CusResponseObject>
        } catch (e: AccountNotFoundException) {
            return ResponseEntity<ErrorHandleObject>(ErrorHandleObject(e.message), HttpStatus.BAD_REQUEST) as ResponseEntity<CusResponseObject>
        } catch (e: InsufficientAccontException) {
            return ResponseEntity<ErrorHandleObject>(ErrorHandleObject(e.message), HttpStatus.BAD_REQUEST) as ResponseEntity<CusResponseObject>
        }
    }
}

interface CusResponseObject{}

data class ErrorHandleObject(val error: String? = ""): CusResponseObject
