package com.igorhenss.kashkeeper.infrastructure

class KashKeeperUtils {
    companion object {
        fun <T> T?.nonEmptyOrThrowException(message: String): T {
            if (this == null || (this is String && this.isBlank())) {
                throw IllegalArgumentException(message)
            }
            return this
        }
    }
}