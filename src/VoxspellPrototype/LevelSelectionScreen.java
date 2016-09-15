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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LevelSelectionScreen extends Parent {

	private Window _window;
	private List<Button> _btnLevels;	
		
	private final String TXT_SELECT_LEVEL = "\nPlease select starting level\n";
	private final int BUTTON_SEPERATION = 6;
	private final int SELECTION_BAR_PADDING = 10;
	private final double SELECTIONBAR_SCREENWIDTH_RATIO = 0.666;	
	private final double BUTTONS_PER_SCREEN = 6;
	private final double BTN_HEIGHT;
	private final double BTN_WIDTH;
	private final double SCROLL_EDGE_SIZE = 200;
	private final double SCROLL_SENSITIVITY = 400;
	private final int TXT_FONT_SIZE = VoxspellPrototype.TXT_FONT_SIZE;
	private final int BTN_FONT_SIZE = VoxspellPrototype.BTN_FONT_SIZE;
	private final String BTN_COLOR = VoxspellPrototype.DARK_BLUE;
	private final String BACK_COLOR = VoxspellPrototype.LIGHT_BLUE;
	private final String BTN_FONT_COLOR = VoxspellPrototype.WHITE;
	private final String TXT_FONT_COLOR = VoxspellPrototype.WHITE;
	private final double TMR_TICK_RATE = 60.0;
	
	private VBox _levelButtons;
	private double _scrollPosition = 0;
	private Timeline _timeline;

	public LevelSelectionScreen(Window window) {
		super();

		this._window = window;
		
		// Create vbox, add all level buttons
		VBox root = new VBox(BUTTON_SEPERATION);
		
		// Set root node size
		root.setPrefWidth(_window.GetWidth());

		_btnLevels = new ArrayList<Button>();
		BTN_HEIGHT = _window.GetHeight() / BUTTONS_PER_SCREEN;
		BTN_WIDTH = _window.GetWidth() * SELECTIONBAR_SCREENWIDTH_RATIO;
		
		Text txtSelection = new Text(TXT_SELECT_LEVEL);
		txtSelection.setStyle("-fx-font: " + TXT_FONT_SIZE + " arial;" +
				" -fx-fill: " + TXT_FONT_COLOR + ";");
		root.getChildren().add(txtSelection);
		
		// Add a button for each spelling list
		for (String listName : WordList.GetWordList().keySet()) {
			Button btn = new Button(listName);
			btn.setPrefWidth(BTN_WIDTH);
 			btn.setPrefHeight(BTN_HEIGHT);
 			btn.setStyle("-fx-font: " + BTN_FONT_SIZE + " arial;" + 
 					" -fx-base: " + BTN_COLOR + ";" + 
 					" -fx-text-fill: " + BTN_FONT_COLOR + ";");
 			
 			btn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					if (e.getSource() instanceof Button) {
						String btnName = ((Button)e.getSource()).getText();
						_window.SetWindowScene(new Scene(new QuizScreen(_window, btnName), _window.GetWidth(), _window.GetHeight()));
					}
				}
 			});
			
			_btnLevels.add(btn);
		}

		root.getChildren().addAll(_btnLevels);
		root.setPrefWidth(_window.GetWidth());
		root.setAlignment(Pos.CENTER);
		root.setTranslateY(_scrollPosition);
		
		// Add padding around vbox (so buttons don't touch screen edge)
		root.setPadding(new Insets(SELECTION_BAR_PADDING));
		
		_timeline = new Timeline(new KeyFrame(Duration.millis(1000 / TMR_TICK_RATE), _scrollTimer));
		_timeline.setCycleCount(Timeline.INDEFINITE);		
		_timeline.play();
		
		this.getChildren().add(root);
		
		this._levelButtons = root;		
		
		// Set root node color
		root.setStyle("-fx-background-color: " + BACK_COLOR + ";");
	}
	
	private EventHandler<ActionEvent> _scrollTimer = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e) {
			Point mousePoint = MouseInfo.getPointerInfo().getLocation();
			Rectangle windowBounds = _window.GetBounds();

			if (windowBounds.contains(mousePoint)) {
				Point relMousePoint = new Point(
						(int) (mousePoint.getY() - windowBounds.getY() - 30), 
						(int) (mousePoint.getX() - windowBounds.getX()));

				// Get distance to top or bottom of screen
				double dist = relMousePoint.getX() < (_window.GetHeight() / 2) ? 
						relMousePoint.getX() : _window.GetHeight() - relMousePoint.getX();
				dist = Math.abs(dist);		
	
				if (Math.abs(dist) < SCROLL_EDGE_SIZE) {
					double scrollSpeed = SCROLL_SENSITIVITY / dist;	
					
					if (relMousePoint.getX() < _window.GetHeight() / 2) {
						_scrollPosition = _scrollPosition + scrollSpeed;
					} else {
						_scrollPosition = _scrollPosition - scrollSpeed;
					}
					
					_scrollPosition = Math.min(0, _scrollPosition);
					_scrollPosition = Math.max(-(_levelButtons.getHeight() - _window.GetHeight()), _scrollPosition);
					_levelButtons.setTranslateY(_scrollPosition);
				}
		
			}
		}	
	};
}
