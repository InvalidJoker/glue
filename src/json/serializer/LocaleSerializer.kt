package dev.fruxz.ascend.json.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.*

/**
 * Alias type for [Locale]s serialized with [LocaleSerializer].
 *
 * Serialized as a BCP 47 tag (e.g. "en-US", "de-DE", "fr").
 * Compatible with both "en_US" and "en-US" during deserialization.
 */
typealias SerializableLocale = @Serializable(LocaleSerializer::class) Locale

object LocaleSerializer : KSerializer<Locale> {
    override val descriptor = PrimitiveSerialDescriptor("Locale", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Locale) {
        encoder.encodeString(value.toLanguageTag())
    }

    override fun deserialize(decoder: Decoder): Locale {
        val str = decoder.decodeString().trim()
        val normalized = str.replace('_', '-')
        return Locale.forLanguageTag(normalized)
    }
}
