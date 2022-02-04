package ru.wsr.wsr_connect

import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Region
import javafx.scene.shape.Circle
import ru.wsr.wsr_connect.chatComponents.ChatScreenController
import ru.wsr.wsr_connect.profileComponents.ProfileScreenController
import ru.wsr.wsr_connect.tasksComponents.StartCompanyScreenController
import ru.wsr.wsr_connect.tasksComponents.TablesScreenController
import java.net.URL
import java.util.*


var user_id = -1

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


    var Parent: MainContainer? = null



    var chat_screen: ChatScreenController? = null
    var taskmanager_screen: TablesScreenController? = null
    var profile_screen: ProfileScreenController? = null

    @FXML
    fun initialize() {

        makeButtons()
        makeAvatar()

        this.center = null

        this.avatarImage.onMouseClicked = EventHandler { e ->
            logout(Parent!!)
        }

        this.messagesButton.setOnAction { e -> messenger() }
        this.calendarButton.setOnAction { e -> taskmanager() }
        this.avatarButton.setOnAction { e -> profile() }
        this.gearButton.setOnAction { e -> settings() }


        fetch_userinfo()

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
        chat_screen = ChatScreenController()
        this.center = chat_screen
    }

    private fun taskmanager(){
        taskmanager_screen = StartCompanyScreenController()

        this.center = taskmanager_screen
    }

    private fun profile(){
        profile_screen = ProfileScreenController()
        this.center = profile_screen
    }

    private fun settings(){
        this.center = null
    }




    fun logout(scope: MainContainer){
        scope.purge_data()
        scope.center = scope.login_screen
    }

    fun fetch_userinfo(){
        APIObject.profileInfo {
            user_id = it.user_id
            if (it.img_url != null){
                val path = it.img_url
                APIObject.getFile(path) {
                    println(it.path)
                    avatarImage.image = Image(it.path)
                }
            }
        }
    }

}
