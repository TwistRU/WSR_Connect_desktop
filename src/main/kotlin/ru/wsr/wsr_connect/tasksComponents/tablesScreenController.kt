package ru.wsr.wsr_connect.tasksComponents

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import javafx.scene.text.Text
import java.net.URL
import java.util.*


class TablesScreenController : BorderPane() {
    @FXML
    private val resources: ResourceBundle? = null

    @FXML
    private val location: URL? = null

    @FXML
    private val tablePreview: VBox? = null

    @FXML
    private val tableImage: ImageView? = null

    @FXML
    private val companyName: Text? = null

    @FXML
    private val companyImage: ImageView? = null

    @FXML
    private val tableTitle: Text? = null

    @FXML
    private val tableStatus: Text? = null

    @FXML
    fun initialize() {

    }

    init {
        val fxmlLoader = FXMLLoader(javaClass.getResource("tablesScreen.fxml"))
        fxmlLoader.setRoot(this)
        fxmlLoader.setController(this)
        fxmlLoader.load<Any>()
    }
}
