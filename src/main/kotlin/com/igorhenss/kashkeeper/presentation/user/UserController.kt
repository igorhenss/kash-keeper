package com.igorhenss.kashkeeper.presentation.user

import com.igorhenss.kashkeeper.application.user.UserService
import com.igorhenss.kashkeeper.presentation.user.dto.UserMutationDTO
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/users")
class UserController(private val service: UserService) {

    @GetMapping
    fun getAll() = service.getAll()

    @GetMapping("/{id}")
    fun getById(id: UUID) = service.getById(id)

    @PostMapping
    fun createUser(body: UserMutationDTO) = service.create(body)

    @PutMapping("/{id}")
    fun updateUser(id: UUID, body: UserMutationDTO) = service.update(id, body)

    @DeleteMapping("/{id}")
    fun deleteUser(id: UUID) = service.delete(id)

}