package de.joker.glue.paper.extensions

import org.bukkit.Location
import org.bukkit.World
import org.bukkit.util.Vector

/**
 * Calculates if [this] is in the specified area
 * @param center The center of the area
 * @param radius The radius of the area around [center]
 * @return if [this] is in the area
 * @author Max Bossing
 * @since 0.0.1
 */
fun Vector.isInArea(center: Vector, radius: Double): Boolean = isInSphere(center, radius)

/**
 * Calculates if [this] is in the specified area
 * @param vec1 One corner of a rectangular area
 * @param vec2 The opposite corner of the area
 * @return if [this] is in the area
 * @author Max Bossing
 * @since 0.0.1
 */
fun Vector.isInArea(vec1: Vector, vec2: Vector) = isInAABB(Vector.getMinimum(vec1, vec2), Vector.getMaximum(vec1, vec2))

/**
 * @see [Vector.isInArea]
 * @since 0.0.1
 */
fun Location.isInArea(center: Location, radius: Double) = toVector().isInArea(center.toVector(), radius)

/**
 * @see [Vector.isInArea]
 * @since 0.0.1
 */
fun Location.isInArea(loc1: Location, loc2: Location) = toVector().isInArea(loc1.toVector(), loc2.toVector())

/**
 * The [World] attached to the [Location] if it's loaded, null if not
 * @since 0.0.1
 */
val Location.worldOrNull: World?
    get() = takeIf { isWorldLoaded }?.world

fun Location.add(x: Number, y: Number, z: Number) = add(x.toDouble(), y.toDouble(), z.toDouble())


fun Location.subtract(x: Number, y: Number, z: Number) = subtract(x.toDouble(), y.toDouble(), z.toDouble())

val Location.blockLoc: Location get() = Location(world, blockX.toDouble(), blockY.toDouble(), blockZ.toDouble())

infix fun Location.relationTo(loc: Location) = this.subtract(loc)

operator fun Location.plus(vec: Vector) = clone().add(vec)

operator fun Location.minus(vec: Vector) = clone().subtract(vec)

operator fun Location.plus(loc: Location) = clone().add(loc)

operator fun Location.minus(loc: Location) = clone().subtract(loc)

operator fun Location.plusAssign(vec: Vector) {
    add(vec)
}

operator fun Location.minusAssign(vec: Vector) {
    subtract(vec)
}

operator fun Location.plusAssign(loc: Location) {
    add(loc)
}

operator fun Location.minusAssign(loc: Location) {
    subtract(loc)
}

val Vector.isFinite: Boolean
    get() = x.isFinite() && y.isFinite() && z.isFinite()

fun vec(x: Number = 0.0, y: Number = 0.0, z: Number = 0.0) = Vector(x.toDouble(), y.toDouble(), z.toDouble())
fun vecXY(x: Number, y: Number) = vec(x, y)
fun vecXZ(x: Number, z: Number) = vec(x, z = z)
fun vecYZ(y: Number, z: Number) = vec(y = y, z = z)
fun vecX(x: Number) = vec(x)
fun vecY(y: Number) = vec(y = y)
fun vecZ(z: Number) = vec(z = z)

operator fun Vector.plus(vec: Vector) = clone().add(vec)
operator fun Vector.minus(vec: Vector) = clone().subtract(vec)

operator fun Vector.times(vec: Vector) = clone().multiply(vec)
operator fun Vector.times(num: Number) = clone().multiply(num.toDouble())

operator fun Vector.plusAssign(vec: Vector) {
    add(vec)
}

operator fun Vector.minusAssign(vec: Vector) {
    subtract(vec)
}

operator fun Vector.timesAssign(vec: Vector) {
    multiply(vec)
}

operator fun Vector.timesAssign(num: Number) {
    multiply(num.toDouble())
}