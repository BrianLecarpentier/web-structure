package org.example.webspark

import org.example.webspark.core.Conf
import org.example.webspark.core.Template
import org.example.webspark.middlewares.LoggerMiddleware
import org.example.webspark.models.*
import spark.Request
import spark.Response
import spark.Route
import spark.Spark.*

fun main() {
    initialize()

    val homeSystem = HomeSystem()

    homeSystem.createLight(Thing.State.UNREACHABLE, false, "Light 1")
    homeSystem.createLight(Thing.State.REACHABLE, false, "Light 2")
    homeSystem.createLight(Thing.State.REACHABLE, false, "Light 3")
    homeSystem.createThermostat(Thing.State.REACHABLE, "Sensor 1")
    homeSystem.createThermostat(Thing.State.UNREACHABLE, "Sensor 2")

    val allLights: List<Light> = homeSystem.getLights()
    val allThermostats: List<Thermostat> = homeSystem.thermostats

    val home = HomeController(homeSystem)
    val thingDetail = ThingDetailsController(homeSystem)

    get(
        "/",
        Route { req, res -> home.list(req, res) })

    get(
        "/things/:id",
        Route { req, res ->
            thingDetail.detail(req, res)
        })
}

fun initialize() {
    Template.initialize()

    // Display exceptions in logs
    exception(
        Exception::class.java
    ) { e: Exception, _: Request?, _: Response? -> e.printStackTrace() }

    // Serve static files (img/css/js)
    staticFiles.externalLocation(Conf.STATIC_DIR.path)

    // Configure server port
    port(Conf.HTTP_PORT)
    val log = LoggerMiddleware()
    before(log::process)
}