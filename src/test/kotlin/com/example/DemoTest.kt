package com.example

import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Test
import jakarta.inject.Inject
import kotlinx.coroutines.runBlocking

@MicronautTest
class DemoTest {
    @Inject @field:Client("/") lateinit var client: HttpClient

    @Test
    fun testItWorks(): Unit = runBlocking {
        val response = client.toBlocking().exchange<String>("/")
        assert(response.body() == "test")
    }
}
