package de.joker.glue.paper.items

import de.joker.glue.extensions.component
import net.kyori.adventure.text.Component
import org.bukkit.inventory.meta.ItemMeta

/**
 * Used to provide simple methods to set the Lore of an [ItemMeta]
 * @since 0.0.1
 * @author Jakob Kretzschmar (https://github.com/jakobkmar)
 * @see setLore
 * @see addLore
 */
class LoreBuilder {
    val lore = mutableListOf<Component>()

    operator fun Component.unaryPlus() = lore.add(this)
    operator fun String.unaryPlus() = lore.add(this.component())
}

/**
 * Sets the Lore of the [ItemMeta]
 * @param builder the [LoreBuilder] to set
 * @author Jakob Kretzschmar (https://github.com/jakobkmar)
 * @since 0.0.1
 */
inline fun ItemMeta.setLore(builder: LoreBuilder.() -> Unit) =
    lore(LoreBuilder().apply(builder).lore)

/**
 * Appends the [LoreBuilder] to the current Lore of the [ItemMeta]
 * @param builder The [LoreBuilder] to append
 * @author Max Bossing
 * @since 0.0.1
 */
inline fun ItemMeta.addLore(builder: LoreBuilder.() -> Unit) =
    lore(lore()?: mutableListOf<Component>().apply { addAll(LoreBuilder().apply(builder).lore) })