package ru.wsr.wsr_connect.chatComponents


import javafx.event.EventHandler
import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Label
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.shape.Circle

class ChatSearchUser : HBox() {

    @FXML
    private lateinit var resources: ResourceBundle

    @FXML
    private lateinit var location: URL

    @FXML
    lateinit var avatarImage: ImageView

    @FXML
    private lateinit var root: HBox

    @FXML
    lateinit var username: Label

    @FXML
    fun initialize() {
        makeAvatar()
    }

    init {
        val fxmlLoader = FXMLLoader(javaClass.getResource("chatSearchUser.fxml"))
        fxmlLoader.setRoot(this)
        fxmlLoader.setController(this)
        fxmlLoader.load<Any>()
    }

    private fun makeAvatar(){
        val clip = Circle(25.0, 25.0, 20.0)
        avatarImage.clip = clip
    }

    fun get_chat(scope: ChatScreenController){
//        scope.
    }

}
