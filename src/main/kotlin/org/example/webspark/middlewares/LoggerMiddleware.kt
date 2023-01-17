package org.example.webspark.middlewares

import spark.Request
import spark.Response

/**
 * Logs the HTTP request in the form of "GET /login user=bob".
 */
class LoggerMiddleware : Middleware {
    override fun process(request: Request, response: Response) {
        val log = request.requestMethod() +
                " " + request.url() +
                " " + request.body()
        println(log)
    }
}