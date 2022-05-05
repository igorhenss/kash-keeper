package com.igorhenss.kashkeeper.application.user

import com.igorhenss.kashkeeper.application.balance.Balance
import com.nhaarman.mockitokotlin2.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserTest {

    private val balance = mock<Balance>()
    private val user = User("TestUser", "Thusser", balance)

    @Test
    fun testToString() {
        assertThat("$user").isEqualTo("TestUser")
    }

    @Test
    fun testEqualsByUsername() {
        val differentBalance = mock<Balance>()
        val differentUserWithSameUsername = User("TestUser", "Surname", differentBalance)

        assertThat(user).isEqualTo(differentUserWithSameUsername)
    }

}