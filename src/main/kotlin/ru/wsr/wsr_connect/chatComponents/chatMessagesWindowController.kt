package ru.wsr.wsr_connect.chatComponents

import javafx.event.EventHandler
import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Button
import javafx.scene.control.ScrollPane
import javafx.scene.control.TextField
import javafx.scene.image.Image
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.text.Text
import ru.wsr.wsr_connect.APIObject
import ru.wsr.wsr_connect.SimpleChatMessageRequest

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

    @FXML
    private lateinit var input: TextField



    var current_chat_id: Int? = null

    @FXML
    fun initialize() {
        makeChatButtons()

//
//        for (msg in mockup_messages){
//            messageScope.children.add(msg)
//        }
        sendButton.onAction = EventHandler { send_message() }

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

    fun display_messages(cash_source: ChatSearchCard){
        messageScope.children.clear()
        var msg: HBox? = null
        for (message in cash_source.cashed_messages){
            if (message.parent_message != null){   // forwarded
//                msg = MessageForwardedController(message.creator_name, message.message_body, message.parent_message.creator_name,
//                    message.parent_message.message_body, message.mine)
                msg = MessageForwardedController(message.mine)
                msg.username.text = message.creator_name
                msg.messageText.text = message.message_body
                msg.forwardedMessageUsername.text = message.parent_message.creator_name
                msg.forwardedMessageText.text = message.parent_message.message_body
            }
            else if (message.img_url == null){    // simple
                msg = MessageSimpleController(message.creator_name, message.message_body, message.mine, message.message_id)
//                messageScope.children.add(msg)
            }
            else {                              // with image
                msg = MessageAttachmentController(message.creator_name)
                APIObject.getFile(message.img_url) {
                    msg.previewImage.image = Image(it.path)
                }
//                messageScope.children.add(msg)
            }
            messageScope.children.add(msg)
        }
    }

    fun send_message(){
        if (input.text != "" && input.text != "" && current_chat_id != null){
            APIObject.postSimpleChatMessage(SimpleChatMessageRequest(current_chat_id!!, input.text)) {
            }
        }
    }
}