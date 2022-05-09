package com.igorhenss.kashkeeper.application.user

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class UserTest {

    private val user = User("TestUser", "Thusser", BigDecimal.TEN)

    @Test
    fun testToString() {
        assertThat("$user").isEqualTo("TestUser")
    }

    @Test
    fun testEqualsByUsername() {
        val differentUserWithSameUsername = User("TestUser", "Surname", BigDecimal.ONE)
        assertThat(user).isEqualTo(differentUserWithSameUsername)
    }

}