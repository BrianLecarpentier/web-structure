package org.example.webspark

import org.example.webspark.core.Template
import org.example.webspark.interfaces.LightObserverInterface
import org.example.webspark.models.HomeSystem
import org.example.webspark.models.Light
import org.example.webspark.models.Thermostat
import org.example.webspark.models.Thing
import spark.Request
import spark.Response

class ThingDetailsController(private val homeSystem: HomeSystem) {

    private lateinit var thingTypeUrl: String
    private lateinit var currentThing: Thing

    fun detail(request: Request, response: Response): String {

        val id: Int = request.params(":id").toInt()
        val action: String = request.queryParamOrDefault("action", "")
        val tmpValue: String = request.queryParamOrDefault("value", "")

        when (homeSystem.thingsList[id - 1].getTypeName()) {
            "Light" -> {
                thingTypeUrl = "thing_light.html"
                currentThing = homeSystem.thingsList[id - 1] as Light
            }
            "Thermostat" -> {
                thingTypeUrl = "thing_thermostat.html"
                currentThing = homeSystem.thingsList[id - 1] as Thermostat
            }
        }

        when (action) {
            "toggle" ->  (currentThing as Light).toggleLight()
            "set_temperature" -> (currentThing as Thermostat).currentTemp = tmpValue.toFloat()
        }

        return Template.render(
            thingTypeUrl,
            hashMapOf(
                "thing" to currentThing,
                "thingId" to id
            ),
        )
    }

}