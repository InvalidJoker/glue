package de.joker.glue.paper.extensions

import org.bukkit.Location
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import kotlin.math.sqrt

/**
 * @see [Location.isInArea]
 * @since 0.0.1
 * @author Max Bossing
 */
fun Entity.isInArea(center: Location, radius: Double): Boolean = location.isInArea(center, radius)

/**
 * @see [Location.isInArea]
 * @since 0.0.1
 * @author Max Bossing
 */
fun Entity.isInArea(loc1: Location, loc2: Location) = location.isInArea(loc1, loc2)

/**
 * Boosts this [Entity] the specified distance in Blocks upwards, accounting for Minecrafts physics and gravity
 * @param distance The distance in Blocks this [Entity] should be boosted up
 * @author Max Bossing
 * @since 0.0.1
 */
fun Entity.moveUpwards(distance: Int) = velocity.setY(sqrt(2 * distance * 9.81) * 0.08)

/**
 * Sets this [LivingEntity]s health to the currently maximum possible value
 * @since 0.0.1
 * @author Jakob Kretzschmar (https://github.com/jakobkmar)
 */
fun LivingEntity.heal() {
    health = getAttribute(Attribute.MAX_HEALTH)?.value
        ?: throw NullPointerException("The entity does not have a max health value!")
}