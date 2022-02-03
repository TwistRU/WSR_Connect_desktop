package ru.wsr.wsr_connect.chatComponents

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.text.Text
import ru.wsr.wsr_connect.APIObject

class MessageAttachmentController(msg_id: Int, cash_source: ChatSearchCard): HBox() {


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



    @FXML
    fun initialize() {
    }

    init {
        val fxmlLoader = FXMLLoader(javaClass.getResource("messageAttachment.fxml"))
        fxmlLoader.setRoot(this)
        fxmlLoader.setController(this)
        fxmlLoader.load<Any>()


        this.username.text = cash_source.cashed_messages[msg_id]?.creator_name ?: "Noname"
        this.fileName.text = "IMG_653214.jpg"
        this.fileSize.text = "1.4MB"

        var t = cash_source.cashed_messages[msg_id]?.created_at
        this.time.text = t!!.substring(11, 16)

        APIObject.getFile(cash_source.cashed_messages[msg_id]?.img_url!!){
            this.previewImage.image = Image(it.path)
        }

//        this.username.text = username
//        this.fileName.text = fileName
//        this.fileSize.text = fileSize
//        this.time.text = time
    }

}
