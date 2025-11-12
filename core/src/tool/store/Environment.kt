package dev.fruxz.ascend.tool.store

object Environment {
    private val env = System.getenv()
    private val suppliers = mutableListOf<Map<String, String>>()

    /**
     * Adds a custom environment supplier (e.g., from a dotenv file or other source).
     *
     * @param supplier A map containing key-value pairs for environment variables.
     */
    fun addSupplier(supplier: Map<String, String>) {
        suppliers += supplier
    }

    /**
     * Clears all registered environment suppliers.
     */
    fun clearSuppliers() {
        suppliers.clear()
    }

    /**
     * Retrieves the value of the environment variable with the specified key.
     *
     * @param key The key of the environment variable.
     * @return The value of the environment variable, or null if not found.
     */
    fun getString(key: String): String? {
        // Try suppliers first (in reverse order: last added takes precedence)
        for (supplier in suppliers.asReversed()) {
            supplier[key]?.let { return it }
        }

        // Fallback to system environment
        return env[key]
    }


    fun getBoolean(key: String): Boolean {
        return getString(key)?.toBoolean() == true
    }

    fun getInt(key: String): Int {
        return getString(key)?.toIntOrNull() ?: 0
    }

    fun getIntOrNull(key: String): Int? {
        return getString(key)?.toIntOrNull()
    }

    fun getIntOrDefault(key: String, default: Int): Int {
        return getIntOrNull(key) ?: default
    }

    fun getLong(key: String): Long {
        return getString(key)?.toLongOrNull() ?: 0L
    }

    fun getLongOrNull(key: String): Long? {
        return getString(key)?.toLongOrNull()
    }

    fun getLongOrDefault(key: String, default: Long): Long {
        return getLongOrNull(key) ?: default
    }

    fun getDouble(key: String): Double {
        return getString(key)?.toDoubleOrNull() ?: 0.0
    }

    fun getDoubleOrNull(key: String): Double? {
        return getString(key)?.toDoubleOrNull()
    }

    fun getDoubleOrDefault(key: String, default: Double): Double {
        return getDoubleOrNull(key) ?: default
    }

    fun getFloat(key: String): Float {
        return getString(key)?.toFloatOrNull() ?: 0.0f
    }

    fun getFloatOrNull(key: String): Float? {
        return getString(key)?.toFloatOrNull()
    }

    fun getFloatOrDefault(key: String, default: Float): Float {
        return getFloatOrNull(key) ?: default
    }

    fun containsKey(key: String): Boolean {
        return getString(key) != null
    }
}