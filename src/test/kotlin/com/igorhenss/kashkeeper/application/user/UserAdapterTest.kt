package com.igorhenss.kashkeeper.application.user

import com.igorhenss.kashkeeper.presentation.user.dto.UserMutationDTO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import java.math.BigDecimal.ZERO

internal class UserAdapterTest {

    companion object {
        private const val USERNAME = "Test.Username"
        private const val SURNAME = "Surname"
    }

    private val adapter = UserAdapter()

    @Test
    fun shouldReturnDto_whenAllFieldsAreFilled_onConversionFromDtoToEntity() {
        val userMutationDto = UserMutationDTO(USERNAME, SURNAME)
        val result = adapter.fromDto(userMutationDto)

        assertThat(result.username).isEqualTo(userMutationDto.username!!.uppercase())
        assertThat(result.surname).isEqualTo(userMutationDto.surname)
        assertThat(result.balance.currentValue).isEqualTo(ZERO)
        assertThat(result.balance.history).isEmpty()
    }

    @Test
    fun shouldReturnEntity_fromDto_onConversionFromEntityToDto() {
        val user = User(USERNAME, SURNAME)
        val result = adapter.fromEntity(user)

        assertThat(result.id).isEqualTo(user.id)
        assertThat(result.username).isEqualTo(user.username)
        assertThat(result.surname).isEqualTo(user.surname)
        assertThat(result.balance).isEqualTo(user.balance.currentValue)
    }

}