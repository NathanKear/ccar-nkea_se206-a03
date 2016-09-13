package VoxspellPrototype;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
	
	public QuizScreen(Window window) {
		this._window = window;
		
		// Create root pane and set its size to whole window
		VBox root = new VBox(80);
		root.setPrefWidth(_window.GetWidth());
		root.setPrefHeight(_window.GetHeight());
		root.setPadding(new Insets(TOP_BOTTOM_PADDING, SIDE_PADDING, TOP_BOTTOM_PADDING, SIDE_PADDING));
		
		// Build Center pane
		HBox centerPane = new HBox(HBX_SPACING);
		Button btnSpeak = new Button(BTN_SPEAK_TEXT);
		Button btnEnter = new Button(BTN_ENTER_TEXT);
		TextField tfdAttempt = new TextField();
		centerPane.getChildren().addAll(btnSpeak, tfdAttempt, btnEnter);
		centerPane.setAlignment(Pos.CENTER);
		btnSpeak.setStyle("-fx-font: " + BTN_FONT_SIZE + " arial;" + 
				" -fx-base: " + BTN_COLOR + ";" + 
				" -fx-text-fill: " + BTN_FONT_COLOR + ";");
		btnEnter.setStyle("-fx-font: " + BTN_FONT_SIZE + " arial;" + 
				" -fx-base: " + BTN_COLOR + ";" + 
				" -fx-text-fill: " + BTN_FONT_COLOR + ";");
		btnEnter.setPrefWidth(BTN_WIDTH);
		btnEnter.setPrefHeight(BTN_HEIGHT);
		btnSpeak.setPrefWidth(BTN_WIDTH);
		btnSpeak.setPrefHeight(BTN_HEIGHT);
		tfdAttempt.setPrefWidth(TFD_WIDTH);
		tfdAttempt.setPrefHeight(BTN_HEIGHT);
		tfdAttempt.setAlignment(Pos.CENTER);
		tfdAttempt.setStyle("-fx-font: " + TFD_FONT_SIZE + " arial;" +
				"-fx-text-fill: " + TFD_FONT_COLOR + ";");
		
		_txtQuiz = new Text("Quiz");
		_txtQuiz.prefWidth(_window.GetWidth());
		_txtQuiz.setTextAlignment(TextAlignment.CENTER);
		_txtQuiz.setWrappingWidth(_window.GetWidth());
		_txtQuiz.setStyle("-fx-font: " + TXT_FONT_SIZE + " arial;" +
				" -fx-fill: " + TXT_FONT_COLOR + ";");
		
		_txtProgress = new Text("Score: 1/10");
		_txtProgress.prefWidth(_window.GetWidth());
		_txtProgress.setTextAlignment(TextAlignment.CENTER);
		_txtProgress.setWrappingWidth(_window.GetWidth() - (SIDE_PADDING * 2));
		_txtProgress.setStyle("-fx-font: " + TXT_FONT_SIZE + " arial;" +
				" -fx-fill: " + TXT_FONT_COLOR + ";");
		
		root.getChildren().addAll(_txtQuiz, centerPane, _txtProgress);
		
		this.getChildren().addAll(root);
		
		root.setStyle("-fx-background-color: " + BACK_COLOR + ";");
	}
}
