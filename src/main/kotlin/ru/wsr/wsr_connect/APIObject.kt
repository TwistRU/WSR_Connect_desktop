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
import io.socket.client.IO
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.javafx.JavaFx
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.json.JSONObject
import java.io.File
import kotlinx.serialization.json.JsonObject
import kotlin.coroutines.CoroutineContext


@Serializable
data class SignInRequest(val username: String, val password: String)

@Serializable
data class SignInResponse(
    val success: Boolean, val errors: List<String>, val token: String = "", val my_id: Int? = null
)

@Serializable
data class SignUpRequest(
    val username: String, val email: String, val first_name: String, val last_name: String, val password: String
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

@Serializable
data class Message(
    val message_id: Int,
    val creator_id: Int,
    val creator_name: String,
    var chat_id: Int,
    val created_at: String,
    val creator_img_url: String? = null,
    val message_body: String? = null,
    val img_url: String? = null,
    val parent_message: Message? = null,
    val mine: Boolean? = null,
    val read: Boolean,
    val edit: Boolean
)

@Serializable
data class ChatMessagesResponse(
    val success: Boolean, val errors: List<String>, val messages: List<Message>? = null
)

@Serializable
data class Chat(
    val chat_name: String,
    val chat_id: Int,
    val last_message: Message? = null,
    val mute: Boolean,
    val pin: Boolean,
    val group: Boolean,
    val img_url: String? = null,
    val mine: Boolean
)

@Serializable
data class ChatsResponse(
    val success: Boolean, val errors: List<String>, val chats: List<Chat>
)

@Serializable
data class SimpleChatMessageRequest(
    val chat_id: Int, val message_body: String, val replied_message_id: Int? = null
)

@Serializable
data class BaseResponse(
    val success: Boolean, val errors: List<String>
)

@Serializable
data class AcceptInvitationRequest(
    val invitation_id: Int
)

@DelicateCoroutinesApi
object APIObject {
    private val client = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
            })
        }
        defaultRequest {
            host = APIObject.host
            port = APIObject.port
        }
    }
    private const val host = "127.0.0.1"
    private const val port = 5000
    private val mSocket = IO.socket("http://$host:$port")
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
                mSocket.connect()
                mSocket.emit("authorization", token)
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
        }
    }


    fun postCompany(company_name: String, func: (BaseResponse) -> Unit) {
        GlobalScope.launch(Dispatchers.JavaFx as CoroutineContext) {
            val response: BaseResponse = client.post {
                url { encodedPath = "/company" }
                parameter("company_name", company_name)
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
                    headers {
                        append(HttpHeaders.Authorization, "Bearer $token")
                    }
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

    fun getChats(search: String? = null, func: (ChatsResponse) -> Unit) {
        GlobalScope.launch(Dispatchers.JavaFx as CoroutineContext) {
            val response: ChatsResponse = client.get {
                url { encodedPath = "/chats" }
                parameter("search", search)
                headers {
                    append(HttpHeaders.Authorization, "Bearer $token")
                }
            }
            func(response)
        }
    }

    fun getChatMessages(chat_id: Int, func: (ChatMessagesResponse) -> Unit) {
        GlobalScope.launch(Dispatchers.JavaFx as CoroutineContext) {
            val response: ChatMessagesResponse = client.get {
                url { encodedPath = "/chat/messages" }
                parameter("chat_id", chat_id)
                headers {
                    append(HttpHeaders.Authorization, "Bearer $token")
                }
            }
            func(response)
        }
    }

    fun postSimpleChatMessage(request: SimpleChatMessageRequest, func: (BaseResponse) -> Unit) {
        GlobalScope.launch(Dispatchers.JavaFx as CoroutineContext) {
            val response: BaseResponse = client.post {
                url { encodedPath = "/chat/message" }
                headers {
                    append(HttpHeaders.Authorization, "Bearer $token")
                }
                contentType(ContentType.Application.Json)
                body = request
            }
            func(response)
        }
    }

    fun setOnMessageEvent(func: (Message) -> Unit) {
        mSocket.on("msg") {
            func(Json.decodeFromString<Message>((it[0] as JSONObject).toString()))
        }
    }
    
    fun acceptInvitation(request: AcceptInvitationRequest, func: (BaseResponse) -> Unit) {
        GlobalScope.launch(Dispatchers.JavaFx as CoroutineContext) {
            val response: BaseResponse = client.put {
                url { encodedPath = "/invitations" }
                contentType(ContentType.Application.Json)
                body = request
            }
            func(response)
        }
    }
}
