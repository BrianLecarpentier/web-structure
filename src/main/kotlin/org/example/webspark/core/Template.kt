package org.example.webspark.core

import org.apache.velocity.app.VelocityEngine
import spark.ModelAndView
import spark.template.velocity.VelocityTemplateEngine

/**
 * Centralizes template initialization and rendering.
 * The template engine is Velocity: https://velocity.apache.org/.
 */
object Template {
    private val engine = VelocityEngine()

    /**
     * Initializes the Template engine. When [Conf.DEBUG_MODE] is true,
     * templates are loaded outside the classpath. This makes the development easier,
     * as you just have to refresh the web page when you change the template
     * (instead of reloading the whole server).
     */
    fun initialize() {
        // Root directory is supposed to be project root
        engine.setProperty("file.resource.loader.path", Conf.TEMPLATE_DIR.path)
        engine.setProperty("velocimacro.library.autoreload", "true")
        engine.setProperty("file.resource.loader.cache", "false")
        engine.setProperty(
            "velocimacro.permissions.allow.inline.to.replace.global",
            "true"
        )
    }

    /**
     * Renders the template with the given model data.
     *
     * @param template: file path of the template. The path is relative to src/main/resources/templates.
     * @param model:    Map containing all the values that should be accessible inside the template.
     * @return the rendered template in which all model data has been compiled.
     */
    fun render(template: String?, model: Map<String, Any?>): String {
        return VelocityTemplateEngine(engine).render(
            ModelAndView(
                model,
                template
            )
        )
    }
}