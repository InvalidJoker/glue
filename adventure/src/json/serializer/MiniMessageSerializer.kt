package de.joker.glue.json.serializer

import de.joker.glue.text.miniMessage
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import net.kyori.adventure.text.Component

/**
 * Alias type for [Component]s serialized with [MiniMessageSerializer].
 *
 * Serialized as a MiniMessage string.
 */
typealias SerializableMiniMessage = @Serializable(MiniMessageSerializer::class) Component

object MiniMessageSerializer : KSerializer<Component> {
    override val descriptor = PrimitiveSerialDescriptor("MiniMessage", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Component = miniMessage.deserialize(decoder.decodeString())
    override fun serialize(encoder: Encoder, value: Component) = encoder.encodeString(miniMessage.serialize(value))
}
