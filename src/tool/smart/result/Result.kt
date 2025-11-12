package dev.fruxz.ascend.tool.smart.result

/**
 * A Rust-like discriminated union representing either a success value [Ok] or an error value [Err].
 *
 * `Result<T, E>` is intended for explicit error handling and chaining via `map`, `andThen`, etc.
 *
 * Use:
 * - [Ok] for successful values
 * - [Err] for error values
 */
sealed class Result<out T, out E> {

    /** True if this result is an [Ok] value. */
    val isOk: Boolean get() = this is Ok

    /** True if this result is an [Err] value. */
    val isErr: Boolean get() = this is Err

    /**
     * Returns the success value or `null` if this is [Err].
     */
    fun getOrNull(): T? = when (this) {
        is Ok -> value
        is Err -> null
    }

    /**
     * Returns the error value or `null` if this is [Ok].
     */
    fun errorOrNull(): E? = when (this) {
        is Ok -> null
        is Err -> error
    }

    /**
     * Maps the success value to another type while preserving errors.
     *
     * @param transform function applied to the success value
     * @return a new [Result] with the transformed value or the original error
     */
    inline fun <R> map(transform: (T) -> R): Result<R, E> = when (this) {
        is Ok -> Ok(transform(value))
        is Err -> this
    }

    /**
     * Maps the error value to another type while preserving successes.
     *
     * @param transform function applied to the error value
     * @return a new [Result] with the transformed error or the original success
     */
    inline fun <F> mapError(transform: (E) -> F): Result<T, F> = when (this) {
        is Ok -> this
        is Err -> Err(transform(error))
    }

    /**
     * Chains another result-producing function, similar to Rust's `and_then` or
     * Haskell's `bind`.
     *
     * @param next function mapping the success value to another [Result]
     * @return the result of calling [next] if successful, otherwise the original error
     */
    inline fun <R> andThen(next: (T) -> Result<R, @UnsafeVariance E>): Result<R, E> = when (this) {
        is Ok -> next(value)
        is Err -> this
    }

    /**
     * Recovers from an error by producing a fallback value.
     *
     * @param recover function mapping the error into a replacement success value
     * @return success value or the recovered fallback
     */
    inline fun recover(recover: (E) -> @UnsafeVariance T): T = when (this) {
        is Ok -> value
        is Err -> recover(error)
    }

    /**
     * Folds both branches into a single output value.
     *
     * @param onOk applied to the success value
     * @param onErr applied to the error value
     */
    inline fun <R> fold(onOk: (T) -> R, onErr: (E) -> R): R = when (this) {
        is Ok -> onOk(value)
        is Err -> onErr(error)
    }

    /**
     * Returns the success value or throws a mapped exception.
     *
     * @param toThrowable converts the error value into a [Throwable]
     */
    inline fun getOrThrow(toThrowable: (E) -> Throwable = {
        IllegalStateException(it.toString())
    }): T = when (this) {
        is Ok -> value
        is Err -> throw toThrowable(error)
    }

    /**
     * Executes a side effect if this is [Ok], returning the original result.
     *
     * Useful for logging or debugging.
     */
    inline fun onOk(side: (T) -> Unit): Result<T, E> = apply {
        if (this is Ok) side(value)
    }

    /**
     * Executes a side effect if this is [Err], returning the original result.
     *
     * Useful for logging or debugging.
     */
    inline fun onErr(side: (E) -> Unit): Result<T, E> = apply {
        if (this is Err) side(error)
    }

    /** Successful result variant. */
    data class Ok<out T>(val value: T) : Result<T, Nothing>() {
        override fun toString(): String = "Ok($value)"
    }

    /** Error result variant. */
    data class Err<out E>(val error: E) : Result<Nothing, E>() {
        override fun toString(): String = "Err($error)"
    }

    companion object {

        /**
         * Executes [block] and wraps its result in [Ok].
         * If the block throws, the exception is wrapped in [Err].
         *
         * @return `Ok(value)` on success or `Err(throwable)` on failure
         */
        inline fun <T> of(block: () -> T): Result<T, Throwable> =
            try {
                Ok(block())
            } catch (t: Throwable) {
                Err(t)
            }

        /**
         * Suspend-friendly version of [of].
         */
        suspend inline fun <T> ofSuspend(crossinline block: suspend () -> T): Result<T, Throwable> =
            try {
                Ok(block())
            } catch (t: Throwable) {
                Err(t)
            }
    }
}

/** Convenience constructor for [Result.Ok]. */
fun <T> ok(value: T): Result.Ok<T> = Result.Ok(value)

/** Convenience constructor for [Result.Err]. */
fun <E> err(error: E): Result.Err<E> = Result.Err(error)

/**
 * A [Result] that carries no meaningful success value.
 */
typealias UnitResult<E> = Result<Unit, E>

/**
 * Combines two successful results into a pair or returns the first encountered error.
 *
 * Similar to Rust's `Result::zip`.
 */
fun <A, B, E> Result<A, E>.zip(other: Result<B, E>): Result<Pair<A, B>, E> =
    andThen { a -> other.map { b -> a to b } }

/**
 * Converts a list of results into a single result containing a list of values.
 * Fails fast on the first error.
 *
 * @return `Ok(List<T>)` if all elements are successful, otherwise the first `Err`.
 */
fun <T, E> Iterable<Result<T, E>>.sequence(): Result<List<T>, E> {
    val out = ArrayList<T>()
    for (r in this) when (r) {
        is Result.Ok -> out += r.value
        is Result.Err -> return r
    }
    return Result.Ok(out)
}

/**
 * Maps each element with [f] and then sequences the results.
 *
 * Similar to Haskell's `traverse`.
 */
inline fun <A, B, E> Iterable<A>.traverse(
    f: (A) -> Result<B, E>
): Result<List<B>, E> = map(f).sequence()