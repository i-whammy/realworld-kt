package com.whammy.user.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.whammy.user.domain.User
import com.whammy.user.exception.AuthorizationFailureException
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

@Service
class UserService {
    // It might be better to have this secret in some external configuration file...
    private val issuer = "Real World App"

    private val secret = "VGhpcyBrZXkgbXVzdCBub3QgYmUgZGVjb2RlZA=="

    private val algorithm = Algorithm.HMAC256(secret)

    private val verifier = JWT.require(algorithm).withIssuer(issuer).build()

    fun issueNewToken(user: User): User {
        return User(
            user.email, user.password, JWT.create()
                .withIssuer(issuer)
                .withClaim("userId", user.email)
                .withExpiresAt(Date.from(LocalDateTime.now().minusHours(1L).atOffset(ZoneOffset.UTC).toInstant()))
                .sign(algorithm)
        )
    }

    private fun isValid(authorizationHeaderContent: String): Boolean {
        try {
            val (authorizationType, token) = authorizationHeaderContent.split(" ")
            val decodedJWT = verifier.verify(token)
            return decodedJWT.expiresAt > Date.from(LocalDateTime.now().atOffset(ZoneOffset.UTC).toInstant()) && authorizationType == "Token"
        } catch (e: Exception) {
            return false
        }
    }

    fun getUserId(authorizationHeaderContent: String):String {
        if (isValid(authorizationHeaderContent)) {
            return verifier.verify(authorizationHeaderContent).getClaim("userId").asString()
        }
        else throw AuthorizationFailureException("Authorization Failed.")
    }
}
