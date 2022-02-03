package ru.wsr.wsr_connect.chatComponents

import javafx.event.EventHandler
import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.text.Text

class MessageSimpleMineController(msg_id: Int, cash_source: ChatSearchCard) : HBox() {


    @FXML
    private lateinit var resources: ResourceBundle

    @FXML
    private lateinit var location: URL

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




    @FXML
    fun initialize() {

    }


    init {
        val fxmlLoader = FXMLLoader(javaClass.getResource("messageSimpleMine.fxml"))
        fxmlLoader.setRoot(this)
        fxmlLoader.setController(this)
        fxmlLoader.load<Any>()


        this.username.text = cash_source.cashed_messages[msg_id]?.creator_name ?: "Noname"
        this.messageText.text = cash_source.cashed_messages[msg_id]?.message_body ?: "Unknown text"

        var t = cash_source.cashed_messages[msg_id]?.created_at
        this.time.text = t!!.substring(11, 16)


//        this.username.text = username
//        this.messageText.text = msgText
//        this.message_id = msg_id
//        this.time.text = time
//        if (mine == true){
//        }
    }



}
