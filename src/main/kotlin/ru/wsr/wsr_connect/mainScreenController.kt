package ru.wsr.wsr_connect

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Button
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Region
import javafx.scene.layout.VBox
import javafx.scene.shape.Circle
import ru.wsr.wsr_connect.chatComponents.ChatMessagesWindow
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
    lateinit var avatarImage: ImageView

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


//    var cards = arrayOf(
//        ChatSearchUser(),
//        ChatSearchUser(),
//        ChatSearchUser(),
//        ChatSearchUser()
//    )


    @FXML
    fun initialize() {

        makeButtons()
        makeAvatar()

        this.componentsRoot.left = null

        this.messagesButton.setOnAction { e -> messages() }
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



    private fun messages() {
        val chat_list = ChatScreenController()
        val chat_window = ChatMessagesWindow()
        this.componentsRoot.left = chat_list
        this.componentsRoot.center = chat_window
    }

    private fun taskmanager(){
        val taskmanager_screen = TablesScreenController()
        this.componentsRoot.left = taskmanager_screen
    }

    private fun profile(){
        val profile_screen = ProfileScreenController()
        this.componentsRoot.left = profile_screen
    }

    private fun settings(){
        this.componentsRoot.left = null
    }
}
