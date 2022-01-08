package ru.wsr.wsr_connect.signComponents

import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.scene.transform.Scale
import javafx.stage.Screen
import java.net.URL
import java.util.*
import java.awt.*

class signupScreenController {

    @FXML
    private lateinit var resources: ResourceBundle

    @FXML
    private lateinit var location: URL

    @FXML
    private lateinit var anchorPane: AnchorPane
    public var anchorPaneH = 1920.0
    public var anchorPaneW = 1080.0

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
    fun initialize() {


        val screenWidth = Screen.getPrimary().getBounds().getWidth() as Double
        val screenHeight = Screen.getPrimary().getBounds().getHeight() as Double


        val scale = Scale()
        scale.setPivotX(0.0)
        scale.setPivotY(0.0)
        scale.x = screenWidth * 0.75 / 1920.0
        scale.y = screenHeight * 0.75 / 1080.0
        anchorPane.transforms.add(scale)





    }

}
