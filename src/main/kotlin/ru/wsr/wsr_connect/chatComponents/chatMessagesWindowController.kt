package ru.wsr.wsr_connect.chatComponents

import javafx.scene.layout.Region
import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Button
import javafx.scene.control.ScrollPane

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
    fun initialize() {
        makeChatButtons()

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
}