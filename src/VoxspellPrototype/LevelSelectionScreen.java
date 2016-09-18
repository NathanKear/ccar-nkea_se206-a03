package VoxspellPrototype;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
	private final String BTN_LOCKED_COLOR = VoxspellPrototype.DARK;
	private final String BACK_COLOR = VoxspellPrototype.LIGHT_BLUE;
	private final String BTN_FONT_COLOR = VoxspellPrototype.WHITE;
	private final String TXT_FONT_COLOR = VoxspellPrototype.WHITE;
	private final double TMR_TICK_RATE = 60.0;

	private VBox _levelButtons;
	private double _scrollPosition = 0;
	private Timeline _timeline;

	private QuizType _quizType;

	public enum QuizType {
		REVIEW_QUIZ, NORMAL_QUIZ
	}

	public LevelSelectionScreen(Window window, String quizType) {
		super();

		if(quizType.equals("Review_Quiz")) {
			_quizType = QuizType.REVIEW_QUIZ;
		} else if (quizType.equals("Normal_Quiz")) {
			_quizType = QuizType.NORMAL_QUIZ;
		}

		this._window = window;

		BTN_HEIGHT = _window.GetHeight() / BUTTONS_PER_SCREEN;
		BTN_WIDTH = _window.GetWidth() * SELECTIONBAR_SCREENWIDTH_RATIO;

		//If the user is opening the application for the first time...
		File wordlog = new File("Word-Log");

		if(!wordlog.exists()) {
			ChooseLevelScreen();
		}

		try {
			BufferedReader r = new BufferedReader(new FileReader(wordlog));

			if(r.readLine() == null) {
				ChooseLevelScreen();
			} else {
				GenerateLevelSelectionScreen();
			}

			r.close();
		} catch (IOException e) {

		}


	}

	private void ChooseLevelScreen() {
		final WordList wordlist = WordList.GetWordList();

		ObservableList<String> options = FXCollections.observableArrayList();
		for(int i = 0; i < wordlist.size(); i++) {
			Level level = wordlist.get(i);
			String levelName = level.levelName();
			options.add(levelName);
		}

		final ComboBox<String> levelSelect = new ComboBox<String>(options);

		VBox root = new VBox(BUTTON_SEPERATION);

		// Set root node size
		root.setPrefWidth(_window.GetWidth());

		Label levelSelectLabel = new Label("Please select which level you wish to start at. All levels below "
				+ "the level you choose, and the level itself, will be unlocked!");
		levelSelectLabel.setStyle("-fx-font: " + TXT_FONT_SIZE + " arial;" +
				" -fx-fill: " + TXT_FONT_COLOR + ";");
		levelSelectLabel.setWrapText(true);
		root.setStyle("-fx-background-color: " + BACK_COLOR);

		root.setPadding(new Insets(SELECTION_BAR_PADDING));

		root.setPrefHeight(_window.GetHeight());
		root.setPrefWidth(_window.GetWidth());

		root.getChildren().addAll(levelSelectLabel, levelSelect);

		this.getChildren().add(root);

		levelSelect.setPromptText("Select a level");
		levelSelect.setStyle("-fx-base: " + BTN_COLOR + "; -fx-fill: " + TXT_FONT_COLOR);
		levelSelect.autosize();

		levelSelect.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0,
					String oldValue, String newValue) {
				boolean levelFound = false;
				for(int i = 0; i < wordlist.size(); i++) {
					Level level = wordlist.get(i);
					String levelName = level.levelName();

					if(!levelFound) {
						level.unlockLevel();
					}

					if(levelName.equals(newValue)) {
						levelFound = true;
					}
				}
				GenerateLevelSelectionScreen();
			}


		});

	}

	private void GenerateLevelSelectionScreen() {

		WordList wordlist = WordList.GetWordList();

		// Create vbox, add all level buttons
		VBox root = new VBox(BUTTON_SEPERATION);

		Button returnToMenuBtn = new Button("Return To Main Menu");
		returnToMenuBtn.setPrefWidth(BTN_WIDTH);
		returnToMenuBtn.setPrefHeight(BTN_HEIGHT);

		returnToMenuBtn.setStyle("-fx-font: " + BTN_FONT_SIZE + " arial;" + 
				" -fx-base: " + BTN_COLOR + ";" + 
				" -fx-text-fill: " + BTN_FONT_COLOR + ";");

		returnToMenuBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				_window.SetWindowScene(new Scene(new MainScreen(_window), _window.GetWidth(), _window.GetHeight()));
			}
		});

		// Set root node size
		root.setPrefWidth(_window.GetWidth());

		_btnLevels = new ArrayList<Button>();

		_btnLevels.add(returnToMenuBtn);


		Text txtSelection = new Text(TXT_SELECT_LEVEL);
		txtSelection.setStyle("-fx-font: " + TXT_FONT_SIZE + " arial;" +
				" -fx-fill: " + TXT_FONT_COLOR + ";");
		root.getChildren().add(txtSelection);

		wordlist = WordList.GetWordList();
		ArrayList<String> levelName = new ArrayList<String>();

		for(int i = 0; i < wordlist.size(); i++) {
			levelName.add(wordlist.get(i).levelName());
			Level level = wordlist.get(i);
			final String listName = level.levelName();

			Button btn = new Button(listName);
			btn.setPrefWidth(BTN_WIDTH);

			btn.setPrefHeight(BTN_HEIGHT);

			if(_quizType == QuizType.NORMAL_QUIZ) {
				if(level.isUnlocked()) {
					btn.setStyle("-fx-font: " + BTN_FONT_SIZE + " arial;" + 
							" -fx-base: " + BTN_COLOR + ";" + 
							" -fx-text-fill: " + BTN_FONT_COLOR + ";");

					btn.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent arg0) {
							_window.SetWindowScene(new Scene(new QuizScreen(_window, listName, _quizType), _window.GetWidth(), _window.GetHeight()));
						}
					});
				} else {
					btn.setStyle("-fx-font: " + BTN_FONT_SIZE + " arial;" + 
							" -fx-base: " + BTN_LOCKED_COLOR + ";" + 
							" -fx-text-fill: " + BTN_FONT_COLOR + ";");

					btn.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent arg0) {
							PopupWindow.DeployPopupWindow("You need to unlock this level before you can use play it!");
						}
					});
				}
			} else if(_quizType == QuizType.REVIEW_QUIZ) {
				if(level.isUnlocked()) {
					btn.setStyle("-fx-font: " + BTN_FONT_SIZE + " arial;" + 
							" -fx-base: " + BTN_COLOR + ";" + 
							" -fx-text-fill: " + BTN_FONT_COLOR + ";");

					if(WordList.GetWordList().GetRandomFailedWords(listName, 10).size() == 0) {
						btn.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent arg0) {
								PopupWindow.DeployPopupWindow("You currently have no words to review!");
							}
						});
					} else {
						btn.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent arg0) {
								_window.SetWindowScene(new Scene(new QuizScreen(_window, listName, _quizType), _window.GetWidth(), _window.GetHeight()));
							}
						});
					}
				} else {
					btn.setStyle("-fx-font: " + BTN_FONT_SIZE + " arial;" + 
							" -fx-base: " + BTN_LOCKED_COLOR + ";" + 
							" -fx-text-fill: " + BTN_FONT_COLOR + ";");

					btn.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent arg0) {
							PopupWindow.DeployPopupWindow("You need to unlock this level before you can use play it!");
						}
					});
				}
			}
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
