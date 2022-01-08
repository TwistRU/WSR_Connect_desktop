package ru.wsr.wsr_connect

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.layout.HBox
import javafx.scene.layout.Region
import javafx.scene.layout.VBox
import javafx.scene.transform.Scale
import javafx.stage.Screen
import java.net.URL
import java.util.*

class mainScreenController {

    @FXML
    private lateinit var resources: ResourceBundle

    @FXML
    private lateinit var location: URL

    @FXML
    private lateinit var avatarButton: Button

    @FXML
    private lateinit var calendarButton: Button

    @FXML
    private lateinit var gearButton: Button

    @FXML
    private lateinit var globalBox: HBox

    @FXML
    private lateinit var messagesButton: Button

    @FXML
    private lateinit var sideBar: VBox

    @FXML
    fun initialize() {
        val screenWidth = Screen.getPrimary().getBounds().getWidth() as Double
        val screenHeight = Screen.getPrimary().getBounds().getHeight() as Double
        val scale = Scale()
        scale.setPivotX(0.0)
        scale.setPivotY(0.0)
        scale.x = screenWidth * 0.75 / 1920.0
        scale.y = screenHeight * 0.75 / 1080.0
        globalBox.transforms.add(scale)
//        globalBox.minWidthProperty().bind(globalBox.heightProperty())
//        globalBox.maxWidthProperty().bind(globalBox.heightProperty())


        val messages = Region()
        messages.styleClass.add("messages")
        messagesButton.setGraphic(messages)

        val calendar = Region()
        calendar.styleClass.add("calendar")
        calendarButton.setGraphic(calendar)

        val avatar = Region()
        avatar.styleClass.add("avatar")
        avatarButton.setGraphic(avatar)

        val gear = Region()
        gear.styleClass.add("gear")
        gearButton.setGraphic(gear)

    }



}
