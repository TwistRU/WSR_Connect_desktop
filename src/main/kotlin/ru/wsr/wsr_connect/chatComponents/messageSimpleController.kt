package ru.wsr.wsr_connect.chatComponents

import javafx.event.EventHandler
import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.text.Text

class MessageSimpleController(username: String, msgText: String?, mine: Boolean? = false, msg_id: Int) : HBox() {


    @FXML
    private lateinit var resources: ResourceBundle

    @FXML
    private lateinit var location: URL

    @FXML
    private lateinit var avatarImage: ImageView

    @FXML
    private lateinit var messageContainer: HBox

    @FXML
    private lateinit var messageText: Text

    @FXML
    private lateinit var root: HBox

    @FXML
    private lateinit var time: Text

    @FXML
    private lateinit var username: Text


    var myMessage: Boolean? = false
    var message_id = -1

    @FXML
    fun initialize() {

    }


    init {
        val fxmlLoader = FXMLLoader(javaClass.getResource("messageSimple.fxml"))
        fxmlLoader.setRoot(this)
        fxmlLoader.setController(this)
        fxmlLoader.load<Any>()

        this.username.text = username
        this.messageText.text = msgText
        this.myMessage = mine
        this.message_id = msg_id
//        if (mine == true){
//        }
    }



}
