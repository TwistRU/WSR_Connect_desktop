package ru.wsr.wsr_connect.chatComponents

import javafx.scene.layout.Region
import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Button
import javafx.scene.control.ScrollPane
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Text

class ChatMessagesWindowCOntroller : ScrollPane() {


    @FXML
    private lateinit var resources: ResourceBundle

    @FXML
    private lateinit var location: URL

    @FXML
    private lateinit var attachButton: Button

    @FXML
    private lateinit var sendButton: Button

    @FXML
    private lateinit var messageScope: VBox


    val mockup_messages = arrayListOf(
        MessageSimpleController("Tovarish Stalin", "Hello world"),
        MessageSimpleController("Adolf Gitler", "Bla bla bla", "Tovarish Stalin", "Hello world"),
        MessageSimpleController("Umpa lumpa", "ololololo"),
        MessageAttachmentController(),
        MessageAttachmentController()
    )

    @FXML
    fun initialize() {
        makeChatButtons()

//        mockup_messages[0].messageText = Text("Hello, world")
//        mockup_messages[0].senderName = Text("Ivan")
////        mockup_messages[0].messageBox.background = Background(BackgroundFill(Color.valueOf("#BBDEFB"), null, null))
//
//        mockup_messages[1].messageText = Text("Ras dva tri afj asdf")
//        mockup_messages[1].senderName = Text("Anonim")
////        mockup_messages[1].messageBox.background = Background(BackgroundFill(Color.valueOf("#BBDEFB"), null, null))
//
//        mockup_messages[2].messageText = Text("Kak dela bla bla bla")
//        mockup_messages[2].children.remove(mockup_messages[2].avatarImage)
//        mockup_messages[2].senderName = Text("John")
//        mockup_messages[2].messageBox.background = Background(BackgroundFill(Color.valueOf("#BBDEFB"), null, null))


        for (msg in mockup_messages){
            messageScope.children.add(msg)
        }

    }

    init {
        val fxmlLoader = FXMLLoader(javaClass.getResource("chatMessagesWindow.fxml"))
        fxmlLoader.setRoot(this)
        fxmlLoader.setController(this)
        fxmlLoader.load<Any>()
    }

    private fun makeChatButtons(){
        val attach = Region()
        attach.styleClass.add("attach")
        attachButton.graphic = attach


        val send = Region()
        send.styleClass.add("send")
        sendButton.graphic = send
    }
}