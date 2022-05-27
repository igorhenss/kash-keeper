package com.igorhenss.kashkeeper.application.user

import com.igorhenss.kashkeeper.data.user.UserRepository
import com.igorhenss.kashkeeper.infrastructure.AlreadyTakenException
import com.igorhenss.kashkeeper.infrastructure.KashKeeperUtils.Companion.nonEmptyOrThrowException
import com.igorhenss.kashkeeper.infrastructure.NotFoundException
import com.igorhenss.kashkeeper.presentation.user.dto.UserMutationDTO
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val adapter: UserAdapter, private val repository: UserRepository) {

    companion object {
        private const val USER_NOT_FOUND = "User [%s] not found."
        private const val USERNAME_ALREADY_TAKEN = "Username [%s] is already taken."
        private const val SURNAME_CANT_BE_BLANK = "Surname can't be blank."
    }

    fun getAll() = repository.findAll()
        .map { adapter.fromEntity(it) }

    fun getById(id: UUID) = adapter.fromEntity(fetchById(id))

    fun update(id: UUID, dto: UserMutationDTO): UUID {
        val user = fetchById(id)
        val surname = dto.surname.nonEmptyOrThrowException(SURNAME_CANT_BE_BLANK)
        user.updateSurname(surname)
        return persist(user).id
    }

    private fun fetchById(id: UUID) = repository.findById(id)
        .orElseThrow { NotFoundException(USER_NOT_FOUND, "$id") }

    fun create(dto: UserMutationDTO): UUID {
        validateUserTaken(dto.username)
        val user = adapter.fromDto(dto)
        return persist(user).id
    }

    private fun validateUserTaken(username: String?) {
        if (username != null && repository.existsByUsername(username)) {
            throw AlreadyTakenException(USERNAME_ALREADY_TAKEN, username)
        }
    }

    fun delete(id: UUID) = repository.deleteById(id)

    private fun persist(user: User) = repository.save(user)

}