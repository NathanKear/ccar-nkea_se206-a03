package VoxspellPrototype;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LevelSelectionScreen extends Parent {

	private Window _window;
	private List<Button> _btnLevels;	
		
	private final int BUTTON_SEPERATION = 6;
	private final int SELECTION_BAR_PADDING = 10;
	private final double SELECTIONBAR_SCREENWIDTH_RATIO = 0.333;	
	private final double BUTTONS_PER_SCREEN = 8;
	private final double BUTTON_HEIGHT;
	private final double BUTTON_WIDTH;
	private final double SCROLL_EDGE_SIZE = 100;

	public LevelSelectionScreen(Window window) {
		super();

		this._window = window;
		
		HBox root = new HBox();
		
		_btnLevels = new ArrayList<Button>();
		BUTTON_HEIGHT = _window.GetHeight() / BUTTONS_PER_SCREEN;
		BUTTON_WIDTH = _window.GetWidth() * SELECTIONBAR_SCREENWIDTH_RATIO;

		// Add a button for each spelling list
		for (String listName : WordList.GetWordList().keySet()) {
			Button btn = new Button(listName);
			btn.setPrefWidth(BUTTON_WIDTH);
 			btn.setPrefHeight(BUTTON_HEIGHT);
			
			_btnLevels.add(btn);
		}

		// Create vbox, add all level buttons
		VBox levelButtons = new VBox(BUTTON_SEPERATION);
		levelButtons.getChildren().addAll(_btnLevels);
		levelButtons.setPrefWidth(_window.GetWidth());
		levelButtons.setAlignment(Pos.CENTER);
		
		Timeline tl = new Timeline(new KeyFrame(Duration.millis(100), _scrollTimer));

		this.getChildren().add(levelButtons);
	}
	
	private EventHandler<ActionEvent> _scrollTimer = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
			Point mousePoint = MouseInfo.getPointerInfo().getLocation();
			Rectangle windowBounds = _window.GetBounds();
			
			if (windowBounds.contains(mousePoint)) {
				Point relMousePoint = new Point();
			}
		}	
	};
}
