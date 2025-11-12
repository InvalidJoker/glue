package dev.fruxz.ascend.tool.math

/**
 * Represents two values, sorted by which is bigger
 * @param a The first value
 * @param b The second value
 * @property min The smaller value
 * @property max The bigger value
 * @author Jakob Kretzschmar (https://github.com/jakobkmar/kspigot)
 */
class MinMaxPair<T : Comparable<T>>(a: T, b: T) {
    /**
     * The smaller value
     */
    val min: T

    /**
     * The bigger value
     */
    val max: T

    init {
        if (a >= b) {
            min = b; max = a
        } else {
            min = a; max = b
        }
    }
}