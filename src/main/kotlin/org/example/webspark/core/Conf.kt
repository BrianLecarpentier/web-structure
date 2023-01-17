package org.example.webspark.core

import java.io.File

/**
 * This class centralizes all the configuration of the app (port, database, resources, etc)
 */
object Conf {
    // General
    const val DEBUG_MODE = true
    const val HTTP_PORT = 8080

    // Resources
    val RESOURCES_DIR = File("src/main/resources")
    @JvmField
    val TEMPLATE_DIR = File(RESOURCES_DIR, "templates")
    val STATIC_DIR = File(RESOURCES_DIR, "static")
}