package org.example.webspark

import org.example.webspark.core.Template
import org.example.webspark.models.HomeSystem
import spark.Request
import spark.Response

class HomeController {

    val homeSystem = HomeSystem.getInstance()

    fun list(request: Request, response: Response): String {
        return Template.render(
            "home.html",
            hashMapOf(
                "things" to homeSystem.thingsList
            ),
        )
    }

}