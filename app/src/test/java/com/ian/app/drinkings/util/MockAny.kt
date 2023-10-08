package com.ian.app.drinkings.util

import org.mockito.Mockito
import kotlin.reflect.KClass

inline fun <reified T : Any> mockAny(): T {
    return Mockito.any(T::class.java) ?: createInstance()
}

inline fun <reified T : Any> createInstance(): T {
    return when (T::class) {
        Boolean::class -> false as T
        Byte::class -> 0.toByte() as T
        Char::class -> 0.toChar() as T
        Short::class -> 0.toShort() as T
        Int::class -> 0 as T
        Long::class -> 0L as T
        Float::class -> 0f as T
        Double::class -> 0.0 as T
        else -> createInstance(T::class)
    }
}

fun <T : Any> createInstance(kClass: KClass<T>): T {
    return castNull()
}

@Suppress("UNCHECKED_CAST")
private fun <T> castNull(): T = null as T
