package VoxspellPrototype;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VoxspellPrototype extends Application {

	private Window _window;
	
	private final String WINDOW_TITLE = "VOXSPELL(Hey Nathan! I hope this works!)";

	public VoxspellPrototype() {
	}

	@Override
	public void start(Stage stage) throws Exception {
		_window = new Window(stage, 500, 200);
		
		_window.SetWindowTitle(WINDOW_TITLE);
		_window.Show();

		//_window.setScene(new Scene(new Statistics(WordList.GetWordList()), _width, _height));
		_window.SetWindowScene(new Scene(new MainScreen(_window)));
	}

	public static void main(String[] args) {
		launch(args);
	}
}