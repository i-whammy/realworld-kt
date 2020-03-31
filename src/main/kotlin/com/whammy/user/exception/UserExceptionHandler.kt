package com.whammy.user.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

@ControllerAdvice
class UserExceptionHandler {

    @ExceptionHandler( LoginFailureException::class )
    @ResponseBody
    fun handleLoginFailureException() : ResponseEntity<Map<String, String>> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(hashMapOf(Pair("message", "No User Found.")))
    }
}