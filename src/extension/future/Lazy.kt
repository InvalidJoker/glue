package dev.fruxz.ascend.extension.future

/**
 * Executes the given [block] if this [Lazy] instance has been initialized.
 * If it has not been initialized, returns null.
 *
 * @param T the type of the lazy value
 * @param R the return type of the [block]
 * @param block the block to execute if initialized
 * @return the result of the [block] if initialized, or null otherwise
 * @author InvalidJoker
 */
inline fun <T, R> Lazy<T>.ifInitialized(block: (T) -> R) = if (isInitialized()) block(value) else null

/**
 * Returns the value of this [Lazy] instance if it has been initialized, or null otherwise.
 *
 * @param T the type of the lazy value
 * @return the value if initialized, or null otherwise
 * @author InvalidJoker
 */
val <T> Lazy<T>.valueIfInitialized get() = ifInitialized { value }

/**
 * Closes the [AutoCloseable] value of this [Lazy] instance if it has been initialized.
 *
 * @receiver The [Lazy] instance containing an [AutoCloseable] value.
 * @author InvalidJoker
 */
fun Lazy<AutoCloseable>.closeIfInitialized() = ifInitialized { value.close() }