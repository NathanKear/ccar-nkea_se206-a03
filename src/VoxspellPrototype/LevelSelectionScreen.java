package VoxspellPrototype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class LevelSelectionScreen extends Parent {
	
	private final int BUTTON_SEPERATION = 6;
	private List<Button> _btnLevels;
	
	public LevelSelectionScreen(HashMap<String, HashMap<String, int[]>> wordLists) {
		super();
			
		_btnLevels = new ArrayList<Button>();
		for (String listName : wordLists.keySet()) {
			_btnLevels.add(new Button(listName));
		}
		
		VBox levelButtons = new VBox(BUTTON_SEPERATION);
		
		levelButtons.getChildren().addAll(_btnLevels);
		
		this.getChildren().add(levelButtons);
	}
}
