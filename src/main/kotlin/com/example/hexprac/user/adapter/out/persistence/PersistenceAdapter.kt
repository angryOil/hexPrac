package com.example.hexprac.user.adapter.out.persistence

import com.example.hexprac.user.adapter.`in`.rest.LoginSuccessModel
import com.example.hexprac.user.application.port.out.LoginPort
import com.example.hexprac.user.application.port.out.RegisterPort
import com.example.hexprac.user.domain.User
import org.springframework.stereotype.Service

@Service
class PersistenceAdapter(
    private val userRepository: UserRepository
) : LoginPort<User?>, RegisterPort<Long?> {

    override fun login(userId: String, pw: String): User? {
        val findUserEntity = userRepository.findByUserId(userId) ?: return null
        //todo encrypt match

        return if (pw != findUserEntity.pw) {
            null
        } else {
            findUserEntity.toUser()
        }
    }

    override fun register(user: User): Long? {
        if (userRepository.existsByUserId(user.userId)) return null

        return userRepository.save(
            UserEntity(
                idx = 0,
                userId = user.userId,
                // todo pw encrypt
                pw = user.pw,
                nickName = user.nickName
            )
        ).idx
    }


    private fun UserEntity.toUser() = User(idx = idx, userId = userId, pw = "", nickName = nickName)
}