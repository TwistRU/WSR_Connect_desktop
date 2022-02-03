package ru.wsr.wsr_connect.signComponents

import javafx.event.EventHandler
import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Button
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import javafx.scene.text.Text
import ru.wsr.wsr_connect.APIObject
import ru.wsr.wsr_connect.MainContainer
import ru.wsr.wsr_connect.SignInRequest
import ru.wsr.wsr_connect.SignUpRequest

class SignupScreenController : StackPane() {

    @FXML
    private lateinit var resources: ResourceBundle

    @FXML
    private lateinit var location: URL

    @FXML
    private lateinit var backButton: Button

    @FXML
    private lateinit var background: ImageView

    @FXML
    private lateinit var boxBox: HBox

    @FXML
    private lateinit var confirmPasswordField: PasswordField

    @FXML
    private lateinit var errorStatus: Text

    @FXML
    private lateinit var firstNameField: TextField

    @FXML
    private lateinit var lastNameField: TextField

    @FXML
    private lateinit var emailField: TextField

    @FXML
    private lateinit var passwordField: PasswordField

    @FXML
    private lateinit var signUpButton: Button

    @FXML
    private lateinit var stackPane: StackPane

    @FXML
    private lateinit var textFieldsBox: VBox

    @FXML
    private lateinit var usernameField: TextField


    var Parent: MainContainer? = null

    @FXML
    fun initialize() {

//        background.fitWidthProperty().bind()
        this.boxBox.scaleX = 0.7
        this.boxBox.scaleY = 0.7


        background.fitHeight = 720.0
        background.fitWidth = 1280.0


        signUpButton.onAction = EventHandler { register(Parent!!) }
        backButton.onAction = EventHandler { switch_to_login(Parent!!) }

    }
    init {
        val fxmlLoader = FXMLLoader(javaClass.getResource("signUpScreen.fxml"))
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


    fun register(scope: MainContainer){
        if (passwordField.text != confirmPasswordField.text){
            errorStatus.text = "Passwords don't match"
        } else if (passwordField.text == ""){
            errorStatus.text = "Password field is empty"
        } else {
            APIObject.registration(
                SignUpRequest(
                    usernameField.text,
                    emailField.text,
                    firstNameField.text,
                    lastNameField.text,
                    passwordField.text
                )
            ) {
                if (it.success) {
                    scope.create_screens()
                    scope.center = scope.main_screen

                } else {
                    errorStatus.text = it.errors[0]
                    println(it.errors)
                }
            }
        }
    }

    fun switch_to_login(scope: MainContainer){
        scope.center = scope.login_screen
    }


}
