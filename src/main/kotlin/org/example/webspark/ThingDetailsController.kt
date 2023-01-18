package org.example.webspark

import org.example.webspark.core.Template
import org.example.webspark.interfaces.LightObserverInterface
import org.example.webspark.models.HomeSystem
import org.example.webspark.models.Light
import spark.Request
import spark.Response

class ThingDetailsController() {

    fun detail(request: Request, response: Response): String {

        val homeSystem = HomeSystem.getInstance()

        val id = request.params(":id").toInt()
        val toggle = request.queryParams("action")
        val currentThing = homeSystem.thingsList[id - 1] as Light

        val thingType = homeSystem.thingsList[id - 1].getTypeName()

        if (toggle == "toggle") {
            currentThing.toggleLight()
        }

        return Template.render(
            "thing_light.html",
            hashMapOf(
                "light" to currentThing,
                "lightId" to id
            ),
        )
    }

}