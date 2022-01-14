package ru.wsr.wsr_connect

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.layout.BorderPane
import ru.wsr.wsr_connect.chatComponents.ChatScreenController
import ru.wsr.wsr_connect.signComponents.LoginScreenController
import ru.wsr.wsr_connect.signComponents.SignupScreenController

class MainContainer : BorderPane() {

    @FXML
    private lateinit var resources: ResourceBundle

    @FXML
    private lateinit var location: URL

    @FXML
    fun initialize() {

        val login_screen = LoginScreenController()
        val signup_screen = SignupScreenController()
        val main_screen = MainScreenController()


        this.center = login_screen

        login_screen.signInButton.setOnAction { e -> this.center = main_screen }
        login_screen.signUpButton.setOnAction { e -> this.center = signup_screen }
        signup_screen.backButton.setOnAction { e -> this.center = login_screen }
        main_screen.avatarImage.setOnMouseClicked { e ->
            this.center = login_screen
            main_screen.center = null
        }



    }

    init {
        val fxmlLoader = FXMLLoader(javaClass.getResource("mainContainer.fxml"))
        fxmlLoader.setRoot(this)
        fxmlLoader.setController(this)
        fxmlLoader.load<Any>()
    }



}
