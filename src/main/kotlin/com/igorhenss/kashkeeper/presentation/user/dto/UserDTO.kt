package com.igorhenss.kashkeeper.presentation.user.dto

import java.math.BigDecimal
import java.util.*

data class UserDTO(
    val id: UUID,
    val username: String,
    val surname: String,
    val balance: BigDecimal,
)