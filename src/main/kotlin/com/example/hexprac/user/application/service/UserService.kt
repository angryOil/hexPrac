package com.example.hexprac.user.application.service

import com.example.hexprac.user.adapter.`in`.rest.LoginSuccessModel
import com.example.hexprac.user.application.port.`in`.LoginUseCase
import com.example.hexprac.user.application.port.`in`.RegisterUseCase
import com.example.hexprac.user.application.port.out.LoginPort
import com.example.hexprac.user.application.port.out.RegisterPort
import com.example.hexprac.user.domain.User
import org.springframework.stereotype.Service

@Service
class UserService(
    private val loginPort: LoginPort<User?>,
    private val registerPort: RegisterPort<Long?>
) : LoginUseCase<LoginSuccessModel?>, RegisterUseCase<Long?> {

    override fun login(id: String, pw: String): LoginSuccessModel? {
        return loginPort.login(userId = id, pw = pw)?.let { LoginSuccessModel(id = it.idx, nickName = it.nickName) }
    }

    override fun register(user: User): Long? {
        return registerPort.register(user)
    }

}