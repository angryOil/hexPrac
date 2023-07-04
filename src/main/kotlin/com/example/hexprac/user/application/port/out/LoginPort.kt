package com.example.hexprac.user.application.port.out

import com.example.hexprac.user.domain.User

interface LoginPort<T> {
    fun login(userId: String, pw: String): T
}