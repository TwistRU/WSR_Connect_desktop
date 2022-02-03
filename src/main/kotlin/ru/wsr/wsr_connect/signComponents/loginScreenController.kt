package ru.wsr.wsr_connect.signComponents

import com.sun.tools.javac.Main
import javafx.event.EventHandler
import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Button
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import javafx.stage.Window
import ru.wsr.wsr_connect.MainContainer
import ru.wsr.wsr_connect.MainScreenController

class LoginScreenController : StackPane() {

    @FXML
    private lateinit var resources: ResourceBundle

    @FXML
    private lateinit var location: URL

    @FXML
    private lateinit var background: ImageView

    @FXML
    private lateinit var boxBox: VBox

    @FXML
    lateinit var emailField: TextField

    @FXML
    lateinit var passwordField: PasswordField

    @FXML
    lateinit var signInButton: Button

    @FXML
    lateinit var signUpButton: Button

    @FXML
    private lateinit var textFieldsBox: VBox


    var Parent: MainContainer? = null

    @FXML
    fun initialize() {

//        background.fitWidthProperty().bind()
        this.boxBox.scaleX = 0.7
        this.boxBox.scaleY = 0.7


        background.fitHeight = 720.0
        background.fitWidth = 1280.0

        //TODO Исправить: функциональные поля выходт за нижнюю границу

        signInButton.onAction = EventHandler { authorise(Parent!!) }
        signUpButton.onAction = EventHandler { switch_to_signup(Parent!!) }



    }
    init {
        val fxmlLoader = FXMLLoader(javaClass.getResource("loginScreen.fxml"))
        fxmlLoader.setRoot(this)
        fxmlLoader.setController(this)
        fxmlLoader.load<Any>()
    }

    fun changeSize(){
        this.scene.widthProperty().addListener { _, old, new ->
            if (this.scene.height / new as Double <= 0.5625){
                background.fitHeight = new / 0.5625
                background.fitWidth = new
            }
        }
        this.scene.heightProperty().addListener { _, old, new ->
            if (new as Double / this.scene.width > 0.5625) {
                background.fitWidth = new / 0.5625
                background.fitHeight = new
            }
        }
    }

    fun authorise(scope: MainContainer){
        scope.create_screens()
        scope.center = scope.main_screen
    }

    fun switch_to_signup(scope: MainContainer){
        scope.center = scope.signup_screen
    }

}
