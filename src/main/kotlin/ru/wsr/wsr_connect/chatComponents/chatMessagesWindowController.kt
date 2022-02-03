package ru.wsr.wsr_connect.chatComponents

import javafx.scene.layout.Region
import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Button
import javafx.scene.control.ScrollPane
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Text
import ru.wsr.wsr_connect.APIObject

class ChatMessagesWindowCOntroller : ScrollPane() {


    @FXML
    private lateinit var resources: ResourceBundle

    @FXML
    private lateinit var location: URL

    @FXML
    private lateinit var attachButton: Button

    @FXML
    private lateinit var sendButton: Button

    @FXML
    lateinit var messageScope: VBox


//    val mockup_messages = arrayListOf(
//        MessageSimpleController("Tovarish Stalin", "Hello world"),
//        MessageSimpleController("Adolf Gitler", "Bla bla bla"),
//        MessageSimpleController("Umpa lumpa", "ololololo"),
//        MessageAttachmentController(),
//        MessageAttachmentController()
//    )

    var current_chat_id: Int? = null

    @FXML
    fun initialize() {
        makeChatButtons()

//
//        for (msg in mockup_messages){
//            messageScope.children.add(msg)
//        }

    }

    init {
        val fxmlLoader = FXMLLoader(javaClass.getResource("chatMessagesWindow.fxml"))
        fxmlLoader.setRoot(this)
        fxmlLoader.setController(this)
        fxmlLoader.load<Any>()
    }

    private fun makeChatButtons(){
        val attach = Region()
        attach.styleClass.add("attach")
        attachButton.graphic = attach


        val send = Region()
        send.styleClass.add("send")
        sendButton.graphic = send
    }

    fun display_messages(chat_id: Int){
        APIObject.getChatMessages(chat_id) {
            for (message in it.messages!!){
                val msg = MessageSimpleController(message.creator_name, message.message_body, message.mine, message.message_id)
                messageScope.children.add(msg)
            }
        }
    }
}