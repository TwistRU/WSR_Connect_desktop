package ru.wsr.wsr_connect.chatComponents

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Region
import javafx.scene.layout.VBox
import javafx.scene.text.Text
import ru.wsr.wsr_connect.APIObject
import ru.wsr.wsr_connect.Chat
import ru.wsr.wsr_connect.Message


class ChatScreenController() : BorderPane() {

    @FXML
    private lateinit var resources: ResourceBundle

    @FXML
    private lateinit var location: URL

    @FXML
    private lateinit var chatBox: VBox

    @FXML
    private lateinit var nameField: Text

    @FXML
    private lateinit var resultList: VBox

    @FXML
    private lateinit var root: BorderPane

    @FXML
    private lateinit var searchButton: Button



    var messageWindow: ChatMessagesWindowCOntroller? = null
    var chats = mutableMapOf<Int, Pair<Chat, ChatSearchCard>>()


    @FXML
    fun initialize() {
        makeSearchButton()
        addMessangerBar()

        APIObject.profileInfo {
            this.nameField.text = it.username
        }

        APIObject.getChats {
            for (chat in it.chats) {
                val curChat = ChatSearchCard()
                curChat.chatName.text = chat.chat_name
                curChat.chatLastMessage.text = chat.last_message?.message_body
                curChat.chat_id = chat.chat_id
                curChat.Parent = messageWindow

                if (chat.last_message?.creator_img_url != null){
                    APIObject.getFile(chat.last_message?.creator_img_url!!){
                        curChat.avatarImage.image = Image(it.path)
                    }
                }
                chats.put(chat.chat_id, Pair(chat, curChat))
                resultList.children.add(curChat)
            }
        }

        APIObject.setOnMessageEvent {
            chats[it.chat_id]?.first?.last_message = it
//            messageWindow?.display_messages(chats[it.chat_id]!!.second)
        }
    }
    init {
        val fxmlLoader = FXMLLoader(javaClass.getResource("chatScreen.fxml"))
        fxmlLoader.setRoot(this)
        fxmlLoader.setController(this)
        fxmlLoader.load<Any>()
    }

    fun makeSearchButton(){
        val search = Region()
        search.styleClass.add("search")
        searchButton.graphic = search
    }

    fun addMessangerBar(){
        messageWindow = ChatMessagesWindowCOntroller()
        this.center = messageWindow
    }

}
