package com.example

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.context.ServerRequestContext
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext

@Controller
class Controller {
    @Get(produces = [MediaType.TEXT_PLAIN])
    suspend fun index(): String = supervisorScope {
        require(ServerRequestContext.currentRequest<Any>().isPresent) {
            "Initial request is not set"
        }
        val result = withContext(coroutineContext) {
            require(ServerRequestContext.currentRequest<Any>().isPresent) {
                "Request is not available in `withContext`"
            }
            "test"
        }
        require(ServerRequestContext.currentRequest<Any>().isPresent) {
            "Request is lost after `withContext`"
        }
        result
    }
}
