package de.joker.glue.paper.tasks

import dev.fruxz.ascend.annotation.InternalGlueApi
import dev.fruxz.ascend.tool.exception.SilentCloseable
import org.bukkit.scheduler.BukkitRunnable

/**
 * Responsible for executing the End Callbacks for [KTask]s
 * @author Jakob Kretzschmar (https://github.com/jakobkmar)
 * @since 0.0.1
 */
@InternalGlueApi
internal object KRunnableHolder : SilentCloseable {
    private val runnableEndCallbacks = HashMap<BukkitRunnable, Pair<() -> Unit, Boolean>>()
    override fun close() {
        runnableEndCallbacks.values.forEach { if (it.second) it.first.invoke() }
        runnableEndCallbacks.clear()
    }

    fun add(runnable: BukkitRunnable, callback: () -> Unit, safe: Boolean) =
        runnableEndCallbacks.put(runnable, Pair(callback, safe))

    fun remove(runnable: BukkitRunnable) = runnableEndCallbacks.remove(runnable)
    fun activate(runnable: BukkitRunnable) = runnableEndCallbacks.remove(runnable)?.first?.invoke()
}