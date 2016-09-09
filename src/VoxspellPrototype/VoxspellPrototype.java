package VoxspellPrototype;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class VoxspellPrototype extends Application {

	private Stage _window;
	
	private int _width, _height;
	
	private final String WINDOW_TITLE = "VOXSPELL(Hey Nathan! I hope this works!)";
	private final int BUTTON_SEPERATION = 6; 
	
	private String _displayText = "Hello World!";
	
	public VoxspellPrototype() {
	}

	@Override
	public void start(Stage window) throws Exception {
		this._window = window;
		_height = 200;
		_width = 500;
		
		HBox root = new HBox(0);
		Pane menuPane = buildMenuBar();
		Text welcomeText = new Text(_displayText);
		
		welcomeText.setTextAlignment(TextAlignment.CENTER);
		welcomeText.setWrappingWidth(_width 
				- menuPane.getPrefWidth() 
				- menuPane.getPadding().getLeft() 
				- menuPane.getPadding().getRight());
		root.getChildren().addAll(menuPane, welcomeText);
		
		_window.setScene(new Scene(root, _width, _height));
		
		_window.setTitle(WINDOW_TITLE);
		_window.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	private Pane buildMenuBar() {
		Button btnNew, btnReview, btnStats, btnClear, btnQuit;
		
		VBox menuButtons = new VBox(BUTTON_SEPERATION);
		
		// Create buttons
		btnNew = new Button("New Quiz");
		btnReview = new Button("Review Mistakes");
		btnStats = new Button("View Stats");
		btnClear = new Button("Clear Stats");
		btnQuit = new Button("Quit");
		
		// Set width and height of buttons
		btnNew.setMinWidth(menuButtons.getPrefWidth()); btnNew.setPrefHeight(Integer.MAX_VALUE);
		btnReview.setMinWidth(menuButtons.getPrefWidth()); btnReview.setPrefHeight(Integer.MAX_VALUE);
		btnStats.setMinWidth(menuButtons.getPrefWidth()); btnStats.setPrefHeight(Integer.MAX_VALUE);
		btnClear.setMinWidth(menuButtons.getPrefWidth()); btnClear.setPrefHeight(Integer.MAX_VALUE);
		btnQuit.setMinWidth(menuButtons.getPrefWidth()); btnQuit.setPrefHeight(Integer.MAX_VALUE);
		
		// Add buttons to pane
		menuButtons.getChildren().addAll(btnNew, btnReview, btnStats, btnClear, btnQuit);
		
		btnNew.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				_window.setScene(new Scene(new LevelSelectionScreen(wordHolder), _width, _height));
			}	
		});
		
		return menuButtons;
	}
}
