package ru.wsr.wsr_connect.chatComponents

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.text.Text


class MessageAttachmentController: HBox() {

    @FXML
    private lateinit var resources: ResourceBundle

    @FXML
    private lateinit var location: URL

    @FXML
    private lateinit var avatarImage: ImageView

    @FXML
    private lateinit var fileSize: Text

    @FXML
    private lateinit var filenName: Text

    @FXML
    private lateinit var previewImage: ImageView

    @FXML
    private lateinit var time: Text

    @FXML
    fun initialize() {
    }

    init {
        val fxmlLoader = FXMLLoader(javaClass.getResource("messageAttachment.fxml"))
        fxmlLoader.setRoot(this)
        fxmlLoader.setController(this)
        fxmlLoader.load<Any>()
    }

}
