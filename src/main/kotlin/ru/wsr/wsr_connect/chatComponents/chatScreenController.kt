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
    private lateinit var resultsBox: BorderPane

    @FXML
    private lateinit var root: BorderPane

    @FXML
    private lateinit var searchButton: Button



    val messagesWindow = null

    var users = arrayListOf<ChatSearchUser>(
        ChatSearchUser(),
        ChatSearchUser()
    )

    var dialogs = arrayListOf<ChatSearchCard>(
        ChatSearchCard(),
        ChatSearchCard()
    )

    @FXML
    fun initialize() {
        makeSearchButton()
        addMessangerBar()

        APIObject.getChats() {
            for (chat in it.chats){
                val curChat = ChatSearchCard()
                curChat.chatName.text = chat.chat_name
                curChat.chatLastMessage.text = chat.last_message?.message_body
                if (chat.last_message?.img_url != null) {
                    curChat.avatarImage.image = Image(chat.last_message?.img_url)
                }
                resultList.children.add(curChat)
            }
        }


//        for (user in users){
//            resultList.children.add(user)
//            user.setOnMouseClicked { e -> user.get_chat( this) }
//        }
//        for (dialog in dialogs){
//            resultList.children.add(dialog)
//        }
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
        val messages_window = ChatMessagesWindowCOntroller()
        this.center = messages_window
    }

}
