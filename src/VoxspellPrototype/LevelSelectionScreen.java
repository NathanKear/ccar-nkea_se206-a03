package VoxspellPrototype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LevelSelectionScreen extends Parent {

	private Window _window;
	private final int BUTTON_SEPERATION = 6;
	private List<Button> _btnLevels;

	public LevelSelectionScreen(Window window) {
		super();

		this._window = window;
		
		_btnLevels = new ArrayList<Button>();

		// Add a button for each spelling list
		for (String listName : WordList.GetWordList().keySet()) {
			_btnLevels.add(new Button(listName));
		}

		// Create vbox, add all level buttons
		VBox levelButtons = new VBox(BUTTON_SEPERATION);
		levelButtons.getChildren().addAll(_btnLevels);

		this.getChildren().add(levelButtons);
	}
}
