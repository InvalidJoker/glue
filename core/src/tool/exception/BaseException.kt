package dev.fruxz.ascend.tool.exception

import dev.fruxz.ascend.annotation.LanguageFeature

/**
 * Exception thrown when unreachable code is executed.
 * @author InvalidJoker
 */
class UnreachableCodeException : IllegalStateException("This code should be unreachable!")

/**
 * Marks a code path as unreachable.
 * @throws UnreachableCodeException always
 * @author InvalidJoker
 */
@LanguageFeature
fun unreachable(): Nothing = throw UnreachableCodeException()