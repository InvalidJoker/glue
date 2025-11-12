package de.joker.glue.text

import de.joker.glue.extensions.miniMessage
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.format.TextDecoration.State
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver

/**
 * Create a [Component] with basic styles. By default, every style is deactivated. The component does not inherit the styles of previous Components
 * @param str The content of the [Component]
 * @param color The [TextColor] of the [Component]. If multiple colors are given, the component will be colored with a gradient of those colors (will be deserialized using [miniMessage]!)
 * @param bold If the [Component] should be bold
 * @param italic If the [Component] should be italic
 * @param underlined If the [Component] should be underlined
 * @param strikethrough If the [Component] should be striked through
 * @param obfuscated If the [Component] should be obfuscated (magic chars)
 * @return A [Component] with the given content with the styles applied accordingly
 * @author Max Bossing
 * @since 0.0.1
 */
fun text(
    str: String,
    vararg color: TextColor = arrayOf(Kolors.GRAY),
    bold: Boolean = false,
    italic: Boolean = false,
    underlined: Boolean = false,
    strikethrough: Boolean = false,
    obfuscated: Boolean = false,
): Component =
    (if (color.size == 1) Component.text(str)
        .color(color.first()) else "<gradient:${color.joinToString(":") { it.asHexString() }}>$str</gradient>".miniMessage())
        .apply {
            decorations(
                mapOf(
                    TextDecoration.BOLD to State.byBoolean(bold),
                    TextDecoration.ITALIC to State.byBoolean(italic),
                    TextDecoration.UNDERLINED to State.byBoolean(underlined),
                    TextDecoration.STRIKETHROUGH to State.byBoolean(strikethrough),
                    TextDecoration.OBFUSCATED to State.byBoolean(obfuscated),
                )
            )
        }

/**
 * Create a [Component] from a MiniMessage formatted [String]
 * @param str The MiniMessage formatted [String]
 * @param tagResolver Optional [TagResolver]s to resolve custom tags
 * @return A [Component] representing the MiniMessage formatted [String]
 * @author Max Bossing
 * @since 0.0.1
 */
fun styledText(
    str: String,
    vararg tagResolver: TagResolver
) = str.miniMessage(tagResolver = tagResolver)