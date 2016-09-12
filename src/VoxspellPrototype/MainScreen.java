package VoxspellPrototype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class MainScreen extends Parent {

	private Window _window;
	private final int BUTTON_SEPERATION = 6; 
	private final int BUTTON_HEIGHT = 20;
	private final int MENU_BAR_WIDTH = 100;
	private String _displayText = "Hello World!";
	
	public MainScreen(Window window) {
		super();
		
		this._window = window;
		
		HBox root = new HBox(0);
		Pane menuPane = buildMenuBar(MENU_BAR_WIDTH);
		Text welcomeText = new Text(_displayText);
		
		welcomeText.setTextAlignment(TextAlignment.CENTER);
		
		//welcomeText.setWrappingWidth( 
		//		- menuPane.getPrefWidth() 
		//		- menuPane.getPadding().getLeft() 
		//		- menuPane.getPadding().getRight());
		
		root.getChildren().addAll(menuPane, welcomeText);
		
		this.getChildren().add(root);
	}
	
	private Pane buildMenuBar(int desiredWidth) {
		Button btnNew, btnReview, btnStats, btnClear, btnQuit;
		
		//double x = this._window.getHeight();
		
		VBox menuButtons = new VBox(BUTTON_SEPERATION);
		menuButtons.setPrefWidth(desiredWidth);
		menuButtons.setPrefHeight(200);

		// Create buttons
		btnNew = new Button("New Quiz");
		btnReview = new Button("Review Mistakes");
		btnStats = new Button("View Stats");
		btnClear = new Button("Clear Stats");
		btnQuit = new Button("Quit");
		
		// Set width and height of buttons
		btnNew.setMinWidth(menuButtons.getPrefWidth()); 
		btnNew.setPrefHeight(Integer.MAX_VALUE);
		
		btnReview.setMinWidth(menuButtons.getPrefWidth()); 
		btnReview.setPrefHeight(Integer.MAX_VALUE);
		
		btnStats.setMinWidth(menuButtons.getPrefWidth()); 
		btnStats.setPrefHeight(Integer.MAX_VALUE);
		
		btnClear.setMinWidth(menuButtons.getPrefWidth()); 
		btnClear.setPrefHeight(Integer.MAX_VALUE);
		
		btnQuit.setMinWidth(menuButtons.getPrefWidth()); 
		btnQuit.setPrefHeight(Integer.MAX_VALUE);
		
		// Add buttons to pane
		menuButtons.getChildren().addAll(btnNew, btnReview, btnStats, btnClear, btnQuit);
		
		btnNew.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				_window.SetWindowScene(new Scene(new LevelSelectionScreen(_window)));
			}	
		});
		
		btnReview.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				_window.SetWindowScene(new Scene(new LevelSelectionScreen(_window)));
			}	
		});
		
		return menuButtons;
	}
}
