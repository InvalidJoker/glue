package dev.fruxz.ascend.annotation

/**
 * Marks the annotated element as part of the internal Ascend API.
 *
 * This annotation should be used to indicate that the annotated element is internal
 * and not intended for public use. It serves as a warning for developers to avoid
 * using the element outside of the Ascend library.
 * @author Fruxz
 * @since 2023.1
 **/
@MustBeDocumented
@RequiresOptIn("This is an internal feature, not intended for public use!")
annotation class InternalGlueApi
