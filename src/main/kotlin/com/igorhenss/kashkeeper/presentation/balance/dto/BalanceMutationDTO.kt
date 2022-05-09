package com.igorhenss.kashkeeper.presentation.balance.dto

import java.math.BigDecimal

data class BalanceMutationDTO(
    val title: String,
    val addedValue: BigDecimal,
    val description: String,
)