package ru.wsr.wsr_connect.chatComponents

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.text.Text

class MessageForwardedMineController(msg_id: Int, cash_source: ChatSearchCard) : HBox() {

    @FXML
    private lateinit var resources: ResourceBundle

    @FXML
    private lateinit var location: URL

    @FXML
    private lateinit var avatarImage: ImageView

    @FXML
    lateinit var forwardedMessageText: Text

    @FXML
    lateinit var forwardedMessageUsername: Text

    @FXML
    lateinit var frwMsgBox: HBox

    @FXML
    private lateinit var messageContainer: HBox

    @FXML
    lateinit var messageText: Text

    @FXML
    lateinit var root: HBox

    @FXML
    lateinit var time: Text

    @FXML
    lateinit var username: Text



    @FXML
    fun initialize() {

    }


    init {
        val fxmlLoader = FXMLLoader(javaClass.getResource("messageForwardedMine.fxml"))
        fxmlLoader.setRoot(this)
        fxmlLoader.setController(this)
        fxmlLoader.load<Any>()


        this.username.text = cash_source.cashed_messages[msg_id]?.creator_name ?: "Noname"
        this.messageText.text = cash_source.cashed_messages[msg_id]?.message_body ?: "Unknown text"
        this.forwardedMessageUsername.text = cash_source.cashed_messages[msg_id]?.parent_message!!.creator_name
        this.forwardedMessageText.text = cash_source.cashed_messages[msg_id]?.parent_message!!.message_body

        var t = cash_source.cashed_messages[msg_id]?.created_at
        this.time.text = t!!.substring(11, 16)


//        this.username.text = username
//        this.messageText.text = msgText
//        this.forwardedMessageUsername.text = frwMsgUsername
//        this.forwardedMessageText.text = frwMsgText
//        this.time.text = time

    }

}
