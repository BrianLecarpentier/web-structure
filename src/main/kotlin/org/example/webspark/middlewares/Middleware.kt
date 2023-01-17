package org.example.webspark.middlewares

import spark.Request
import spark.Response

/**
 * A middleware intercepts a Request or a Response and provides a behavior (logging, auth, etc).
 */
interface Middleware {
    /**
     * The logic of the middleware should take place in this function.
     * @param request: the original request object
     * @param response: the original response object
     */
    fun process(request: Request, response: Response)
}