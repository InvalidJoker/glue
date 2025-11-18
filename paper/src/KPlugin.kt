package de.joker.glue.paper

import de.joker.glue.paper.module.GlueModule
import de.joker.glue.paper.tasks.KRunnableHolder
import dev.fruxz.ascend.annotation.InternalGlueApi
import dev.fruxz.ascend.extension.forceCast
import org.bukkit.plugin.java.JavaPlugin

/**
 * The Main instance of the Plugin
 * @author InvalidJoker
 */
lateinit var PluginInstance: KPlugin
    private set

/**
 * The Paper Entrypoint for KPlugin
 *
 * When working with Glue, extend from this class and not [JavaPlugin]
 *
 * Instead of overriding [onLoad], [onEnable] and [onDisable], override:
 * - [load] - at server startup - not everything is ready
 * - [start] - called when everything serverside is ready
 * - [stop] - called at server shutdown
 *
 * @author InvalidJoker
 * @since 0.0.1
 */
@OptIn(InternalGlueApi::class)
abstract class KPlugin : JavaPlugin() {
    private val kRunnableHolderProperty = lazy { KRunnableHolder }
    internal val kRunnableHolder by kRunnableHolderProperty

    /**
     * [GlueModule]s that will be integrated into the lifecycle
     */
    open val modules: List<GlueModule<*>> = listOf()

    override fun onLoad() {
        if (::PluginInstance.isInitialized) {
            logger.severe("The KPlugin instance has already been initialized.")
        }
        PluginInstance = this
        modules.forEach { it.load(this.forceCast()) }
        load()
    }

    override fun onEnable() {
        modules.forEach { it.start(this.forceCast()) }
        start()
    }

    override fun onDisable() {
        stop()
        modules.forEach { it.stop(this.forceCast()) }
        if (kRunnableHolderProperty.isInitialized()) kRunnableHolderProperty.value.close()
    }

    /**
     * Called on plugin load
     */
    open fun load() {}

    /**
     * Called on plugin start
     */
    open fun start() {}

    /**
     * Called on plugin stop
     */
    open fun stop() {}
}