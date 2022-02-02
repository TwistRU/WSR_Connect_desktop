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
import ru.wsr.wsr_connect.profileComponents.ProfileScreenController
import ru.wsr.wsr_connect.tasksComponents.TablesScreenController
import java.net.URL
import java.util.*


class MainScreenController : BorderPane() {


    @FXML
    private lateinit var resources: ResourceBundle

    @FXML
    private lateinit var location: URL

    @FXML
    private lateinit var avatarButton: Button

    @FXML
    lateinit var avatarImage: ImageView

    @FXML
    private lateinit var calendarButton: Button

    @FXML
    private lateinit var gearButton: Button

    @FXML
    private lateinit var messagesButton: Button

    @FXML
    private lateinit var sideBar: VBox



    @FXML
    fun initialize() {

        makeButtons()
        makeAvatar()

        this.center = null

        this.messagesButton.setOnAction { e -> messenger() }
        this.calendarButton.setOnAction { e -> taskmanager() }
        this.avatarButton.setOnAction { e -> profile() }
        this.gearButton.setOnAction { e -> settings() }

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



    private fun messenger(){
        val chat_screen = ChatScreenController()
        this.center = chat_screen
    }

    private fun taskmanager(){
        val taskmanager_screen = TablesScreenController()
        this.center = taskmanager_screen
    }

    private fun profile(){
        val profile_screen = ProfileScreenController()
        this.center = profile_screen
    }

    private fun settings(){
        this.center = null
    }

}
