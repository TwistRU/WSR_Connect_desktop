package ru.wsr.wsr_connect

import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Screen
import javafx.stage.Stage
import ru.wsr.wsr_connect.signComponents.LoginScreenController
import ru.wsr.wsr_connect.signComponents.SignupScreenController


open class WSRConnectApplication : Application() {

    override fun start(stage: Stage) {
        val screenWidth = Screen.getPrimary().bounds.width
        val screenHeight = Screen.getPrimary().bounds.height
        val fxmlLoader = MainContainer()
        val scene = Scene(fxmlLoader, screenWidth * 0.75, screenHeight * 0.75)


//        (fxmlLoader.center as LoginScreenController).changeSize()



        stage.title = "WSR Connect"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(WSRConnectApplication::class.java)
}