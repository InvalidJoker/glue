package dev.fruxz.ascend.tool.map

import kotlin.time.Duration

/**
 * A mutable map that holds entries for a limited time. After the specified duration has passed since an entry was added,
 * it will no longer be accessible through the map.
 *
 * @param K the type of keys maintained by this map
 * @param V the type of mapped values
 * @param persistenceDuration the duration (in milliseconds) for which an entry should persist in the map
 * @see MutableMap
 * @author InvalidJoker
 */
class TemporaryMap<K, V>(
    private val persistenceDuration: Long
) : MutableMap<K, V> {
    constructor(persistenceDuration: Duration) : this(persistenceDuration.inWholeMilliseconds)

    private val backing = mutableMapOf<K, TemporaryValueWrapper<V>>()

    override fun get(key: K): V? {
        val wrapper = backing[key] ?: return null
        return if (System.currentTimeMillis() - wrapper.persistedAt < persistenceDuration) {
            wrapper.value
        } else {
            null
        }
    }

    override fun put(key: K, value: V): V? {
        val old = get(key)
        backing[key] = TemporaryValueWrapper(value, System.currentTimeMillis())
        return old
    }

    override fun remove(key: K): V? {
        return backing.remove(key)?.value
    }

    override fun putAll(from: Map<out K, V>) {
        from.forEach { (key, value) ->
            backing[key] = TemporaryValueWrapper(value, System.currentTimeMillis())
        }
    }

    override fun clear() {
        backing.clear()
    }

    override val entries: MutableSet<MutableMap.MutableEntry<K, V>>
        get() = backing
            .mapValues { it.value.value }
            .toMutableMap()
            .entries

    override val keys: MutableSet<K>
        get() = backing.keys

    override val size: Int
        get() = backing.size

    override val values: MutableCollection<V>
        get() = backing.values.map { it.value }.toMutableList()

    override fun isEmpty(): Boolean = backing.isEmpty()

    override fun containsKey(key: K): Boolean = get(key) != null

    override fun containsValue(value: V): Boolean =
        backing.values.any { it.value == value && (System.currentTimeMillis() - it.persistedAt < persistenceDuration) }

    data class TemporaryValueWrapper<V>(
        val value: V,
        val persistedAt: Long
    )
}
