package ru.wsr.wsr_connect.signComponents

import javafx.beans.binding.Bindings
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.scene.text.Text
import javafx.scene.transform.Scale
import javafx.stage.Screen
import java.net.URL
import java.util.*

class loginScreenController {

    fun loginScreenController(){
        print(anchorPane.scene.window.width)
    }

    @FXML
    private lateinit var resources: ResourceBundle

    @FXML
    private lateinit var location: URL

    @FXML
    private lateinit var anchorPane: AnchorPane

    @FXML
    private lateinit var boxBox: VBox

    @FXML
    private lateinit var emailField: TextField

    @FXML
    private lateinit var pane: Pane

    @FXML
    private lateinit var passwordField: PasswordField

    @FXML
    private lateinit var signInButton: Button

    @FXML
    private lateinit var signUpButton: Button

    @FXML
    private lateinit var textFieldsBox: VBox

    @FXML
    private lateinit var upperDarkWave: ImageView

    @FXML
    fun initialize() {



        val screenWidth = Screen.getPrimary().getBounds().getWidth() as Double
        val screenHeight = Screen.getPrimary().getBounds().getHeight() as Double



        val scale = Scale()
        scale.setPivotX(0.0)
        scale.setPivotY(0.0)
        scale.x = screenWidth * 0.75 / 1920.0
        scale.y = screenHeight * 0.75 / 1080.0
        anchorPane.transforms.add(scale)
        anchorPane.minWidthProperty().bind(anchorPane.heightProperty())
        anchorPane.maxWidthProperty().bind(anchorPane.heightProperty())

//        println(screenWidth)
//        println(screenHeight)
//

//        anchorPane.widthProperty().addListener {  }

    }

//    @FXML
//    fun adjustWidth(actionEvent: ActionEvent) {
////        val w = anchorPane.scene.window.width
////        println(w)
//        println(anchorPane.width)
//    }



}
