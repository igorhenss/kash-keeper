package com.igorhenss.kashkeeper.infrastructure

class NotFoundException(message: String, vararg args: String): RuntimeException() {
    override val message: String
    init { this.message = message.format(*args) }
}