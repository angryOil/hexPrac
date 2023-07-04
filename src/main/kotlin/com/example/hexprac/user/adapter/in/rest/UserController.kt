package com.example.hexprac.user.adapter.`in`.rest

import com.example.hexprac.user.adapter.`in`.rest.model.CreateModel
import com.example.hexprac.user.adapter.`in`.rest.model.LoginModel
import com.example.hexprac.user.application.port.`in`.LoginUseCase
import com.example.hexprac.user.application.port.`in`.RegisterUseCase
import com.example.hexprac.user.domain.User
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(
    private val loginUserCase: LoginUseCase<LoginSuccessModel?>,
    private val registerUseCase: RegisterUseCase<Long?>
) {
    @PostMapping("/register")
    fun register(@RequestBody model: CreateModel): ResponseEntity<HttpStatus> {
        return registerUseCase.register(
            User(
                idx = 0,
                userId = model.userId,
                pw = model.pw,
                nickName = model.nickName
            )
        )?.let { return ResponseEntity(HttpStatus.CREATED) } ?: ResponseEntity(HttpStatus.CONFLICT)
    }

    @PostMapping("/login")
    fun login(@RequestBody model: LoginModel): ResponseEntity<LoginSuccessModel> {
        return loginUserCase.login(id = model.userId, pw = model.pw)?.let {
            return ResponseEntity.ok(LoginSuccessModel(id = it.id, nickName = it.nickName))
        } ?: ResponseEntity.badRequest().build()
    }
}