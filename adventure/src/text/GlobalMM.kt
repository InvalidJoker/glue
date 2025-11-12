package de.joker.glue.text

import net.kyori.adventure.text.minimessage.MiniMessage

/**
 * The global [MiniMessage] instance
 * To add custom behavior (eg tag resolvers), overwrite this instance with a custom one
 * @author InvalidJoker
 */
var miniMessage = MiniMessage.miniMessage()