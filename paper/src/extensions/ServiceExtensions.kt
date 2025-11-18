package de.joker.glue.paper.extensions

import de.joker.glue.paper.PluginInstance
import org.bukkit.plugin.ServicePriority
import org.bukkit.plugin.ServicesManager


/**
 * Registers a service instance with the ServicesManager using reified type T.
 * @param T The type of the service to register.
 * @param instance The service instance to register.
 * @param priority The priority of the service (default is ServicePriority.Normal).
 *
 * @author InvalidJoker
 */
inline fun <reified T : Any> ServicesManager.register(
    instance: T,
    priority: ServicePriority = ServicePriority.Normal
) {
    register(T::class.java, instance, PluginInstance, priority)
}

/**
 * Retrieves a service instance of type T from the ServicesManager.
 * @param T The type of the service to retrieve.
 * @return The service instance if registered, otherwise null.
 *
 * @author InvalidJoker
 */
inline fun <reified T : Any> ServicesManager.get(): T? {
    return getRegistration(T::class.java)?.provider
}

/**
 * Loads a service instance of type T from the ServicesManager.
 * @param T The type of the service to load.
 * @return The service instance.
 * @throws IllegalStateException if the service is not registered.
 *
 * @author InvalidJoker
 */
inline fun <reified T : Any> ServicesManager.load(): T {
    return getRegistration(T::class.java)?.provider
        ?: throw IllegalStateException("Service of type ${T::class.java.name} is not registered")
}

/**
 * Loads a service instance of type T from the ServicesManager, or returns null if not registered.
 * @param T The type of the service to load.
 * @return The service instance if registered, otherwise null.
 *
 * @author InvalidJoker
 */
inline fun <reified T : Any> ServicesManager.loadOrNull(): T? {
    return getRegistration(T::class.java)?.provider
}

/**
 * Unregisters a service instance of type T from the ServicesManager.
 * @param T The type of the service to unregister.
 * @param instance The service instance to unregister.
 *
 * @author InvalidJOker
 */
inline fun <reified T : Any> ServicesManager.unregister(instance: T) {
    unregister(T::class.java, instance)
}