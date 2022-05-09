package com.igorhenss.kashkeeper.application.user

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserTest {

    private val user = User("TestUser", "Thusser")

    @Test
    fun testToString() {
        assertThat("$user").isEqualTo("TESTUSER")
    }

    @Test
    fun testEqualsByUsername() {
        val differentUserWithSameUsername = User("TestUser", "Surname")
        assertThat(user.id).isNotEqualTo(differentUserWithSameUsername.id)
        assertThat(user).isEqualTo(differentUserWithSameUsername)
    }

}