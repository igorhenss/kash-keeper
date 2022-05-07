package com.igorhenss.kashkeeper.infrastructure

class NotFoundException(message: String, args: Array<String>): RuntimeException(message) {

    constructor(message: String, arg: String): this(message, arrayOf(arg))

    init {
        message.format(args)
    }

}