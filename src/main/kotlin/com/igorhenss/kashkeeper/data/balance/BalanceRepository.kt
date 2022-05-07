package com.igorhenss.kashkeeper.data.balance

import com.igorhenss.kashkeeper.application.balance.Balance
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface BalanceRepository: JpaRepository<Balance, UUID>