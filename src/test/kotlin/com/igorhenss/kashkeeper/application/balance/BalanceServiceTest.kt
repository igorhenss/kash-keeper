package com.igorhenss.kashkeeper.application.balance

import com.igorhenss.kashkeeper.TestHelper.Companion.USER_ID
import com.igorhenss.kashkeeper.data.balance.BalanceRepository
import com.igorhenss.kashkeeper.infrastructure.NotFoundException
import com.igorhenss.kashkeeper.presentation.balance.dto.BalanceMutationDTO
import com.nhaarman.mockitokotlin2.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.verifyNoInteractions
import java.math.BigDecimal
import java.util.*

internal class BalanceServiceTest {

    companion object {
        private val BALANCE_VALUE = BigDecimal.TEN
    }

    private val adapter = mock<BalanceAdapter>()
    private val repository = mock<BalanceRepository>()
    private val service = BalanceService(adapter, repository)

    private val balance = mock<Balance> { on { id } doReturn USER_ID }
    private val balanceHistory = mock<BalanceHistory>()
    private val balanceMutationDto = mock<BalanceMutationDTO>()

    @Test
    fun shouldUpdateBalanceAndHistory_whenBalanceIsFound_onUpdate() {
        whenever(repository.findById(USER_ID)).thenReturn(Optional.of(balance))
        whenever(adapter.historyFromDto(balance, balanceMutationDto)).thenReturn(balanceHistory)
        whenever(repository.save(balance)).thenReturn(balance)
        whenever(balance.currentValue).thenReturn(BALANCE_VALUE)

        val result = service.update(USER_ID, balanceMutationDto)

        assertThat(result).isEqualTo(BALANCE_VALUE)
        verify(repository).findById(USER_ID)
        verify(repository).save(balance)
        verifyNoMoreInteractions(repository)
        verify(adapter).historyFromDto(balance, balanceMutationDto)
        verifyNoMoreInteractions(adapter)
    }

    @Test
    fun shouldThrowException_whenBalanceIsNotFound_onUpdate() {
        whenever(repository.findById(USER_ID)).thenReturn(Optional.empty())

        val exception = assertThrows<NotFoundException> { service.update(USER_ID, balanceMutationDto) }

        assertThat(exception.message).isEqualTo("User [$USER_ID] not found.")
        verify(repository).findById(USER_ID)
        verifyNoMoreInteractions(repository)
        verifyNoInteractions(adapter)
    }

}