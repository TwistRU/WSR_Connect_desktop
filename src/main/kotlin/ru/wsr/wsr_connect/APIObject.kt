package ru.wsr.wsr_connect

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.javafx.JavaFx
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import kotlin.coroutines.CoroutineContext


@Serializable
data class SignInRequest(val username: String, val password: String)

@Serializable
data class SignInResponse(val success: Boolean, val errors: List<String>, val token: String = "")

@DelicateCoroutinesApi
object APIObject {
    private val client = HttpClient(CIO) {
        install(JsonFeature) { serializer = KotlinxSerializer() }
        defaultRequest {
            host = APIObject.host
            port = APIObject.port
        }
    }
    private const val host = "localhost"
    private const val port = 5000
    private var token = ""
    private var savedLogin = ""
    private var savedPassword = ""

    fun auth(request: SignInRequest, func: (SignInResponse) -> Unit) {
        savedLogin = request.username
        savedPassword = request.password
        GlobalScope.launch(Dispatchers.JavaFx as CoroutineContext) {
            val response: SignInResponse = client.post {
                url { encodedPath = "/auth/login" }
                contentType(ContentType.Application.Json)
                body = request
            }
            if (response.success){
                token = response.token.substring(1, response.token.length - 1)
            }
            func(response)
        }
    }

    fun profileInfo(func: () -> Unit) {
        if (token != "") {
            val t = GlobalScope.launch(Dispatchers.JavaFx as CoroutineContext) {
                val response: JsonObject = client.get {
                    url { encodedPath = "/profile/info" }
                    headers {
                        append(HttpHeaders.Authorization, "Bearer $token")
                    }
                }
                println(response)
            }
        }
    }

}
