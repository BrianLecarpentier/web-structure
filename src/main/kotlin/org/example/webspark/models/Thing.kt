package org.example.webspark.models

abstract class Thing {
    enum class State {
        REACHABLE,
        UNREACHABLE,
    }

    var name: String = ""
    var state: State = State.REACHABLE

    abstract fun getTypeName(): String
    abstract fun getDescription(): String
}