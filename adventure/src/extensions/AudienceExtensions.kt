package de.joker.glue.extensions

import de.joker.glue.text.styledText
import de.joker.glue.text.text
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver

/**
 * Sends a styled message to this [Audience]
 * @param message The message to send
 * @param tagResolver The [TagResolver] to use for parsing the message
 * @author InvalidJoker
 */
fun Audience.send(message: String, tagResolver: TagResolver = TagResolver.standard()) = this.sendMessage(styledText(message, tagResolver))

/**
 * Sends an empty line to this [Audience]
 * @author InvalidJoker
 */
fun Audience.sendEmptyLine() = sendMessage(text(" "))

/**
 * Sends a styled message to each [Audience] in this [Collection]
 * @param message The message to send
 * @param tagResolver The [TagResolver] to use for parsing the message
 * @author InvalidJoker
 */
fun Collection<Audience>.send(message: String, tagResolver: TagResolver = TagResolver.standard()) {
    forEach { it.send(message, tagResolver) }
}

/**
 * Sends a [Component] message to each [Audience] in this [Collection]
 * @param message The message to send
 * @author InvalidJoker
 */
fun Collection<Audience>.send(message: Component) {
    forEach { it.sendMessage(message) }
}

/**
 * Sends a block of messages to this [Audience], with an empty line before and after
 * @param lines The lines to send
 * @author InvalidJoker
 */
fun Audience.sendMessageBlock(vararg lines: String) {
    sendEmptyLine()
    lines.forEach { send(it) }
    sendEmptyLine()
}