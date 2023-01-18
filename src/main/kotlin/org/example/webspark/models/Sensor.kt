package org.example.webspark.models

class Sensor: Thing() {

    var isEnabled: Boolean = false
    
    override fun getTypeName(): String = "Sensor"
    override fun getDescription(): String = "Sensor name-$name, reachable=$state, is $isEnabled"
}