package com.acmebank.accountmanager.controller

import com.acmebank.accountmanager.model.Balance
import com.acmebank.accountmanager.model.exception.BalanceNotFoundException
import com.acmebank.accountmanager.model.exception.InsufficientBalanceException
import com.acmebank.accountmanager.service.BalanceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/")
class BalanceController(@Autowired val balanceService: BalanceService) {

    @GetMapping("/{id}")
    fun getBalanceById(@PathVariable("id") id: Int): ResponseEntity<CusResponseObject> {
        try {
            val balance = balanceService.getBalance(id)
            return ResponseEntity<Balance>(balance, HttpStatus.OK) as ResponseEntity<CusResponseObject>
        } catch (e: BalanceNotFoundException) {
            return ResponseEntity<ErrorHandleObject>(ErrorHandleObject(e.message), HttpStatus.BAD_REQUEST) as ResponseEntity<CusResponseObject>
        }
    }

    @GetMapping("/list")
    fun getAllBalance(): ResponseEntity<CusResponseObject> {
        val list = balanceService.getAllBalance()
        if (list.isEmpty()) {
            return ResponseEntity<ErrorHandleObject>(ErrorHandleObject("No Records Found."), HttpStatus.BAD_REQUEST) as ResponseEntity<CusResponseObject>
        }
        return ResponseEntity<List<Balance>>(list, HttpStatus.OK) as ResponseEntity<CusResponseObject>
    }

    @PostMapping("/transfer")
    @ResponseBody
    fun transferMoney(@RequestParam("from") from: Int,
                      @RequestParam("to") to: Int,
                      @RequestParam("amount") amount: Double): ResponseEntity<CusResponseObject> {
        try {
            return ResponseEntity<Balance>(balanceService.transferMoney(from, to, amount), HttpStatus.OK) as ResponseEntity<CusResponseObject>
        } catch (e: BalanceNotFoundException) {
            return ResponseEntity<ErrorHandleObject>(ErrorHandleObject(e.message), HttpStatus.BAD_REQUEST) as ResponseEntity<CusResponseObject>
        } catch (e: InsufficientBalanceException) {
            return ResponseEntity<ErrorHandleObject>(ErrorHandleObject(e.message), HttpStatus.BAD_REQUEST) as ResponseEntity<CusResponseObject>
        }
    }
}

interface CusResponseObject{}

data class ErrorHandleObject(val error: String? = ""): CusResponseObject
