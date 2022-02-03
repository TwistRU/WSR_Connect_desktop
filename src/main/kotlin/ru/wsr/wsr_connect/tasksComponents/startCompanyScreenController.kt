package ru.wsr.wsr_connect.tasksComponents

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Button
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Pane
import javafx.scene.text.TextFlow
import java.net.URL
import java.util.*

class StartCompanyScreenController : BorderPane() {

    @FXML
    fun initialize() {

    }
    init{
        val fxmlLoader = FXMLLoader(javaClass.getResource("startCompanyScreen.fxml"))
        fxmlLoader.setRoot(this)
        fxmlLoader.setController(this)
        fxmlLoader.load<Any>()
    }
}
