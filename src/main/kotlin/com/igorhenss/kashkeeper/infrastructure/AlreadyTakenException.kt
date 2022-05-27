package com.igorhenss.kashkeeper.infrastructure

class AlreadyTakenException(message: String, vararg args: String): RuntimeException() {
    override val message: String
    init { this.message = message.format(*args) }
}