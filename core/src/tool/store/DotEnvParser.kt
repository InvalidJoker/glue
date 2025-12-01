package dev.fruxz.ascend.tool.store

import java.io.File

/**
 * A simple parser for dotenv (.env) files.
 *
 * This class can load key-value pairs from a specified dotenv file
 * and provides methods to access the parsed values.
 *
 * @author InvalidJoker
 */
open class DotEnvParser {
    private val values = mutableMapOf<String, String>()

    /**
     * Loads and parses the dotenv file at the specified path.
     *
     * @param path The path to the dotenv file.
     */
    fun load(path: String) {
        val file = File(path)
        if (!file.exists() || !file.isFile) return
        file.useLines { lines ->
            lines.forEach { line ->
                val trimmed = line.trim()

                if (trimmed.isEmpty() || trimmed.startsWith("#")) return@forEach

                val idx = trimmed.indexOf('=')
                if (idx == -1) return@forEach

                val key = trimmed.take(idx).trim()
                var value = trimmed.substring(idx + 1).trim()

                if ((value.startsWith("\"") && value.endsWith("\"")) ||
                    (value.startsWith("'") && value.endsWith("'"))) {
                    value = value.substring(1, value.length - 1)
                }

                values[key] = value
            }
        }
    }

    fun load(vararg paths: String) {
        paths.forEach { load(it) }
    }

    operator fun get(key: String): String? = values[key]

    fun getOrDefault(key: String, default: String): String =
        values.getOrDefault(key, default)

    fun asMap(): Map<String, String> = values.toMap()
}
