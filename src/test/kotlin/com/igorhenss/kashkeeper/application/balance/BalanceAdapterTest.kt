package com.igorhenss.kashkeeper.application.balance

import com.igorhenss.kashkeeper.application.user.User
import com.igorhenss.kashkeeper.presentation.balance.dto.BalanceMutationDTO
import com.nhaarman.mockitokotlin2.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDate

internal class BalanceAdapterTest {

    companion object {
        private const val TITLE = "HistoryTitle"
        private const val DESCRIPTION = "HistoryDescription"
        private val ADDED_VALUE = BigDecimal.TEN
    }

    private val adapter = BalanceAdapter()

    private val user = mock<User>()
    private val balance = Balance(user)

    @Test
    fun shouldReturnBalanceHistory_fromBalanceAndDto_onConversionToBalanceHistory() {
        val balanceMutationDto = dtoOfAddedValue(ADDED_VALUE)
        val result = adapter.historyFromDto(balance, balanceMutationDto)

        assertThat(result.title).isEqualTo(balanceMutationDto.title)
        assertThat(result.description).isEqualTo(balanceMutationDto.description)
        assertThat(result.createdAt.toLocalDate()).isEqualTo(LocalDate.now())
        assertThat(result.addedValue).isEqualTo(balanceMutationDto.addedValue).isPositive
    }

    @Test
    fun shouldReturnNegativeBalanceHistory_fromBalanceAndDto_onConversionToBalanceHistory() {
        val balanceMutationDto = dtoOfAddedValue(-ADDED_VALUE)
        val result = adapter.historyFromDto(balance, balanceMutationDto)

        assertThat(result.addedValue).isNegative
    }

    @Test
    fun shouldReturnZeroBalanceHistory_fromBalanceAndDto_onConversionToBalanceHistory() {
        val balanceMutationDto = dtoOfAddedValue(BigDecimal.ZERO)
        val result = adapter.historyFromDto(balance, balanceMutationDto)

        assertThat(result.addedValue).isZero
    }

    private fun dtoOfAddedValue(addedValue: BigDecimal) = BalanceMutationDTO(TITLE, addedValue, DESCRIPTION)

}