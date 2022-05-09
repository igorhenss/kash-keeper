package com.igorhenss.kashkeeper.application.balance

import com.igorhenss.kashkeeper.data.balance.BalanceRepository
import com.igorhenss.kashkeeper.infrastructure.NotFoundException
import com.igorhenss.kashkeeper.presentation.balance.dto.BalanceMutationDTO
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*

@Service
class BalanceService(private val adapter: BalanceAdapter, private val repository: BalanceRepository) {

    companion object {
        private const val USER_NOT_FOUND = "User [%s] not found."
    }

    fun update(userId: UUID, dto: BalanceMutationDTO): BigDecimal {
        val balance = fetchById(userId)
        val history = adapter.fromDto(balance, dto)
        balance.updateValue(history)
        return persist(balance).currentValue
    }

    private fun fetchById(id: UUID) = repository.findByIdOrNull(id) ?: throw NotFoundException(USER_NOT_FOUND, "$id")

    private fun persist(balance: Balance) = repository.save(balance)

}