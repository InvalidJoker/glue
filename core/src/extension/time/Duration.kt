package dev.fruxz.ascend.extension.time

import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

/**
 * Takes the [Duration.inWholeMilliseconds] and divides it by 50,
 * to represent the duration in minecraft-ticks (20 ticks in a second).
 * @author Fruxz
 * @since 2023.1
 */
val Duration.inWholeMinecraftTicks: Long
	get() = this.inWholeMilliseconds / 50

/**
 * The amount of minecraft-ticks (20 ticks in a second) as a [Duration].
 * @since 2026.8
 */
val Int.minecraftTicks: Duration
	get() = (this.toLong() * 50).milliseconds

/** @see Int.minecraftTicks */
val Long.minecraftTicks: Duration
	get() = (this * 50).milliseconds

/** @see Int.minecraftTicks */
val Double.minecraftTicks: Duration
	get() = (this * 50).milliseconds

/** @see Int.minecraftTicks */
val Float.minecraftTicks: Duration
	get() = (this * 50.0).milliseconds
