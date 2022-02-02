package ru.wsr.wsr_connect

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.auth.*
import io.ktor.client.features.auth.providers.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable
data class Success(val success: Boolean)

object APIObject {
    private val client = HttpClient(CIO) {
        install(JsonFeature) { serializer = KotlinxSerializer() }
        install(Auth) { bearer { } }
    }
    private const val url = "http://localhost:5000"
    var a = 1;
    var token = ""

    @DelicateCoroutinesApi
    fun auth(login: String = "", password: String = "") {
        GlobalScope.launch(Dispatchers.IO) {
            val response: Success  = client.get("$url/") {


            }
            println(response)
        }
    }
}
