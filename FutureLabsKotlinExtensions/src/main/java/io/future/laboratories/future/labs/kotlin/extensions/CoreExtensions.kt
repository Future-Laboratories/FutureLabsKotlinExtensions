package io.future.laboratories.future.labs.kotlin.extensions

import java.lang.Exception

public object CoreExtensions {
    /**
     * This function allows to add two numbers,
     * converts the second number and returning the type of the first number.
     * @receiver Number
     * @param T Number to add
     * @return T
     * @throws NotSupportedException when type is not supported
     */
    @Suppress("UNCHECKED_CAST")
    @Experimental
    public infix fun<T: Number> T.plus(rhs: T): T {
        return when(this) {
            is Double -> this + rhs.toDouble()
            is Float -> this + rhs.toFloat()
            is Int -> this + rhs.toInt()
            is Byte -> this + rhs.toByte()
            is Long -> this + rhs.toLong()
            is Short -> this + rhs.toShort()
            else -> throw NotSupportedException
        } as T
    }

    /**
     * This function allows to subtract two numbers, returning the type of the first number.
     * @receiver Number
     * @param T Number to subtract
     * @return T
     * @throws NotSupportedException when type is not supported
     */
    @Suppress("UNCHECKED_CAST")
    @Experimental
    public infix fun<T: Number> T.minus(rhs: T): T {
        return when(this) {
            is Double -> this - rhs.toDouble()
            is Float -> this - rhs.toFloat()
            is Int -> this - rhs.toInt()
            is Byte -> this - rhs.toByte()
            is Long -> this - rhs.toLong()
            is Short -> this - rhs.toShort()
            else -> throw NotSupportedException
        } as T
    }

    private object NotSupportedException: Exception("Operation not Supported")
}
