package com.whammy.user.repository

import com.whammy.user.driver.UserModel
import com.whammy.user.domain.User
import com.whammy.user.usecase.IUserRepository
import org.springframework.stereotype.Repository

@Repository
class UserRepository(private val driver: UserDriver): IUserRepository {
    override fun isUserExists(email: String, password: String): Boolean {
        return driver.findUserByEmailAddressAndPassword(email, password) != null
    }

    override fun findUserByEmailAddress(email: String): User {
        val model = driver.findUserByEmailAddress(email)
        return User(model!!.email, model.password, model.token)
    }

    override fun save(user: User) {
        val model = UserModel(user.email, user.password, user.token)
        driver.save(model)
    }
}