package org.example.webspark.models

import org.example.webspark.interfaces.LightObserverInterface
import org.example.webspark.interfaces.LoggerInterface

class HomeSystem (private val logger: LoggerInterface) : LightObserverInterface {
    /** Singleton */
    /*companion object {
        private lateinit var instance: HomeSystem
        fun getInstance(): HomeSystem {
            if (!this::instance.isInitialized) {
                instance = HomeSystem()
            }

            return instance
        }
    }*/

    var thingsList: MutableList<Thing> = mutableListOf()
    var systemStatus: Boolean = true

    fun getLights(): List<Light> = thingsList.filterIsInstance<Light>()

    val thermostats: List<Thermostat>
        get() = thingsList.filterIsInstance<Thermostat>()

    fun createLight(state: Thing.State, isLighOn: Boolean, name: String) {
        val newLight = Light()
        newLight.name = name
        newLight.isLightOn = isLighOn
        newLight.state = state
        newLight.lightChange = this

        thingsList.add(newLight)
    }

    fun createThermostat(state: Thing.State, name: String) {
        val newThermostat = Thermostat()
        newThermostat.name = name
        newThermostat.state = state

        thingsList.add(newThermostat)
    }

    fun toggleLights(lightsStatus: Boolean) {
        val lights: List<Light> = getLights()

        if (systemStatus) {
            lights.map { light: Light -> light.isLightOn = lightsStatus }
        }
    }

    override fun onLighChange(light: Light) {
        logger.addLog("Light ${light.name} is ${light.isLightOn}")
    }
}