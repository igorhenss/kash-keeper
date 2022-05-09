package com.igorhenss.kashkeeper.presentation.balance

import com.igorhenss.kashkeeper.application.balance.BalanceService
import com.igorhenss.kashkeeper.presentation.balance.dto.BalanceMutationDTO
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/users")
class BalanceController(private val service: BalanceService) {

    @PutMapping("/{userId}/balance")
    fun update(@PathVariable userId: UUID, @RequestBody body: BalanceMutationDTO) = service.update(userId, body)

}