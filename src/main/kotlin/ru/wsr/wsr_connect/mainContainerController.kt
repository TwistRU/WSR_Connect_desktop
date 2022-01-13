package ru.wsr.wsr_connect

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.layout.BorderPane
import ru.wsr.wsr_connect.chatComponents.ChatScreenController

class MainContainer : BorderPane() {

    @FXML
    private lateinit var resources: ResourceBundle

    @FXML
    private lateinit var location: URL

    @FXML
    fun initialize() {
        this.center = MainScreenController()

    }
    init {
        val fxmlLoader = FXMLLoader(javaClass.getResource("mainContainer.fxml"))
        fxmlLoader.setRoot(this)
        fxmlLoader.setController(this)
        fxmlLoader.load<Any>()
    }
}
