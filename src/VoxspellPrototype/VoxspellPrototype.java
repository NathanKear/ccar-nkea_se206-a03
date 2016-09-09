package VoxspellPrototype;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
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
		initialiseNathansAwesomeDataStructure("NZCER-spelling-lists.txt");
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
		
		return menuButtons;
	}
	
	private HashMap<String, HashMap<String, int[]>> initialiseNathansAwesomeDataStructure(String fileName) {
		File wordList = new File(fileName);

		String line;
		int lvlCounter = 1;
		String levelKey = "";
		
		HashMap<String, HashMap<String, int[]>> nathansAwesomeDataStructure = new HashMap<String, HashMap<String, int[]>>();
		HashMap<String, int[]> levelHashMap = new HashMap<String, int[]>();
		
		try {
			BufferedReader textFileReader = new BufferedReader(new FileReader(wordList));

			while((line = textFileReader.readLine()) != null) {
	
				if(line.charAt(0) == '%') {
					levelKey = "level " + lvlCounter;
					lvlCounter++;
					levelHashMap = new HashMap<String, int[]>();

				} else {
					levelHashMap.put(line, new int[3]);
					nathansAwesomeDataStructure.put(levelKey, levelHashMap);
				}

			}
			textFileReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return nathansAwesomeDataStructure;
	}
}
