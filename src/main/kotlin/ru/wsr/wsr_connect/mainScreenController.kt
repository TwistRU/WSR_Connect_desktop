package ru.wsr.wsr_connect

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Button
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Region
import javafx.scene.layout.VBox
import javafx.scene.shape.Circle
import ru.wsr.wsr_connect.chatComponents.ChatScreenController
import java.net.URL
import java.util.*


class MainScreenController : BorderPane() {

    @FXML
    private lateinit var resources: ResourceBundle

    @FXML
    private lateinit var location: URL

    @FXML
    private lateinit var avatarImage: ImageView

    @FXML
    private lateinit var avatarButton: Button

    @FXML
    private lateinit var calendarButton: Button

    @FXML
    private lateinit var gearButton: Button

    @FXML
    private lateinit var componentsRoot: BorderPane

    @FXML
    private lateinit var globalBox: BorderPane

    @FXML
    private lateinit var messagesButton: Button

    @FXML
    private lateinit var sideBar: VBox

    @FXML
    fun initialize() {

        makeButtons()
        makeAvatar()
        this.componentsRoot.left = ChatScreenController()

    }

    init {
        val loader = FXMLLoader(javaClass.getResource("mainScreen.fxml"))
        loader.setController(this)
        loader.setRoot(this)
        loader.load<Any>()
    }


    private fun makeButtons(){
        val messages = Region()
        messages.styleClass.add("messages")
        messagesButton.graphic = messages

        val calendar = Region()
        calendar.styleClass.add("calendar")
        calendarButton.graphic = calendar

        val avatar = Region()
        avatar.styleClass.add("avatar")
        avatarButton.graphic = avatar

        val gear = Region()
        gear.styleClass.add("gear")
        gearButton.graphic = gear
    }

    private fun makeAvatar(){
        val clip = Circle(35.0, 35.0, 30.0)
        avatarImage.clip = clip
    }

}
