package com.igorhenss.kashkeeper.application.user

import com.igorhenss.kashkeeper.infrastructure.KashKeeperUtils.Companion.nonEmptyOrThrowException
import com.igorhenss.kashkeeper.presentation.user.dto.UserDTO
import com.igorhenss.kashkeeper.presentation.user.dto.UserMutationDTO
import org.springframework.stereotype.Service

@Service
class UserAdapter {

    companion object {
        private const val USERNAME_CANT_BE_BLANK = "Username can't be blank"
        private const val SURNAME_CANT_BE_BLANK = "Surname can't be blank."
    }

    fun fromDto(dto: UserMutationDTO) = User(
        username = dto.username.nonEmptyOrThrowException(USERNAME_CANT_BE_BLANK),
        surname = dto.surname.nonEmptyOrThrowException(SURNAME_CANT_BE_BLANK),
    )

    fun fromEntity(entity: User) = UserDTO(
        id = entity.id,
        username = entity.username,
        surname = entity.surname,
        balance = entity.balance.currentValue,
    )

}