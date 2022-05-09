package com.igorhenss.kashkeeper.application.balance

import com.igorhenss.kashkeeper.application.user.User
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class BalanceTest {

    private val user = mock<User> { on { toString() } doReturn "TestUser" }
    private val balance = Balance(user, BigDecimal.TEN)

    @Test
    fun testToString() {
        assertThat("$balance").isEqualTo("TestUser - R$ 10.00")
    }

    @Test
    fun testEqualsByUser() {
        val differentBalanceWithSameUser = Balance(user, BigDecimal.ONE)

        assertThat(balance).isEqualTo(differentBalanceWithSameUser)
    }

}