package dev.fruxz.ascend.extension.time

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.absoluteValue
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

private val DATE = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH)
private val TIME = DateTimeFormatter.ofPattern("HH:mm", Locale.ENGLISH)
private val DATE_TIME = DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm", Locale.ENGLISH)

private data class Span(val short: String, val long: String)

private val SPANS = listOf(Span("d", "day"), Span("h", "hour"), Span("m", "minute"), Span("s", "second"))

/**
 * Formats the [Duration] as a clock, `1:05` or `1:02:03` with hours. Never negative.
 * @since 2026.8
 */
val Duration.clock: String
	get() = coerceAtLeast(Duration.ZERO).toComponents { hours, minutes, seconds, _ ->
		if (hours > 0) "%d:%02d:%02d".format(Locale.ROOT, hours, minutes, seconds) else "%d:%02d".format(Locale.ROOT, minutes, seconds)
	}

/**
 * Formats the [units] largest non-zero parts of the [Duration], like `2d 3h`, `5m 12s` or `45s`.
 * Under a second this is `0s`.
 * @since 2026.8
 */
fun Duration.short(units: Int = 2): String =
	parts(units).joinToString(" ") { (count, span) -> "$count${span.short}" }.ifEmpty { "0s" }

/** @see Duration.short */
val Duration.short: String
	get() = short()

/**
 * Formats the [units] largest non-zero parts of the [Duration], like `2 days 3 hours` or `1 minute`.
 * @since 2026.8
 */
fun Duration.long(units: Int = 2): String =
	parts(units).joinToString(" ") { (count, span) -> "$count ${span.long}${if (count == 1L) "" else "s"}" }.ifEmpty { "0 seconds" }

/** @see Duration.long */
val Duration.long: String
	get() = long()

/**
 * Formats the date of this [Instant] in [zone], like `23 Oct 2026`.
 * @since 2026.8
 */
fun Instant.date(zone: ZoneId = ZoneId.systemDefault()): String = DATE.format(atZone(zone))

/**
 * Formats the time of this [Instant] in [zone], like `14:05`.
 * @since 2026.8
 */
fun Instant.time(zone: ZoneId = ZoneId.systemDefault()): String = TIME.format(atZone(zone))

/**
 * Formats date and time of this [Instant] in [zone], like `23 Oct 2026, 14:05`.
 * @since 2026.8
 */
fun Instant.dateTime(zone: ZoneId = ZoneId.systemDefault()): String = DATE_TIME.format(atZone(zone))

/**
 * Formats this [Instant] relative to [now], like `5m ago`, `in 2h 10m` or `just now` within a second.
 * @since 2026.8
 */
fun Instant.relative(now: Instant = Instant.now(), units: Int = 1): String {
	val difference = (toEpochMilli() - now.toEpochMilli()).milliseconds
	return when {
		difference.absoluteValue < 1.seconds -> "just now"
		difference.isNegative() -> "${difference.absoluteValue.short(units)} ago"
		else -> "in ${difference.short(units)}"
	}
}

private fun Duration.parts(units: Int): List<Pair<Long, Span>> {
	require(units > 0) { "At least one unit, not $units" }
	return absoluteValue.toComponents { days, hours, minutes, seconds, _ ->
		listOf(days, hours.toLong(), minutes.toLong(), seconds.toLong()).zip(SPANS).filter { (count, _) -> count > 0 }.take(units)
	}
}
