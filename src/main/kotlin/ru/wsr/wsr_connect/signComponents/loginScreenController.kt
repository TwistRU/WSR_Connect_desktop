package ru.wsr.wsr_connect.signComponents

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.FXML
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox

class loginScreenController {

    @FXML
    private lateinit var resources: ResourceBundle

    @FXML
    private lateinit var location: URL

    @FXML
    private lateinit var anchorPane: AnchorPane

    @FXML
    private lateinit var boxBox: VBox

    @FXML
    private lateinit var pane: Pane

    @FXML
    private lateinit var textFieldsBox: VBox

    @FXML
    fun initialize() {
        assert(anchorPane != null) {"fx:id=\"anchorPane\" was not injected: check your FXML file 'loginScreen.fxml'." }
        assert(boxBox != null) {"fx:id=\"boxBox\" was not injected: check your FXML file 'loginScreen.fxml'." }
        assert(pane != null) {"fx:id=\"pane\" was not injected: check your FXML file 'loginScreen.fxml'." }
        assert(textFieldsBox != null) {"fx:id=\"textFieldsBox\" was not injected: check your FXML file 'loginScreen.fxml'." }

    }

}
