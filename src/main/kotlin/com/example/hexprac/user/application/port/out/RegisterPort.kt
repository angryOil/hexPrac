package com.example.hexprac.user.application.port.out

import com.example.hexprac.user.domain.User

interface RegisterPort<T> {
    fun register(user:User):T
}