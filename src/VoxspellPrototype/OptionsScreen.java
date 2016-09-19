package VoxspellPrototype;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class OptionsScreen extends Parent {

	private Window _window;
	
	private final int TXT_FONT_SIZE = VoxspellPrototype.TXT_FONT_SIZE;
	private final String BACK_COLOR = VoxspellPrototype.LIGHT_BLUE;
	private final int BTN_FONT_SIZE = VoxspellPrototype.BTN_FONT_SIZE;
	private final String BTN_COLOR = VoxspellPrototype.DARK_BLUE;
	private final String TXT_FONT_COLOR = VoxspellPrototype.WHITE;
	private final int OPTIONS_PADDING = 10;
	private final String BTN_FONT_COLOR = VoxspellPrototype.WHITE;

	public OptionsScreen(Window window) {
		super();

		_window = window;

		VBox root = new VBox(40);
		root.setPadding(new Insets(OPTIONS_PADDING));
		
		this.getChildren().add(root);

		HBox voiceSpeedBox = new HBox(30);
		voiceSpeedBox.setAlignment(Pos.CENTER);

		HBox voiceTypeBox = new HBox(40);
		voiceTypeBox.setAlignment(Pos.CENTER);
		
		Text optionsLabel = new Text("Options");

		optionsLabel.prefWidth(_window.GetWidth());
		optionsLabel.setTextAlignment(TextAlignment.CENTER);
		optionsLabel.setWrappingWidth(_window.GetWidth());
		
		optionsLabel.setStyle("-fx-font: " + TXT_FONT_SIZE + " arial;" + 
				" -fx-fill: " + TXT_FONT_COLOR + ";");
		
		Button returnToMenuBtn = new Button("Return To Main Menu");
		
		returnToMenuBtn.setPrefWidth(_window.GetWidth());
		returnToMenuBtn.setPrefHeight(_window.GetHeight()/8);

		returnToMenuBtn.setStyle("-fx-font: " + BTN_FONT_SIZE + " arial;" + 
				" -fx-base: " + BTN_COLOR + ";" + 
				" -fx-text-fill: " + BTN_FONT_COLOR + ";");

		returnToMenuBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				_window.SetWindowScene(new Scene(new MainScreen(_window), _window.GetWidth(), _window.GetHeight()));
			}
		});


		root.getChildren().addAll(returnToMenuBtn, optionsLabel, voiceSpeedBox, voiceTypeBox);
		
		root.setStyle("-fx-background-color: " + BACK_COLOR + ";");
		
		root.setPrefHeight(_window.GetHeight());
		root.setPrefWidth(_window.GetWidth());
		
		ObservableList<String> voiceSpeedOptions = FXCollections.observableArrayList(
				"Voice Speed 1",
				"Voice Speed 2"
				);

		final ComboBox<String> voiceSpeedComboBox = new ComboBox<String>(voiceSpeedOptions);
		voiceSpeedComboBox.setStyle("-fx-base: " + BTN_COLOR + "; -fx-text-fill: " + TXT_FONT_COLOR);
		voiceSpeedComboBox.setTranslateY(5);
		
		voiceSpeedComboBox.setPromptText("Choose a voice speed");

		Label voiceSpeedLabel = new Label("Select voice speed");
		voiceSpeedLabel.setStyle("-fx-font: " + TXT_FONT_SIZE + " arial;" + 
				" -fx-base: " + BTN_COLOR + ";" + 
				" -fx-text-fill: " + TXT_FONT_COLOR + ";");

		voiceSpeedBox.getChildren().addAll(voiceSpeedLabel, voiceSpeedComboBox);

		ObservableList<String> voiceTypeOptions = FXCollections.observableArrayList(
				"Normal Voice",
				"New Zealand Voice"
				);

		final ComboBox<String> voiceTypeComboBox = new ComboBox<String>(voiceTypeOptions);
		voiceTypeComboBox.setStyle("-fx-base: " + BTN_COLOR + "; -fx-text-fill: " + TXT_FONT_COLOR);
		
		voiceTypeComboBox.setPromptText("Choose a voice");
		voiceTypeComboBox.setTranslateY(5);

		Label voiceTypeLabel = new Label("Select voice type");
		voiceTypeLabel.setStyle("-fx-font: " + TXT_FONT_SIZE + " arial;" + 
				" -fx-base: " + BTN_COLOR + ";" + 
				" -fx-text-fill: " + TXT_FONT_COLOR + ";");
		
		voiceTypeBox.getChildren().addAll(voiceTypeLabel, voiceTypeComboBox);
		
		voiceTypeComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0,
					String oldValue, String newValue) {
				//Add code here to call appropriate script 
			}

		});
		
		voiceSpeedComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0,
					String oldValue, String newValue) {
				//Add code here to call appropriate script 
			}

		});

	}

}
