package com.example.usecases.base

interface BaseUsecase <in InputT, out OutputT> {
    suspend fun exec(input: InputT) : OutputT
}