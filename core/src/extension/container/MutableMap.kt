package dev.fruxz.ascend.extension.container

/**
 * Creates a new [MutableMap] by copying all the content from the current [MutableMap].
 *
 * @return A new [MutableMap] with the same key-value pairs as the current [MutableMap].
 *
 * @param T The type of the keys in the [MutableMap].
 * @param E The type of the values in the [MutableMap].
 * @param M The type of the [MutableMap] to copy.
 *
 * @receiver The current [MutableMap] to copy.
 *
 * @see MutableMap
 * @see MutableMap.toMutableMap
 */
@JvmName("copyMutableMap")
fun <T, E, M : MutableMap<T, E>> M.copy() =
	this.toMutableMap()

/**
 * Creates a shallow copy of the current map.
 *
 * @return A new map that is a shallow copy of the current map.
 * @param T The type of the keys in the map.
 * @param E The type of the values in the map.
 * @param M The type of the current map.
 */
fun <T, E, M : Map<T, E>> M.copy() =
	this.toMap()

/**
 * This function removes every element from this [MutableMap], which matches the [condition].
 * @author Fruxz
 * @since 2023.1
 */
inline fun <T, E, M : MutableMap<T, E>> M.removeAll(condition: (key: T, value: E) -> Boolean) = copy().forEach { (key, value) ->
	if (condition(key, value)) {
		this.remove(key)
	}
}

/**
 * Retrieves the element corresponding to [key] from this map if it already exists
 * or creates it by calling [initializer] and saving the result to the map
 */
inline fun <K, E> MutableMap<K, E>.computeIfAbsent(key: K, initializer: () -> E): E {
	val v1 = this[key]
	if (v1 == null) {
		val v2 = initializer()
		this[key] = v2
		return v2
	}
	return v1
}