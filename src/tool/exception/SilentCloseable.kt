package dev.fruxz.ascend.tool.exception

/**
 * Extension of [AutoCloseable] which allows to close without handling Exceptions
 * @author Max Bossing
 */
interface SilentCloseable : AutoCloseable {
    /**
     * Close silently (without throwing)
     * @return the thrown [Throwable] when applicable, or null
     * @author Max Bossing
     */
    fun closeSilently(): Throwable? = runCatching { close() }.exceptionOrNull()

    /**
     * Close reporting, printing Exceptions to stdout
     * @author Max Bossing
     */
    fun closeReporting() = runCatching { close() }.exceptionOrNull()?.printStackTrace()
}