package ru.wsr.wsr_connect

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class WSRConnectApplication : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(WSRConnectApplication::class.java.getResource("mainContainer.fxml"))
        val scene = Scene(fxmlLoader.load(), 1080.0, 720.0)
        stage.title = "WSR Connect"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(WSRConnectApplication::class.java)
}