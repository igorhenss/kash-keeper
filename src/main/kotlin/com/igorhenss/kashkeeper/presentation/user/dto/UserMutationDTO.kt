package com.igorhenss.kashkeeper.presentation.user.dto

import java.math.BigDecimal

data class UserMutationDTO(
    val username: String?,
    val surname: String?,
    val balance: BigDecimal?,
)