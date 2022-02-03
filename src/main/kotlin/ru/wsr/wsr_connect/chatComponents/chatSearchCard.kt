package ru.wsr.wsr_connect.chatComponents


import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Label
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.shape.Circle
import ru.wsr.wsr_connect.Message

class ChatSearchCard : HBox() {

    @FXML
    private lateinit var resources: ResourceBundle

    @FXML
    private lateinit var location: URL

    @FXML
    lateinit var avatarImage: ImageView

    @FXML
    private lateinit var root: HBox

    @FXML
    lateinit var chatLastMessage: Label

    @FXML
    lateinit var chatName: Label



    @FXML
    fun initialize() {
        makeAvatar()
    }

    init {
        val fxmlLoader = FXMLLoader(javaClass.getResource("chatSearchCard.fxml"))
        fxmlLoader.setRoot(this)
        fxmlLoader.setController(this)
        fxmlLoader.load<Any>()
    }

    private fun makeAvatar(){
        val clip = Circle(30.0, 30.0, 25.0)
        avatarImage.clip = clip
    }

}
