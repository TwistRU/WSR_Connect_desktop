package ru.wsr.wsr_connect.signComponents

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
import ru.wsr.wsr_connect.mainScreenController

class loginScreenController : StackPane() {

    @FXML
    private lateinit var resources: ResourceBundle

    @FXML
    private lateinit var location: URL

    @FXML
    private lateinit var background: ImageView

    @FXML
    private lateinit var boxBox: VBox

    @FXML
    private lateinit var emailField: TextField

    @FXML
    private lateinit var passwordField: PasswordField

    @FXML
    private lateinit var signInButton: Button

    @FXML
    private lateinit var signUpButton: Button

    @FXML
    private lateinit var textFieldsBox: VBox

    @FXML
    fun initialize() {

//        background.fitWidthProperty().bind()
        this.boxBox.scaleX = 0.7
        this.boxBox.scaleY = 0.7   // Рома, не бей

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

}
