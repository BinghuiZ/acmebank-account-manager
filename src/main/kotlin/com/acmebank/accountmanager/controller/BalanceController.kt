package com.acmebank.accountmanager.controller

import com.acmebank.accountmanager.model.Balance
import com.acmebank.accountmanager.service.BalanceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/")
class BalanceController(@Autowired val balanceService: BalanceService) {

    @GetMapping("/{id}")
    fun getBalanceById(@PathVariable("id") id: Int): ResponseEntity<Balance> {
        val balance = balanceService.getBalance(id)
        return ResponseEntity<Balance>(balance, HttpStatus.OK)
    }

    @GetMapping("/list")
    fun getAllBalance(): ResponseEntity<List<Balance>> {
        return ResponseEntity<List<Balance>>(balanceService.getAllBalance(), HttpStatus.OK)
    }

    @PostMapping("/transfer")
    @ResponseBody
    fun transferMoney(@RequestParam("from") from: Int,
                      @RequestParam("to") to: Int,
                      @RequestParam("amount") amount: Double): ResponseEntity<Balance> {
        val user = balanceService.transferMoney(from, to, amount)
        if (user != null) {
            return ResponseEntity<Balance>(user, HttpStatus.OK)
        } else {
            return ResponseEntity<Balance>(null, HttpStatus.BAD_REQUEST)
        }
    }
}
