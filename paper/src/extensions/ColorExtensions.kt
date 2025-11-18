package de.joker.glue.paper.extensions

import net.kyori.adventure.text.format.TextColor
import org.bukkit.Color as BukkitColor
import java.awt.Color as JavaColor

/**
 * Convert this [TextColor] into a [BukkitColor]
 * @return a [BukkitColor] representing this [TextColor]
 * @since 0.0.1
 * @author Max Bossing
 */
fun TextColor.bukkit() = BukkitColor.fromRGB(value())

/**
 * Convert this [BukkitColor] into a [TextColor]
 * @return a [TextColor] representing this [BukkitColor]
 * @since 0.0.1
 * @author Max Bossing
 */
fun BukkitColor.adventure(): TextColor = TextColor.color(asRGB())

/**
 * Convert this [BukkitColor] into a [JavaColor]
 * @return a [JavaColor] representing this [BukkitColor]
 * @since 0.0.1
 * @author Max Bossing
 */
fun BukkitColor.awt(): JavaColor = JavaColor(asRGB())

/**
 * Convert this [JavaColor] into a [BukkitColor]
 * @return a [BukkitColor] representing this [JavaColor]
 * @since 0.0.1
 * @author Max Bossing
 */
fun JavaColor.bukkit() = BukkitColor.fromRGB(rgb)