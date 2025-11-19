package de.joker.glue.paper.extensions

import de.joker.glue.extensions.miniMessage
import net.kyori.adventure.text.Component
import net.kyori.adventure.title.Title
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import org.bukkit.attribute.Attribute
import org.bukkit.block.Block
import org.bukkit.entity.Player
import java.util.Locale
import kotlin.time.toJavaDuration

/**
 * Sends an action bar message to the player with MiniMessage parsing
 * @param text The message to send
 * @author InvalidJoker
 */
fun Player.actionBar(text: String) {
    sendActionBar(text.miniMessage())
}

/**
 * Gets the locale of the player
 * @return The locale of the player
 * @author InvalidJoker
 */
val Player.locale: Locale
    get() = locale()

/**
 * Gets the locale tag of the player
 * @return The locale tag of the player
 * @author InvalidJoker
 */
val Player.localeTag: String
    get() = locale().toLanguageTag()

/**
 * Gets or sets the scale attribute of the player
 * @author InvalidJoker
 */
var Player.scale: Double
    get() = getAttribute(Attribute.SCALE)?.baseValue ?: 1.0
    set(value) {
        getAttribute(Attribute.SCALE)?.baseValue = value
    }

/**
 * Gets the block the player is currently standing on
 * @return The block the player is standing on
 * @author InvalidJoker
 */
val Player.standingOn: Block
    get() = world.getBlockAt(location.blockX, location.blockY - 1, location.blockZ)

/**
 * Sends a title and subtitle to the player with MiniMessage parsing
 * @param title The title to send
 * @param subtitle The subtitle to send
 * @param fadeIn The fade in time in ticks
 * @param stay The stay time in ticks
 * @param fadeOut The fade out time in ticks
 * @author InvalidJoker
 */
fun Player.title(
    title: String? = null,
    subtitle: String? = null,
    fadeIn: Int = 20,
    stay: Int = 60,
    fadeOut: Int = 20
) {
    title(
        title?.miniMessage(),
        subtitle?.miniMessage(),
        fadeIn,
        stay,
        fadeOut
    )
}

/**
 * Sends a title and subtitle to the player
 * @param title The title to send
 * @param subtitle The subtitle to send
 * @param fadeIn The fade in time in ticks
 * @param stay The stay time in ticks
 * @param fadeOut The fade out time in ticks
 * @author InvalidJoker
 */
fun Player.title(
    title: Component? = null,
    subtitle: Component? = null,
    fadeIn: Int = 20,
    stay: Int = 60,
    fadeOut: Int = 20
) {
    showTitle(
        Title.title(
            title ?: Component.text(" "),
            subtitle ?: Component.text(" "),
            Title.Times.times(fadeIn.minecraftTicks.toJavaDuration(), stay.minecraftTicks.toJavaDuration(), fadeOut.minecraftTicks.toJavaDuration())
        )
    )
}

/**
 * Converts this Player to an OfflinePlayer
 * @return The OfflinePlayer representation of this Player
 * @author InvalidJoker
 */
fun Player.toOfflinePlayer(): OfflinePlayer = Bukkit.getOfflinePlayer(uniqueId)