package org.example.webspark.models

import org.example.webspark.interfaces.LightObserverInterface

class HomeSystem private constructor() : LightObserverInterface {
    companion object {
        private lateinit var instance: HomeSystem
        fun getInstance(): HomeSystem {
            if (!this::instance.isInitialized) {
                instance = HomeSystem()
            }

            return instance
        }
    }

    var thingsList: MutableList<Thing> = mutableListOf()
    var logs: MutableList<String> = mutableListOf()
    fun getLights(): List<Light> = thingsList.filterIsInstance<Light>()

    val sensors: List<Sensor>
        get() = thingsList.filterIsInstance<Sensor>()

    fun createLight(state: Thing.State, isLighOn: Boolean, name: String) {
        val newLight = Light()
        newLight.name = name
        newLight.isLightOn = isLighOn
        newLight.state = state
        newLight.lightChange = this

        this.thingsList.add(newLight)
    }

    fun createSensor(state: Thing.State, isEnabled: Boolean, name: String) {
        val newSensor = Sensor()
        newSensor.name = name
        newSensor.isEnabled = isEnabled
        newSensor.state = state

        this.thingsList.add(newSensor)
    }

    override fun onLighChange(light: Light) {
        logs.add("Light ${light.name} is ${light.isLightOn}")
        println(logs.last())
    }
}