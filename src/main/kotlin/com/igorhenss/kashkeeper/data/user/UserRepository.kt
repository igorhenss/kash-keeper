package com.igorhenss.kashkeeper.data.user

import com.igorhenss.kashkeeper.application.user.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository: JpaRepository<User, UUID> {
    fun existsByUsername(username: String): Boolean
}