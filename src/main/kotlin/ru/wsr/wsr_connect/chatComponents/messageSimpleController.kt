package ru.wsr.wsr_connect.chatComponents

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.text.Text

class MessageSimpleController(username: String, msgText: String, frwMsgUsername: String = "", frwMsgText: String = "",
                              mine: Boolean = false) : HBox() {

    @FXML
    private lateinit var resources: ResourceBundle

    @FXML
    private lateinit var location: URL

    @FXML
    private lateinit var avatarImage: ImageView

    @FXML
    private lateinit var forwardedMessageText: Text

    @FXML
    private lateinit var forwardedMessageUsername: Text

    @FXML
    private lateinit var frwMsgBox: HBox

    @FXML
    private lateinit var messageText: Text

    @FXML
    private lateinit var username: Text

    @FXML
    private lateinit var time: Text


    var myMessage = false

    @FXML
    fun initialize() {
        if (this.forwardedMessageUsername.text == "" || this.forwardedMessageText.text == ""){
            this.frwMsgBox
        }
    }


    init {
        val fxmlLoader = FXMLLoader(javaClass.getResource("messageSimple.fxml"))
        fxmlLoader.setRoot(this)
        fxmlLoader.setController(this)
        fxmlLoader.load<Any>()

        this.username.text = username
        this.messageText.text = msgText
        if (frwMsgUsername == "" || frwMsgText == ""){
            this.children.remove(this.username)
        }
        else {
            this.forwardedMessageUsername.text = frwMsgUsername
            this.forwardedMessageText.text = frwMsgText
        }
    }

}
