package com.igorhenss.kashkeeper.application.balance

import com.igorhenss.kashkeeper.application.user.User
import com.igorhenss.kashkeeper.application.user.UserTest
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

internal class BalanceTest {

    companion object {
        private const val TITLE = "Título"
        private const val DESCRIPTION = "Descrição"
        private val ADDED_VALUE = BigDecimal.TEN
    }

    private val user = mock<User> {
        on { id } doReturn UUID.fromString("62f5ba52-eb29-461b-ba28-b7f4032f14fb")
        on { toString() } doReturn "TestUser"
    }
    private val balance = Balance(user)

    @Test
    fun shouldReturnUsersUsernameAndBalance_whenToString() {
        assertThat("$balance").isEqualTo("TestUser - R$ 0.00")
    }

    @Test
    fun shouldUpdateBalance() {
        val balanceHistory = BalanceHistory(balance, TITLE, DESCRIPTION, ADDED_VALUE)
        balance.updateValue(balanceHistory)

        assertThat(balance.currentValue).isEqualTo(BigDecimal.TEN)
        assertThat(balance.history.size).isEqualTo(1)

        val balanceHistoryAfterUpdate = balance.history[0]

        assertThat(balanceHistoryAfterUpdate).isEqualTo(balanceHistory)
        assertThat(balanceHistoryAfterUpdate.title).isEqualTo(TITLE)
        assertThat(balanceHistoryAfterUpdate.description).isEqualTo(DESCRIPTION)
        assertThat(balanceHistoryAfterUpdate.addedValue).isEqualTo(balance.currentValue)
        assertThat(balanceHistoryAfterUpdate.createdAt.toLocalDate()).isEqualTo(LocalDate.now())
    }

    @Test
    fun shouldReturnEquals_whenTwoBalancesHaveTheSameUser() {
        val differentBalanceWithSameUser = Balance(user)
        assertThat(balance).isEqualTo(differentBalanceWithSameUser)
    }

}