package ru.wsr.wsr_connect

import io.ktor.client.*
import io.ktor.client.call.*
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
import java.io.File
import kotlin.coroutines.CoroutineContext


@Serializable
data class SignInRequest(val username: String, val password: String)

@Serializable
data class SignInResponse(val success: Boolean, val errors: List<String>, val token: String = "")

@Serializable
data class SignUpRequest(
    val username: String,
    val email: String,
    val first_name: String,
    val last_name: String,
    val password: String
)

@Serializable
data class SignUpResponse(val success: Boolean, val errors: List<String>, val token: String = "")

@Serializable
data class UserInfoResponse(
    val user_id: Int,
    val username: String,
    val first_name: String?,
    val last_name: String?,
    val email: String,
    val img_url: String?,
    val company_id: Int?,
    val about_me: String?
)

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
            if (response.success) {
                token = response.token
            }
            func(response)
        }
    }

    fun registration(request: SignUpRequest, func: (SignUpResponse) -> Unit) {
        savedLogin = request.username
        savedPassword = request.password
        GlobalScope.launch(Dispatchers.JavaFx as CoroutineContext) {
            val response: SignUpResponse = client.post {
                url { encodedPath = "/auth/registration" }
                contentType(ContentType.Application.Json)
                body = request
            }
            if (response.success) {
                token = response.token
            }
            func(response)
        }
    }

    fun profileInfo(func: (UserInfoResponse) -> Unit) {
        if (token != "") {
            GlobalScope.launch(Dispatchers.JavaFx as CoroutineContext) {
                val response: UserInfoResponse = client.get {
                    url { encodedPath = "/profile/info" }
                    headers {
                        append(HttpHeaders.Authorization, "Bearer $token")
                    }
                }
                func(response)
            }
        }
    }

    fun getFile(url: String, func: (File) -> Unit) {
        val path = File(System.getenv("LOCALAPPDATA") + "/WSRConnect")
        path.mkdir()
        val file = File(path.absolutePath + "/$url.cache")
        println(file)
        if (file.createNewFile()) {
            GlobalScope.launch(Dispatchers.JavaFx as CoroutineContext) {
                val response: HttpResponse = client.get {
                    url { encodedPath = url }
                }
                val responseBody: ByteArray = response.receive()
                file.writeBytes(responseBody)
                println("A file saved to ${file.path}")
                func(file)
            }
        } else {
            func(file)
        }
    }

}
