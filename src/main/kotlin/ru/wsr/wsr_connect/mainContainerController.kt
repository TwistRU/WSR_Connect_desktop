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
        signup_screen = SignupScreenController()



        switch_to_login_screen()



        login_screen!!.signInButton.setOnAction { e -> authorise()}
        login_screen!!.signUpButton.setOnAction { e -> switch_to_signup_screen() }
        signup_screen!!.backButton.setOnAction { e -> switch_to_login_screen() }

    }


    init {
        val fxmlLoader = FXMLLoader(javaClass.getResource("mainContainer.fxml"))
        fxmlLoader.setRoot(this)
        fxmlLoader.setController(this)
        fxmlLoader.load<Any>()
    }



    fun create_screens(){
        main_screen = MainScreenController()
    }

    fun purge_data(){
        main_screen = null
    }

    fun switch_to_login_screen(){
        this.center = login_screen
    }

    fun switch_to_signup_screen(){
        this.center = signup_screen
    }

    fun authorise(){
        create_screens()
        this.center = main_screen
        main_screen!!.avatarImage.setOnMouseClicked { e ->
            this.center = login_screen
            main_screen!!.center = null
        }
    }

    fun register(){}

    fun logout(){
        purge_data()
        switch_to_login_screen()
    }




}
