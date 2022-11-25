@file:Suppress("TooManyFunctions", "TooGenericExceptionCaught")
package com.example.domain

import com.example.domain.Result.Failure
import com.example.domain.Result.Success
//import com.example.domain.graphql.GraphQLIOException

sealed class Result<out T> {
    @PublishedApi
    internal data class Success<out T>(val value: T) : Result<T>()

    @PublishedApi
    internal data class Failure(val exception: Throwable) : Result<Nothing>()

    val isSuccess get() = this is Success

    val isFailure get() = !isSuccess

    companion object {
        fun <T> success(value: T): Result<T> = Success(value)

        fun <T> failure(exception: Throwable): Result<T> = Failure(exception)

        inline fun <T> runCatching(block: () -> T) = try {
            success(block())
        } catch (t: Throwable) {
            failure(t)
        }
    }
}

fun <T> Result<T>.getOrNull(): T? = when (this) {
    is Success -> value
    is Failure -> null
}

fun <T> Result<T>.exceptionOrNull(): Throwable? = when (this) {
    is Success -> null
    is Failure -> exception
}

fun <T> Result<T>.getOrThrow(): T = when (this) {
    is Success -> value
    is Failure -> throw exception
}

inline fun <R, T : R> Result<T>.getOrElse(onFailure: (exception: Throwable) -> R) = when (this) {
    is Success -> value
    is Failure -> onFailure(exception)
}

fun <R, T : R> Result<T>.getOrDefault(defaultValue: R) = when (this) {
    is Success -> value
    is Failure -> defaultValue
}

inline fun <R, T> Result<T>.fold(
    onSuccess: (value: T) -> R,
    onFailure: (exception: Throwable) -> R,
) = when (this) {
    is Success -> onSuccess(value)
    is Failure -> onFailure(exception)
}

inline fun <R, T> Result<T>.map(transform: (value: T) -> R) = when (this) {
    is Success -> Result.success(transform(value))
    is Failure -> Result.failure(exception)
}

inline fun <R, T> Result<T>.mapCatching(transform: (value: T) -> R) = when (this) {
    is Success -> try {
        Result.success(transform(value))
    } catch (t: Throwable) {
        Failure(t)
    }
    is Failure -> Result.failure(exception)
}

inline fun <R, T : R> Result<T>.recover(transform: (exception: Throwable) -> R) = when (this) {
    is Success -> this
    is Failure -> Result.success(transform(exception))
}

inline fun <R, T : R> Result<T>.recoverCatching(transform: (exception: Throwable) -> R) = when (this) {
    is Success -> this
    is Failure -> try {
        Result.success(transform(exception))
    } catch (t: Throwable) {
        Result.failure(t)
    }
}

//inline infix fun <T> Result<T>.onFailureToGraphQL(action: (exception: GraphQLIOException) -> Unit): Result<T> {
//    exceptionOrNull()?.let { (it as? GraphQLIOException)?.let(action) }
//    return this
//}

inline infix fun <T> Result<T>.onFailure(action: (exception: Throwable) -> Unit): Result<T> {
    exceptionOrNull()?.let(action)
    return this
}

inline infix fun <T> Result<T>.onSuccess(action: (value: T) -> Unit): Result<T> {
    getOrNull()?.let(action)
    return this
}

inline infix fun <R, T> Result<T>.flatMap(transform: (value: T) -> Result<R>) = when (this) {
    is Success -> transform(value)
    is Failure -> this
}

fun <T> Result<Result<T>>.flatten() = flatMap { it }

fun <R, T : R> Iterable<Result<T>>.unwrap() = fold(Result.success(emptyList<R>())) { elements, element ->
    elements.mapCatching { it + element.getOrThrow() }
}
