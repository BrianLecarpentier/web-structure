package org.example.webspark.utils

import java.io.UnsupportedEncodingException
import java.net.URLDecoder

/**
 * Set of helpers function to manipulate URLs.
 */
object URLUtils {
    /**
     * Extract query params for an URL. This function does NOT support multiple
     * values for the same key.
     *
     * @param query the input query (e.g. https://www.google.com?q=hello&tab=2)
     * @return a Map of query keys to query values.
     */
    fun decodeQuery(query: String): Map<String, String> {
        return try {
            val params: MutableMap<String, String> = LinkedHashMap()
            for (param in query.split("&".toRegex())
                .dropLastWhile { it.isEmpty() }.toTypedArray()) {
                val keyValue =
                    param.split("=".toRegex(), limit = 2).toTypedArray()
                val key = URLDecoder.decode(keyValue[0], "UTF-8")
                val value = if (keyValue.size > 1) URLDecoder.decode(
                    keyValue[1],
                    "UTF-8"
                ) else ""
                if (key.isNotEmpty()) {
                    params[key] = value
                }
            }
            params
        } catch (e: UnsupportedEncodingException) {
            throw IllegalStateException(e) // Cannot happen with UTF-8 encoding.
        }
    }
}