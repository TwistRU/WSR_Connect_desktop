package ru.wsr.wsr_connect.chatComponents

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.image.ImageView
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.HBox
import javafx.scene.paint.Paint
import javafx.scene.text.Text
import java.awt.Color
import javax.swing.GroupLayout

class MessageForwardedController(username: String, msgText: String, frwMsgUsername: String = "", frwMsgText: String = "",
                              mine: Boolean = false) : HBox() {

    @FXML
    private lateinit var resources: ResourceBundle

    @FXML
    private lateinit var location: URL

    @FXML
    private lateinit var root: HBox

    @FXML
    private lateinit var avatarImage: ImageView

    @FXML
    private lateinit var forwardedMessageText: Text

    @FXML
    private lateinit var forwardedMessageUsername: Text

    @FXML
    private lateinit var frwMsgBox: HBox

    @FXML
    private lateinit var messageContainer: HBox

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
        val fxmlLoader = FXMLLoader(javaClass.getResource("messageForwarded.fxml"))
        fxmlLoader.setRoot(this)
        fxmlLoader.setController(this)
        fxmlLoader.load<Any>()

        this.username.text = username
        this.messageText.text = msgText
        if (frwMsgUsername == "" || frwMsgText == ""){  // удалить пересланное сообщение - не работает

        }
        else {
            this.forwardedMessageUsername.text = frwMsgUsername
            this.forwardedMessageText.text = frwMsgText
        }
    }

}
