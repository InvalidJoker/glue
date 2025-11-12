package dev.fruxz.ascend.json.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.util.*

/**
* Alias type for [UUID]s serialized with [UUIDSerializer]
*
* However, you probably want to use [kotlin.uuid.Uuid] anyways
*/
typealias SerializableUUID = @Serializable(UUIDSerializer::class) UUID

object UUIDSerializer : KSerializer<UUID> {
    override val descriptor = PrimitiveSerialDescriptor("UUID", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): UUID =
        UUID.fromString(decoder.decodeString())

    override fun serialize(encoder: Encoder, value: UUID) =
        encoder.encodeString(value.toString())

}
