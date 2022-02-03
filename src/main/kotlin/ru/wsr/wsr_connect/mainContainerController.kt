package ru.wsr.wsr_connect

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.layout.BorderPane
import ru.wsr.wsr_connect.signComponents.LoginScreenController
import ru.wsr.wsr_connect.signComponents.SignupScreenController

class MainContainer : BorderPane() {

    @FXML
    private lateinit var resources: ResourceBundle

    @FXML
    private lateinit var location: URL


    var login_screen: LoginScreenController? = null
    var signup_screen: SignupScreenController? = null
    var main_screen: MainScreenController? = null

    @FXML
    fun initialize() {

        login_screen = LoginScreenController()
        login_screen!!.Parent = this
        signup_screen = SignupScreenController()
        signup_screen!!.Parent = this

        this.center = login_screen



    }


    init {
        val fxmlLoader = FXMLLoader(javaClass.getResource("mainContainer.fxml"))
        fxmlLoader.setRoot(this)
        fxmlLoader.setController(this)
        fxmlLoader.load<Any>()
    }



    fun create_screens(){
        main_screen = MainScreenController()
        main_screen!!.Parent = this
    }

    fun purge_data(){
        main_screen?.chat_screen = null
        main_screen?.taskmanager_screen = null
        main_screen?.profile_screen = null
        main_screen = null
    }

}
