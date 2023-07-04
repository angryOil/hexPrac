package com.example.hexprac.user.application.port.`in`

import com.example.hexprac.user.domain.User

interface RegisterUseCase<T> {
    fun register(user: User): Long?
}