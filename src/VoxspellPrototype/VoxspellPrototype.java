package VoxspellPrototype;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javafx.application.Application;
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
	
	private HashMap<String, HashMap<String, int[]>> _wordHolder;
	
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
		
		_wordHolder = initialiseNathansAwesomeDataStructure("NZCER-spelling-lists.txt");
		
		_window.setScene(new Scene(new Statistics(_wordHolder), _width, _height));
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
		//Creating the file to read from
		File wordList = new File(fileName);

		String line;
		int lvlCounter = 1;
		String levelKey = "";
		
		//Initialising the data structures
		HashMap<String, HashMap<String, int[]>> nathansAwesomeDataStructure = new HashMap<String, HashMap<String, int[]>>();
		HashMap<String, int[]> levelHashMap = new HashMap<String, int[]>();
		
		try {
			//Creating the reader to loop through each line in the text file
			BufferedReader textFileReader = new BufferedReader(new FileReader(wordList));

			while((line = textFileReader.readLine()) != null) {
	
				//If the first char is % then its the name of the level
				if(line.charAt(0) == '%') {

					//Set the level name and increase the counter by 1
					levelKey = "level " + lvlCounter;
					lvlCounter++;
					//Create the hashmap for that level
					levelHashMap = new HashMap<String, int[]>();

				} else {

					//Hashing each word to the level hashmap
					levelHashMap.put(line, new int[3]);
					
					//Hashing the level hashmap to the overall hashmap
					nathansAwesomeDataStructure.put(levelKey, levelHashMap);
					
				}
			}

			textFileReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nathansAwesomeDataStructure.put(levelKey, levelHashMap);
		return nathansAwesomeDataStructure;
	}
}
