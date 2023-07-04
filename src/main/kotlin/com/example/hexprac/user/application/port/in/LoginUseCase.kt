package com.example.hexprac.user.application.port.`in`

interface LoginUseCase<T> {
    fun login(id: String, pw: String): T
}