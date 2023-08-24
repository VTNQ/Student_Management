package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
public class SampleController {
	   @FXML
	    private Button Loginac;

	    @FXML
	    private Label RegisterAc;

	    @FXML
	    void CloseAction(MouseEvent event) {
	    	Stage stage=(Stage)Loginac.getScene().getWindow();
	    	stage.close();
	    }
}
