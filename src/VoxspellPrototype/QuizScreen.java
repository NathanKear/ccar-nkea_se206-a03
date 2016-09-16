package VoxspellPrototype;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class QuizScreen extends Parent {

	private Window _window;
	
	private final String BTN_SPEAK_TEXT = "Speak";
	private final String BTN_ENTER_TEXT = "Enter";
	private final int HBX_SPACING = 10;
	private final String BTN_COLOR = VoxspellPrototype.DARK_BLUE;
	private final String BACK_COLOR = VoxspellPrototype.LIGHT_BLUE;
	private final String BTN_FONT_COLOR = VoxspellPrototype.WHITE;
	private final String TXT_FONT_COLOR = VoxspellPrototype.WHITE;
	private final String TFD_FONT_COLOR = VoxspellPrototype.DARK;
	private final int TXT_FONT_SIZE = VoxspellPrototype.TXT_FONT_SIZE;
	private final int BTN_FONT_SIZE = VoxspellPrototype.BTN_FONT_SIZE;
	private final int TFD_FONT_SIZE = VoxspellPrototype.BTN_FONT_SIZE;
	private final int SIDE_PADDING = 10;
	private final int TOP_BOTTOM_PADDING = 80;
	private final int BTN_WIDTH = 200;
	private final int BTN_HEIGHT = 70;
	private final int TFD_WIDTH = 300;
	
	private final Text _txtQuiz;
	private final Text _txtProgress;
	private TextField _tfdAttempt;
	
	private List<String> _words;
	private int _wordIndex = 0;
	private boolean _firstGuess = true;
	
	public QuizScreen(Window window) {
		this._window = window;
		
		// Get words for this quiz
		_words = WordList.GetWordList().GetRandomWords("level 1", VoxspellPrototype.QUIZ_LENGTH);
		
		// Create root pane and set its size to whole window
		VBox root = new VBox(80);
		root.setPrefWidth(_window.GetWidth());
		root.setPrefHeight(_window.GetHeight());
		root.setPadding(new Insets(TOP_BOTTOM_PADDING, SIDE_PADDING, TOP_BOTTOM_PADDING, SIDE_PADDING));
		
		
		// Create quiz title text
		_txtQuiz = new Text("Quiz");
		_txtQuiz.prefWidth(_window.GetWidth());
		_txtQuiz.setTextAlignment(TextAlignment.CENTER);
		_txtQuiz.setWrappingWidth(_window.GetWidth());
		_txtQuiz.setStyle("-fx-font: " + TXT_FONT_SIZE + " arial;" +
				" -fx-fill: " + TXT_FONT_COLOR + ";");
		
		// Create score progress counter text
		_txtProgress = new Text("Word 0/" + _words.size());
		_txtProgress.prefWidth(_window.GetWidth());
		_txtProgress.setTextAlignment(TextAlignment.CENTER);
		_txtProgress.setWrappingWidth(_window.GetWidth() - (SIDE_PADDING * 2));
		_txtProgress.setStyle("-fx-font: " + TXT_FONT_SIZE + " arial;" +
				" -fx-fill: " + TXT_FONT_COLOR + ";");
		
		// Add all nodes to root pane
		root.getChildren().addAll(_txtQuiz, buildCenterPane(BTN_HEIGHT), _txtProgress);
		
		// Add root pane to parent
		this.getChildren().addAll(root);
		
		// Color background
		root.setStyle("-fx-background-color: " + BACK_COLOR + ";");
		
		new FestivalSpeakTask("Spell " + currentWord()).run();
	}
	
	private Pane buildCenterPane(double desiredHeight) {
		// Build center pane
		HBox centerPane = new HBox(HBX_SPACING);
		
		// Create center pane nodes
		Button btnSpeak = new Button(BTN_SPEAK_TEXT);
		Button btnEnter = new Button(BTN_ENTER_TEXT);
		_tfdAttempt = new TextField();
		
		// Add nodes to center pane
		centerPane.getChildren().addAll(btnSpeak, _tfdAttempt, btnEnter);
		
		// Set node styles
		btnSpeak.setStyle("-fx-font: " + BTN_FONT_SIZE + " arial;" + 
				" -fx-base: " + BTN_COLOR + ";" + 
				" -fx-text-fill: " + BTN_FONT_COLOR + ";");
		btnEnter.setStyle("-fx-font: " + BTN_FONT_SIZE + " arial;" + 
				" -fx-base: " + BTN_COLOR + ";" + 
				" -fx-text-fill: " + BTN_FONT_COLOR + ";");
		_tfdAttempt.setStyle("-fx-font: " + TFD_FONT_SIZE + " arial;" +
				"-fx-text-fill: " + TFD_FONT_COLOR + ";");
		
		// Center text in text-field
		_tfdAttempt.setAlignment(Pos.CENTER);
		
		// Set node dimensions
		btnEnter.setPrefWidth(BTN_WIDTH);
		btnEnter.setPrefHeight(BTN_HEIGHT);
		btnSpeak.setPrefWidth(BTN_WIDTH);
		btnSpeak.setPrefHeight(BTN_HEIGHT);
		_tfdAttempt.setPrefWidth(TFD_WIDTH);
		_tfdAttempt.setPrefHeight(BTN_HEIGHT);
		
		centerPane.setAlignment(Pos.CENTER);
		
		// Set action for speak button
		btnSpeak.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				new FestivalSpeakTask(currentWord()).run();
			}
		});
		
		btnEnter.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {				
				attemptWord(_tfdAttempt.getText());
			}
		});
		
		_tfdAttempt.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				if (e.getCode() == KeyCode.ENTER) {
					attemptWord(_tfdAttempt.getText());
				}
			}	
		});
		
		return centerPane;
	}
	
	/**
	 * Test entered word for correctness and update the GUI
	 * @param word
	 * @return whether word is correct or not
	 */
	private boolean attemptWord(String word) {
		if (!word.matches("[a-zA-Z ]+")) {
			// Word attempt may only contain alphabet characters.
			
			return false;
		}
		
		if (word.contains(" ")) {
			// Word attempt may not contain white space
			
			return false;
		}
		
		boolean correct = (word.toLowerCase().equals(currentWord().toLowerCase()));
		boolean advance = false;
		String speechOutput = "";
		
		if (_firstGuess) {
			if (correct) {
				// Correct on first guess
				speechOutput = speechOutput + "Correct.";
				advance = true;
			} else {
				// Incorrect on first guess
				speechOutput = speechOutput + "Incorrect, try again.. " + currentWord() + ".. " + currentWord() + ".";
				_firstGuess = false;
			}
		} else {
			if (correct) {
				// Correct on second guess
				speechOutput = speechOutput + "Correct.";
				advance = true;
			} else {
				// Incorrect on second guess
				speechOutput = speechOutput + "Incorrect.";
				advance = true;
			}
		}
		
		if (advance && nextWord()) {
			speechOutput = speechOutput + " Spell " + currentWord();
		}
		
		new FestivalSpeakTask(speechOutput).run();
		_tfdAttempt.clear();
		
		return correct;
	}
	
	/**
	 * Move on to next word
	 * @return true if a next word is available.
	 */
	private boolean nextWord() {
		if (_wordIndex + 1 < _words.size()) {
			// There are words left to spell
			_wordIndex++;
			_firstGuess = true;
			_txtProgress.setText("Word: " + _wordIndex + "/" + _words.size());
			
			return true;
		} else {
			// No words left to spell
			return false;
		}
	}
	
	private String currentWord() {
		return _words.get(_wordIndex);
	}
}
