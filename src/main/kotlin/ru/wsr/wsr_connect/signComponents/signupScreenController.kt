package ru.wsr.wsr_connect.signComponents

import javafx.event.EventHandler
import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.control.Button
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.image.ImageView
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import ru.wsr.wsr_connect.MainContainer

class SignupScreenController : StackPane() {

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
    lateinit var signUpButton: Button

    @FXML
    lateinit var backButton: Button

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


        signUpButton.onAction = EventHandler { authorise(Parent!!) }
        backButton.onAction = EventHandler { switch_to_login(Parent!!) }

    }
    init {
        val fxmlLoader = FXMLLoader(javaClass.getResource("signUpScreen.fxml"))
        fxmlLoader.setRoot(this)
        fxmlLoader.setController(this)
        fxmlLoader.load<Any>()
    }

    fun changeSize(){
        background.fitHeight = 720.0
        background.fitWidth = 1280.0
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

    fun switch_to_login(scope: MainContainer){
        scope.center = scope.login_screen
    }


}
