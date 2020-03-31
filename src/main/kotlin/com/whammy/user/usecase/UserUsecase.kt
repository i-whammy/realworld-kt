package com.whammy.user.usecase

import com.whammy.user.exception.LoginFailureException
import com.whammy.user.domain.LoginInformation
import com.whammy.user.domain.User
import com.whammy.user.service.UserService
import org.springframework.stereotype.Service

@Service
class UserUsecase(private val userRepository: IUserRepository, private val userService: UserService) {
    fun login(loginInformation: LoginInformation): User {
        val email = loginInformation.email
        val password = loginInformation.password
        if (userRepository.isUserExists(email, password)) {
            val user = userRepository.findUserByEmailAddress(email)
            val newUser = userService.issueNewToken(user)
            userRepository.save(newUser)
            return newUser
        } else throw LoginFailureException("The user with $email does not exist.")
    }
}
