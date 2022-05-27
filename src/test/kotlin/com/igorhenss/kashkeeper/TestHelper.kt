package com.igorhenss.kashkeeper

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.util.*

class TestHelper {

    companion object {
        val USER_ID = UUID.fromString("4dc9282a-6542-44c4-90dd-a5ebb9aeaf04")
        fun asJson(value: Any) = jacksonObjectMapper().writeValueAsString(value)
    }

}