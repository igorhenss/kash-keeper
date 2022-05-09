package com.igorhenss.kashkeeper.application.balance

import com.igorhenss.kashkeeper.application.user.User
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

internal class BalanceTest {

    private val user = mock<User> {
        on { id } doReturn UUID.fromString("62f5ba52-eb29-461b-ba28-b7f4032f14fb")
        on { toString() } doReturn "TestUser"
    }
    private val balance = Balance(user)

    @Test
    fun testToString() {
        assertThat("$balance").isEqualTo("TestUser - R$ 0.00")
    }

    @Test
    fun shouldBeEqualToUser_byUser() {
        val differentBalanceWithSameUser = Balance(user)
        assertThat(balance).isEqualTo(differentBalanceWithSameUser)
    }

}