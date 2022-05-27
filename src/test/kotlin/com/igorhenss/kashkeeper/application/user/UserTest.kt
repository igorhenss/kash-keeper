package com.igorhenss.kashkeeper.application.user

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class UserTest {

    companion object {
        private const val USERNAME = "Test.User"
        private const val SURNAME = "Surname"
    }

    private val user = User(USERNAME, SURNAME)

    @Test
    fun shouldReturnUsernameOnUppercase() {
        assertThat("$user").isEqualTo(USERNAME.uppercase())
    }

    @Test
    fun shouldUpdateSurname() {
        assertThat(user.surname).isEqualTo(SURNAME)
        val newSurname = "New Surname"
        user.updateSurname(newSurname)
        assertThat(user.surname).isEqualTo(newSurname)
        assertThat(newSurname).isNotEqualTo(SURNAME)
    }

    @Test
    fun shouldReturnEquals_whenTwoUsersHaveTheSameUsername() {
        val differentUserWithSameUsername = User("Test.User", "Surname")
        assertThat(user.id).isNotEqualTo(differentUserWithSameUsername.id)
        assertThat(user).isEqualTo(differentUserWithSameUsername)
    }

}