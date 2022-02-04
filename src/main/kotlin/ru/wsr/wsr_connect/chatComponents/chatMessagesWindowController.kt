package ru.wsr.wsr_connect.chatComponents

import javafx.event.EventHandler
import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Button
import javafx.scene.control.ScrollPane
import javafx.scene.control.TextField
import javafx.scene.layout.*
import javafx.stage.FileChooser
import javafx.stage.Stage
import ru.wsr.wsr_connect.APIObject
import ru.wsr.wsr_connect.SimpleChatMessageRequest
import ru.wsr.wsr_connect.user_id
import java.io.File

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
        sendButton.onAction = EventHandler { send_simple_message() }
        attachButton.onAction = EventHandler { send_attachment() }

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
        for ((id, message) in cash_source.cashed_messages){
            if (user_id != message.creator_id) {
                if (message.parent_message != null) {   // forwarded
                    msg = MessageForwardedController(message.message_id, cash_source)
                } else if (message.img_url == null) {    // simple
                    msg = MessageSimpleController(message.message_id, cash_source)
                } else {                              // with image
                    msg = MessageAttachmentController(message.message_id, cash_source)
                }
            } else {
                if (message.parent_message != null) {   // forwarded
                    msg = MessageForwardedMineController(message.message_id, cash_source)
                } else if (message.img_url == null) {    // simple
                    msg = MessageSimpleMineController(message.message_id, cash_source)
                } else {                              // with image
                    msg = MessageAttachmentMineController(message.message_id, cash_source)
                }
            }
            messageScope.children.add(msg)
        }
        cash_source.cashed_messages
    }

    fun send_simple_message(){
        if (input.text != "" && input.text != "" && current_chat_id != null){
            input.text = ""
            APIObject.postSimpleChatMessage(SimpleChatMessageRequest(current_chat_id!!, input.text)) {
            }
        }
    }

    fun send_attachment(){
        var path_to_image: File? = null
        val fileChooser = FileChooser()
        val imageFilter = FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.bmp")
        fileChooser.extensionFilters.add(imageFilter)
        path_to_image = fileChooser.showOpenDialog(Stage())
    }
}