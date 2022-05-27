package com.igorhenss.kashkeeper.application.user

import com.igorhenss.kashkeeper.TestHelper.Companion.USER_ID
import com.igorhenss.kashkeeper.data.user.UserRepository
import com.igorhenss.kashkeeper.infrastructure.AlreadyTakenException
import com.igorhenss.kashkeeper.infrastructure.NotFoundException
import com.igorhenss.kashkeeper.presentation.user.dto.UserDTO
import com.igorhenss.kashkeeper.presentation.user.dto.UserMutationDTO
import com.nhaarman.mockitokotlin2.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.verifyNoInteractions
import org.springframework.dao.EmptyResultDataAccessException
import java.util.*

internal class UserServiceTest {

    companion object {
        private const val USERNAME = "Username"
        private const val SURNAME = "Surname"
    }

    private val adapter = mock<UserAdapter>()
    private val repository = mock<UserRepository>()
    private val service = UserService(adapter, repository)

    private val user = mock<User> { on { id } doReturn USER_ID }
    private val userDto = mock<UserDTO> { on { id } doReturn USER_ID }
    private val userMutationDto = mock<UserMutationDTO> { on { surname } doReturn SURNAME }

    @Test
    fun shouldReturnAllUsers_whenUsersAreFound_onGetAll() {
        whenever(repository.findAll()).thenReturn(listOf(user))
        whenever(adapter.fromEntity(user)).thenReturn(userDto)

        val result = service.getAll()

        assertThat(result.size).isEqualTo(1)
        assertThat(result[0]).isEqualTo(userDto)
        verify(repository).findAll()
        verifyNoMoreInteractions(repository)
        verify(adapter).fromEntity(any())
        verifyNoMoreInteractions(adapter)
    }

    @Test
    fun shouldReturnAnEmptyList_whenUsersAreNotFound_onGetAll() {
        whenever(repository.findAll()).thenReturn(listOf())

        val result = service.getAll()

        assertThat(result).isEmpty()
        verify(repository).findAll()
        verifyNoMoreInteractions(repository)
        verifyNoInteractions(adapter)
    }

    @Test
    fun shouldReturnUser_whenUserIsFound_onGetById() {
        whenever(repository.findById(USER_ID)).thenReturn(Optional.of(user))
        whenever(adapter.fromEntity(user)).thenReturn(userDto)

        val result = service.getById(USER_ID)

        assertThat(result).isEqualTo(userDto)
        verify(repository).findById(USER_ID)
        verifyNoMoreInteractions(repository)
        verify(adapter).fromEntity(any())
        verifyNoMoreInteractions(adapter)
    }

    @Test
    fun shouldThrowException_whenUserIsNotFound_onGetById() {
        whenever(repository.findById(USER_ID)).thenReturn(Optional.empty())

        val exception = assertThrows<NotFoundException> { service.getById(USER_ID) }

        assertThat(exception.message).isEqualTo("User [$USER_ID] not found.")
        verify(repository).findById(any())
        verifyNoMoreInteractions(repository)
        verifyNoInteractions(adapter)
    }

    @Test
    fun shouldUpdateSurname_whenUserIsFound_onUpdate() {
        whenever(repository.findById(USER_ID)).thenReturn(Optional.of(user))
        whenever(repository.save(user)).thenReturn(user)

        val result = service.update(USER_ID, userMutationDto)

        assertThat(result).isEqualTo(USER_ID)
        verify(repository).findById(USER_ID)
        verify(repository).save(user)
        verifyNoMoreInteractions(repository)
        verifyNoInteractions(adapter)
    }

    @Test
    fun shouldThrowException_whenUserIsNotFound_onUpdate() {
        whenever(repository.findById(USER_ID)).thenReturn(Optional.empty())

        val exception = assertThrows<NotFoundException> { service.update(USER_ID, userMutationDto) }

        assertThat(exception.message).isEqualTo("User [$USER_ID] not found.")
        verify(repository).findById(any())
        verifyNoMoreInteractions(repository)
        verifyNoInteractions(adapter)
    }

    @Test
    fun shouldCreateUser_whenUsernameIsNotTaken_onCreate() {
        whenever(userMutationDto.username).thenReturn(USERNAME)
        whenever(repository.existsByUsername(USERNAME)).thenReturn(false)
        whenever(adapter.fromDto(userMutationDto)).thenReturn(user)
        whenever(repository.save(user)).thenReturn(user)

        val result = service.create(userMutationDto)

        assertThat(result).isEqualTo(USER_ID)
        verify(repository).existsByUsername(USERNAME)
        verify(repository).save(user)
        verifyNoMoreInteractions(repository)
        verify(adapter).fromDto(userMutationDto)
        verifyNoMoreInteractions(adapter)
    }

    @Test
    fun shouldThrowException_whenUsernameIsTaken_onCreate() {
        whenever(userMutationDto.username).thenReturn(USERNAME)
        whenever(repository.existsByUsername(USERNAME)).thenReturn(true)

        val exception = assertThrows<AlreadyTakenException> { service.create(userMutationDto) }

        assertThat(exception.message).isEqualTo("Username [$USERNAME] is already taken.")
        verify(repository).existsByUsername(USERNAME)
        verifyNoMoreInteractions(repository)
        verifyNoInteractions(adapter)
    }

    @Test
    fun shouldDeleteUser_whenUserIsFound_onDelete() {
        service.delete(USER_ID)

        verify(repository).deleteById(USER_ID)
        verifyNoMoreInteractions(repository)
        verifyNoInteractions(adapter)
    }

    @Test
    fun shouldThrowException_whenUserIsNotFound_onDelete() {
        whenever(repository.deleteById(USER_ID)).thenThrow(EmptyResultDataAccessException(0))

        assertThrows<EmptyResultDataAccessException> { service.delete(USER_ID) }
        verify(repository).deleteById(USER_ID)
        verifyNoMoreInteractions(repository)
        verifyNoInteractions(adapter)
    }

}