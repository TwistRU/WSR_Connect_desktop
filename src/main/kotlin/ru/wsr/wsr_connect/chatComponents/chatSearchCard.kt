package ru.wsr.wsr_connect.chatComponents


import javafx.event.EventHandler
import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Label
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.shape.Circle
import ru.wsr.wsr_connect.APIObject
import ru.wsr.wsr_connect.MainContainer
import ru.wsr.wsr_connect.Message

class ChatSearchCard : HBox() {

    @FXML
    private lateinit var resources: ResourceBundle

    @FXML
    private lateinit var location: URL

    @FXML
    lateinit var avatarImage: ImageView

    @FXML
    private lateinit var root: HBox

    @FXML
    lateinit var chatLastMessage: Label

    @FXML
    lateinit var chatName: Label


    var chat_id: Int? = null
    var Parent: ChatMessagesWindowCOntroller? = null

    var cashed_messages = mutableMapOf<Int, Message>()
    var loaded = false


    @FXML
    fun initialize() {
        makeAvatar()
        root.onMouseClicked = EventHandler { display_chat(Parent) }
    }

    init {
        val fxmlLoader = FXMLLoader(javaClass.getResource("chatSearchCard.fxml"))
        fxmlLoader.setRoot(this)
        fxmlLoader.setController(this)
        fxmlLoader.load<Any>()
    }

    private fun makeAvatar(){
        val clip = Circle(30.0, 30.0, 25.0)
        avatarImage.clip = clip
    }

    fun display_chat(Parent: ChatMessagesWindowCOntroller?){
        if (!loaded){
            load_messages()
            loaded = true
        }
        Parent!!.current_chat_id = this.chat_id
        Parent!!.messageScope.children.clear()

        Parent.display_messages(this)
    }

    private fun load_messages() {
        cashed_messages.clear()
        APIObject.getChatMessages(this.chat_id!!) {
            for (message in it.messages!!){
                cashed_messages.put(message.message_id, message)
            }
        }
    }

}
