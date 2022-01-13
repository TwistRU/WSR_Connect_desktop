package ru.wsr.wsr_connect.profileComponents

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Button
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import javafx.scene.text.Text
import java.net.URL
import java.util.*

class ProfileScreenController : BorderPane() {
    @FXML
    private val resources: ResourceBundle? = null

    @FXML
    private val location: URL? = null

    @FXML
    private val profileChangePassField: PasswordField? = null

    @FXML
    private val profileSavePassButton: Button? = null

    @FXML
    private val profileSaveEmailButton: Button? = null

    @FXML
    private val profileNickname: Text? = null

    @FXML
    private val profileChangeNameField: TextField? = null

    @FXML
    private val profileChangeEmailField: TextField? = null

    @FXML
    private val profileSaveNameButton: Button? = null

    @FXML
    private val profileImage: ImageView? = null

    @FXML
    fun initialize() {
    }

    init {
        val fxmlLoader = FXMLLoader(javaClass.getResource("profileScreen.fxml"))
        fxmlLoader.setRoot(this)
        fxmlLoader.setController(this)
        fxmlLoader.load<Any>()
    }
}