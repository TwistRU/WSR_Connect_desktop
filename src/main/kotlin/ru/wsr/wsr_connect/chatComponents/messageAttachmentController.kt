package ru.wsr.wsr_connect.chatComponents

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.text.Text

class MessageAttachmentController(username: String, fileName: String = "IMG_653214.png", fileSize: String = "1.4 MB",
                                  mine: Boolean = false, time: String = "16.20"): HBox() {


    @FXML
    private lateinit var resources: ResourceBundle

    @FXML
    private lateinit var location: URL

    @FXML
    private lateinit var root: HBox

    @FXML
    private lateinit var username: Text

    @FXML
    lateinit var avatarImage: ImageView

    @FXML
    private lateinit var fileSize: Text

    @FXML
    private lateinit var fileName: Text

    @FXML
    lateinit var previewImage: ImageView

    @FXML
    private lateinit var time: Text

    var myMessage: Boolean? = null

    @FXML
    fun initialize() {
    }

    init {
        val fxmlLoader = FXMLLoader(javaClass.getResource("messageAttachment.fxml"))
        fxmlLoader.setRoot(this)
        fxmlLoader.setController(this)
        fxmlLoader.load<Any>()

        this.username.text = username
        this.fileName.text = fileName
        this.fileSize.text = fileSize
        this.myMessage = mine
        this.time.text = time
    }

}
