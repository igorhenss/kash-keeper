package com.igorhenss.kashkeeper

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KashKeeperApplication

fun main(args: Array<String>) {
	runApplication<KashKeeperApplication>(*args)
}
