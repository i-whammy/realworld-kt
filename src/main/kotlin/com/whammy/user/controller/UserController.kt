package com.whammy.user.controller

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import com.whammy.user.domain.LoginInformation
import com.whammy.user.usecase.UserUsecase

// @RequestMapping("/api/users")
class UserController(private val usecase: UserUsecase) {
//
//    @RequestMapping("/login", method = [RequestMethod.POST])
//    fun login(@RequestBody request: LoginRequest): ResponseEntity<LoginResponse> {
//        val user = usecase.login(
//            LoginInformation(
//                request.email,
//                request.password
//            )
//        )
//        return ResponseEntity.ok(
//            LoginResponse(
//                user.email,
//                user.token
//            )
//        )
//    }
}

@JsonRootName("user")
data class LoginRequest(
    @JsonProperty("email") var email: String,
    @JsonProperty("password") var password: String
)

@JsonRootName("user")
data class LoginResponse(
    @JsonProperty("email", required = true) var email: String,
    @JsonProperty("token", required = true) var token: String
)
