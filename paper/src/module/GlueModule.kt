package de.joker.glue.paper.module

import de.joker.glue.paper.KPlugin

/**
 * Module Entrypoint for Modules
 *
 * If a module is imported, its Entrypoint (the class implementing the respective Module class)
 * is added to the module list in the Plugins entrypoint.
 * The load, start and stop functions will then be called at the right times.
 *
 * - [load] is called at the very beginning of the startup sequence. This should be used scarcely and most things should be done in [start]
 * - [start] is called when everything is ready for the module to start. This should be the main function used
 * - [stop] is called when the platform is being shut down.
 *
 * @author InvalidJoker
 * @since 0.0.0
 */
interface GlueModule<T : KPlugin> {
    fun load(entrypoint: T)
    fun start(entrypoint: T)
    fun stop(entrypoint: T)
}