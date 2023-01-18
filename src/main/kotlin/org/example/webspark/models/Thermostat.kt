package org.example.webspark.models

import kotlin.math.min

class Thermostat: Thing() {
    var currentTemp: Float = 20.5F
        set(value) {
            if (value in minTemp..maxTemp && state == State.REACHABLE) {
                field = value
            }
        }

    private val minTemp: Float = 10F
    private val maxTemp: Float = 100F
    
    override fun getTypeName(): String = "Thermostat"
    override fun getDescription(): String = "Thermostat name-$name, reachable=$state, max = $maxTemp, min = $minTemp, current = $currentTemp"
}