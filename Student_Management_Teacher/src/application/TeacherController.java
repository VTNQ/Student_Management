package application;

import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.event.ActionEvent;

public class TeacherController {
	@FXML
	ContextMenu contextMenu=new ContextMenu();
	void show_list(ActionEvent event) {
		if(ContextMenu.class.isHidden()==true) {
			//contextMenu.show(MenuItem.class);
		}
    }
}
