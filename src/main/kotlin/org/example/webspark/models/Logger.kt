package org.example.webspark.models

import org.example.webspark.interfaces.LightObserverInterface
import org.example.webspark.interfaces.LoggerInterface

class Logger: LoggerInterface {

    private var logs: MutableList<String> = mutableListOf()

    override fun addLog(message: String) {
        logs.add(message)
        println(message)
    }
}