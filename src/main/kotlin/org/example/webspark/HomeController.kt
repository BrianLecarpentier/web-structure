package org.example.webspark

import org.example.webspark.core.Template
import org.example.webspark.models.HomeSystem
import spark.Request
import spark.Response

class HomeController (private val homeSystem: HomeSystem) {

    fun list(request: Request, response: Response): String {
        val toggleLights = request.queryParams("action")

        when(toggleLights) {
            "toggle_on" -> homeSystem.toggleLights(true)
            "toggle_off" -> homeSystem.toggleLights(false)
        }

        return Template.render(
            "home.html",
            hashMapOf(
                "things" to homeSystem.thingsList
            ),
        )
    }

}