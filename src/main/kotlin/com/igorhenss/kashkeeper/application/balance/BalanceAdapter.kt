package com.igorhenss.kashkeeper.application.balance

import com.igorhenss.kashkeeper.presentation.balance.dto.BalanceMutationDTO
import org.springframework.stereotype.Service

@Service
class BalanceAdapter {

    fun fromDto(balance: Balance, dto: BalanceMutationDTO) = BalanceHistory(
        balance = balance,
        title = dto.title,
        description = dto.description,
        addedValue = dto.addedValue
    )

}